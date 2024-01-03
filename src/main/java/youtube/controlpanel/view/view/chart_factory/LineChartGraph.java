// LineChartGraph.java
package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartGraph extends Graph {
    private final DefaultCategoryDataset dataset;
    private final String title;
    public LineChartGraph(DefaultCategoryDataset dataset, String title) {
        this.dataset = dataset;
        this.title = title;
    }

    @Override
    public JFreeChart createChart() {
        // Asegurándose de utilizar la sobrecarga correcta del método createLineChart
        return ChartFactory.createLineChart(
                title, // chart title
                "Time",              // domain axis label
                "Value",       // range axis label
                dataset,             // data
                PlotOrientation.VERTICAL, // orientation
                true,                // include legend
                true,                // tooltips
                false                // urls
        );
    }
}

