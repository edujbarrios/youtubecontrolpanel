package youtube.controlpanel.view.observer;

import com.google.api.services.youtube.model.Video;
import java.util.List;

public interface YouTubeDataObserver {
    void update(List<Video> videos);
}
