package youtube.controlpanel.model.data;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.strategies.*;

/**
 * VideoData class serves as a context in the Strategy pattern.
 * It uses a DataRetrievalStrategy to fetch details of a specific YouTube video.
 */
public class VideoData {
    private DataRetrievalStrategy strategy;

    /**
     * Constructs a VideoData object with a specified data retrieval strategy.
     */
    public VideoData() {
        this.strategy = new VideoDataRetrievalStrategy();
    }

    /**
     * Retrieves detailed information about a specific YouTube video.
     *
     * @param videoUrl The URL of the YouTube video.
     * @return A Video object containing details of the requested video, or null if not found.
     */
    public Video retrieveData(String videoUrl) {
        return (Video) strategy.retrieveData(videoUrl);
    }
}
