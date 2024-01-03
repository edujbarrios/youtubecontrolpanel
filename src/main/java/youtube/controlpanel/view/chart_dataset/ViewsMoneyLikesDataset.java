package youtube.controlpanel.view.chart_dataset;

import com.google.api.services.youtube.model.Video;
import org.jfree.data.category.DefaultCategoryDataset;
import youtube.controlpanel.model.resources.YouTubeEarningsCalculator;

public class ViewsMoneyLikesDataset implements Dataset {

    DefaultCategoryDataset dataset;
    Video _video;

    public ViewsMoneyLikesDataset(Video video) {
        dataset = new DefaultCategoryDataset();
        _video = video;
    }

    // Creates a dataset for the charts based on video statistics
    @Override
    public void updateData() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series = "Video Views";
        dataset.addValue(_video.getStatistics().getViewCount(), series, "Views");
    }

    @Override
    public DefaultCategoryDataset getDataset() {
        return dataset;
    }
}
