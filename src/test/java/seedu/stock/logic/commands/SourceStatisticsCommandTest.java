package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;

public class SourceStatisticsCommandTest {

    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(),
            getTypicalSerialNumberSetsBook());

    @Test
    public void executeValidSourceStatisticsCommand_success() {
        SourceStatisticsCommand sourceStatisticsCommand = new SourceStatisticsCommand();

        //expected data
        Map<String, Integer> statisticsData = new HashMap<>();
        statisticsData.put("cold storage", 1);
        statisticsData.put("ntuc", 1);
        statisticsData.put("fairprice", 1);
        statisticsData.put("giant", 1);

        String[] otherStatisticsData = new String[]{"source"};

        CommandResult expectedResult = new CommandResult(SourceStatisticsCommand.MESSAGE_SUCCESS,
                statisticsData, false, false, null, true,
                otherStatisticsData, false, false);

        Model expectedModel = model;

        assertCommandSuccess(sourceStatisticsCommand, model, expectedResult, expectedModel);
    }


}
