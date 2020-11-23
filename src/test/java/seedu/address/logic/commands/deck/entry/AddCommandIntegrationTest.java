package seedu.address.logic.commands.deck.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.deck.TypicalDecks.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.entry.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.entry.Entry;
import seedu.address.testutil.entry.EntryBuilder;


/**
 * Tests for add command. Contains integration tests with model and deck.
 */
public class AddCommandIntegrationTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noDeckSelected_throwsCommandException() {
        Entry validEntry = new EntryBuilder().build();
        AddCommand addCommand = new AddCommand(validEntry);
        assertThrows(CommandException.class, Messages.MESSAGE_NO_DECK_SELECTED, () -> addCommand.execute(model));
    }

    @Test
    public void execute_entryAcceptedByRealModel_addSuccessful() throws Exception {
        Entry validEntry = new EntryBuilder().build();
        model.selectDeck(INDEX_FIRST);

        CommandResult commandResult = new AddCommand(validEntry).execute(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEntry), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEntry), model.getFilteredEntryList());
    }
}
