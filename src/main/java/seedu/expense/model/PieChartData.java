package seedu.expense.model;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Association class that collects necessary data from {@code ReadOnlyExpenseBook}
 * in order for {@code GraphicalDisplayWindow} to initialise charts.
 */

public class PieChartData implements ChartDataCollector {

    private final ReadOnlyExpenseBook expenseBook;

    /**
     * Creates instance of PieChartData using {@code ReadOnlyExpenseBook}
     * @param expenseBook ReadOnlyExpenseBook that contains stored data.
     */
    public PieChartData(ReadOnlyExpenseBook expenseBook) {
        this.expenseBook = expenseBook;
    }

    @Override
    public ObservableList<PieChart.Data> retrieveData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        getDataAsMap().entrySet().stream().filter(entry -> entry.getValue() > 0).map(entry ->
                new PieChart.Data(entry.getKey() + " [$" + entry.getValue() + "]",
                        entry.getValue())).forEach(pieChartData::add);
        return pieChartData;
    }

    /**
     * Collects necessary data in a HashMap.
     *
     * @return Map containing {@code Tag.toString()} as keys and Double as values.
     */
    private HashMap<String, Double> getDataAsMap() {
        HashMap<String, Double> map = new HashMap<>();
        map.put("Default", 0.0);
        expenseBook.getTags().forEach(tag -> map.put(tag.toString(), 0.0));
        // map must now contain all existing tags. Assertion here?
        expenseBook.getExpenseList().forEach(expense ->
                map.put(expense.getTag().toString(), map.get(expense.getTag().toString())
                        + expense.getAmount().getDollarAsDoubleValue()));
        return map;
    }

    @Override
    public String getDataAsString() {
        StringBuilder sb = new StringBuilder();
        getDataAsMap().entrySet().stream().map(entry -> "[" + entry.getKey() + " : $"
                + entry.getValue() + "] ").forEach(sb::append);
        return sb.toString();
    }
}
