package seedu.stock.ui;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.stock.commons.core.LogsCenter;

/**
 * Controller for a SourceStatistics page
 */
public class SourceStatisticsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(SourceStatisticsWindow.class);
    private static final String FXML = "SourceStatisticsWindow.fxml";

    @FXML
    private PieChart sourceStatisticsPieChart;

    @FXML
    private AnchorPane anchorPane;

    /**
     * Creates a new source statistics window.
     */
    public SourceStatisticsWindow() {
        this(new Stage());
    }

    /**
     * Creates a new source statistics window.
     *
     * @param root Stage to use as the root of the source statistics window.
     */
    public SourceStatisticsWindow(Stage root) {
        super(FXML, root);

        sourceStatisticsPieChart.setTitle("Source statistics");
        sourceStatisticsPieChart.setLegendVisible(false);
    }

    /**
     * Shows the source statistics window.
     *
     * @param statisticsData
     * @throws IllegalStateException <ul>
     * <li>
     * if this method is called on a thread other than the JavaFX Application Thread.
     * </li>
     * <li>
     * if this method is called during animation or layout processing.
     * </li>
     * <li>
     * if this method is called on the primary stage.
     * </li>
     * <li>
     * if {@code dialogStage} is already showing.
     * </li>
     * </ul>
     */
    public void show(Map<String, Integer> statisticsData) {
        logger.fine("Showing statistics window about the application.");
        refreshData(statisticsData);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Refreshes the statistics data to be shown.
     *
     * @param statisticsData The statistics data to display.
     */
    public void refreshData(Map<String, Integer> statisticsData) {
        //erases all existing data
        sourceStatisticsPieChart.getData().clear();

        Set<Map.Entry<String, Integer>> entrySet = statisticsData.entrySet();
        int totalStocks = statisticsData.entrySet().stream().map(set -> set.getValue())
                .reduce(0, (x, y) -> x + y);

        //updates with new data
        entrySet.forEach(set -> {
            double percentage = 100 * ((double) set.getValue() / totalStocks);
            String name = String.format("%s, %.1f", set.getKey(), percentage) + "%";
            int value = set.getValue();
            sourceStatisticsPieChart.getData().add(new PieChart.Data(name, value));
        });
        sourceStatisticsPieChart.layout();
    }

    /**
     * Returns true if the source statistics window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the source statistics window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the source statistics window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
