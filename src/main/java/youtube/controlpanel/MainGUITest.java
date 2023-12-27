package youtube.controlpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.data.ChannelData;
import youtube.controlpanel.model.data.VideoData;
import youtube.controlpanel.model.resources.LinkParser;

/**
 * Main application class for processing YouTube URLs with extended video information using a GUI.
 */
public class MainGUITest extends JFrame {

    private JTextField urlTextField;
    private ChannelData channelData;
    private VideoData videoData;
    private JFrame controlPanelFrame; // Frame for the control panel

    /**
     * Constructor to set up the GUI components and the control panel.
     */
    public MainGUITest() {
        channelData = new ChannelData();
        videoData = new VideoData();
        createUI();
        prepareControlPanel();
    }

    /**
     * Method to create the user interface of the main window.
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
     * Prepares the control panel window with a grid layout.
     */
    private void prepareControlPanel() {
        controlPanelFrame = new JFrame("Video Control Panel");
        controlPanelFrame.setLayout(new GridLayout(2, 2, 10, 10)); // Grid layout for 2x2 matrix
        controlPanelFrame.setSize(600, 400);
        controlPanelFrame.setLocationRelativeTo(null);
    }

    /**
     * Fetches videos from a given YouTube channel URL and displays recent videos.
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
     * Displays a window with a list of recent videos as buttons.
     *
     * @param videosList List of recent Video objects.
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
                    displayVideoDetailsInControlPanel(video);
                }
            });

            recentVideosFrame.add(videoButton);
            count++;
        }

        recentVideosFrame.setVisible(true);
    }

    /**
     * Displays video details in the control panel window.
     *
     * @param video The Video object whose details are to be displayed.
     */
    private void displayVideoDetailsInControlPanel(Video video) {
        controlPanelFrame.getContentPane().removeAll();

        controlPanelFrame.add(createDetailPanel("Title: " + video.getSnippet().getTitle()));
        controlPanelFrame.add(createDetailPanel("Likes: " + video.getStatistics().getLikeCount()));
        controlPanelFrame.add(createDetailPanel("Views: " + video.getStatistics().getViewCount()));
        controlPanelFrame.add(createDetailPanel("Comments: " + video.getStatistics().getCommentCount()));

        controlPanelFrame.revalidate();
        controlPanelFrame.repaint();
        controlPanelFrame.setVisible(true);
    }

    /**
     * Creates a panel for a single video detail.
     *
     * @param detail The video detail to be displayed.
     * @return JPanel containing the detail.
     */
    private JPanel createDetailPanel(String detail) {
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailPanel.add(new JLabel(detail), BorderLayout.CENTER);
        return detailPanel;
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

