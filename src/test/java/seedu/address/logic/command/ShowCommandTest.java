package seedu.address.logic.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.logic.parser.ShowCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ShowCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(1);
    }

    @AfterEach
    public void tearDown() {
        model = null;
    }

    @Test
    public void execute_showActivity_success() {
        try {
            ShowCommand expectedCommand = new ShowCommand("activity");
            Assertions.assertTrue(expectedCommand.equals(new ShowCommandParser().parse(" -activity")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }

    @Test
    public void execute_showAccommodation_success() {
        try {
            ShowCommand expectedCommand = new ShowCommand("accommodation");
            Assertions.assertTrue(expectedCommand.equals(new ShowCommandParser().parse(" -accommodation")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }

    @Test
    public void execute_showFriend_success() {
        try {
            ShowCommand expectedCommand = new ShowCommand("friend");
            Assertions.assertTrue(expectedCommand.equals(new ShowCommandParser().parse(" -friend")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }

    @Test
    public void execute_show_failure() {
        try {
            ShowCommand expectedCommand = new ShowCommand("friend");
            Assertions.assertFalse(expectedCommand.equals(new ShowCommandParser().parse(" -friends")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }

    @Test
    public void execute_showActivityCommand_success() {
        ShowCommand showCommand = new ShowCommand("activity");

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_SUCCESS,
                "activity");

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        expectedModel.setDirectory(1);
        model.updateFilteredActivityList(Model.PREDICATE_SHOW_ALL);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel, 0);
    }

    @Test
    public void execute_showAccommodationCommand_success() {
        ShowCommand showCommand = new ShowCommand("accommodation");

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_SUCCESS,
                "accommodation");

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        expectedModel.setDirectory(1);
        model.updateFilteredAccommodationList(Model.PREDICATE_SHOW_ALL);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel, 1);
    }

    @Test
    public void execute_showFriendCommand_success() {
        ShowCommand showCommand = new ShowCommand("friend");

        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_SUCCESS,
                "friend");

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        expectedModel.setDirectory(1);
        model.updateFilteredFriendList(Model.PREDICATE_SHOW_ALL);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel, 2);
    }

    @Test
    public void execute_showFriendCommand_failure() throws CommandException {
        ShowCommand showCommand = new ShowCommand("frienda");

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        expectedModel.setDirectory(1);
        model.updateFilteredFriendList(Model.PREDICATE_SHOW_ALL);

        assertCommandFailure(showCommand, expectedModel,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        ShowCommand showFirstCommand = new ShowCommand("activity");
        ShowCommand showSecondCommand = new ShowCommand("friend");

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand("activity");
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals("123"));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different showCommand -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
}
