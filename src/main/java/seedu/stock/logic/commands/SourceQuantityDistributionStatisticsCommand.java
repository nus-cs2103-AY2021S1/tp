package seedu.stock.logic.commands;

import static seedu.stock.commons.core.Messages.MESSAGE_SOURCE_COMPANY_NOT_FOUND;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

public class SourceQuantityDistributionStatisticsCommand extends StatisticsCommand {

    public static final String STATISTICS_TYPE = "source-qd-";
    public static final String MESSAGE_SUCCESS = "Opened statistics for source-qd window\n"
            + "WARNING: Diagram shown may not be optimal for certain quantities";

    private final String targetSource;

    public SourceQuantityDistributionStatisticsCommand(String targetSource) {
        this.targetSource = targetSource;
    }

    @Override
    public CommandResult execute(Model model) throws SourceCompanyNotFoundException {
        ObservableList<Stock> stockBookList = model.getStockBook().getStockList();
        Map<String, Integer> nameQuantityTable = new HashMap<>();
        stockBookList.forEach(stock -> {
            String sourceString = stock.getSource().value;
            if (sourceString.equals(targetSource)) {
                nameQuantityTable.put(stock.getName().fullName, Integer.parseInt(stock.getQuantity().quantity));
            }
        });

        if (nameQuantityTable.size() == 0) {
            throw new SourceCompanyNotFoundException(MESSAGE_SOURCE_COMPANY_NOT_FOUND);
        }

        //array of size 2, index 0 is statistics type, index 1 is target source company
        String[] otherStatisticsDetails = {"source-qd-", targetSource};
        return new CommandResult(MESSAGE_SUCCESS, nameQuantityTable, false, true, otherStatisticsDetails, false);
    }


    public String getTargetSource() {
        return targetSource;
    }
}
