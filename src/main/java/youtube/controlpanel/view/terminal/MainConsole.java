package youtube.controlpanel.view.terminal;

import youtube.controlpanel.view.observer.YouTubeDataManager;


public class MainConsole {

    public static void main(String[] args) {
        YouTubeDataManager dataManager = new YouTubeDataManager();
        ConsoleView consoleView = new ConsoleView(dataManager);
        consoleView.start();
    }
}
