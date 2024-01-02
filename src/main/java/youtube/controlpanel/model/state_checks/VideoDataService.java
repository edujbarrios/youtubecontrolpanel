package youtube.controlpanel.model.state_checks;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import youtube.controlpanel.view.observer.YouTubeDataManager;
import youtube.controlpanel.model.strategies.VideoDataRetrievalStrategy;


public class VideoDataService {
    private YouTubeDataManager dataManager;
    private Timer refreshTimer;
    private String currentVideoUrl;
    private VideoDataState currentState;

    public VideoDataService(String videoUrl) {
        this.currentVideoUrl = videoUrl;
        this.dataManager = new YouTubeDataManager(new VideoDataRetrievalStrategy());
        setState(new InitializingState()); // Start with the initial state
        initRefreshTimer();
    }

    private void initRefreshTimer() {
        ActionListener refreshAction = e -> executeCurrentStateAction();
        refreshTimer = new Timer(20000, refreshAction); // Schedule state execution every 20 seconds
        refreshTimer.start();
    }

    private void executeCurrentStateAction() {
        if (currentState != null) {
            currentState.handle(this);
        }
    }

    public void setState(VideoDataState newState) {
        this.currentState = newState;
        currentState.handle(this); // Execute the action associated with the new state
    }

    public void fetchData() {
        if (currentVideoUrl != null && !currentVideoUrl.isEmpty()) {
            dataManager.fetchData(currentVideoUrl); // Use YouTubeDataManager to fetch data
        }
    }

    public void setCurrentVideoUrl(String url) {
        this.currentVideoUrl = url;
        fetchData(); // Fetch data immediately when URL is updated
    }

    public void stopTimer() {
        if (refreshTimer != null) {
            refreshTimer.stop(); // Stop the timer when service is no longer needed
        }
    }

    public void scheduleNextDataRetrieval(int delayMillis) {
        if (refreshTimer != null) {
            refreshTimer.stop();
        }
        ActionListener refreshAction = e -> executeCurrentStateAction();
        refreshTimer = new Timer(delayMillis, refreshAction);
        refreshTimer.start();
    }

    // Additional necessary methods...
}
