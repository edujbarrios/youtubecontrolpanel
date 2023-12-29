package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartGraph implements Graph {
    private DefaultCategoryDataset dataset;

    public BarChartGraph(DefaultCategoryDataset dataset) {
        this.dataset = dataset;
    }

    @Override
    public JFreeChart createChart() {
        // Use the correct method createBarChart for creating a bar chart
        return ChartFactory.createBarChart(
                "Total Subscribers", // chart title
                "Time",              // domain axis label
                "Subscribers",       // range axis label
                dataset,             // data
                PlotOrientation.VERTICAL, // orientation
                true,                // include legend
                true,                // tooltips
                false                // urls
        );
    }
}

