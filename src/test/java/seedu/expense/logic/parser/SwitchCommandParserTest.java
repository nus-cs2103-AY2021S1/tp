package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.SwitchCommand;
import seedu.expense.model.tag.Tag;

class SwitchCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE);

    private final SwitchCommandParser parser = new SwitchCommandParser();

    @Test
    void parse_validValue_success() {
        String category = "Food";
        String userInput = " " + PREFIX_TAG + category;

        SwitchCommand expectedCommand = new SwitchCommand(new Tag(category));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + "Food", MESSAGE_INVALID_FORMAT);
    }
}
