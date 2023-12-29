package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.resources.YouTubeEarningsCalculator;
import youtube.controlpanel.view.observer.YouTubeDataObserver;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ControlPanelView extends JFrame implements YouTubeDataObserver {

    private JFrame controlPanelFrame;

    public ControlPanelView() {
        prepareControlPanel();
    }

    private void prepareControlPanel() {
        controlPanelFrame = new JFrame("Video Control Panel");
        controlPanelFrame.setLayout(new GridLayout(3, 1, 10, 10));
        controlPanelFrame.setSize(600, 400);
        controlPanelFrame.setLocationRelativeTo(null);
    }

    public void displayVideoDetails(Video video, String channelName) {
        controlPanelFrame.getContentPane().removeAll();

        controlPanelFrame.add(createDetailPanel("Channel Name: " + channelName));
        controlPanelFrame.add(createDetailPanel("Video Title: " + video.getSnippet().getTitle()));
        controlPanelFrame.add(createDetailPanel("Likes: " + video.getStatistics().getLikeCount()));
        controlPanelFrame.add(createDetailPanel("Views: " + video.getStatistics().getViewCount()));
        controlPanelFrame.add(createDetailPanel("Comments: " + video.getStatistics().getCommentCount()));
        // Get and display the estimated earnings
        controlPanelFrame.add(createDetailPanel("Estimated Earnings of the video: $" +
                String.format("%.2f", YouTubeEarningsCalculator.calculateAdjustedEarnings(video))));


        controlPanelFrame.revalidate();
        controlPanelFrame.repaint();
        controlPanelFrame.setVisible(true);
    }

    @Override
    public void update(List<Video> videos) {
        JFrame recentVideosFrame = new JFrame("Recent Videos");
        recentVideosFrame.setLayout(new GridLayout(0, 1));
        recentVideosFrame.setSize(400, 300);
        recentVideosFrame.setLocationRelativeTo(this);

        for (int count = 0; count < Math.min(videos.size(), 5); count++) {
            Video video = videos.get(count);
            String videoTitle = video.getSnippet().getTitle();
            JButton videoButton = new JButton(videoTitle);
            videoButton.addActionListener(e -> displayVideoDetails(video, video.getSnippet().getChannelTitle()));
            recentVideosFrame.add(videoButton);
        }

        recentVideosFrame.setVisible(true);
    }

    private JPanel createDetailPanel(String detail) {
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailPanel.add(new JLabel(detail), BorderLayout.CENTER);
        return detailPanel;
    }
}

