package youtube.controlpanel;

import youtube.controlpanel.view.frames.MainView;

import javax.swing.*;

public class Client {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }

}
