package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ingredientcommands.IngredientFindCommand;
import seedu.address.model.ingredient.IngredientNameContainsKeywordsPredicate;

public class IngredientFindCommandParserTest {
    private IngredientFindCommandParser parser = new IngredientFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                IngredientFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSalesFindCommand() {
        IngredientFindCommand expectedIngredientFindCommand =
                new IngredientFindCommand(new IngredientNameContainsKeywordsPredicate(Arrays.asList("milk")));
    }
}
