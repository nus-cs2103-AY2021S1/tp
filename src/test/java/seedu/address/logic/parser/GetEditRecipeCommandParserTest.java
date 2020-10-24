package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetEditRecipeCommand;

public class GetEditRecipeCommandParserTest {
    private GetEditRecipeCommandParser parser = new GetEditRecipeCommandParser();

    @Test
    public void parse_validArgs_returnsGetEditCommand() {
        assertParseSuccess(parser, "1", new GetEditRecipeCommand(INDEX_FIRST_INGREDIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GetEditRecipeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GetEditRecipeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GetEditRecipeCommand.MESSAGE_USAGE));
    }
}
