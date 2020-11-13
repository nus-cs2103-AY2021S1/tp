package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.meeting.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.meeting.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.ModelMeetingManager;



class ListCommandTest {

    private ModelMeeting modelMeeting;
    private ModelMeeting expectedModelMeeting;

    @BeforeEach
    public void setUp() {
        modelMeeting = new ModelMeetingManager(getTypicalMeetingBook(), new UserPrefs());
        expectedModelMeeting = new ModelMeetingManager(modelMeeting.getMeetingBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), modelMeeting, ListCommand.MESSAGE_SUCCESS, expectedModelMeeting);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showMeetingAtIndex(modelMeeting, INDEX_FIRST);
        assertCommandSuccess(new ListCommand(), modelMeeting, ListCommand.MESSAGE_SUCCESS, expectedModelMeeting);
    }

}
