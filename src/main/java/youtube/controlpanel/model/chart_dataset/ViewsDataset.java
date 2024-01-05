package youtube.controlpanel.model.chart_dataset;

import com.google.api.services.youtube.model.Video;
import org.jfree.data.category.DefaultCategoryDataset;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewsDataset implements Dataset {
    DefaultCategoryDataset dataset;
    Video _video;

    public ViewsDataset(Video video) {
        dataset = new DefaultCategoryDataset();
        _video = video;
    }

    // Creates a dataset for the charts based on video statistics
    @Override
    public void updateData() {
        String series = "Video Views";

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentDateTime = dateFormat.format(currentDate);

        dataset.addValue(_video.getStatistics().getViewCount(), series, currentDateTime);
    }

    @Override
    public DefaultCategoryDataset getDataset() {
        return dataset;
    }
}
