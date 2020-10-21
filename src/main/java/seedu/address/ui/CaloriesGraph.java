package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Region;

public class CaloriesGraph extends UiPart<Region> {
    private static final String FXML = "CaloriesGraph.fxml";

    @FXML
    private LineChart<String, Number> graph;

    /**
     * Creates a {@code CaloriesGraph} with the given {@code HashMap}.
     */
    public CaloriesGraph() {
        super(FXML);
        graph = generateGraph();
    }

    private static LineChart<String, Number> generateGraph() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        return lineChart;
    }
}
