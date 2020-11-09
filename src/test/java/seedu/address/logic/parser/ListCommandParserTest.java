package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.list.ListContactCommand;
import seedu.address.logic.commands.list.ListEventCommand;
import seedu.address.logic.commands.list.ListTaskCommand;
import seedu.address.logic.commands.list.ListTodoCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_invalidCommand_failure() {
        assertParseFailure(parser, "random ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validInput_success() {
        assertParseSuccess(parser, "contact" , new ListContactCommand());

        assertParseSuccess(parser, "event" , new ListEventCommand());

        assertParseSuccess(parser, "todo" , new ListTodoCommand());

        assertParseSuccess(parser, "task" , new ListTaskCommand());
    }
}
