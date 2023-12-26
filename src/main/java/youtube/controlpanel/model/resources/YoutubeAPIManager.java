package youtube.controlpanel.model.resources;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

public class YoutubeAPIManager {
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
                .setApplicationName("youtube-api")
                .build();
    }
}