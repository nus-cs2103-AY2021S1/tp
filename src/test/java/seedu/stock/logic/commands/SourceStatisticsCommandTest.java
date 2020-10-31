package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

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

        String expectedMessage = SourceStatisticsCommand.MESSAGE_SUCCESS;

        Model expectedModel = model;

        assertCommandSuccess(sourceStatisticsCommand, model, expectedMessage, expectedModel);
    }


}
