package youtube.controlpanel.view.graphic;

import com.google.api.services.youtube.model.Video;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VideoListView extends JFrame {
    private JList<String> videoList;
    private DefaultListModel<String> listModel;
    private ControlPanelView controlPanelView;

    public VideoListView(ControlPanelView controlPanelView) {
        this.controlPanelView = controlPanelView;
        setTitle("Select a Video");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prepareUI();
    }

    private void prepareUI() {
        listModel = new DefaultListModel<>();
        videoList = new JList<>(listModel);
        videoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        videoList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());

                }
            }
        });
        add(new JScrollPane(videoList), BorderLayout.CENTER);
    }

    public void displayVideos(List<Video> videos) {
        listModel.clear();
        for (Video video : videos) {
            listModel.addElement(video.getSnippet().getTitle());
        }
    }
}
