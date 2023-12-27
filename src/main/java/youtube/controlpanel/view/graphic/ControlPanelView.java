package youtube.controlpanel.view.graphic;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.view.observer.YouTubeDataObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        int count = 0;
        for (Video video : videos) {
            if (count >= 5) break;
            String videoTitle = video.getSnippet().getTitle();
            JButton videoButton = new JButton(videoTitle);
            videoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayVideoDetails(video, video.getSnippet().getChannelTitle());
                }
            });

            recentVideosFrame.add(videoButton);
            count++;
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
