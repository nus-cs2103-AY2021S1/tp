package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meetingcommands.EditMeetingCommand;
import seedu.address.model.Model;
import seedu.address.model.id.PropertyId;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class MeetingCommandTestUtil {

    public static final String VALID_PROPERTY_ID_A = "P1";
    public static final String VALID_PROPERTY_ID_B = "P2";

    public static final String INVALID_PROPERTY_ID_A = "P*(";
    public static final String INVALID_PROPERTY_ID_B = "P$0";

    public static final String VALID_BIDDER_ID_A = "B1";
    public static final String VALID_BIDDER_ID_B = "B2";

    public static final String INVALID_BIDDER_ID_A = "B^";
    public static final String INVALID_BIDDER_ID_B = "B*(";

    public static final String VALID_MEETING_TYPE_A = "p";
    public static final String VALID_MEETING_TYPE_B = "a";

    public static final String INVALID_MEETING_TYPE_A = "z";
    public static final String INVALID_MEETING_TYPE_B = "l";

    public static final String VALID_VENUE_A = "33 Pasir Ris Drive";
    public static final String VALID_VENUE_B = "91 WhiteWater Street";

    public static final String INVALID_VENUE_A = "TEST1";
    public static final String INVALID_VENUE_B = "TEST2";

    public static final String VALID_DATE_A = "03-08-2021";
    public static final String VALID_DATE_B = "15-10-2021";

    public static final String INVALID_DATE_A = "99-99-2001";
    public static final String INVALID_DATE_B = "98-$3-2*12";

    public static final String VALID_START_TIME_A = "12:30";
    public static final String VALID_START_TIME_B = "13:40";

    public static final String INVALID_START_TIME_A = "12330";
    public static final String INVALID_START_TIME_B = "ASDADA";

    public static final String VALID_END_TIME_A = "15:30";
    public static final String VALID_END_TIME_B = "16:40";

    public static final String INVALID_END_TIME_A = "12330";
    public static final String INVALID_END_TIME_B = "ASDADA";

    public static final PropertyId DEFAULT_PROPERTY_ID = new PropertyId("P1");

    public static final String PROPERTY_ID_DESC_A = " " + PREFIX_MEETING_PROPERTY_ID + VALID_PROPERTY_ID_A;
    public static final String PROPERTY_ID_DESC_B = " " + PREFIX_MEETING_PROPERTY_ID + VALID_PROPERTY_ID_B;

    public static final String INVALID_PROPERTY_ID_DESC_A = " " + PREFIX_MEETING_PROPERTY_ID + INVALID_PROPERTY_ID_A;
    public static final String INVALID_PROPERTY_ID_DESC_B = " " + PREFIX_MEETING_PROPERTY_ID + INVALID_PROPERTY_ID_B;

    public static final String BIDDER_ID_DESC_A = " " + PREFIX_MEETING_BIDDER_ID + VALID_BIDDER_ID_A;
    public static final String BIDDER_ID_DESC_B = " " + PREFIX_MEETING_BIDDER_ID + VALID_BIDDER_ID_B;

    public static final String INVALID_BIDDER_ID_DESC_A = " " + PREFIX_MEETING_BIDDER_ID + INVALID_BIDDER_ID_A;
    public static final String INVALID_BIDDER_ID_DESC_B = " " + PREFIX_MEETING_BIDDER_ID + INVALID_BIDDER_ID_B;

    public static final String MEETING_TYPE_A = " " + PREFIX_MEETING_TYPE + VALID_MEETING_TYPE_A;
    public static final String MEETING_TYPE_B = " " + PREFIX_MEETING_TYPE + VALID_MEETING_TYPE_B;

    public static final String INVALID_MEETING_TYPE_DESC_A = " " + PREFIX_MEETING_TYPE + INVALID_MEETING_TYPE_A;
    public static final String INVALID_MEETING_TYPE_DESC_B = " " + PREFIX_MEETING_TYPE + INVALID_MEETING_TYPE_B;

    public static final String MEETING_DATE_DESC_A = " " + PREFIX_MEETING_DATE + VALID_DATE_A;
    public static final String MEETING_DATE_DESC_B = " " + PREFIX_MEETING_DATE + VALID_DATE_B;

    public static final String INVALID_MEETING_DATE_DESC_A = " " + PREFIX_MEETING_DATE + INVALID_DATE_A;
    public static final String INVALID_MEETING_DATE_DESC_B = " " + PREFIX_MEETING_DATE + INVALID_DATE_B;

    public static final String MEETING_VENUE_DESC_A = " " + PREFIX_MEETING_VENUE + VALID_VENUE_A;
    public static final String MEETING_VENUE_DESC_B = " " + PREFIX_MEETING_VENUE + VALID_VENUE_B;

    public static final String INVALID_MEETING_VENUE_DESC_A = " " + PREFIX_MEETING_VENUE + INVALID_VENUE_A;
    public static final String INVALID_MEETING_VENUE_DESC_B = " " + PREFIX_MEETING_VENUE + INVALID_VENUE_B;

    public static final String START_TIME_DESC_A = " " + PREFIX_MEETING_STARTTIME + VALID_START_TIME_A;
    public static final String START_TIME_DESC_B = " " + PREFIX_MEETING_STARTTIME + VALID_START_TIME_B;

    public static final String INVALID_START_TIME_DESC_A = " " + PREFIX_MEETING_STARTTIME + INVALID_START_TIME_A;
    public static final String INVALID_START_TIME_DESC_B = " " + PREFIX_MEETING_STARTTIME + INVALID_START_TIME_B;

    public static final String END_TIME_DESC_A = " " + PREFIX_MEETING_ENDTIME + VALID_END_TIME_A;
    public static final String END_TIME_DESC_B = " " + PREFIX_MEETING_ENDTIME + VALID_END_TIME_B;

    public static final String INVALID_END_TIME_DESC_A = " " + PREFIX_MEETING_ENDTIME + INVALID_END_TIME_A;
    public static final String INVALID_END_TIME_DESC_B = " " + PREFIX_MEETING_ENDTIME + INVALID_END_TIME_B;

    public static final EditMeetingCommand.EditMeetingDescriptor VALID_MEETING_A;
    public static final EditMeetingCommand.EditMeetingDescriptor VALID_MEETING_B;

    static {
        VALID_MEETING_A = new EditMeetingDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_A)
                .withBidderId(VALID_BIDDER_ID_A).withVenue(VALID_VENUE_A).withDate(VALID_DATE_A)
                .withStartTime(VALID_START_TIME_A).withEndTime(VALID_END_TIME_A).build();
        VALID_MEETING_B = new EditMeetingDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_B)
                .withBidderId(VALID_BIDDER_ID_B).withVenue(VALID_VENUE_B).withDate(VALID_DATE_B)
                .withStartTime(VALID_START_TIME_B).withEndTime(VALID_END_TIME_B).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }


}
