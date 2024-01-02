package youtube.controlpanel.model.state_checks;


public class DataRetrievedState implements VideoDataState {
    DataRetrievedState() {}

    @Override
    public void handle(VideoDataService service) {
        // Assuming YouTubeDataManager inside VideoDataService handles the notification
        service.setState(new RetrievingDataState());
    }
}
