package seedu.internhunter.logic.commands.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showProfileItemAtIndex;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.INVALID_PROFILE_INDEX_MESSAGE;
import static seedu.internhunter.logic.commands.view.ViewCommand.MESSAGE_ALREADY_VIEWING;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

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
 * {@code ViewProfileCommand}.
 */
class ViewProfileCommandTest {

    private Model model;
    private Model expectedModel;

    private final String messageAlreadyViewing = String.format(MESSAGE_ALREADY_VIEWING, PROFILE_ITEM_NAME,
        INDEX_SECOND);

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleProfileItemList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleProfileItemList(),
                new UserPrefs());
        model.setTabName(TabName.PROFILE);
    }

    @Test
    public void execute_validAndNotCurrentIndexDifferentTabUnfilteredList_success() {
        ViewProfileCommand viewCommand = new ViewProfileCommand(INDEX_SECOND);

        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, PROFILE_ITEM_NAME, INDEX_SECOND);
        final CommandResult commandResultSuccess = new CommandResult(messageViewSuccess, false, false, false, true);

        expectedModel.setProfileViewIndex(INDEX_SECOND);
        expectedModel.setTabName(TabName.PROFILE); // changes model tab from the default company to application
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProfileListSize() + 1);
        ViewProfileCommand viewCommand = new ViewProfileCommand(outOfBoundIndex);
        assertCommandFailure(viewCommand, model, INVALID_PROFILE_INDEX_MESSAGE);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // Start from any tab besides the Profile tab
        model.setTabName(TabName.COMPANY);
        expectedModel.setTabName(TabName.COMPANY);

        showProfileItemAtIndex(model, INDEX_FIRST);
        ViewProfileCommand viewCommand = new ViewProfileCommand(INDEX_FIRST);

        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, PROFILE_ITEM_NAME, INDEX_FIRST);

        CommandResult commandResultSuccess = new CommandResult(messageViewSuccess, false, false, true, true);

        expectedModel.setProfileViewIndex(INDEX_FIRST);
        expectedModel.setTabName(TabName.PROFILE);
        showProfileItemAtIndex(expectedModel, INDEX_FIRST);
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProfileItemAtIndex(model, INDEX_SECOND);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of profile list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredProfileListSize());

        ViewProfileCommand viewCommand = new ViewProfileCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, INVALID_PROFILE_INDEX_MESSAGE);
    }

    @Test
    public void execute_validAndCurrentIndexAndCurrentTab_successAlreadyViewing() {
        model.setProfileViewIndex(INDEX_SECOND);
        ViewProfileCommand viewCommand = new ViewProfileCommand(INDEX_SECOND);

        final CommandResult commandResultViewing = new CommandResult(messageAlreadyViewing, false, false, false, false);

        expectedModel.setProfileViewIndex(INDEX_SECOND);
        expectedModel.setTabName(TabName.PROFILE);
        assertCommandSuccess(viewCommand, model, commandResultViewing, expectedModel);
    }

    @Test
    public void execute_currentTabAndDifferentIndex_success() {
        ViewProfileCommand viewCommand = new ViewProfileCommand(INDEX_SECOND);
        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, PROFILE_ITEM_NAME, INDEX_SECOND);
        final CommandResult commandResultSuccess = new CommandResult(messageViewSuccess);

        expectedModel.setProfileViewIndex(INDEX_SECOND);
        expectedModel.setTabName(TabName.PROFILE);
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void equals_test_success() {
        ViewProfileCommand viewFirstCommand = new ViewProfileCommand(INDEX_FIRST);
        ViewProfileCommand viewSecondCommand = new ViewProfileCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(viewFirstCommand, viewFirstCommand);

        // same values -> returns true
        ViewProfileCommand viewFirstCommandCopy = new ViewProfileCommand(INDEX_FIRST);
        assertEquals(viewFirstCommandCopy, viewFirstCommand);

        // different types -> returns false
        assertNotEquals(viewFirstCommand, new ViewProfileCommand(INDEX_SECOND));

        // null -> returns false
        assertNotEquals(viewFirstCommand, null);

        // different index -> returns false
        assertNotEquals(viewSecondCommand, viewFirstCommand);
    }
}
