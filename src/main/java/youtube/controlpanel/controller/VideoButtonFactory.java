package youtube.controlpanel.controller;

import javax.swing.*;
import java.awt.*;

public class VideoButtonFactory {

    public static JButton createVideoButton(String buttonText) {
        JButton button = new JButton(buttonText);

        // Estilo para el botón de video
        button.setBackground(new Color(205, 32, 31)); // Mismo fondo que fetchButton
        button.setForeground(Color.BLACK); // Mismo color de texto que fetchButton
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Misma fuente que fetchButton
        button.setBorder(BorderFactory.createRaisedBevelBorder()); // Mismo borde que fetchButton
        button.setFocusPainted(false);

        return button;
    }
}
