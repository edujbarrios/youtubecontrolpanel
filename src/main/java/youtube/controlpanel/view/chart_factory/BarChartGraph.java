package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartGraph implements Graph {
    private final DefaultCategoryDataset dataset;
    private final String title;
    public BarChartGraph(DefaultCategoryDataset dataset, String title) {
        this.dataset = dataset;
        this.title = title;
    }
    @Override
    public JFreeChart createChart() {
        // Use the correct method createBarChart for creating a bar chart
        return ChartFactory.createBarChart(
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

