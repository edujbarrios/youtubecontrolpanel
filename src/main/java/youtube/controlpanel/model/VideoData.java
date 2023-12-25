
package youtube.controlpanel.model;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

public class VideoData {

    /** YouTube Data API Key */
    private static final String API_KEY = "AIzaSyCCtrW5wx9A7tXo5_s68BkNpE7vv96qGpM";

    /**
     * Retrieves detailed information of a specific YouTube video.
     *
     * @param videoUrl The URL of the YouTube video.
     * @return A Video object containing details like title, description, views, and likes, or null if not found.
     */
    public Video getVideoDetails(String videoUrl) {
        Video videoDetail = null;

        try {
            // Extract the video ID from the URL
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
