package youtube.controlpanel.view.chart_factory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class RingChartGraph extends Graph {
    private final DefaultCategoryDataset categoryDataset;
    private final String title;

    public RingChartGraph(DefaultCategoryDataset dataset, String title) {
        this.categoryDataset = dataset;
        this.title = title;
    }

    @Override
    public JFreeChart createChart() {
        // Convert DefaultCategoryDataset to DefaultPieDataset
        DefaultPieDataset pieDataset = createPieDatasetFromCategoryDataset(categoryDataset);

        // Use the correct method createRingChart for creating a ring chart
        return ChartFactory.createRingChart(
                title, // chart title
                pieDataset,          // data
                true,                // include legend
                true,                // tooltips
                false                // urls
        );
    }

    private DefaultPieDataset createPieDatasetFromCategoryDataset(DefaultCategoryDataset categoryDataset) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        // Assuming there is a single row in the categoryDataset
        Comparable<?> rowKey = categoryDataset.getRowKey(0);

        for (int i = 0; i < categoryDataset.getColumnCount(); i++) {
            Comparable<?> columnKey = categoryDataset.getColumnKey(i);
            Number value = categoryDataset.getValue(rowKey, columnKey);
            pieDataset.setValue(columnKey, value);
        }

        return pieDataset;
    }
}
