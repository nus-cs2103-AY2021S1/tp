package seedu.address.logic.parser.recipe;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.recipe.SelectRecipeCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the SelectCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the SelectCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SelectRecipeCommandParserTest {

    private SelectRecipeCommandParser parser = new SelectRecipeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectRecipeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        // valid index
        SelectRecipeCommand expectedSearchRecipeCommand =
                new SelectRecipeCommand(INDEX_FIRST_RECIPE);
        assertParseSuccess(parser, " " + 1, expectedSearchRecipeCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectRecipeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "a", expectedMessage);
    }
}
