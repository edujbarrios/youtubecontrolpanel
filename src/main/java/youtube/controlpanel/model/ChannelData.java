package youtube.controlpanel.model;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

import java.util.ArrayList;
import java.util.List;

public class ChannelData {

    /** YouTube Data API Key */
    private static final String API_KEY = "AIzaSyCCtrW5wx9A7tXo5_s68BkNpE7vv96qGpM";

    public List<Video> getVideos() {
        List<Video> videoList = new ArrayList<>();

        try {
            YouTube youtubeService = YoutubeAPIManager.getService();
            String channelID = "UCByOQJjav0CUDwxCk-jVNRQ";

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
