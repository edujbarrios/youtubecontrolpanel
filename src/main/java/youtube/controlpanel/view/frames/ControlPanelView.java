package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.resources.YouTubeEarningsCalculator;
import youtube.controlpanel.view.observer.YouTubeDataObserver;


import javax.swing.*;
import java.awt.*;
import java.time.chrono.JapaneseChronology;
import java.util.List;

public class ControlPanelView extends JFrame implements YouTubeDataObserver {

    private JFrame mainFrame;
    private JPanel detailsPanel, videosPanel;

    public ControlPanelView(JFrame frame) {
        mainFrame = frame;
        prepareControlPanel();
    }

    private void prepareControlPanel() {
        detailsPanel = new JPanel();
        videosPanel = new JPanel();
    }

    public void displayVideoDetails(Video video, String channelName) {
        detailsPanel.removeAll();
        detailsPanel.setLayout(new GridLayout(6, 1));

        detailsPanel.add(createDetailPanel("Channel Name: " + channelName));
        detailsPanel.add(createDetailPanel("Video Title: " + video.getSnippet().getTitle()));
        detailsPanel.add(createDetailPanel("Likes: " + video.getStatistics().getLikeCount()));
        detailsPanel.add(createDetailPanel("Views: " + video.getStatistics().getViewCount()));
        detailsPanel.add(createDetailPanel("Comments: " + video.getStatistics().getCommentCount()));
        // Get and display the estimated earnings
        detailsPanel.add(createDetailPanel("Estimated Earnings of the video: $" +
                String.format("%.2f", YouTubeEarningsCalculator.calculateAdjustedEarnings(video))));

        mainFrame.add(detailsPanel, BorderLayout.EAST);
        mainFrame.pack();
    }

    @Override
    public void update(List<Video> videos) {
        videosPanel.removeAll();
        videosPanel.setLayout(new GridLayout(5, 1));

        for (int count = 0; count < Math.min(videos.size(), 5); count++) {
            Video video = videos.get(count);
            String videoTitle = video.getSnippet().getTitle();
            JButton videoButton = new JButton(videoTitle);
            videoButton.addActionListener(e -> displayVideoDetails(video, video.getSnippet().getChannelTitle()));
            videosPanel.add(videoButton);
        }

        mainFrame.add(videosPanel, BorderLayout.SOUTH);
        mainFrame.pack();
    }

    private JPanel createDetailPanel(String detail) {
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailPanel.add(new JLabel(detail), BorderLayout.CENTER);
        return detailPanel;
    }
}

