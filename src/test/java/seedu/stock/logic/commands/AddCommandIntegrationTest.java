package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Stock validStock = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getStockBook(), new UserPrefs());
        expectedModel.addStock(validStock);

        assertCommandSuccess(new AddCommand(validStock), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStock), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Stock stockInList = model.getStockBook().getStockList().get(0);
        assertCommandFailure(new AddCommand(stockInList), model, AddCommand.MESSAGE_DUPLICATE_STOCK);
    }

}
