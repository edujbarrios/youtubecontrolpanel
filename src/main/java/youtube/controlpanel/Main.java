package youtube.controlpanel;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.data.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChannelData channelData = new ChannelData();
        VideoData videoData = new VideoData();

        List<String> videosURList = channelData.retrieveData("https://www.youtube.com/channel/UCByOQJjav0CUDwxCk-jVNRQ");
        List<Video> videosList = new ArrayList<>();

        for (String videoURL : videosURList) {
            videosList.add(videoData.retrieveData(videoURL));
        }

        for (Video video : videosList) {
            System.out.println("Video Title: " + video.getSnippet().getTitle());
            System.out.println("Video Likes: " + video.getStatistics().getLikeCount());
            System.out.println("Video Views: " + video.getStatistics().getViewCount());
            System.out.println("Video Comments: " + video.getStatistics().getCommentCount());
            System.out.println("-----");
        }
    }
}