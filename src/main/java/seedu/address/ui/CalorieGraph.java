package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.calorie.DailyCalorie;


public class CalorieGraph extends UiPart<Region> {

    private static final String FXML = "CalorieGraph.fxml";

    private final Logger logger = LogsCenter.getLogger(CalorieGraph.class);

    private ObservableList<DailyCalorie> calorieLog;

    private final XYChart.Series<String, Number> series;


    // Independent Ui parts residing in this Ui container

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private LineChart<String, Number> graph;


    /** Constructs CalorieGraph
     *
     * @param calorieLog
     */
    public CalorieGraph(ObservableList<DailyCalorie> calorieLog) {
        super(FXML);

        this.calorieLog = calorieLog;
        series = new XYChart.Series<String, Number>();
        xAxis.setLabel("Day");
        yAxis.setLabel("Daily Calories");
        graph.setTitle("Calorie Graph");
        graph.setLegendVisible(false);
        graph.getData().add(series);


        update();

        calorieLog.addListener((ListChangeListener<DailyCalorie>) change -> {
            update();
        });
    }

    private void update() {
        series.getData().clear();
        if (calorieLog.size() != 0) {
            for (DailyCalorie entry : calorieLog) {
                series.getData().add(
                        new XYChart.Data<>(entry.getDate().getDayOfWeek().toString(), entry.getCalories())
                );
            }
            if (!graph.getData().contains(series)) {
                graph.getData().add(series);
            }
        } else {
            graph.getData().remove(series);
        }
    }
}
