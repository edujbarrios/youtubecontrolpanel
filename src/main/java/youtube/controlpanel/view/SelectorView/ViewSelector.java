package youtube.controlpanel.view.SelectorView;

import youtube.controlpanel.view.graphic.MainView;
import youtube.controlpanel.view.terminal.MainConsole;

public class ViewSelector {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("console")) {
            MainConsole.main(new String[]{});
        } else {
            MainView.main(new String[]{});
        }
    }
}
