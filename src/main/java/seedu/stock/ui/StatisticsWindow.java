package seedu.stock.ui;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.SourceQuantityDistributionStatisticsCommand;
import seedu.stock.logic.commands.SourceStatisticsCommand;

/**
 * Controller for a SourceStatistics page
 */
public class StatisticsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);
    private static final String FXML = "StatisticsWindow.fxml";

    @FXML
    private PieChart statisticsPieChart;

    @FXML
    private AnchorPane anchorPane;

    /**
     * Creates a new source statistics window.
     */
    public StatisticsWindow() {
        this(new Stage());
    }

    /**
     * Creates a new source statistics window.
     *
     * @param root Stage to use as the root of the source statistics window.
     */
    public StatisticsWindow(Stage root) {
        super(FXML, root);

        statisticsPieChart.setLegendVisible(false);
    }

    /**
     * Shows the source statistics window.
     *
     * @param statisticsData The data to be used.
     * @param otherStatisticsDetails The other statistics data that is needed.
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
    public void show(Map<String, Integer> statisticsData, String[] otherStatisticsDetails) {
        logger.fine("Showing statistics window about the application.");
        refreshData(statisticsData, otherStatisticsDetails);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Refreshes the statistics data to be shown.
     *
     * @param statisticsData The statistics data to display.
     * @param otherStatisticsDetails The other statistics data that is needed.
     */
    public void refreshData(Map<String, Integer> statisticsData, String[] otherStatisticsDetails) {
        //erases all existing data
        assert otherStatisticsDetails.length > 0;

        statisticsPieChart.getData().clear();
        String type = otherStatisticsDetails[0];

        switch (type) {
        case SourceStatisticsCommand.STATISTICS_TYPE:
            updateDataForSourceStatistics(statisticsData);
            break;

        case SourceQuantityDistributionStatisticsCommand.STATISTICS_TYPE:
            assert otherStatisticsDetails.length > 1;
            String targetSource = otherStatisticsDetails[1];
            updateDataForSourceQuantityDistributionStatistics(statisticsData, targetSource);
            break;

        default:
            break;
        }
    }

    /**
     * Data update for piechart for SourceQuantityDistributionStatistics.
     *
     * @param statisticsData The data to be used.
     * @param targetSource The target source company.
     */
    public void updateDataForSourceQuantityDistributionStatistics(
                        Map<String, Integer> statisticsData, String targetSource) {

        statisticsPieChart.setTitle("Distribution for " + targetSource + " company");

        Set<Map.Entry<String, Integer>> entrySet = statisticsData.entrySet();
        int totalStocks = statisticsData.entrySet().stream().map(set -> set.getValue())
                .reduce(0, (x, y) -> x + y);

        //updates with new data
        entrySet.forEach(set -> {
            double percentage = 100 * ((double) set.getValue() / totalStocks);
            String name = String.format("%s, %.1f", set.getKey(), percentage) + "%";
            int value = set.getValue();
            statisticsPieChart.getData().add(new PieChart.Data(name, value));
        }
        );
        statisticsPieChart.layout();
    }

    /**
     * Data update for piechart for SourceStatistics.
     *
     * @param statisticsData The data to be used.
     */
    public void updateDataForSourceStatistics(Map<String, Integer> statisticsData) {
        statisticsPieChart.setTitle("Source statistics");
        Set<Map.Entry<String, Integer>> entrySet = statisticsData.entrySet();
        int totalStocks = statisticsData.entrySet().stream().map(set -> set.getValue())
                .reduce(0, (x, y) -> x + y);

        //updates with new data
        entrySet.forEach(set -> {
            double percentage = 100 * ((double) set.getValue() / totalStocks);
            String name = String.format("%s, %.1f", set.getKey(), percentage) + "%";
            int value = set.getValue();
            statisticsPieChart.getData().add(new PieChart.Data(name, value));
        });
        statisticsPieChart.layout();
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
