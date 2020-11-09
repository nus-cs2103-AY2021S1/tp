package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.sort.SortClearCommand;
import seedu.address.logic.commands.sort.SortContactCommand;
import seedu.address.logic.commands.sort.SortTaskCommand;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();
    @Test
    public void parse_validArgs_returnsSortCommand() {
        //"clear" is a valid option for sorting
        assertParseSuccess(parser, "clear", new SortClearCommand());
        //"contact is a valid option for sorting
        assertParseSuccess(parser, "contact", new SortContactCommand());
        //"task" is a valid option for sorting
        assertParseSuccess(parser, "task", new SortTaskCommand());
    }
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
