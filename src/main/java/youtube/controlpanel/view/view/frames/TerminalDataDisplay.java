package youtube.controlpanel.view.frames;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.resources.YouTubeEarningsCalculator;

public class TerminalDataDisplay {

    // Método para mostrar los detalles de un video en la terminal
    public static void displayVideoDetails(Video video, String channelName) {
        System.out.println("Channel Name: " + channelName);
        System.out.println("Video Title: " + video.getSnippet().getTitle());
        System.out.println("Likes: " + video.getStatistics().getLikeCount());
        System.out.println("Views: " + video.getStatistics().getViewCount());
        System.out.println("Comments: " + video.getStatistics().getCommentCount());
        System.out.println("Estimated Earnings of the video: $" +
                String.format("%.2f", YouTubeEarningsCalculator.calculateAdjustedEarnings(video)));
    }
}
