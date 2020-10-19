package seedu.stock.logic.commands;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

public class SourceStatisticsCommand extends StatisticsCommand {

    public static final String STATISTICS_TYPE = "source";
    private static final String MESSAGE_SUCCESS = "Opened statistics for source window\n"
                    + "WARNING: Diagram shown may not be optimal for certain quantities";

    @Override
    public CommandResult execute(Model model) {
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
        return new CommandResult(MESSAGE_SUCCESS, sourceData, false, true, "source", false);
    }
}
