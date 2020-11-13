package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showProfileItemAtIndex;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.INVALID_PROFILE_INDEX_MESSAGE;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_SKILL;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_INTERNSHIP;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GOVTECH_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.ExitCommand;
import seedu.internhunter.logic.commands.edit.EditProfileCommand.EditProfileItemDescriptor;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.commands.util.profile.EditProfileItemDescriptorBuilder;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;
import seedu.internhunter.ui.tabs.TabName;

public class EditProfileCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleProfileItemList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(), model.getUnfilteredProfileList(),
                new UserPrefs());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        ProfileItem editedProfileItem = new ProfileItemBuilder().build();
        EditProfileItemDescriptor descriptor = new EditProfileItemDescriptorBuilder(editedProfileItem).build();
        EditProfileCommand editCommand = new EditProfileCommand(INDEX_SECOND, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, PROFILE_NAME, editedProfileItem);

        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);
        expectedModel.setProfileItem(model.getProfileItemFromFilteredList(INDEX_SECOND.getZeroBased()),
                editedProfileItem);
        expectedModel.setTabName(TabName.PROFILE);
        expectedModel.setProfileViewIndex(INDEX_SECOND);

        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        Index indexLastProfileItem = Index.fromOneBased(model.getFilteredProfileListSize());
        ProfileItem lastProfileItem = model.getProfileItemFromFilteredList(indexLastProfileItem.getZeroBased());

        ProfileItemBuilder profileItemInList = new ProfileItemBuilder(lastProfileItem);
        ProfileItem editedProfileItem = profileItemInList.withCategory(VALID_CATEGORY_SKILL).build();
        EditProfileItemDescriptor descriptor =
                new EditProfileItemDescriptorBuilder().withProfileItemCategory(VALID_CATEGORY_SKILL).build();
        EditProfileCommand editCommand = new EditProfileCommand(indexLastProfileItem, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, PROFILE_NAME, editedProfileItem);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);

        expectedModel.setProfileItem(lastProfileItem, editedProfileItem);
        expectedModel.setProfileViewIndex(indexLastProfileItem);
        expectedModel.setTabName(TabName.PROFILE);
        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProfileCommand editCommand = new EditProfileCommand(INDEX_SECOND, new EditProfileItemDescriptor());
        ProfileItem editedProfileItem = model.getProfileItemFromFilteredList(INDEX_SECOND.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, PROFILE_NAME, editedProfileItem);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);
        expectedModel.setTabName(TabName.PROFILE);
        expectedModel.setProfileViewIndex(INDEX_SECOND);

        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProfileItemAtIndex(model, INDEX_SECOND);
        showProfileItemAtIndex(expectedModel, INDEX_SECOND);
        ProfileItem profileItemInFilteredList =
                model.getProfileItemFromFilteredList(INDEX_FIRST.getZeroBased());
        ProfileItem editedProfileItem =
                new ProfileItemBuilder(profileItemInFilteredList).withTitle(VALID_TITLE_INTERNSHIP).build();
        EditProfileCommand editCommand = new EditProfileCommand(INDEX_FIRST,
                new EditProfileItemDescriptorBuilder().withTitle(VALID_TITLE_INTERNSHIP).build());

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, PROFILE_NAME, editedProfileItem);
        expectedModel.setProfileItem(model.getProfileItemFromFilteredList(INDEX_FIRST.getZeroBased()),
                editedProfileItem);
        expectedModel.setTabName(TabName.PROFILE);
        expectedModel.setProfileViewIndex(INDEX_SECOND);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);

        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidProfileItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProfileListSize() + 1);
        EditProfileItemDescriptor descriptor =
                new EditProfileItemDescriptorBuilder().withProfileItemCategory(VALID_CATEGORY_ACHIEVEMENT).build();
        EditProfileCommand editCommand = new EditProfileCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, INVALID_PROFILE_INDEX_MESSAGE);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of profile list
     */
    @Test
    public void execute_invalidProfileItemIndexFilteredList_failure() {
        showProfileItemAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of profile list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredProfileListSize());

        EditProfileCommand editCommand = new EditProfileCommand(outOfBoundIndex,
                new EditProfileItemDescriptorBuilder().withProfileItemCategory(VALID_CATEGORY_ACHIEVEMENT).build());

        assertCommandFailure(editCommand, model, INVALID_PROFILE_INDEX_MESSAGE);
    }

    @Test
    public void execute_editAnItemToBeTheSameAsAnItemWhichAlreadyExist_throwsCommandException() {
        EditProfileCommand.EditProfileItemDescriptor descriptor =
            new EditProfileItemDescriptorBuilder(GOVTECH_EXPERIENCE).build();
        EditProfileCommand editCommand = new EditProfileCommand(INDEX_FIRST, descriptor);
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void equals_test_success() {
        EditProfileItemDescriptor govtechInternship = new EditProfileItemDescriptorBuilder(GOVTECH_EXPERIENCE).build();
        EditProfileItemDescriptor htmlSkill = new EditProfileItemDescriptorBuilder(HTML_SKILL).build();
        final EditProfileCommand standardCommand = new EditProfileCommand(INDEX_SECOND, govtechInternship);

        // same values -> returns true
        EditProfileItemDescriptor copyDescriptor = new EditProfileItemDescriptor(govtechInternship);
        EditProfileCommand commandWithSameValues = new EditProfileCommand(INDEX_SECOND, copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new ExitCommand(), standardCommand);

        // different index -> returns false
        assertNotEquals(new EditProfileCommand(INDEX_FIRST, govtechInternship), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditProfileCommand(INDEX_SECOND, htmlSkill), standardCommand);
    }
}
