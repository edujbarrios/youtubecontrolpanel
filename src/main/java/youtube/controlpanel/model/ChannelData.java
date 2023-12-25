package youtube.controlpanel.model;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.util.ArrayList;
import java.util.List;

public class ChannelData {

    /** YouTube Data API Key */
    private static final String API_KEY = "AIzaSyCCtrW5wx9A7tXo5_s68BkNpE7vv96qGpM";

    /**
     * Retrieves a list of videos from a given YouTube channel URL.
     *
     * @param channelUrl The URL of the YouTube channel.
     * @return A list of Video objects from the specified channel.
     */
    public List<Video> getVideos(String channelUrl) {
        List<Video> videoList = new ArrayList<>();

        try {
            YouTube youtubeService = YoutubeAPIManager.getService();
            
//Added this function
            
            // Use LinkParser to extract the channel ID from the channel URL
            String channelID = LinkParser.extractChannelIdFromUrl(channelUrl);
            if (channelID == null) {
                System.out.println("Invalid channel URL");
                return videoList;
            }

            SearchListResponse searchListResponse = youtubeService.search()
                    .list("id")
                    .setChannelId(channelID)
                    .setType("video")
                    .setMaxResults((long) 10)
                    .setKey(API_KEY)
                    .execute();

            List<SearchResult> searchResults = searchListResponse.getItems();

            for (SearchResult searchResult : searchResults) {
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

