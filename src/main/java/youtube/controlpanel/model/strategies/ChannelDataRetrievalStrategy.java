package youtube.controlpanel.model.strategies;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import youtube.controlpanel.model.resources.LinkParser;
import youtube.controlpanel.model.resources.YoutubeAPIManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the data retrieval strategy for YouTube channel data.
 * This strategy is responsible for fetching a list of videos from a specified YouTube channel.
 */
public class ChannelDataRetrievalStrategy implements DataRetrievalStrategy {
    private static final String API_KEY = "AIzaSyCCtrW5wx9A7tXo5_s68BkNpE7vv96qGpM";

    /**
     * Retrieves a list of videos from a given YouTube channel URL.
     *
     * @param channelUrl The URL of the YouTube channel from which to retrieve videos.
     * @return A list of Video objects representing the videos from the specified channel.
     */
    @Override
    public List<Video> retrieveData(String channelUrl) {
        List<Video> videoList = new ArrayList<>();
        try {
            String channelID = LinkParser.extractChannelIdFromUrl(channelUrl);
            if (channelID == null) {
                System.out.println("Invalid channel URL");
                return videoList;
            }

            YouTube youtubeService = YoutubeAPIManager.getService();

            SearchListResponse searchListResponse = youtubeService.search()
                    .list("id")
                    .setChannelId(channelID)
                    .setType("video")
                    .setMaxResults((long) 10)
                    .setKey(API_KEY)
                    .execute();

            for (SearchResult searchResult : searchListResponse.getItems()) {
                String videoID = searchResult.getId().getVideoId();

                VideoListResponse videoListResponse = youtubeService.videos()
                        .list("snippet,statistics")
                        .setId(videoID)
                        .setKey(API_KEY)
                        .execute();

                videoList.add(videoListResponse.getItems().get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoList;
    }
}
