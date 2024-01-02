package youtube.controlpanel.model.state_checks;


public class InitializingState implements VideoDataState {
    @Override
    public void handle(VideoDataService service) {
        service.setState(new RetrievingDataState());
    }
}
