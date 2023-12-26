package youtube.controlpanel.model.strategies;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import youtube.controlpanel.model.resources.LinkParser;
import youtube.controlpanel.model.resources.YoutubeAPIManager;

/**
 * Implements the data retrieval strategy for YouTube video data.
 * This strategy is responsible for fetching detailed information of a specific YouTube video.
 */
public class VideoDataRetrievalStrategy implements DataRetrievalStrategy {
    private static final String API_KEY = "AIzaSyCCtrW5wx9A7tXo5_s68BkNpE7vv96qGpM";

    /**
     * Retrieves detailed information about a specific YouTube video.
     *
     * @param videoUrl The URL of the YouTube video for which details are to be retrieved.
     * @return A Video object containing details of the requested video, or null if not found.
     * @throws Exception if there is an issue with the YouTube service or data retrieval.
     */
    @Override
    public Video retrieveData(String videoUrl) {
        Video videoDetail = null;
        try {
            String videoId = LinkParser.extractVideoIdFromUrl(videoUrl);
            if (videoId == null) {
                System.out.println("Invalid YouTube Video URL.");
                return null;
            }

            YouTube youtubeService = YoutubeAPIManager.getService();

            VideoListResponse videoListResponse = youtubeService.videos()
                    .list("snippet,contentDetails,statistics")
                    .setId(videoId)
                    .setKey(API_KEY)
                    .execute();

            if (!videoListResponse.getItems().isEmpty()) {
                videoDetail = videoListResponse.getItems().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoDetail;
    }
}
