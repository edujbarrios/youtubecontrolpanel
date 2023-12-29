// GraphFactory.java
package youtube.controlpanel.view.chart_factory;

import org.jfree.data.category.DefaultCategoryDataset;


public class GraphFactory {
    public Graph createGraph(String type, DefaultCategoryDataset dataset) {
        return switch (type) {
            case "line" -> new LineChartGraph(dataset);
            case "bar" -> new BarChartGraph(dataset);
            case "pie" -> new PieChartGraph(dataset);
            default -> throw new IllegalArgumentException("Unknown graph type: " + type);
        };
    }
}
