package youtube.controlpanel.view.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckboxPanel extends JPanel {
    ControlPanelView controlPanelView;
    ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
    CheckboxPanel(ControlPanelView controlPanelView){
        this.controlPanelView =controlPanelView;

        // Create checkboxes
        JCheckBox barChartCheckbox = new JCheckBox("BarChart Graph");
        JCheckBox pieChartCheckbox = new JCheckBox("PieChart Graph");
        JCheckBox areaChartCheckbox = new JCheckBox("AreaChart Graph");
        JCheckBox ringChartCheckbox = new JCheckBox("RingChart Graph");
        JCheckBox waterfallChartCheckbox = new JCheckBox("WaterfallChart Graph");
        checkboxes.add(barChartCheckbox);
        checkboxes.add(pieChartCheckbox);
        checkboxes.add(areaChartCheckbox);
        checkboxes.add(ringChartCheckbox);
        checkboxes.add(waterfallChartCheckbox);
        setLayout(new GridLayout(7, 1));
        add(barChartCheckbox);
        add(pieChartCheckbox);
        add(areaChartCheckbox);
        add(ringChartCheckbox);
        add(waterfallChartCheckbox);
        // Create button to apply selection
        JButton applyButton = new JButton("Apply Selection");
        // Add action listener to apply button
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlPanelView.displayCharts(checkboxes);
            }
        });
        add(applyButton);

    }
}
