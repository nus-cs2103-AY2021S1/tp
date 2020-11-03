package seedu.stock.logic.commands.statisticsutil;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

public class GenerateStatisticsData {
    /**
     * Generates data for source statistics.
     * @param model The model used.
     * @return The Map containing the data.
     */
    public static Map<String, Integer> generateSourceStatisticsData(Model model) {
        ObservableList<Stock> stockBookList = model.getStockBook().getStockList();
        Map<String, Integer> sourceData = new HashMap<>();
        stockBookList.forEach(stock -> {
            String sourceString = stock.getSource().value;
            if (!sourceData.containsKey(sourceString)) {
                sourceData.put(sourceString, 1);
            } else {
                int sourceCount = sourceData.get(sourceString);
                sourceData.put(sourceString, sourceCount + 1);
            }
        });
        return sourceData;
    }

    /**
     * Generates data for source quantity distribution statistics.
     * @param model The model used.
     * @return The Map containing the data.
     */
    public static Map<String, Integer> generateSourceQuantityDistributionStatisticsData(
            Model model, String targetSource) {
        ObservableList<Stock> stockBookList = model.getStockBook().getStockList();
        Map<String, Integer> nameQuantityTable = new HashMap<>();
        stockBookList.forEach(stock -> {
            String sourceString = stock.getSource().value;
            if (sourceString.equals(targetSource)) {
                nameQuantityTable.put(stock.getName().fullName, Integer.parseInt(stock.getQuantity().quantity));
            }
        });

        return nameQuantityTable;
    }
}
