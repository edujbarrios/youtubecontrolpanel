
package youtube.controlpanel;

import youtube.controlpanel.view.frames.MainView;

import javax.swing.*;

/**
 * Clase principal del cliente para la aplicación Youtube Control Panel.
 * Esta clase es responsable de iniciar la interfaz de usuario.
 */
public class Client {

    /**
     * El punto de entrada principal de la aplicación.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try {
            // Iniciar la interfaz de usuario en el hilo de despacho de eventos de Swing
            SwingUtilities.invokeLater(Client::initializeUI);
        } catch (Exception e) {
            // Manejo básico de excepciones, podría mejorarse con un logger
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Inicializa y muestra la interfaz principal de usuario.
     */
    private static void initializeUI() {
        MainView mainView = new MainView();
        mainView.setVisible(true);
    }
}
