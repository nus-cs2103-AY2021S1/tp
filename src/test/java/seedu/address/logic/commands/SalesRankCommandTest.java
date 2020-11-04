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

class SalesRankCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        SalesBook salesBook = new SalesBook();

        SalesRecordEntry entryOne = new SalesRecordEntry(Drink.BSBM, 50);
        SalesRecordEntry entryTwo = new SalesRecordEntry(Drink.BSBBT, 30);
        SalesRecordEntry entryThree = new SalesRecordEntry(Drink.BSBGT, 70);

        salesBook.addSalesRecordEntry(entryOne);
        salesBook.addSalesRecordEntry(entryTwo);
        salesBook.addSalesRecordEntry(entryThree);

        model = new ModelManager(new AddressBook(), salesBook,
                new IngredientBook(), new UserPrefs());

        SalesBook sortedSalesBook = new SalesBook();
        sortedSalesBook.addSalesRecordEntry(entryThree);
        sortedSalesBook.addSalesRecordEntry(entryOne);
        sortedSalesBook.addSalesRecordEntry(entryTwo);

        expectedModel = new ModelManager(model.getAddressBook(), sortedSalesBook,
                model.getIngredientBook(), new UserPrefs());

    }

    @Test
    void execute_rank_success() {
        String expectedMessage = SalesRankCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(new SalesRankCommand(), model, expectedMessage, expectedModel);
    }
}
