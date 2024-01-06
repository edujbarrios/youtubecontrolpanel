// LineChartGraph.java
package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartGraph extends Graph {
    private final DefaultCategoryDataset _dataset;
    private final String title;
    private JFreeChart chart;
    public LineChartGraph(DefaultCategoryDataset dataset, String title) {
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
        chart = ChartFactory.createLineChart(
                title,                // chart title
                "Time",               // domain axis label
                "Value",              // range axis label
                dataset,              // data
                PlotOrientation.VERTICAL, // orientation
                true,                 // include legend
                true,                 // tooltips
                false                 // urls
        );
        CategoryPlot plot = chart.getCategoryPlot();


        // Convert the value to a Double
        Double firstValueAsDouble =  dataset.getValue(0, 0).doubleValue();
        Double lastValueAsDouble =  dataset.getValue(0, dataset.getColumnKeys().size()-1).doubleValue();

        // Get the NumberAxis from the plot
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

        rangeAxis.setLowerBound(firstValueAsDouble);
        rangeAxis.setUpperBound(lastValueAsDouble+1);
    }

}

