package seedu.expense.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Statistical calculation of expense data to construct charts/graphs.
 */
public interface ChartDataCollector {

    /**
     * Returns an ObservableList that contains data required to contruct the chart.
     *
     * @return List of data.
     */
    ObservableList<PieChart.Data> retrieveData();

    /**
     * Returns a String representation of chart data.
     *
     * @return Chart data as String.
     */
    String getDataAsString();
}
