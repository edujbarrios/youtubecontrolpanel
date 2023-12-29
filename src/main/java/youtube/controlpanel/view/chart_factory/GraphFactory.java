// GraphFactory.java
package youtube.controlpanel.view.chart_factory;

import org.jfree.data.category.DefaultCategoryDataset;


public class GraphFactory {
    public Graph createGraph(String type, DefaultCategoryDataset dataset) {
        switch (type) {
            case "line":
                return new LineChartGraph(dataset);
            // Add more cases
            default:
                throw new IllegalArgumentException("Unknown graph type: " + type);
        }
    }
}
