package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.logic.commands.CommandTestUtil.showStockAtSerialNumber;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBookSortedSerialNumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStockBook(), new UserPrefs(), getTypicalSerialNumberSetsBook());
        expectedModel = new ModelManager(getTypicalStockBookSortedSerialNumber(), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStockAtSerialNumber(model, SERIAL_NUMBER_FIRST_STOCK);
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
