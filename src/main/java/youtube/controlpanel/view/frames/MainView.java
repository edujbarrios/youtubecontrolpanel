package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.data.ChannelData;
import youtube.controlpanel.model.data.VideoData;
import youtube.controlpanel.model.resources.LinkParser;
import youtube.controlpanel.view.observer.YouTubeDataObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    private JTextField urlTextField;
    private JButton fetchButton;
    private ChannelData channelData;
    private VideoData videoData;
    private YouTubeDataObserver controlPanelView;
    private String channelName;
    private List<YouTubeDataObserver> observers;

    public MainView() {
        channelData = new ChannelData();
        videoData = new VideoData();
        controlPanelView = new ControlPanelView(this);
        observers = new ArrayList<>();
        addObserver(controlPanelView);
        createUI();
    }

    private void createUI() {
        setTitle("YouTube Video Information Viewer");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cargar el logo y escalar la imagen
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/app_logo.png"));
        Image image = originalIcon.getImage(); // Transform it
        Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon logoIcon = new ImageIcon(newimg);  // transform it back

        // Crear el label del logo
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear título y subtítulo
        JLabel titleLabel = new JLabel("YOUTUBE CONTROL PANEL");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(150, 31, 31)); // Color rojo al estilo de YouTube
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Copy and paste (Control + V) a link of a YouTube channel");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.DARK_GRAY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para el logo, título y subtítulo
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio antes del logo
        titlePanel.add(logoLabel); // Agregar logo encima del título
        titlePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre logo y título
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio entre título y subtítulo
        titlePanel.add(subtitleLabel);

        // Añadir el panel de título al marco principal
        add(titlePanel, BorderLayout.NORTH);

        urlTextField = new JTextField(40);
        fetchButton = new JButton("Fetch Video Details");
        // Estilo para el botón Fetch
        fetchButton.setBackground(new Color(205, 32, 31)); // Un rojo más oscuro para el botón
        fetchButton.setForeground(Color.BLACK);
        fetchButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fetchButton.setBorder(BorderFactory.createRaisedBevelBorder());
        fetchButton.setFocusPainted(false);

        fetchButton.addActionListener((ActionEvent e) -> fetchChannelVideos());

        JPanel inputPanel = new JPanel();
        inputPanel.add(urlTextField);
        inputPanel.add(fetchButton);

        // Estilo para el panel de entrada
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(inputPanel, BorderLayout.CENTER);
    }

    private void addObserver(YouTubeDataObserver observer) {
        observers.add(observer);
    }

    private void fetchChannelVideos() {
        String url = urlTextField.getText();
        if (!LinkParser.isChannelUrl(url)) {
            // Usar HTML para personalizar el mensaje de error
            JOptionPane.showMessageDialog(this,
                    "<html><body><p style='width: 200px;'>" +
                            "Invalid URL: <span style='color: red;'>" + url + "</span></p></body></html>",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> videosURLList = channelData.retrieveData(url);
        List<Video> videosList = new ArrayList<>();
        for (String videoURL : videosURLList) {
            Video video = videoData.retrieveData(videoURL);
            videosList.add(video);
            if (channelName == null || channelName.isEmpty()) {
                channelName = video.getSnippet().getChannelTitle();
            }
        }

        notifyObservers(videosList);
    }

    private void notifyObservers(List<Video> videos) {
        for (YouTubeDataObserver observer : observers) {
            observer.update(videos);
        }
    }
}
