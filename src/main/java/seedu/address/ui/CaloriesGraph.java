package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class CaloriesGraph extends UiPart<Region> {
    private static final String FXML = "CaloriesGraph.fxml";

    @FXML
    private Pane pane;

    /**
     * Creates a {@code CaloriesGraph} with the given {@code HashMap}.
     */
    public CaloriesGraph() {
        super(FXML);
        fillInfo();
    }

    private void fillInfo() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        Series<Number, Number> series = new Series<Number, Number>();
        series.setName("No of schools in an year");
        series.getData().add(new XYChart.Data(1970, 15));
        series.getData().add(new XYChart.Data(1980, 30));
        series.getData().add(new XYChart.Data(1990, 60));
        series.getData().add(new XYChart.Data(2000, 120));
        series.getData().add(new XYChart.Data(2013, 240));
        series.getData().add(new XYChart.Data(2014, 300));
        lineChart.getData().add(series);
        pane.getChildren().add(lineChart);
    }
}
