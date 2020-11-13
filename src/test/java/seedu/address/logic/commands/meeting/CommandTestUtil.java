package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TO;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.TitleDescriptionContainsKeywordsPredicate;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class CommandTestUtil {

    public static final String VALID_TITLE_A = "Complete Release";
    public static final String VALID_TITLE_B = "Finalise UG";
    public static final String VALID_DESCRIPTION_A = "With business associates";
    public static final String VALID_DESCRIPTION_B = "With product designers";
    public static final String VALID_FROM_A = "01-01-2020 14:00";
    public static final String VALID_FROM_B = "01-01-2020 12:00";
    public static final String VALID_TO_A = "15:00";
    public static final String VALID_TO_B = "14:00";

    public static final String VALID_CONTACTS_A = "Alice, Bob, Cameron";
    public static final String VALID_CONTACTS_B = "Jeannie, Justin, Lenny Sarah";
    public static final String VALID_LOCATION_A = "Singapore";
    public static final String VALID_LOCATION_B = "Jakarta";

    public static final String TITLE_DESC_A = " " + PREFIX_TITLE + VALID_TITLE_A;
    public static final String TITLE_DESC_B = " " + PREFIX_TITLE + VALID_TITLE_B;
    public static final String DESCRIPTION_DESC_A = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_A;
    public static final String DESCRIPTION_DESC_B = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_B;
    public static final String FROM_DESC_A = " " + PREFIX_FROM + VALID_FROM_A;
    public static final String FROM_DESC_B = " " + PREFIX_FROM + VALID_FROM_B;
    public static final String TO_DESC_A = " " + PREFIX_TO + VALID_TO_A;
    public static final String TO_DESC_B = " " + PREFIX_TO + VALID_TO_B;
    public static final String CONTACTS_DESC_A = " " + PREFIX_CONTACTS + VALID_CONTACTS_A;
    public static final String CONTACTS_DESC_B = " " + PREFIX_CONTACTS + VALID_CONTACTS_B;
    public static final String LOCATION_DESC_A = " " + PREFIX_LOCATION + VALID_LOCATION_A;
    public static final String LOCATION_DESC_B = " " + PREFIX_LOCATION + VALID_LOCATION_B;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + ""; // "" empty not allowed not allowed
    public static final String INVALID_FROM_DESC = " " + PREFIX_FROM + "3-03-2020 09:30"; // missing '0' digit
    public static final String INVALID_TO_DESC = " " + PREFIX_TO + "03-03-2020 0930"; // missing colon
    public static final String INVALID_CONTACTS_DESC = " " + PREFIX_CONTACTS + "1 2 3"; // no commas

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditMeetingDescriptor DESC_MEETING_A;
    public static final EditCommand.EditMeetingDescriptor DESC_MEETING_B;

    static {
        DESC_MEETING_A = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_A)
                .withDescription(VALID_DESCRIPTION_A).withFrom(VALID_FROM_A).withTo(VALID_TO_A)
                .withContacts(VALID_CONTACTS_A).withLocation(VALID_LOCATION_A).build();
        DESC_MEETING_B = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_B)
                .withDescription(VALID_DESCRIPTION_B).withFrom(VALID_FROM_B).withTo(VALID_TO_B)
                .withContacts(VALID_CONTACTS_B).withLocation(VALID_LOCATION_B).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, ModelMeeting actualModelMeeting,
                                            CommandResult expectedCommandResult, ModelMeeting expectedModelPerson) {
        try {
            CommandResult result = command.execute(actualModelMeeting);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModelPerson, actualModelMeeting);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, ModelMeeting, CommandResult, ModelMeeting)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, ModelMeeting actualModelMeeting, String expectedMessage,
                                            ModelMeeting expectedModelMeeting) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModelMeeting, expectedCommandResult, expectedModelMeeting);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the meeting book, filtered meeting list and selected meeting in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, ModelMeeting actualModelMeeting, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MeetingBook expectedMeetingBook = new MeetingBook(actualModelMeeting.getMeetingBook());
        List<Meeting> expectedFilteredList = new ArrayList<>(actualModelMeeting.getFilteredMeetingList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModelMeeting));
        assertEquals(expectedMeetingBook, actualModelMeeting.getMeetingBook());
        assertEquals(expectedFilteredList, actualModelMeeting.getFilteredMeetingList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s meeting book.
     */
    public static void showMeetingAtIndex(ModelMeeting modelMeeting, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < modelMeeting.getFilteredMeetingList().size());

        Meeting meeting = modelMeeting.getFilteredMeetingList().get(targetIndex.getZeroBased());
        final String[] splitName = meeting.getTitle().value.split("\\s+");
        modelMeeting.updateFilteredMeetingList(new TitleDescriptionContainsKeywordsPredicate(
                Arrays.asList(splitName[0])));

        assertEquals(1, modelMeeting.getFilteredMeetingList().size());
    }
}
