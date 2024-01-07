package youtube.controlpanel.controller;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    public static JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);

        // Estilo para el botón
        button.setBackground(new Color(205, 32, 31)); // Un rojo más oscuro para el botón
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFocusPainted(false);

        return button;
    }
}
