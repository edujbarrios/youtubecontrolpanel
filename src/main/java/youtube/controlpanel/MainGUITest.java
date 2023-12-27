package youtube.controlpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.data.ChannelData;
import youtube.controlpanel.model.data.VideoData;
import youtube.controlpanel.model.resources.LinkParser;

/**
 * Main application class for processing YouTube URLs with extended video information using a GUI.
 * This class provides a graphical user interface to fetch and display details of YouTube videos and channels.
 */
public class MainGUITest extends JFrame {

    private JTextField urlTextField;
    private ChannelData channelData;
    private VideoData videoData;

    /**
     * Constructor to set up the GUI components.
     */
    public MainGUITest() {
        channelData = new ChannelData();
        videoData = new VideoData();
        createUI();
    }

    /**
     * Method to create the user interface.
     * This method sets up the layout, text field, and button for the main application window.
     */
    private void createUI() {
        setTitle("YouTube Video Information Viewer");
        setSize(600, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        urlTextField = new JTextField(40);
        JButton fetchButton = new JButton("Fetch Video Details");

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchChannelVideos();
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(urlTextField);
        topPanel.add(fetchButton);

        add(topPanel, BorderLayout.CENTER);
    }

    /**
     * Method to fetch videos from a YouTube channel URL.
     * It validates the URL and retrieves video data for display.
     */
    private void fetchChannelVideos() {
        String url = urlTextField.getText();
        if (!LinkParser.isChannelUrl(url)) {
            JOptionPane.showMessageDialog(this, "Invalid URL: " + url, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> videosURLList = channelData.retrieveData(url);
        List<Video> videosList = new ArrayList<>();
        for (String videoURL : videosURLList) {
            videosList.add(videoData.retrieveData(videoURL));
        }

        displayRecentVideos(videosList);
    }

    /**
     * Displays a list of the most recent videos in a new window.
     * Each video is represented as a button that, when clicked, displays detailed video information.
     * 
     * @param videosList A list of Video objects to display.
     */
    private void displayRecentVideos(List<Video> videosList) {
        JFrame recentVideosFrame = new JFrame("Recent Videos");
        recentVideosFrame.setLayout(new GridLayout(0, 1));
        recentVideosFrame.setSize(400, 300);
        recentVideosFrame.setLocationRelativeTo(this);

        int count = 0;
        for (Video video : videosList) {
            if (count >= 5) break;
            String videoTitle = video.getSnippet().getTitle();
            JButton videoButton = new JButton(videoTitle);
            videoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayVideoDetailsInSeparateWindows(video);
                }
            });

            recentVideosFrame.add(videoButton);
            count++;
        }

        recentVideosFrame.setVisible(true);
    }

    /**
     * Displays video details in separate windows.
     * Each detail such as title, likes, views, and comments is shown in its own window.
     * 
     * @param video The Video object whose details are to be displayed.
     */
    private void displayVideoDetailsInSeparateWindows(Video video) {
        showDetailWindow("Title: " + video.getSnippet().getTitle(), 0, 0);
        showDetailWindow("Likes: " + video.getStatistics().getLikeCount(), 0, 100);
        showDetailWindow("Views: " + video.getStatistics().getViewCount(), 300, 0);
        showDetailWindow("Comments: " + video.getStatistics().getCommentCount(), 300, 100);
    }

    /**
     * Shows a detail of a video in a separate window.
     * 
     * @param detail The detail to be displayed in the window.
     * @param x The x-coordinate for the window's position.
     * @param y The y-coordinate for the window's position.
     */
    private void showDetailWindow(String detail, int x, int y) {
        JFrame detailFrame = new JFrame();
        detailFrame.setSize(300, 100);
        detailFrame.add(new JLabel(detail, SwingConstants.CENTER));
        detailFrame.setLocation(x, y);
        detailFrame.setVisible(true);
    }

    /**
     * The main method to launch the application.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUITest().setVisible(true));
    }
}
