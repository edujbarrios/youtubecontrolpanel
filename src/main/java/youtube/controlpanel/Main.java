package youtube.controlpanel;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.ChannelData;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChannelData channelData = new ChannelData();
        List<Video> videoResults = channelData.getVideos();

        for (Video video : videoResults) {
            System.out.println("Video Title: " + video.getSnippet().getTitle());
            System.out.println("Video ID: " + video.getId());
            System.out.println("Video Likes: " + video.getStatistics().getLikeCount());
            System.out.println("------");
        }
    }
}