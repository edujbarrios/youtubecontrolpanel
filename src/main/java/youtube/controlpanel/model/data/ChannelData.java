package youtube.controlpanel.model.data;

import youtube.controlpanel.model.strategies.*;

import java.util.List;

/**
 * ChannelData class represents a context in the Strategy pattern.
 * It uses a DataRetrievalStrategy to retrieve a list of videos from a YouTube channel.
 */
public class ChannelData {
    private DataRetrievalStrategy strategy;

    /**
     * Constructor for ChannelData.
     */
    public ChannelData() {
        this.strategy = new ChannelDataRetrievalStrategy();
    }

    /**
     * Retrieves a list of URL videos from a specified YouTube channel URL.
     *
     * @param channelUrl The URL of the YouTube channel from which to retrieve videos.
     * @return A list of URLs, each identifying a video in the channel.
     */
    public List<String> retrieveData(String channelUrl) {
        return (List<String>) strategy.retrieveData(channelUrl);
    }
}
