package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertSortParserFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertSortParserSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.command.sort.SortAccommodationCommand;
import seedu.address.logic.command.sort.SortActivityCommand;
import seedu.address.logic.command.sort.SortCommand;
import seedu.address.logic.command.sort.SortFriendCommand;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortActivityCommand() {
        assertSortParserSuccess(parser, " -activity cost", new SortActivityCommand("cost"));
    }

    @Test
    public void parse_invalidArgsActivity_throwsParseException() {
        assertSortParserFailure(parser, " -activity cost cost",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortAccommodationCommand() {
        assertSortParserSuccess(parser, " -accommodation cost", new SortAccommodationCommand("cost"));
    }

    @Test
    public void parse_invalidArgsAccommodation_throwsParseException() {
        assertSortParserFailure(parser, " -accommodation cost cost",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortFriendCommand() {
        assertSortParserSuccess(parser, " -friend mobile", new SortFriendCommand("mobile"));
    }

    @Test
    public void parse_invalidArgsFriend_throwsParseException() {
        assertSortParserFailure(parser, " -friend name name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertSortParserFailure(parser, " -activiti cost",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
