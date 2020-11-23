package seedu.address.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CLIENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.CARL;
import static seedu.address.testutil.TypicalClients.getTypicalHairStyleX;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.client.ClientNameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalClients;

public class FilterClientCommandTest {

    private Model model = new ModelManager(getTypicalHairStyleX(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());

    @Test
    public void equals() {
        ClientNameContainsKeywordsPredicate firstPredicate =
                new ClientNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ClientNameContainsKeywordsPredicate secondPredicate =
                new ClientNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FilterClientCommand filterFirstCommand = new FilterClientCommand(firstPredicate);
        FilterClientCommand filterSecondCommand = new FilterClientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterClientCommand filterFirstCommandCopy = new FilterClientCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noClientFound() {
        String expectedMessage = String.format(MESSAGE_CLIENT_LISTED_OVERVIEW, 0);
        ClientNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterClientCommand command = new FilterClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENT_LISTED_OVERVIEW, 3);
        ClientNameContainsKeywordsPredicate predicate = preparePredicate("alice benson kurz");
        FilterClientCommand command = new FilterClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ClientNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ClientNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
