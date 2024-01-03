package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class WaterfallChartGraph extends Graph {
    private final DefaultCategoryDataset _dataset;
    private final String title;
    private JFreeChart chart;

    public WaterfallChartGraph(DefaultCategoryDataset dataset, String title) {
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
                "Category",      // domain axis label
                "Value",         // range axis label
                dataset,         // data
                PlotOrientation.VERTICAL, // orientation
                true,            // include legend
                true,            // tooltips
                false            // urls
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = new BarRenderer();

        // Personalización adicional para el gráfico de cascada
        // Por ejemplo, configurar colores, visibilidad de barras, etc.

        plot.setRenderer(renderer);
    }
}
