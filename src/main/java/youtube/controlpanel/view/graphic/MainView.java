package youtube.controlpanel.view.graphic;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.data.ChannelData;
import youtube.controlpanel.model.data.VideoData;
import youtube.controlpanel.model.resources.LinkParser;
import youtube.controlpanel.view.observer.YouTubeDataObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    private JTextField urlTextField;
    private JButton fetchButton;
    private ChannelData channelData;
    private VideoData videoData;
    private ControlPanelView controlPanelView;
    private String channelName;
    private List<YouTubeDataObserver> observers;

    public MainView() {
        channelData = new ChannelData();
        videoData = new VideoData();
        controlPanelView = new ControlPanelView();
        observers = new ArrayList<>();
        addObserver((YouTubeDataObserver) controlPanelView);
        createUI();
    }

    private void createUI() {
        setTitle("YouTube Video Information Viewer");
        setSize(600, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        urlTextField = new JTextField(40);
        fetchButton = new JButton("Fetch Video Details");

        fetchButton.addActionListener((ActionEvent e) -> fetchChannelVideos());

        JPanel topPanel = new JPanel();
        topPanel.add(urlTextField);
        topPanel.add(fetchButton);

        add(topPanel, BorderLayout.CENTER);
    }

    private void addObserver(YouTubeDataObserver observer) {
        observers.add(observer);
    }

    private void fetchChannelVideos() {
        String url = urlTextField.getText();
        if (!LinkParser.isChannelUrl(url)) {
            JOptionPane.showMessageDialog(this, "Invalid URL: " + url, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> videosURLList = channelData.retrieveData(url);
        List<Video> videosList = new ArrayList<>();
        for (String videoURL : videosURLList) {
            Video video = videoData.retrieveData(videoURL);
            videosList.add(video);
            if (channelName == null || channelName.isEmpty()) {
                channelName = video.getSnippet().getChannelTitle();
            }
        }

        notifyObservers(videosList);
    }

    private void notifyObservers(List<Video> videos) {
        for (YouTubeDataObserver observer : observers) {
            observer.update(videos);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
}
