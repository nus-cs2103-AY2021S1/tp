package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Drink;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SalesBook;
import seedu.address.model.SalesRecordEntry;
import seedu.address.model.UserPrefs;

public class SalesListCommandTest {

    private Model model;
    private Model expectedModel;

    private SalesRecordEntry entryOne = new SalesRecordEntry(Drink.BSBM, 50);
    private SalesRecordEntry entryTwo = new SalesRecordEntry(Drink.BSBBT, 30);
    private SalesRecordEntry entryThree = new SalesRecordEntry(Drink.BSBGT, 70);

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new SalesBook(),
                new IngredientBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new SalesBook(),
                model.getIngredientBook(), new UserPrefs());

    }

    @Test
    public void execute_emptySalesBook_displaysNoItemPresentMessage() {
        assertCommandSuccess(new SalesListCommand(), model, SalesListCommand.MESSAGE_NO_RECORD_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySalesBook_displayInSortedOrderSuccessMessage() {
        model.addSalesRecordEntry(entryOne);
        model.addSalesRecordEntry(entryTwo);
        model.addSalesRecordEntry(entryThree);

        String expectedMessage = SalesListCommand.MESSAGE_SUCCESS;
        expectedModel.addSalesRecordEntry(entryThree);
        expectedModel.addSalesRecordEntry(entryOne);
        expectedModel.addSalesRecordEntry(entryTwo);

        assertCommandSuccess(new SalesListCommand(), model, expectedMessage, expectedModel);
    }
}
