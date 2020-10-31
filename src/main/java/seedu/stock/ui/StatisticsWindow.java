package seedu.stock.ui;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.SourceQuantityDistributionStatisticsCommand;
import seedu.stock.logic.commands.SourceStatisticsCommand;

/**
 * Controller for a SourceStatistics page
 */
public class StatisticsWindow extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);
    private static final String FXML = "StatisticsWindow.fxml";

    @FXML
    private PieChart statisticsPieChart;

    /**
     * Default constructor for Statistics Window tab.
     */
    public StatisticsWindow() {
        super(FXML);
        statisticsPieChart.setTitle("No statistics shown");
    }

    /**
     * Updates the statistics data to be shown.
     *
     * @param statisticsData The statistics data to display.
     * @param otherStatisticsDetails The other statistics data that is needed.
     */
    public void updateData(Map<String, Integer> statisticsData, String[] otherStatisticsDetails) {
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
     * Data update for pie chart for SourceQuantityDistributionStatistics.
     *
     * @param statisticsData The data to be used.
     * @param targetSource The target source company.
     */
    public void updateDataForSourceQuantityDistributionStatistics(
                        Map<String, Integer> statisticsData, String targetSource) {

        logger.log(Level.INFO, "Source quantity distribution statistics called");

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
     * Data update for pie chart for SourceStatistics.
     *
     * @param statisticsData The data to be used.
     */
    public void updateDataForSourceStatistics(Map<String, Integer> statisticsData) {
        statisticsPieChart.setTitle("Source statistics");

        logger.log(Level.INFO, "Source statistics called");

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

}
