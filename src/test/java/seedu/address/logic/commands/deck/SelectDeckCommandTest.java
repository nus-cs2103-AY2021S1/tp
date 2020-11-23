
package seedu.address.logic.commands.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.deck.TypicalDecks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class SelectDeckCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deckSelectedByModel_selectSuccessful() throws Exception {
        SelectDeckCommand selectDeckCommand = new SelectDeckCommand(Index.fromZeroBased(1));
        CommandResult commandResult = selectDeckCommand.execute(model);
        assertEquals(String.format(SelectDeckCommand.MESSAGE_SELECT_DECK_SUCCESS, model.getCurrentDeck()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_selectFail() {
        SelectDeckCommand selectDeckCommand = new SelectDeckCommand(Index.fromZeroBased(5));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX, () ->
                selectDeckCommand.execute(model));
    }
}
