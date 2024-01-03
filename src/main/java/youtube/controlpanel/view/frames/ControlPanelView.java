w.frames;

import com.google.api.services.youtube.model.Video;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import youtube.controlpanel.model.YouTubeEarningsCalculator;
import youtube.controlpanel.view.chart_dataset.Dataset;
import youtube.controlpanel.view.chart_dataset.ViewsDataset;
import youtube.controlpanel.view.chart_factory.Graph;
import youtube.controlpanel.view.chart_factory.GraphFactory;
import youtube.controlpanel.view.observer.YouTubeDataObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// The main view class for the YouTube Control Panel
public class ControlPanelView extends JFrame implements YouTubeDataObserver {

    // Reference to the main frame
    private JFrame mainFrame;

    // Panels for displaying video details and list of videos
    private JPanel detailsPanel, videosPanel;

    // Constructor to initialize the view with the main frame
    public ControlPanelView(JFrame frame) {
        mainFrame = frame;
        prepareControlPanel();
    }

    // Initializes the detailsPanel and videosPanel
    private void prepareControlPanel() {
        detailsPanel = new JPanel();
        videosPanel = new JPanel();
    }

    // Displays the details of a selected video
    public void displayVideoDetails(Video video, String channelName) {
        detailsPanel.removeAll();
        detailsPanel.setLayout(new BorderLayout());

        // Create and display video details panel
        createVideoDetailsPanel(video, channelName);

        // Create and display charts panel in a new window
        JFrame controlPanelFrame = createControlPanelWindow(video);

        mainFrame.add(detailsPanel, BorderLayout.CENTER);
        mainFrame.pack();

        // Call to the function to display details in the terminal
        TerminalDataDisplay.displayVideoDetails(video, channelName);

        // Show the control panel window
        controlPanelFrame.setVisible(true);
    }

    // Creates a new window for the control panel with chart widgets
    private JFrame createControlPanelWindow(Video video) {
        JFrame controlPanelFrame = new JFrame("Control Panel - " + video.getSnippet().getTitle());
        controlPanelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        controlPanelFrame.setLayout(new GridLayout(2, 3)); // Ajuste el diseño de la cuadrícula a 2 filas, 3 columnas

        // Añadir los widgets de gráficos al marco
        controlPanelFrame.add(createChartWidget("Video Views", createGraph("line", video, "LineChart Graph")));
        controlPanelFrame.add(createChartWidget("Different Video Views", createGraph("bar", video, "BarChart Graph")));
        controlPanelFrame.add(createChartWidget("Views, Money, Likes", createGraph("pie", video, "PieChart Graph")));
        controlPanelFrame.add(createChartWidget("Area Chart", createGraph("area", video, "AreaChart Graph")));
        controlPanelFrame.add(createChartWidget("Ring Chart", createGraph("ring", video, "RingChart Graph")));
        controlPanelFrame.add(createChartWidget("Waterfall Chart", createGraph("waterfall", video, "WaterfallChart Graph"))); // Añadir widget de gráfico de cascada

        controlPanelFrame.pack();
        controlPanelFrame.setLocationRelativeTo(null);
        return controlPanelFrame;
    }


    // Wraps a graph in a styled widget panel
    private JPanel createChartWidget(String title, Graph graph) {
        JPanel widgetPanel = new JPanel(new BorderLayout());
        widgetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        widgetPanel.add(titleLabel, BorderLayout.NORTH);

        ChartPanel chartPanel = (ChartPanel) graph.getChartPanel();
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        widgetPanel.add(chartPanel, BorderLayout.CENTER);

        return widgetPanel;
    }

    // Creates the panel displaying video details
    private void createVideoDetailsPanel(Video video, String channelName) {
        JPanel videoDetailsPanel = new JPanel(new GridLayout(6, 2));

        // Adding video details components
        videoDetailsPanel.add(createDetailPanel("Channel Name: " + channelName));
        videoDetailsPanel.add(createDetailPanel("Video Title: " + video.getSnippet().getTitle()));
        videoDetailsPanel.add(createDetailPanel("Likes: " + video.getStatistics().getLikeCount()));
        videoDetailsPanel.add(createDetailPanel("Views: " + video.getStatistics().getViewCount()));
        videoDetailsPanel.add(createDetailPanel("Comments: " + video.getStatistics().getCommentCount()));
        videoDetailsPanel.add(createDetailPanel("Estimated Earnings of the video: $" +
                String.format("%.2f", YouTubeEarningsCalculator.calculateAdjustedEarnings(video))));
        detailsPanel.add(videoDetailsPanel, BorderLayout.NORTH);
    }

    // Creates the panel displaying charts
    private JPanel createChartsPanel(Video video) {
        // Ajuste el número de columnas a 6 para acomodar el nuevo gráfico de cascada
        JPanel chartsPanel = new JPanel(new GridLayout(1, 6));

        // Agregando gráficos de línea, barra, pastel, área, anillo y cascada al panel
        Graph videoViews = createGraph("line", video, "LineChart Graph");
        Graph differentVideoViews = createGraph("bar", video, "BarChart Graph");
        Graph viewsMoneyLikes = createGraph("pie", video, "PieChart Graph");
        Graph areaChart = createGraph("area", video, "AreaChart Graph");
        Graph ringChart = createGraph("ring", video, "RingChart Graph");
        Graph waterfallChart = createGraph("waterfall", video, "WaterfallChart Graph"); // Gráfico de cascada añadido

        // Agregando los paneles de gráficos al chartsPanel
        chartsPanel.add(videoViews.getChartPanel());
        chartsPanel.add(differentVideoViews.getChartPanel());
        chartsPanel.add(viewsMoneyLikes.getChartPanel());
        chartsPanel.add(areaChart.getChartPanel());
        chartsPanel.add(ringChart.getChartPanel());
        chartsPanel.add(waterfallChart.getChartPanel()); // Añadir panel de gráfico de cascada

        return chartsPanel;
    }



    // Adds a chart to the specified panel based on the chart type
    private Graph createGraph( String chartType, Video video, String title) {
        Dataset viewsDataset= new ViewsDataset();
        DefaultCategoryDataset dataset = viewsDataset.createDataset(video);
        GraphFactory graphFactory = new GraphFactory();
        return graphFactory.createGraph(chartType, dataset, title);
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

