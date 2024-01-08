package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.resources.YouTubeEarningsCalculator;

public class TerminalDataDisplay {

    public static void displayVideoDetails(Video video, String channelName) {
        System.out.println("Channel Name: " + channelName);
        System.out.println("Video Title: " + video.getSnippet().getTitle());
        System.out.println("Likes: " + video.getStatistics().getLikeCount());
        System.out.println("Views: " + video.getStatistics().getViewCount());
        System.out.println("Comments: " + video.getStatistics().getCommentCount());
        System.out.println("Estimated Earnings of the video: $" +
                String.format("%.2f", YouTubeEarningsCalculator.calculateAdjustedEarnings(video)));
    }

    // Método nuevo para actualizar los detalles del video
    public static void updateVideoDetails(Video video, String channelName) {
        // Llamada al método displayVideoDetails para la actualización
        displayVideoDetails(video, channelName);
    }
}
