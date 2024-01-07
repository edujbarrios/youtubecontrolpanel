import javax.swing.*;
import java.awt.*;

public class ComboBoxFactory {

    public static JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);

        // Estilo para el JComboBox
        comboBox.setBackground(new Color(255, 255, 255)); // Fondo blanco
        comboBox.setForeground(new Color(205, 32, 31)); // Color rojo YouTube
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Misma fuente que fetchButton

        return comboBox;
    }
}
