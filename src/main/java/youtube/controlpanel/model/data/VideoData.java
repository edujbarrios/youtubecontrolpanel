package youtube.controlpanel.model.data;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.strategies.DataRetrievalStrategy;

/**
 * VideoData class serves as a context in the Strategy pattern.
 * It uses a DataRetrievalStrategy to fetch details of a specific YouTube video.
 */
public class VideoData {
    private DataRetrievalStrategy strategy;

    /**
     * Constructs a VideoData object with a specified data retrieval strategy.
     *
     * @param strategy The data retrieval strategy to be used for fetching YouTube video data.
     */
    public VideoData(DataRetrievalStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Retrieves detailed information about a specific YouTube video.
     *
     * @param videoUrl The URL of the YouTube video.
     * @return A Video object containing details of the requested video, or null if not found.
     */
    public Video retrieveVideoDetails(String videoUrl) {
        return (Video) strategy.retrieveData(videoUrl);
    }
}
