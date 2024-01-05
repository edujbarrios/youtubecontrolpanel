package youtube.controlpanel.view.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckboxPanel extends JPanel {
    ControlPanelView controlPanelView;
    JComboBox<String> chartComboBox;
    JButton applyButton;

    CheckboxPanel(ControlPanelView controlPanelView) {
        this.controlPanelView = controlPanelView;

        // Create combo box with chart options
        String[] chartOptions = {"BarChart Graph", "PieChart Graph", "AreaChart Graph", "RingChart Graph", "WaterfallChart Graph"};
        chartComboBox = new JComboBox<>(chartOptions);

        // Create button to apply selection
        applyButton = new JButton("Apply Selection");

        // Add action listener to apply button
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedChart = (String) chartComboBox.getSelectedItem();
                controlPanelView.displayChart(selectedChart);
            }
        });

        // Set layout and add components
        setLayout(new GridLayout(2, 1));
        add(chartComboBox);
        add(applyButton);
    }
}
