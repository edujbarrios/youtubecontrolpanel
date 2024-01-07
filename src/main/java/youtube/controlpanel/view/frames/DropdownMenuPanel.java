package youtube.controlpanel.view.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DropdownMenuPanel extends JPanel {
    ControlPanelView controlPanelView;
    JComboBox<String> chartComboBox;
    JButton applyButton;

    DropdownMenuPanel(ControlPanelView controlPanelView) {
        this.controlPanelView = controlPanelView;

        // Create combo box with chart options
        String[] chartOptions = {"BarChart Graph", "PieChart Graph", "AreaChart Graph", "RingChart Graph", "WaterfallChart Graph", "LineChart Graph"};
        chartComboBox = new JComboBox<>(chartOptions);
        styleComboBox(chartComboBox); // Apply style to JComboBox

        // Create button to apply selection
        applyButton = new JButton("Apply Selection");
        styleButton(applyButton); // Apply style to JButton

        CheckboxPanel checkboxPanel = new CheckboxPanel();
        // Add action listener to apply button
        applyButton.addActionListener(e -> {
            String selectedChart = (String) chartComboBox.getSelectedItem();
            controlPanelView.displayChart(selectedChart, checkboxPanel.getCheckboxes());
        });

        // Set layout and add components
        setLayout(new GridLayout(3, 1));
        add(chartComboBox);
        add(checkboxPanel);
        add(applyButton);
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(255, 255, 255)); // Same background as fetchButton
        comboBox.setForeground(new Color(205, 32, 31)); // Changed to Youtube RED
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Same font as fetchButton
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(205, 32, 31)); // Same background as fetchButton
        button.setForeground(Color.BLACK); // Same foreground as fetchButton
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Same font as fetchButton
        button.setBorder(BorderFactory.createRaisedBevelBorder()); // Same border as fetchButton
        button.setFocusPainted(false);
    }
}
