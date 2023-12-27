package youtube.controlpanel.view.terminal;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.resources.LinkParser;
import youtube.controlpanel.view.observer.YouTubeDataManager;
import youtube.controlpanel.view.observer.YouTubeDataObserver;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements YouTubeDataObserver {

    private YouTubeDataManager dataManager;

    public ConsoleView(YouTubeDataManager dataManager) {
        this.dataManager = dataManager;
        this.dataManager.addObserver(this);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("YouTube Video Information Viewer (Console Version)");

        while (true) {
            System.out.print("Enter a YouTube URL (or 'exit' to quit): ");
            String url = scanner.nextLine();

            if ("exit".equalsIgnoreCase(url)) {
                break;
            }

            if (LinkParser.isChannelUrl(url) || LinkParser.isVideoUrl(url)) {
                dataManager.fetchData(url);
            } else {
                System.out.println("Invalid URL: " + url);
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }

    @Override
    public void update(List<Video> videos) {
        for (Video video : videos) {
            System.out.println("Video Title: " + video.getSnippet().getTitle());
            System.out.println("Channel: " + video.getSnippet().getChannelTitle());
            System.out.println("Published Date: " + video.getSnippet().getPublishedAt());
            System.out.println("Likes: " + video.getStatistics().getLikeCount());
            System.out.println("Views: " + video.getStatistics().getViewCount());
            System.out.println("Comments: " + video.getStatistics().getCommentCount());
            System.out.println("-----");
        }
    }
}
