package youtube.controlpanel.controller;

import javax.swing.*;
import java.awt.*;

public class CheckboxFactory {

    public static JCheckBox createStyledCheckbox(String label) {
        JCheckBox checkbox = new JCheckBox(label);

        // Estilo para el checkbox
        checkbox.setBackground(new Color(205, 32, 31)); // Mismo fondo que fetchButton
        checkbox.setForeground(Color.BLACK); // Mismo color de texto que fetchButton
        checkbox.setFont(new Font("Arial", Font.PLAIN, 14)); // Misma fuente que fetchButton
        checkbox.setFocusPainted(false);
        checkbox.setBorder(BorderFactory.createRaisedBevelBorder()); // Mismo borde que fetchButton

        return checkbox;
    }
}
