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
        detailsPanel.setLayout(new GridLayout(5, 2));

        detailsPanel.add(createDetailPanel("Channel Name: " + channelName));
        detailsPanel.add(createDetailPanel("Video Title: " + video.getSnippet().getTitle()));
        detailsPanel.add(createDetailPanel("Likes: " + video.getStatistics().getLikeCount()));
        detailsPanel.add(createDetailPanel("Views: " + video.getStatistics().getViewCount()));
        detailsPanel.add(createDetailPanel("Comments: " + video.getStatistics().getCommentCount()));
        // Get and display the estimated earnings
        detailsPanel.add(createDetailPanel("Estimated Earnings of the video: $" +
                String.format("%.2f", YouTubeEarningsCalculator.calculateAdjustedEarnings(video))));


        addCharts();
        mainFrame.add(detailsPanel, BorderLayout.EAST);
        mainFrame.pack();
    }
    private void addCharts(){
        DefaultCategoryDataset dataset = createSampleDataset();
        // Use the factory to create a line chart graph
        GraphFactory graphFactory = new GraphFactory();
        Graph lineChart = graphFactory.createGraph("line", dataset);
        JFreeChart chart = lineChart.createChart();
        detailsPanel.add(new ChartPanel(chart));

        Graph barChart = graphFactory.createGraph("bar", dataset);
        chart = barChart.createChart();
        detailsPanel.add(new ChartPanel(chart));


        Graph pieChart = graphFactory.createGraph("pie", dataset);
        chart = pieChart.createChart();
        detailsPanel.add(new ChartPanel(chart));
    }
    // Example method to create a sample dataset
    private static DefaultCategoryDataset createSampleDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add sample data to the dataset
        dataset.addValue(1.0, "Series1", "Category1");
        dataset.addValue(4.0, "Series1", "Category2");
        dataset.addValue(3.0, "Series1", "Category3");
        dataset.addValue(5.0, "Series2", "Category1");
        dataset.addValue(7.0, "Series2", "Category2");
        dataset.addValue(8.0, "Series2", "Category3");

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

