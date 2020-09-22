package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.stock.Stock;
import seedu.address.testutil.PersonBuilder;

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
