package youtube.controlpanel.view.chart_factory;

import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphFactory {
    public Graph createGraph(String type, Dataset dataset, String title) {
        return switch (type) {
            case "line" -> new LineChartGraph((DefaultCategoryDataset) dataset, title);
            case "bar" -> new BarChartGraph((DefaultCategoryDataset) dataset, title);
            case "pie" -> new PieChartGraph((DefaultCategoryDataset) dataset, title);
            case "area" -> new AreaChartGraph((DefaultCategoryDataset) dataset, title);
            case "ring" -> new RingChartGraph((DefaultCategoryDataset) dataset, title);
            case "waterfall" -> new WaterfallChartGraph((DefaultCategoryDataset) dataset, title); // Añadido gráfico de cascada
            default -> throw new IllegalArgumentException("Unknown graph type: " + type);
        };
    }
}

