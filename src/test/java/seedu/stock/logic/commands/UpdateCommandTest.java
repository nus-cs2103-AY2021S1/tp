package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStocksSerialNumbers;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.UpdateCommand.UpdateStockDescriptor;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.StockBuilder;
import seedu.stock.testutil.UpdateStockDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class UpdateCommandTest {

    private SerialNumberSetsBook serialNumbers = getTypicalStocksSerialNumbers();
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), serialNumbers);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Stock updatedStock = new StockBuilder().build();
        UpdateStockDescriptor descriptor = new UpdateStockDescriptorBuilder(updatedStock).build();
        UpdateCommand updateCommand = new UpdateCommand(descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_STOCK_SUCCESS, "\n" + updatedStock);

        Model expectedModel = new ModelManager(new StockBook(model.getStockBook()), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));
        expectedModel.setStock(model.getFilteredStockList().get(1), updatedStock);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }
}
