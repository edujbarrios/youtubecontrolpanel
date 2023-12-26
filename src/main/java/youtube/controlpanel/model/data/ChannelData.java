package youtube.controlpanel.model.data;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.strategies.DataRetrievalStrategy;

import java.util.List;

/**
 * ChannelData class represents a context in the Strategy pattern.
 * It uses a DataRetrievalStrategy to retrieve a list of videos from a YouTube channel.
 */
public class ChannelData {
    private DataRetrievalStrategy strategy;

    /**
     * Constructor for ChannelData.
     *
     * @param strategy The data retrieval strategy to be used for fetching YouTube channel data.
     */
    public ChannelData(DataRetrievalStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Retrieves a list of videos from a specified YouTube channel URL.
     *
     * @param channelUrl The URL of the YouTube channel from which to retrieve videos.
     * @return A list of Video objects, each representing a video in the channel.
     */
    public List<Video> RetrieveData(String channelUrl) {
        return (List<Video>) strategy.retrieveData(channelUrl);
    }
}
