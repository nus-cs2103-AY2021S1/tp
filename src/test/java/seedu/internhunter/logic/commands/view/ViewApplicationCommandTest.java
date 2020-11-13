package seedu.internhunter.logic.commands.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INVALID_APPLICATION_INDEX_MESSAGE;
import static seedu.internhunter.logic.commands.view.ViewCommand.MESSAGE_ALREADY_VIEWING;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewApplicationCommand}.
 */
public class ViewApplicationCommandTest {

    private Model model;
    private Model expectedModel;

    private final String messageAlreadyViewing = String.format(MESSAGE_ALREADY_VIEWING, APPLICATION_NAME, INDEX_SECOND);

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), getSampleApplicationItemList(), new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), model.getUnfilteredApplicationList(), new ItemList<>(),
                new UserPrefs());
    }

    @Test
    public void execute_validAndNotCurrentIndexDifferentTabUnfilteredList_success() {
        ViewApplicationCommand viewCommand = new ViewApplicationCommand(INDEX_SECOND);

        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, APPLICATION_NAME, INDEX_SECOND);
        final CommandResult commandResultSuccess = new CommandResult(messageViewSuccess, false, false, true, true);

        expectedModel.setApplicationViewIndex(INDEX_SECOND);
        expectedModel.setTabName(TabName.APPLICATION); // changes model tab from the default company to application
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationListSize() + 1);
        ViewApplicationCommand viewCommand = new ViewApplicationCommand(outOfBoundIndex);
        assertCommandFailure(viewCommand, model, INVALID_APPLICATION_INDEX_MESSAGE);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST);
        ViewApplicationCommand viewCommand = new ViewApplicationCommand(INDEX_FIRST);

        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, APPLICATION_NAME, INDEX_FIRST);

        CommandResult commandResultSuccess = new CommandResult(messageViewSuccess, false, false, true, true);

        expectedModel.setApplicationViewIndex(INDEX_FIRST);
        expectedModel.setTabName(TabName.APPLICATION); // changes model tab from the default company to application
        showApplicationAtIndex(expectedModel, INDEX_FIRST);
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_SECOND);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of application list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredApplicationListSize());

        ViewApplicationCommand viewCommand = new ViewApplicationCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, INVALID_APPLICATION_INDEX_MESSAGE);
    }

    @Test
    public void execute_validAndCurrentIndexAndCurrentTab_successAlreadyViewing() {
        model.setTabName(TabName.APPLICATION);
        model.setApplicationViewIndex(INDEX_SECOND);
        ViewApplicationCommand viewCommand = new ViewApplicationCommand(INDEX_SECOND);

        final CommandResult commandResultViewing = new CommandResult(messageAlreadyViewing, false, false, false, false);

        expectedModel.setApplicationViewIndex(INDEX_SECOND);
        expectedModel.setTabName(TabName.APPLICATION);
        assertCommandSuccess(viewCommand, model, commandResultViewing, expectedModel);
    }

    @Test
    public void execute_currentTabAndDifferentIndex_success() {
        model.setTabName(TabName.APPLICATION);

        ViewApplicationCommand viewCommand = new ViewApplicationCommand(INDEX_SECOND);
        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, APPLICATION_NAME, INDEX_SECOND);
        final CommandResult commandResultSuccess = new CommandResult(messageViewSuccess);

        expectedModel.setApplicationViewIndex(INDEX_SECOND);
        expectedModel.setTabName(TabName.APPLICATION);
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void equals_test_success() {
        ViewApplicationCommand viewFirstCommand = new ViewApplicationCommand(INDEX_FIRST);
        ViewApplicationCommand viewSecondCommand = new ViewApplicationCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(viewFirstCommand, viewFirstCommand);

        // same values -> returns true
        ViewApplicationCommand viewFirstCommandCopy = new ViewApplicationCommand(INDEX_FIRST);
        assertEquals(viewFirstCommandCopy, viewFirstCommand);

        // different types -> returns false
        assertNotEquals(viewFirstCommand, new ViewProfileCommand(INDEX_SECOND));

        // null -> returns false
        assertNotEquals(viewFirstCommand, null);

        // different index -> returns false
        assertNotEquals(viewSecondCommand, viewFirstCommand);
    }

}
