package seedu.address.logic.parser.hairdresser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.hairdresser.FilterHairdresserCommand;
import seedu.address.model.person.hairdresser.HairdresserNameContainsKeywordsPredicate;

public class FilterHairdresserCommandParserTest {

    private FilterHairdresserCommandParser parser = new FilterHairdresserCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterHairdresserCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterHairdresserCommand() {
        // no leading and trailing whitespaces
        FilterHairdresserCommand expectedFilterHairdresserCommand =
                new FilterHairdresserCommand(
                        new HairdresserNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFilterHairdresserCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFilterHairdresserCommand);
    }

}
