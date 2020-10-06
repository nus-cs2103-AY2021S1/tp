package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

import org.junit.jupiter.api.BeforeEach;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventoryBook(), new UserPrefs());
    }

    //    @Test
    //    public void execute_newItem_success() {
    //        Item validItem = new ItemBuilder().build();
    //
    //        Model expectedModel = new ModelManager(model.getInventoryBook(), new UserPrefs());
    //        expectedModel.addItem(validItem);
    //
    //        assertCommandSuccess(new AddCommand(validItem), model,
    //                String.format(AddCommand.MESSAGE_SUCCESS, validItem), expectedModel);
    //    }
}
