package youtube.controlpanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;


//Emulates a changing chart with different numbers
public class ExampleDataChart {

    public static void displayChart(DefaultCategoryDataset dataset) {
        // Crear el gráfico
        JFreeChart barChart = ChartFactory.createBarChart(
                "YouTube Video Statistics",
                "Category",
                "Count",
                dataset);

        // Mostrar el gráfico en un JFrame
        ChartPanel chartPanel = new ChartPanel(barChart);
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setTitle("YouTube Data Visualization");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Inicializar dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Crear y mostrar el gráfico
        displayChart(dataset);

        // Bucle para actualizar datos cada 2 segundos
        while (true) {
            // Generar datos de prueba
            int testLikes = (int) (Math.random() * 500);
            int testDislikes = (int) (Math.random() * 500);
            int testComments = (int) (Math.random() * 500);

            // Actualizar dataset
            dataset.clear();
            dataset.addValue(testLikes, "Likes", "YouTube Data");
            dataset.addValue(testDislikes, "Dislikes", "YouTube Data");
            dataset.addValue(testComments, "Comments", "YouTube Data");

            // Esperar 2 segundos antes de la próxima actualización
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

