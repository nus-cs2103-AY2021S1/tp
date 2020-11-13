package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
import static seedu.address.logic.commands.meeting.CommandTestUtil.DESC_MEETING_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.DESC_MEETING_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_LOCATION_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TITLE_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TITLE_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.meeting.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.meeting.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.ModelMeetingManager;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;


class EditCommandTest {
    private ModelMeeting modelMeeting = new ModelMeetingManager(getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedPerson = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedPerson);

        ModelMeeting expectedModelPerson =
                new ModelMeetingManager(new MeetingBook(modelMeeting.getMeetingBook()), new UserPrefs());
        expectedModelPerson.setMeeting(modelMeeting.getFilteredMeetingList().get(0), editedPerson);

        assertCommandSuccess(editCommand, modelMeeting, expectedMessage, expectedModelPerson);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(modelMeeting.getFilteredMeetingList().size());
        Meeting lastMeeting = modelMeeting.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withTitle(VALID_TITLE_A).withLocation(VALID_LOCATION_A).build();

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_A)
                .withLocation(VALID_LOCATION_A).build();

        EditCommand editCommand = new EditCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        ModelMeeting expectedModelMeeting =
                new ModelMeetingManager(new MeetingBook(modelMeeting.getMeetingBook()), new UserPrefs());
        expectedModelMeeting.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editCommand, modelMeeting, expectedMessage, expectedModelMeeting);
    }

    @Test
    public void execute_unchangedMeeting_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST, new EditMeetingDescriptor());
        Meeting editedMeeting = modelMeeting.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_UNCHANGED, editedMeeting);

        ModelMeeting expectedModelMeeting =
                new ModelMeetingManager(new MeetingBook(modelMeeting.getMeetingBook()), new UserPrefs());

        assertCommandFailure(editCommand, modelMeeting, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(modelMeeting, INDEX_FIRST);

        Meeting meetingInFilteredList = modelMeeting.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withTitle(VALID_TITLE_A).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_A).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        ModelMeeting expectedModelMeeting =
                new ModelMeetingManager(new MeetingBook(modelMeeting.getMeetingBook()), new UserPrefs());
        expectedModelMeeting.setMeeting(modelMeeting.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editCommand, modelMeeting, expectedMessage, expectedModelMeeting);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = modelMeeting.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, modelMeeting, EditCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        showMeetingAtIndex(modelMeeting, INDEX_FIRST);

        // edit meeting in filtered list into a duplicate in meeting book
        Meeting meetingInList = modelMeeting.getMeetingBook().getMeetingList()
                .get(INDEX_SECOND.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditMeetingDescriptorBuilder(meetingInList).build());

        assertCommandFailure(editCommand, modelMeeting, EditCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelMeeting.getFilteredMeetingList().size() + 1);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_B).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, modelMeeting, MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of meeting book
     */
    @Test
    public void execute_invalidMeetingIndexFilteredList_failure() {
        showMeetingAtIndex(modelMeeting, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of meeting book list
        assertTrue(outOfBoundIndex.getZeroBased() < modelMeeting.getMeetingBook().getMeetingList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_B).build());

        assertCommandFailure(editCommand, modelMeeting, MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST, DESC_MEETING_A);

        // same values -> returns true
        EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(DESC_MEETING_A);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        //        // different types -> returns false
        //        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND, DESC_MEETING_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST, DESC_MEETING_B)));
    }

}
