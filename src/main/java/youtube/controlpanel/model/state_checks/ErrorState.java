package youtube.controlpanel.model.state_checks;

public class ErrorState implements VideoDataState {
    @Override
    public void handle(VideoDataService service) {
        // Manejar error y decidir el siguiente estado
    }
}
