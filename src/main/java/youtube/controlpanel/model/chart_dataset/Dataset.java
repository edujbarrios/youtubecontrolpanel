package youtube.controlpanel.model.chart_dataset;

import org.jfree.data.category.DefaultCategoryDataset;

public interface Dataset {
    void updateData();
    DefaultCategoryDataset getViewsDataset();
    DefaultCategoryDataset getLikesDataset();
    DefaultCategoryDataset getCommentsDataset();
    DefaultCategoryDataset getEarningsDataset();
}
