// Graph.java
package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.*;

public abstract class Graph {
    public abstract JFreeChart createChart();
    public ChartPanel getChartPanel(){
        JFreeChart chart = this.createChart();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        return chartPanel;
    }

}
