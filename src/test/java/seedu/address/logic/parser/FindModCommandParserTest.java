package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindModCommand;
import seedu.address.model.module.CodeOrNameMatchesKeywordPredicate;

class FindModCommandParserTest {
    private FindModCommandParser parser = new FindModCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindModCommand expectedFindCommand =
                new FindModCommand(new CodeOrNameMatchesKeywordPredicate(Arrays.asList(
                        "CS2103", "Software", "Engineering")));
        assertParseSuccess(parser, "CS2103 Software Engineering", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103 \n \t Software Engineering  \t", expectedFindCommand);
    }
}
