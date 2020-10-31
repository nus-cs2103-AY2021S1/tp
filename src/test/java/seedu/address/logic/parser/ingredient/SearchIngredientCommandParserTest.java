package seedu.address.logic.parser.ingredient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ingredient.SearchIngredientCommand;
import seedu.address.model.ingredient.IngredientContainsKeywordsPredicate;

public class SearchIngredientCommandParserTest {

    private SearchIngredientCommandParser parser = new SearchIngredientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchIngredientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SearchIngredientCommand expectedSearchIngredientCommand =
                new SearchIngredientCommand(new IngredientContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + "Alice Bob", expectedSearchIngredientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + " \n Alice \n \t Bob  \t", expectedSearchIngredientCommand);
    }

}
