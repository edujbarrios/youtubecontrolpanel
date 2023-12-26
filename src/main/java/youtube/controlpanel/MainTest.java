package youtube.controlpanel;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.data.*;
import youtube.controlpanel.model.resources.LinkParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main application class for processing YouTube URLs with extended video information.
 */
public class MainTest {

    /**
     * Entry point of the application.
     * Retrieves and displays extended information about YouTube videos and channels.
     *
     * @param args Command-line arguments (not used).
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

            displayVideoDetails(videosList);
        }

        scanner.close();
        System.out.println("Program terminated.");
    }

    /**
     * Displays detailed information about a list of videos.
     *
     * @param videosList A list of Video objects.
     */
    private static void displayVideoDetails(List<Video> videosList) {
        for (Video video : videosList) {
            System.out.println("Video Title: " + video.getSnippet().getTitle());
            System.out.println("Channel: " + video.getSnippet().getChannelTitle());
            System.out.println("Published Date: " + video.getSnippet().getPublishedAt());
            System.out.println("Likes: " + video.getStatistics().getLikeCount());
            System.out.println("Views: " + video.getStatistics().getViewCount());
            System.out.println("Comments: " + video.getStatistics().getCommentCount());
            // Add more fields as needed, depending on what is available from the API
            System.out.println("-----");
        }
    }

    /**
     * Displays the count of videos published per year for a list of videos.
     *
     * @param videosList A list of Video objects.
     */
    private static void displayVideosPerYear(List<Video> videosList) {
        Map<Integer, Integer> videosPerYear = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        for (Video video : videosList) {
            try {
                Date publishedDate = dateFormat.parse(video.getSnippet().getPublishedAt().toStringRfc3339());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(publishedDate);
                int year = calendar.get(Calendar.YEAR);

                videosPerYear.put(year, videosPerYear.getOrDefault(year, 0) + 1);
            } catch (ParseException e) {
                System.out.println("Error parsing date: " + e.getMessage());
            }
        }

        for (Map.Entry<Integer, Integer> entry : videosPerYear.entrySet()) {
            System.out.println("Year " + entry.getKey() + ": " + entry.getValue() + " video(s)");
        }
    }
}
