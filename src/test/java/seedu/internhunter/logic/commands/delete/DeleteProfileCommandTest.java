package seedu.internhunter.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DELETED_ITEM;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showNoProfile;
import static seedu.internhunter.logic.commands.CommandTestUtil.showProfileItemAtIndex;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.INVALID_PROFILE_INDEX_MESSAGE;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteProfileCommand}.
 */
public class DeleteProfileCommandTest {
    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleProfileItemList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(), model.getUnfilteredProfileList(),
                new UserPrefs());
        model.setTabName(TabName.PROFILE);
        expectedModel.setTabName(TabName.PROFILE);
    }


    @Test
    public void execute_validIndexUnfilteredList_success() {
        ProfileItem profileItemToDelete = model.getFilteredProfileList().get(INDEX_FIRST.getZeroBased());
        DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETED_ITEM, PROFILE_NAME, profileItemToDelete);

        expectedModel.deleteProfileItem(profileItemToDelete);
        assertCommandSuccess(deleteProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProfileListSize() + 1);
        DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(outOfBoundIndex);

        assertCommandFailure(deleteProfileCommand, model, INVALID_PROFILE_INDEX_MESSAGE);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProfileItemAtIndex(model, INDEX_FIRST);

        ProfileItem profileItemToDelete = model.getProfileItemFromFilteredList(INDEX_FIRST.getZeroBased());
        DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETED_ITEM, PROFILE_NAME, profileItemToDelete);

        expectedModel.deleteProfileItem(profileItemToDelete);
        showNoProfile(expectedModel);

        assertCommandSuccess(deleteProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProfileItemAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of profile list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredProfileListSize());

        DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(outOfBoundIndex);

        assertCommandFailure(deleteProfileCommand, model, INVALID_PROFILE_INDEX_MESSAGE);
    }

    @Test
    public void equals_test_success() {
        DeleteProfileCommand deleteFirstCommand = new DeleteProfileCommand(INDEX_FIRST);
        DeleteProfileCommand deleteSecondCommand = new DeleteProfileCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteProfileCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different profile item -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
