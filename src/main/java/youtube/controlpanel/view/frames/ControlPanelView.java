package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import youtube.controlpanel.model.resources.YouTubeEarningsCalculator;
import youtube.controlpanel.view.chart_dataset.Dataset;
import youtube.controlpanel.view.chart_dataset.ViewsDataset;
import youtube.controlpanel.view.chart_factory.Graph;
import youtube.controlpanel.view.chart_factory.GraphFactory;
import youtube.controlpanel.view.observer.YouTubeDataObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// The main view class for the YouTube Control Panel
public class ControlPanelView extends JFrame implements YouTubeDataObserver {

    // Reference to the main frame
    private JFrame mainFrame;

    // Panels for displaying video details and list of videos
    private JPanel detailsPanel, videosPanel, graphsPanel;
    private Timer timer;

    // Constructor to initialize the view with the main frame
    public ControlPanelView(JFrame frame) {
        mainFrame = frame;
        detailsPanel = new JPanel();
        videosPanel = new JPanel();
        graphsPanel = new JPanel();
    }

    // Displays the details of a selected video
    public void displayVideoDetails(Video video, String channelName) {
        detailsPanel.removeAll();
        graphsPanel.removeAll();
        detailsPanel.setLayout(new BorderLayout());
        graphsPanel.setLayout(new GridLayout(2, 3));

        // Create and display video details panel
        JPanel videoDetailsPanel = new JPanel(new GridLayout(6, 2));

        // Adding video details components
        videoDetailsPanel.add(createDetailPanel("Channel Name: " + channelName));
        videoDetailsPanel.add(createDetailPanel("Video Title: " + video.getSnippet().getTitle()));
        detailsPanel.add(videoDetailsPanel, BorderLayout.CENTER);

        // Añadir los widgets de gráficos al marco
        graphsPanel.add(createChartWidget("Video Views", createGraph("line", video, "Views overtime")));

        // graphsPanel.add(createChartWidget("Different Video Views", createGraph("bar", video, "BarChart Graph")));
        // graphsPanel.add(createChartWidget("Views, Money, Likes", createGraph("pie", video, "PieChart Graph")));
        // graphsPanel.add(createChartWidget("Area Chart", createGraph("area", video, "AreaChart Graph")));
        // graphsPanel.add(createChartWidget("Ring Chart", createGraph("ring", video, "RingChart Graph")));
        // graphsPanel.add(createChartWidget("Waterfall Chart", createGraph("waterfall", video, "WaterfallChart Graph"))); // Añadir widget de gráfico de cascada

        mainFrame.add(graphsPanel, BorderLayout.EAST);
        mainFrame.add(detailsPanel, BorderLayout.WEST);
        mainFrame.pack();

        // Call to the function to display details in the terminal
        TerminalDataDisplay.displayVideoDetails(video, channelName);
    }

    // Wraps a graph in a styled widget panel
    private JPanel createChartWidget(String title, Graph graph) {
        JPanel widgetPanel = new JPanel(new BorderLayout());
        widgetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        widgetPanel.add(titleLabel, BorderLayout.NORTH);

        ChartPanel chartPanel = graph.getChartPanel();
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        widgetPanel.add(chartPanel, BorderLayout.CENTER);

        return widgetPanel;
    }

    // Adds a chart to the specified panel based on the chart type
    private Graph createGraph(String chartType, Video video, String title) {
        if (timer != null) timer.stop();
        Dataset viewsDataset = new ViewsDataset(video);
        viewsDataset.updateData();
        GraphFactory graphFactory = new GraphFactory();

        Graph g = graphFactory.createGraph(chartType, viewsDataset.getDataset(), title);
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewsDataset.updateData();
                g.updateChart(viewsDataset.getDataset());
                graphsPanel.removeAll();
                graphsPanel.add(createChartWidget("Video Views", g));
                mainFrame.pack();
            }
        });
        timer.start();

        return g;
    }

    // Updates the view with the latest list of videos
    @Override
    public void update(List<Video> videos) {
        videosPanel.removeAll();
        videosPanel.setLayout(new GridLayout(5, 1));

        // Display up to 5 latest videos as buttons
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

    // Creates a detail panel with the specified text
    private JPanel createDetailPanel(String detail) {
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailPanel.add(new JLabel(detail), BorderLayout.CENTER);
        return detailPanel;
    }
}