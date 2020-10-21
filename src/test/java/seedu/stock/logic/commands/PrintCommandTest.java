package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.TypicalStocks.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code PrintCommand}.
 */
public class PrintCommandTest {
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), getTypicalSerialNumberSetsBook());

//    @Test
//    public void execute_stockAcceptedByModel_addSuccessful() throws CommandException {
//        CommandResult commandResult = new PrintCommand().execute(model);
//
//        assertEquals(PrintCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
//    }

    @Test
    public void equals() {
        PrintCommand printCommand = new PrintCommand();

        // same object -> returns true
        assertTrue(printCommand.equals(printCommand));

        // same values -> returns true
        PrintCommand printCommandCopy = new PrintCommand();
        assertTrue(printCommand.equals(printCommandCopy));

        // different types -> returns false
        assertFalse(printCommand.equals(1));

    }
}
