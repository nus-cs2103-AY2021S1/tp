package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetEditIngredientCommand;

public class GetEditIngredientCommandParserTest {
    private GetEditIngredientCommandParser parser = new GetEditIngredientCommandParser();

    @Test
    public void parse_validArgs_returnsGetEditCommand() {
        assertParseSuccess(parser, "1", new GetEditIngredientCommand(INDEX_FIRST_INGREDIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GetEditIngredientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GetEditIngredientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GetEditIngredientCommand.MESSAGE_USAGE));
    }
}
