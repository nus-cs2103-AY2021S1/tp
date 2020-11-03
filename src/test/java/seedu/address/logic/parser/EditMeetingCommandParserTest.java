package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PARTICIPANT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_NAME_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_NAME_DESC_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CM1112_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class EditMeetingCommandParserTest {

    private static final String PARTICIPANT_EMPTY = " " + PREFIX_PARTICIPANT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no module name specified
        assertParseFailure(parser, VALID_MEETING_NAME_CM1111_MEETING, MESSAGE_INVALID_FORMAT);

        // no meeting name specified
        assertParseFailure(parser, VALID_MODULE_NAME_CM1111_MEETING, MESSAGE_INVALID_FORMAT);

        // no field specified
        // assertParseFailure(parser, VALID_MODULE_NAME_CM1111_MEETING + VALID_MEETING_NAME_CM1111_MEETING,
        //         EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no name and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid name format
        assertParseFailure(parser, "-5" + MEETING_NAME_DESC_CM1111_MEETING, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    /*
    @Test
    public void parse_allFieldsSpecified_success() {
        ModuleName targetModule = new ModuleName(VALID_MODULE_NAME_CM1111_MEETING);
        MeetingName targetMeetingName = new MeetingName(VALID_MEETING_NAME_CM1111_MEETING);
        String userInput = MODULE_DESC_CM1111_MEETING + MEETING_NAME_DESC_CM1111_MEETING + DATE_DESC_CM1112_MEETING
                + PARTICIPANT_DESC_AMY + TIME_DESC_CM1112_MEETING + MEETING_NAME_DESC_CM1112_MEETING
                + PARTICIPANT_DESC_BOB;

        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withMeetingName(VALID_MEETING_NAME_CM1112_MEETING)
                .withDate(VALID_DATE_CM1112_MEETING)
                .withTime(VALID_TIME_CM1112_MEETING)
                .withMembers(VALID_PARTICIPANT_AMY, VALID_PARTICIPANT_BOB).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetModule, targetMeetingName, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
     */
}
