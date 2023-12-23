package youtube.controlpanel.model;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

import java.util.List;

public class ChannelData {

    /** YouTube Data API Key */
    private static final String API_KEY = "AIzaSyCCtrW5wx9A7tXo5_s68BkNpE7vv96qGpM";

    public List<SearchResult> getVideos() {
        SearchListResponse searchResponse = null;

        try {
            YouTube youtubeService = YoutubeAPIManager.getService();
            String channelID = "UCByOQJjav0CUDwxCk-jVNRQ";

            searchResponse = youtubeService.search()
                    .list("id,snippet")
                    .setChannelId(channelID)
                    .setType("video")
                    .setMaxResults((long) 10)
                    .setKey(API_KEY)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchResponse.getItems();
    }
}
