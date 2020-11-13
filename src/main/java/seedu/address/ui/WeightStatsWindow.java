package seedu.address.ui;

import java.util.Arrays;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.day.Day;

/**
 * The controller for the weight statistics window.
 */
public class WeightStatsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(WeightStatsWindow.class);
    private static final String FXML = "WeightStatsWindow.fxml";

    private ObservableList<Day> dayList;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private ObservableList<String> dates = FXCollections.observableArrayList();

    /**
     * Creates a new WeightStatsWindow.
     *
     * @param root Stage to use as the root of the WeightStatsWindow.
     */
    public WeightStatsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new WeightStatsWindow.
     *
     * @param dayList dayList from seedu.address.logic.Logic
     */
    public WeightStatsWindow(ObservableList<Day> dayList) {
        this(new Stage());

        assert dayList != null;

        this.dayList = dayList;

        lineChart.setTitle("Daily Weight Statistics");
        initialize(dayList);
        setWeightData(dayList);

        //ListChangeListener to check for any changes to the dayList and updates the line chart accordingly
        dayList.addListener((ListChangeListener<Day>) (c -> updateChart()));
    }

    /**
     * Initializes the X-Axis (dates from dayList) of Line Chart.
     *
     * @param dayList dayList from seedu.address.logic.Logic
     */
    @FXML
    private void initialize(ObservableList<Day> dayList) {
        assert dayList != null;

        String[] datesString = dayList.stream().map(x -> x.getDate().get().toString())
                .toArray(String[]::new);

        dates.addAll(Arrays.asList(datesString));

        //Assign the dates as categories and label to x-Axis.
        xAxis.setCategories(dates);
        xAxis.setLabel("Dates");

        //Assign label to y-Axis
        yAxis.setLabel("Weight (kg)");
    }

    /**
     * Sets the weight data points to show the statistics for.
     *
     * @param dayList dayList from seedu.address.logic.Logic
     */
    public void setWeightData(ObservableList<Day> dayList) {
        assert dayList != null;

        XYChart.Series<String, Integer> weightsSeries = new XYChart.Series<>();
        weightsSeries.setName("Weight");

        //Get the weight data from all the days in dayList.
        for (int i = 0; i < dayList.size(); i++) {
            XYChart.Data<String, Integer> weightData = new XYChart.Data<>(dates.get(i),
                    Integer.parseInt(dayList.get(i).getWeight().value));
            weightsSeries.getData().add(weightData);

            //set the data nodes to allow display of values when the mouse hovers over it
            weightData.setNode(new HoveredWeightNode(
                    Integer.parseInt(dayList.get((i == 0) ? 0 : i - 1).getWeight().value),
                    Integer.parseInt(dayList.get(i).getWeight().value)));
        }

        lineChart.getData().add(weightsSeries);
    }

    /**
     * Updates the line chart when there is a change in dayList.
     */
    private void updateChart() {
        //clear all data points and xAxis
        lineChart.getData().clear();
        assert lineChart.getData().isEmpty();

        dates.clear();
        assert dates.isEmpty();

        //re-initialize the xAxis
        initialize(dayList);
        xAxis.invalidateRange(dates);

        //set the new data points
        setWeightData(dayList);
    }

    /**
     * Shows the weight statistics window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing weight statistics.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


}
