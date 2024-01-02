package youtube.controlpanel.model.state_checks;


public class RetrievingDataState implements VideoDataState {
    RetrievingDataState() {}

    @Override
    public void handle(VideoDataService service) {
        service.fetchData(); // Fetch the latest video data
        service.scheduleNextDataRetrieval(20000); // Schedule the next retrieval after 20 seconds
    }
}
