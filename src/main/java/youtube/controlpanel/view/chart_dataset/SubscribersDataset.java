package youtube.controlpanel.view.chart_dataset;

import com.google.api.services.youtube.model.Video;
import org.jfree.data.category.DefaultCategoryDataset;
import youtube.controlpanel.model.calculator.YouTubeEarningsCalculator;

public class SubscribersDataset implements Dataset {

    // Creates a dataset for the charts based on video statistics
    @Override
    public DefaultCategoryDataset createDataset(Video video) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String series = "Video Details";

        dataset.addValue(video.getStatistics().getLikeCount(), series, "Likes");
        dataset.addValue(video.getStatistics().getViewCount(), series, "Views");
        dataset.addValue(video.getStatistics().getCommentCount(), series, "Comments");
        dataset.addValue(YouTubeEarningsCalculator.calculateAdjustedEarnings(video), series, "Estimated Earnings");

        return dataset;
    }
}
