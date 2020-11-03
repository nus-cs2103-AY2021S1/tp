package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.ModuleBuilder;

import java.util.HashSet;
import java.util.Set;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

public class DeleteMeetingCommandParserTest {
    private DeleteMeetingCommandParser parser = new DeleteMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMeetingCommand() {
        Set<Person> expectedMembers = new HashSet<>();
        expectedMembers.add(BOB);
        expectedMembers.add(ALICE);
        Module expectedModule = new ModuleBuilder().withName("CS2103").withMembers(expectedMembers).build();
        Meeting expectedMeeting = new MeetingBuilder().withName("CS2103 Meeting").withModule(expectedModule)
                .withDate("2020-10-03").withTime("10:00").withMembers(expectedMembers).build();

        DeleteMeetingCommand expectedDeletedMeetingCommand = new DeleteMeetingCommand(expectedModule.getModuleName(),
                expectedMeeting.getMeetingName());

        // module first then meeting name
        assertParseSuccess(parser, " m/CS2103 n/CS2103 Meeting", expectedDeletedMeetingCommand);

        // meeting name first then module name
        assertParseSuccess(parser, " n/CS2103 Meeting m/CS2103", expectedDeletedMeetingCommand);

        // multiple white spaces
        assertParseSuccess(parser, "     n/CS2103 Meeting             m/CS2103", expectedDeletedMeetingCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no space in front
        assertParseFailure(parser, "/m CS2103 /n CS2103 Meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE));

        // no space after prefix
        assertParseFailure(parser, " /mCS2103 /n CS2103 Meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE));

        // no input
        assertParseFailure(parser, " /m /n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE));

        // only module input
        assertParseFailure(parser, " /m CS2103 /n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE));

        // only meeting name input
        assertParseFailure(parser, " /m /n CS2103 Meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE));

        // wrong prefixes
        assertParseFailure(parser, " /mo CS2103 /na CS2103 Meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMeetingCommand.MESSAGE_USAGE));
    }
}
