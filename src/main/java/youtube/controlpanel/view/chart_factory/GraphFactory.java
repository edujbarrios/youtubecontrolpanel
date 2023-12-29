// GraphFactory.java
package youtube.controlpanel.view.chart_factory;

import org.jfree.data.category.DefaultCategoryDataset;


public class GraphFactory {
    public Graph createGraph(String type, DefaultCategoryDataset dataset, String title) {
        return switch (type) {
            case "line" -> new LineChartGraph(dataset, title);
            case "bar" -> new BarChartGraph(dataset, title);
            case "pie" -> new PieChartGraph(dataset, title);
            default -> throw new IllegalArgumentException("Unknown graph type: " + type);
        };
    }
}
