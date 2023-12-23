package youtube.controlpanel;

import com.google.api.services.youtube.model.SearchResult;
import youtube.controlpanel.model.ChannelData;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChannelData channelData = new ChannelData();
        List<SearchResult> searchResults = channelData.getVideos();

        for (SearchResult searchResult : searchResults) {
            System.out.println("Video Title: " + searchResult.getSnippet().getTitle());
            System.out.println("Video ID: " + searchResult.getId());
            System.out.println("---------");
        }
    }
}