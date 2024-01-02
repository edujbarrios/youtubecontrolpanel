package youtube.controlpanel.view.observer;

import com.google.api.services.youtube.model.Video;
import youtube.controlpanel.model.strategies.DataRetrievalStrategy;
import java.util.ArrayList;
import java.util.List;

public class YouTubeDataManager {
    private List<YouTubeDataObserver> observers = new ArrayList<>();
    private DataRetrievalStrategy dataRetrievalStrategy;

    public YouTubeDataManager(DataRetrievalStrategy strategy) {
        this.dataRetrievalStrategy = strategy;
    }

    public void addObserver(YouTubeDataObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(YouTubeDataObserver observer) {
        observers.remove(observer);
    }

    public void fetchData(String url) {
        List<Video> videos = (List<Video>) dataRetrievalStrategy.retrieveData(url);
        notifyObservers(videos);
    }

    public void notifyObservers(List<Video> videos) {
        for (YouTubeDataObserver observer : observers) {
            observer.update(videos);
        }
    }
}
