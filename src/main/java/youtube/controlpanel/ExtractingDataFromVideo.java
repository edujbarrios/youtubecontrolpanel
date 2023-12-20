package youtube.controlpanel;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

/**
 * Demonstrates how to make a basic request to the YouTube Data API using the Java client library.
 */
public class ExtractingDataFromVideo {

    /** Your YouTube Data API Key */
    private static final String API_KEY = "YOUR_API_KEY";

    /**
     * Main method. Makes a call to the YouTube Data API and prints details of a specific video.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            YouTube youtubeService = getService();

            String videoId = "VIDEO_ID"; // Specify the YouTube video ID

            VideoListResponse videoResponse = youtubeService.videos()
                    .list("snippet,contentDetails,statistics")
                    .setId(videoId)
                    .setKey(API_KEY)
                    .execute();

            for (Video video : videoResponse.getItems()) {
                System.out.println("Title: " + video.getSnippet().getTitle());
                System.out.println("Views: " + video.getStatistics().getViewCount());
                System.out.println("Likes: " + video.getStatistics().getLikeCount());
                System.out.println("Dislikes: " + video.getStatistics().getDislikeCount());
                System.out.println("Number of Comments: " + video.getStatistics().getCommentCount());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and returns the YouTube service object.
     *
     * @return YouTube service object.
     * @throws Exception if the service fails to initialize.
     */
    public static YouTube getService() throws Exception {
        return new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                new JacksonFactory(),
                request -> {})
                .setApplicationName("youtube-api-example")
                .build();
    }
}
