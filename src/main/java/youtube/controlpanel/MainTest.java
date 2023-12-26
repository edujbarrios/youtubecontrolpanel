package youtube.controlpanel;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.data.*;
import youtube.controlpanel.model.resources.LinkParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for processing YouTube URLs.
 */
public class MainTest {

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChannelData channelData = new ChannelData();
        VideoData videoData = new VideoData();

        while (true) {
            System.out.println("Enter a YouTube video or channel URL (or type 'exit' to terminate):");
            String url = scanner.nextLine();

            if ("exit".equalsIgnoreCase(url)) {
                break;
            }

            List<Video> videosList = new ArrayList<>();

            if (LinkParser.isChannelUrl(url)) {
                List<String> videosURList = channelData.retrieveData(url);
                for (String videoURL : videosURList) {
                    videosList.add(videoData.retrieveData(videoURL));
                }
            } else if (LinkParser.isVideoUrl(url)) {
                videosList.add(videoData.retrieveData(url));
            } else {
                System.out.println("Invalid URL: " + url);
                continue;
            }

            for (Video video : videosList) {
                System.out.println("Video Title: " + video.getSnippet().getTitle());
                System.out.println("Video Likes: " + video.getStatistics().getLikeCount());
                System.out.println("Video Views: " + video.getStatistics().getViewCount());
                System.out.println("Video Comments: " + video.getStatistics().getCommentCount());
                System.out.println("-----");
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }
}
