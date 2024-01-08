package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartGraph extends Graph {
    private final DefaultCategoryDataset _dataset;
    private final String title;
    private JFreeChart chart;
    public BarChartGraph(DefaultCategoryDataset dataset, String title) {
        this._dataset = dataset;
        this.title = title;
    }
    @Override
    public JFreeChart createChart() {
        updateChart(_dataset);
        return chart;
    }

    @Override
    public void updateChart(DefaultCategoryDataset dataset) {
        chart = ChartFactory.createBarChart(
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

