package seedu.address.logic.parser.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.FilterClientCommand;
import seedu.address.model.person.client.ClientNameContainsKeywordsPredicate;

public class FilterClientCommandParserTest {

    private FilterClientCommandParser parser = new FilterClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterClientCommand() {
        // no leading and trailing whitespaces
        FilterClientCommand expectedFilterClientCommand =
                new FilterClientCommand(new ClientNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFilterClientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFilterClientCommand);
    }

}
