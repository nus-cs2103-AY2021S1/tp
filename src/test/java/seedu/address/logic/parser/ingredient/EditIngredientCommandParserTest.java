package seedu.address.logic.parser.ingredient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INGREDIENT_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.ingredient.EditIngredientCommand;
import seedu.address.model.ingredient.Ingredient;

public class EditIngredientCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIngredientCommand.MESSAGE_USAGE);

    private EditIngredientCommandParser parser = new EditIngredientCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        String ingredientWithoutIndex = CommandTestUtil.missingIngredientField(CommandTestUtil.Field.INDEX);
        assertParseFailure(parser, ingredientWithoutIndex, MESSAGE_INVALID_FORMAT);

        // no field to edit specified
        assertParseFailure(parser, "1", EditIngredientCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        String ingredientWithNegativeIndex =
                CommandTestUtil.invalidIngredientIndexField(CommandTestUtil.Number.NEGATIVE);
        assertParseFailure(parser, ingredientWithNegativeIndex, MESSAGE_INVALID_FORMAT);

        // zero index
        String ingredientWithZeroIndex = CommandTestUtil.invalidIngredientIndexField(CommandTestUtil.Number.ZERO);
        assertParseFailure(parser, ingredientWithZeroIndex, MESSAGE_INVALID_FORMAT);

        // non integer index
        String ingredientWithNonIntegerIndex =
                CommandTestUtil.invalidIngredientIndexField(CommandTestUtil.Number.NON_INTEGER);
        assertParseFailure(parser, ingredientWithNonIntegerIndex, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 prefix/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {

        //The test cases testing for all valid fields except one
        // invalid ingredient name specified
        String ingredientWithInvalidName =
                CommandTestUtil.invalidIngredientField(CommandTestUtil.Field.INGREDIENT_NAME);
        assertParseFailure(parser, ingredientWithInvalidName, Ingredient.MESSAGE_CONSTRAINTS);

        //empty ingredient names
        String ingredientWithInvalidName1 =
                CommandTestUtil.missingIngredientField(CommandTestUtil.Field.EMPTY_IG_NAME_1);
        assertParseFailure(parser, ingredientWithInvalidName1, EditIngredientCommand.MESSAGE_ONE_INGREDIENT);

        String ingredientWithInvalidName2 =
                CommandTestUtil.missingIngredientField(CommandTestUtil.Field.EMPTY_IG_NAME_2);
        assertParseFailure(parser, ingredientWithInvalidName2, EditIngredientCommand.MESSAGE_ONE_INGREDIENT);

        String ingredientWithInvalidName3 =
                CommandTestUtil.missingIngredientField(CommandTestUtil.Field.EMPTY_IG_NAME_3);
        assertParseFailure(parser, ingredientWithInvalidName3, EditIngredientCommand.MESSAGE_ONE_INGREDIENT);

        String ingredientWithInvalidName4 =
                CommandTestUtil.missingIngredientField(CommandTestUtil.Field.EMPTY_IG_NAME_4);
        assertParseFailure(parser, ingredientWithInvalidName4, EditIngredientCommand.MESSAGE_ONE_INGREDIENT);

        // invalid ingredient quantity specified
        String ingredientWithInvalidQuantity =
                CommandTestUtil.invalidIngredientField(CommandTestUtil.Field.INGREDIENT_QUANTITY);
        assertParseFailure(parser, ingredientWithInvalidQuantity, Ingredient.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the invalid value captured is ordered
        // take note that missing instructions in edit command is valid
        // by: 1. Ingredient Name 2. Ingredient Quantity
        assertParseFailure(parser, INGREDIENT_INDEX + INVALID_INGREDIENT_DESC + INVALID_INGREDIENT_QUANTITY,
                Ingredient.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                INGREDIENT_INDEX + " " + PREFIX_INGREDIENT + VALID_INGREDIENT_MARGARITAS
                        + INVALID_INGREDIENT_QUANTITY, Ingredient.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INGREDIENT;
        String userInputIngredient = targetIndex.getOneBased() + INGREDIENT_DESC_NOODLE;
        EditIngredientCommand.EditIngredientDescriptor descriptorIngredient = DESC_INGREDIENT_NOODLE;
        EditIngredientCommand expectedCommandIngredient = new EditIngredientCommand(targetIndex,
                descriptorIngredient);
        assertParseSuccess(parser, userInputIngredient, expectedCommandIngredient);
    }

}

