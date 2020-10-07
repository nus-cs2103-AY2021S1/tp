package seedu.stock.logic.commands;

import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStocksSerialNumbers;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class UpdateCommandTest {

    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), getTypicalStocksSerialNumbers());
}
