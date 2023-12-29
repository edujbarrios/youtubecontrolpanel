package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import youtube.controlpanel.model.resources.YouTubeEarningsCalculator;
import youtube.controlpanel.view.chart_factory.Graph;
import youtube.controlpanel.view.chart_factory.GraphFactory;
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
        detailsPanel.setLayout(new BorderLayout());

        // Video Details Panel
        JPanel videoDetailsPanel = new JPanel(new GridLayout(6, 2));
        videoDetailsPanel.add(createDetailPanel("Channel Name: " + channelName));
        videoDetailsPanel.add(createDetailPanel("Video Title: " + video.getSnippet().getTitle()));
        videoDetailsPanel.add(createDetailPanel("Likes: " + video.getStatistics().getLikeCount()));
        videoDetailsPanel.add(createDetailPanel("Views: " + video.getStatistics().getViewCount()));
        videoDetailsPanel.add(createDetailPanel("Comments: " + video.getStatistics().getCommentCount()));
        videoDetailsPanel.add(createDetailPanel("Estimated Earnings of the video: $" +
                String.format("%.2f", YouTubeEarningsCalculator.calculateAdjustedEarnings(video))));
        detailsPanel.add(videoDetailsPanel, BorderLayout.NORTH);

        // Charts Panel
        JPanel chartsPanel = new JPanel(new GridLayout(1, 3));

        addChart(chartsPanel, "line", video);
        addChart(chartsPanel, "bar", video);
        addChart(chartsPanel, "pie", video);

        detailsPanel.add(chartsPanel, BorderLayout.CENTER);

        mainFrame.add(detailsPanel, BorderLayout.CENTER);
        mainFrame.pack();
    }

    private void addChart(JPanel panel, String chartType,Video video) {
        DefaultCategoryDataset dataset = createDataset(video);
        GraphFactory graphFactory = new GraphFactory();
        Graph chartGraph = graphFactory.createGraph(chartType, dataset);
        JFreeChart chart = chartGraph.createChart();

        // Adjust the size of the ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300)); // Adjust dimensions as needed
        panel.add(chartPanel);
    }
    private DefaultCategoryDataset createDataset(Video video) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String series = "Video Details"; // Series name for the dataset

        dataset.addValue(video.getStatistics().getLikeCount(), series, "Likes");
        dataset.addValue(video.getStatistics().getViewCount(), series, "Views");
        dataset.addValue(video.getStatistics().getCommentCount(), series, "Comments");
        dataset.addValue(YouTubeEarningsCalculator.calculateAdjustedEarnings(video), series, "Estimated Earnings");

        return dataset;
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

