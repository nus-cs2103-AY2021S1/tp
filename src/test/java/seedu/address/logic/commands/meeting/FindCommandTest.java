package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.meeting.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING_C;
import static seedu.address.testutil.TypicalMeetings.MEETING_D;
import static seedu.address.testutil.TypicalMeetings.MEETING_E;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.ModelMeetingManager;
import seedu.address.model.meeting.meeting.TitleDescriptionContainsKeywordsPredicate;

class FindCommandTest {

    private ModelMeeting modelMeeting = new ModelMeetingManager(getTypicalMeetingBook(), new UserPrefs());
    private ModelMeeting expectedModelMeeting = new ModelMeetingManager(getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleDescriptionContainsKeywordsPredicate firstPredicate =
                new TitleDescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        TitleDescriptionContainsKeywordsPredicate secondPredicate =
                new TitleDescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModelMeeting.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, modelMeeting, expectedMessage, expectedModelMeeting);
        assertEquals(Collections.emptyList(), modelMeeting.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleMeetingsFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate("Final");
        FindCommand command = new FindCommand(predicate);
        expectedModelMeeting.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, modelMeeting, expectedMessage, expectedModelMeeting);
        assertEquals(Arrays.asList(MEETING_C), modelMeeting.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleDescriptionKeywords_multipleMeetingsFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate("features");
        FindCommand command = new FindCommand(predicate);
        expectedModelMeeting.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, modelMeeting, expectedMessage, expectedModelMeeting);
        assertEquals(Arrays.asList(MEETING_C, MEETING_D), modelMeeting.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleTitleKeywords_multipleMeetingsFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        TitleDescriptionContainsKeywordsPredicate predicate = preparePredicate("Mid");
        FindCommand command = new FindCommand(predicate);
        expectedModelMeeting.updateFilteredMeetingList(predicate);
        assertCommandSuccess(command, modelMeeting, expectedMessage, expectedModelMeeting);
        // description with 'disucss'
        assertEquals(Arrays.asList(MEETING_D, MEETING_E), modelMeeting.getFilteredMeetingList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TitleDescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleDescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
