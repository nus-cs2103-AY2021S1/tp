package seedu.homerce.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.client.TypicalClients.CARL;
import static seedu.homerce.testutil.client.TypicalClients.ELLE;
import static seedu.homerce.testutil.client.TypicalClients.FIONA;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.client.predicate.ClientNamePredicate;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindClientCommandTest {
    private Model model = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void equals() {
        ClientNamePredicate firstPredicate =
                new ClientNamePredicate(Collections.singletonList("first"));
        ClientNamePredicate secondPredicate =
                new ClientNamePredicate(Collections.singletonList("second"));

        FindClientCommand findFirstCommand = new FindClientCommand(firstPredicate);
        FindClientCommand findSecondCommand = new FindClientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindClientCommand findFirstCommandCopy = new FindClientCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noClientFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 0);
        ClientNamePredicate predicate = preparePredicate(" ");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3);
        ClientNamePredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code ClientNamePredicate}.
     */
    private ClientNamePredicate preparePredicate(String userInput) {
        return new ClientNamePredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
