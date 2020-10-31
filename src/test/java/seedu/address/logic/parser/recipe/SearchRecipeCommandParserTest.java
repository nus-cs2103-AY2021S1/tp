package seedu.address.logic.parser.recipe;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.recipe.SearchRecipeCommand;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.model.recipe.RecipeContainsIngredientsPredicate;
import seedu.address.model.recipe.TagContainsKeywordsPredicate;

public class SearchRecipeCommandParserTest {

    private SearchRecipeCommandParser parser = new SearchRecipeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchRecipeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces in name
        SearchRecipeCommand expectedSearchRecipeCommand =
                new SearchRecipeCommand(new NameContainsKeywordsPredicate(Arrays.asList("Pork", "Sandwich")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Pork Sandwich", expectedSearchRecipeCommand);

        // multiple whitespaces between keywords in name
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Pork \n \t Sandwich  \t", expectedSearchRecipeCommand);

        // no leading and trailing whitespaces in ingredients
        expectedSearchRecipeCommand =
                new SearchRecipeCommand(new RecipeContainsIngredientsPredicate(Arrays.asList("Potato", "Bread")));
        assertParseSuccess(parser, " " + PREFIX_INGREDIENT + "Potato Bread", expectedSearchRecipeCommand);

        // multiple whitespaces between ingredients keywords
        assertParseSuccess(parser, " " + PREFIX_INGREDIENT + " \n Potato \n \t Bread  \t", expectedSearchRecipeCommand);

        // no leading and trailing whitespaces in tag
        expectedSearchRecipeCommand =
                new SearchRecipeCommand(new TagContainsKeywordsPredicate(Arrays.asList("low", "calories")));
        assertParseSuccess(parser, " " + PREFIX_TAG + "low calories", expectedSearchRecipeCommand);

        // multiple whitespaces between tag keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + " \n low \n \t calories  \t", expectedSearchRecipeCommand);
    }

}
