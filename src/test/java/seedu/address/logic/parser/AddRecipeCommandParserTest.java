package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECIPE_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_DESCRIPTION_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_DESCRIPTION_BANANA_PIE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_INGREDIENTS_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_INGREDIENTS_BANANA_PIE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_PRODUCT_NAME_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_PRODUCT_NAME_BANANA_PIE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_QUANTITY_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_QUANTITY_BANANA_PIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_DESC_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_INGREDIENTS_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_PRODUCT_NAME_APPLE_PIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_QUANTITY_APPLE_PIE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_QUANTITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.RecipeParserUtil.DEFAULT_DESCRIPTION;
import static seedu.address.logic.parser.RecipeParserUtil.DEFAULT_PRODUCT_QUANTITY;
import static seedu.address.testutil.TypicalIngredientPrecursors.getTypicalIngredientList;
import static seedu.address.testutil.TypicalRecipePrecursors.APPLE_PIE_PRECURSOR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.model.recipe.ProductQuantity;
import seedu.address.model.recipe.RecipePrecursor;
import seedu.address.testutil.RecipePrecursorBuilder;

public class AddRecipeCommandParserTest {
    private AddRecipeCommandParser parser = new AddRecipeCommandParser();

    /**
     * Tests for if all fields present.
     */
    @Test
    public void parse_allFieldsPresent_success() {
        RecipePrecursor expectedItem = new RecipePrecursorBuilder(APPLE_PIE_PRECURSOR).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                new AddRecipeCommand(expectedItem));

        // multiple names - last name accepted
        assertParseSuccess(parser, RECIPE_PRODUCT_NAME_BANANA_PIE
                        + RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                new AddRecipeCommand(expectedItem));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_QUANTITY_BANANA_PIE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                new AddRecipeCommand(expectedItem));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_DESCRIPTION_BANANA_PIE
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                new AddRecipeCommand(expectedItem));

        // multiple ingredients - last ingredients accepted
        assertParseSuccess(parser, RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + RECIPE_INGREDIENTS_BANANA_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                new AddRecipeCommand(expectedItem));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        RecipePrecursor expectedItem = new RecipePrecursorBuilder(APPLE_PIE_PRECURSOR)
                .withIngredients(getTypicalIngredientList())
                .withDescription(DEFAULT_DESCRIPTION)
                .withQuantity(DEFAULT_PRODUCT_QUANTITY)
                .build();

        // Missing description field
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                new AddRecipeCommand(expectedItem));

        // Missing quantity field
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE + " "
                        + PREFIX_RECIPE_PRODUCT_QUANTITY
                        + DEFAULT_PRODUCT_QUANTITY,
                new AddRecipeCommand(expectedItem));

        // Missing both fields
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                        + RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                new AddRecipeCommand(expectedItem));
    }

    /**
     * Tests for compulsory fields.
     */
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                expectedMessage);

        // missing ingredients prefix
        assertParseFailure(parser, RECIPE_PRODUCT_NAME_APPLE_PIE
                        + RECIPE_QUANTITY_APPLE_PIE
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + VALID_RECIPE_INGREDIENTS_APPLE_PIE,
                expectedMessage);

        // missing all prefix
        assertParseFailure(parser, VALID_RECIPE_PRODUCT_NAME_APPLE_PIE
                        + VALID_RECIPE_QUANTITY_APPLE_PIE
                        + VALID_RECIPE_DESC_APPLE_PIE
                        + VALID_RECIPE_INGREDIENTS_APPLE_PIE,
                expectedMessage);
    }

    /**
     * Tests for invalid values.
     */
    @Test
    public void parse_invalidValue_failure() {
        // invalid quantity
        assertParseFailure(parser, RECIPE_PRODUCT_NAME_APPLE_PIE
                        + INVALID_RECIPE_QUANTITY
                        + RECIPE_DESCRIPTION_APPLE_PIE
                        + RECIPE_INGREDIENTS_APPLE_PIE,
                ProductQuantity.MESSAGE_CONSTRAINTS);
    }
}
