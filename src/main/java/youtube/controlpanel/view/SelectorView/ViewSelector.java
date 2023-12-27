package youtube.controlpanel.view.SelectorView;

import youtube.controlpanel.view.graphic.MainView;
import youtube.controlpanel.view.terminal.MainConsole;

/**
 * The ViewSelector class serves as an entry point to select between two different views in a YouTube control panel application.
 */
public class ViewSelector {
    
    /**
     * The main method is the entry point of the application.
     * 
     * @param args Command line arguments that determine which view to launch.
     */
    public static void main(String[] args) {
        // Check if any arguments are passed and the first argument is "console".
        if (args.length > 0 && args[0].equalsIgnoreCase("console")) {
            // If "console" is the first argument, launch the console-based main view.
            MainConsole.main(new String[]{});
        } else {
            // If no argument or an argument other than "console" is provided, launch the graphical main view.
            MainView.main(new String[]{});
        }
    }
}

