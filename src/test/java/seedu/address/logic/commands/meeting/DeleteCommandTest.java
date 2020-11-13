package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
import static seedu.address.logic.commands.meeting.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.meeting.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.meeting.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.logic.commands.meeting.DeleteCommand.MESSAGE_DELETE_MEETING_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeeting;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.ModelMeetingManager;
import seedu.address.model.meeting.meeting.Meeting;


public class DeleteCommandTest {

    private ModelMeeting modelMeeting = new ModelMeetingManager(getTypicalMeetingBook(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() {
        Meeting meetingToDelete = getTypicalMeeting().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        ModelMeetingManager expectedModel = new ModelMeetingManager(modelMeeting.getMeetingBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteCommand, modelMeeting, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(modelMeeting.getFilteredMeetingList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelMeeting, MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(modelMeeting, INDEX_FIRST);

        Meeting personToDelete = modelMeeting.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_MEETING_SUCCESS, personToDelete);

        ModelMeeting expectedModelPerson = new ModelMeetingManager(modelMeeting.getMeetingBook(), new UserPrefs());
        expectedModelPerson.deleteMeeting(personToDelete);
        showNoMeeting(expectedModelPerson);

        assertCommandSuccess(deleteCommand, modelMeeting, expectedMessage, expectedModelPerson);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(modelMeeting, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of meeting book list

        assertTrue(outOfBoundIndex.getZeroBased() < modelMeeting.getMeetingBook().getMeetingList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, modelMeeting, MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMeeting(ModelMeeting modelMeeting) {
        modelMeeting.updateFilteredMeetingList(p -> false);

        assertTrue(modelMeeting.getFilteredMeetingList().isEmpty());
    }
}
