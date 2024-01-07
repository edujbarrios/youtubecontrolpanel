package youtube.controlpanel.view.frames;

import javax.swing.*;
import java.awt.*;

public class DropdownMenuPanel extends JPanel {
    ControlPanelView controlPanelView;
    JComboBox<String> chartComboBox;
    JButton applyButton;

    DropdownMenuPanel(ControlPanelView controlPanelView) {
        this.controlPanelView = controlPanelView;

        // Crear combo box con opciones de gráfico
        String[] chartOptions = {"BarChart Graph", "PieChart Graph", "AreaChart Graph", "RingChart Graph", "WaterfallChart Graph", "LineChart Graph"};
        chartComboBox = ComboBoxFactory.createStyledComboBox(chartOptions);

        // Crear botón para aplicar la selección
        applyButton = ButtonFactory.createButton("Apply Selection");

        CheckboxPanel checkboxPanel = new CheckboxPanel();
        // Añadir ActionListener al botón de aplicar
        applyButton.addActionListener(e -> {
            String selectedChart = (String) chartComboBox.getSelectedItem();
            controlPanelView.displayChart(selectedChart, checkboxPanel.getCheckboxes());
        });

        // Establecer el layout y añadir componentes
        setLayout(new GridLayout(3, 1));
        add(chartComboBox);
        add(checkboxPanel);
        add(applyButton);
    }
}
