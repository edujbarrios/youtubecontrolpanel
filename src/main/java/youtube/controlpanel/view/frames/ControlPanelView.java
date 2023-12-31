package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import org.jfree.chart.ChartPanel;
import youtube.controlpanel.controller.VideoButtonFactory;
import youtube.controlpanel.model.chart_dataset.Dataset;
import youtube.controlpanel.model.chart_dataset.ChartDataset;
import youtube.controlpanel.view.chart_factory.Graph;
import youtube.controlpanel.view.chart_factory.GraphFactory;
import youtube.controlpanel.view.observer.YouTubeDataObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// The main view class for the YouTube Control Panel
public class ControlPanelView extends JFrame implements YouTubeDataObserver {

    // Reference to the main frame
    private JFrame mainFrame;

    // Panels for displaying video details and list of videos
    private JPanel detailsPanel, videosPanel, graphsPanel, dropdownPanel;
    private Timer timer;
    private Video video;
    // Constructor to initialize the view with the main frame
    public ControlPanelView(JFrame frame) {
        mainFrame = frame;
        detailsPanel = new JPanel();
        videosPanel = new JPanel();
        graphsPanel = new JPanel();
        dropdownPanel = new DropdownMenuPanel(this);
    }

    // Displays the details of a selected video
    public void displayVideoDetails(Video video, String channelName) {
        this.video = video;
        detailsPanel.removeAll();
        graphsPanel.removeAll();
        detailsPanel.setLayout(new BorderLayout());
        graphsPanel.setLayout(new GridLayout(2, 2));

        // Create and display video details panel
        JPanel videoDetailsPanel = new JPanel(new GridLayout(2, 1));

        // Adding video details components
        videoDetailsPanel.add(createDetailPanel("Channel Name: " + channelName));
        videoDetailsPanel.add(createDetailPanel("Video Title: " + video.getSnippet().getTitle()));
        detailsPanel.add(videoDetailsPanel, BorderLayout.CENTER);

        JPanel westPanel = new JPanel(new GridLayout(2, 1));
        westPanel.add(detailsPanel);

        // Add checkboxes to a panel
        if (dropdownPanel.getParent() != westPanel) westPanel.add(dropdownPanel);
        mainFrame.add(graphsPanel, BorderLayout.EAST);
        mainFrame.add(westPanel, BorderLayout.WEST);
        mainFrame.pack();

        // Call to the function to display details in the terminal
        TerminalDataDisplay.displayVideoDetails(video, channelName);
    }

    public void displayChart(String selectedChart, ArrayList<JCheckBox> checkboxes) {
        graphsPanel.removeAll();

        // Add selected graph based on the selected chart using switch case
        switch (selectedChart) {
            case "BarChart Graph" -> createGraphs("bar", "BarChart Graph", checkboxes);
            case "PieChart Graph" -> createGraphs("pie", "PieChart Graph", checkboxes);
            case "AreaChart Graph" -> createGraphs("area", "AreaChart Graph", checkboxes);
            case "RingChart Graph" -> createGraphs("ring", "RingChart Graph", checkboxes);
            case "WaterfallChart Graph" -> createGraphs("waterfall", "WaterfallChart Graph", checkboxes);
            case "LineChart Graph"-> createGraphs("line", "LineChart Graph", checkboxes);
        }
        revalidate();
        repaint();
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

    // Método modificado para crear y actualizar gráficos
    // Método modificado para crear y actualizar gráficos y detalles del video
    private void createGraphs(String chartType, String title, ArrayList<JCheckBox> checkboxes) {
        if (timer != null) timer.stop();
        Dataset chartDataset = new ChartDataset(video);
        chartDataset.updateData();
        GraphFactory graphFactory = new GraphFactory();

        // Inicializa los gráficos
        Graph g = graphFactory.createGraph(chartType, chartDataset.getViewsDataset(), title);
        Graph gLikes = graphFactory.createGraph(chartType, chartDataset.getLikesDataset(), title);
        Graph gComments = graphFactory.createGraph(chartType, chartDataset.getCommentsDataset(), title);
        Graph gEarnings = graphFactory.createGraph(chartType, chartDataset.getEarningsDataset(), title);

        // Configura el Timer para actualizar los gráficos y los detalles del video
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartDataset.updateData();

                // Actualiza los gráficos con los nuevos datos
                g.updateChart(chartDataset.getViewsDataset());
                gLikes.updateChart(chartDataset.getLikesDataset());
                gComments.updateChart(chartDataset.getCommentsDataset());
                gEarnings.updateChart(chartDataset.getEarningsDataset());

                // Elimina y vuelve a añadir los gráficos actualizados
                graphsPanel.removeAll();
                for (JCheckBox checkBox : checkboxes) {
                    if (checkBox.isSelected()) {
                        String checkboxText = checkBox.getText();
                        switch (checkboxText) {
                            case "Video views":
                                graphsPanel.add(createChartWidget("Video Views", g));
                                break;
                            case "Video likes":
                                graphsPanel.add(createChartWidget("Video Likes", gLikes));
                                break;
                            case "Video comments":
                                graphsPanel.add(createChartWidget("Video Comments", gComments));
                                break;
                            case "Money earning":
                                graphsPanel.add(createChartWidget("Video Earnings", gEarnings));
                                break;
                        }
                    }
                }
                mainFrame.pack();

                // Actualiza los detalles del video en la terminal
                TerminalDataDisplay.updateVideoDetails(video, video.getSnippet().getChannelTitle());
            }
        });
        timer.start();
    }

//updates the videos dependin on the search, and it gives a video list with buttons
        //Created a button factory to handle these buttons
@Override
public void update(List<Video> videos) {
    videosPanel.removeAll();
    videosPanel.setLayout(new GridLayout(5, 1));

    // Aplicar estilo y mostrar hasta 5 videos recientes como botones
    for (int count = 0; count < Math.min(videos.size(), 5); count++) {
        Video video = videos.get(count);
        String videoTitle = video.getSnippet().getTitle();
        JButton videoButton = VideoButtonFactory.createVideoButton(videoTitle);

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
