package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INSTRUCTION_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.INSTRUCTION_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CALORIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_IMAGE_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_IMAGE_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUCTION_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_IMAGE_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MARGARITAS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRecipes.MARGARITAS;
import static seedu.address.testutil.TypicalRecipes.NOODLE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.RecipeBuilder;

public class AddRecipeCommandParserTest {
    private AddRecipeCommandParser parser = new AddRecipeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Recipe expectedRecipe = new RecipeBuilder(MARGARITAS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS
                + CALORIES_DESC_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                + TAG_DESC_MARGARITAS, new AddRecipeCommand(expectedRecipe));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_NOODLE + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS
                + CALORIES_DESC_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                + TAG_DESC_MARGARITAS, new AddRecipeCommand(expectedRecipe));

        // multiple ingredients - last ingredients accepted
        assertParseSuccess(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_NOODLE + INGREDIENT_DESC_MARGARITAS
                + CALORIES_DESC_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                + TAG_DESC_MARGARITAS, new AddRecipeCommand(expectedRecipe));
        // multiple tags - all accepted
        Recipe expectedRecipeMultipleTags = new RecipeBuilder(MARGARITAS)
                .build();
        assertParseSuccess(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS
                + CALORIES_DESC_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                + TAG_DESC_MARGARITAS + TAG_DESC_MARGARITAS, new AddRecipeCommand(expectedRecipeMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Recipe expectedRecipe = new RecipeBuilder(NOODLE).build();
        assertParseSuccess(parser, NAME_DESC_NOODLE + INGREDIENT_DESC_NOODLE
                        + CALORIES_DESC_NOODLE + INSTRUCTION_DESC_NOODLE + RECIPE_IMAGE_DESC_NOODLE,
                new AddRecipeCommand(expectedRecipe));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                        + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS,
                expectedMessage);

        // missing ingredients prefix
        assertParseFailure(parser, NAME_DESC_MARGARITAS + VALID_INGREDIENT_MARGARITAS + CALORIES_DESC_MARGARITAS
                        + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS,
                expectedMessage);

        // missing calories prefix
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + VALID_CALORIES_MARGARITAS
                        + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS,
                expectedMessage);

        // missing instruction prefix
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + VALID_CALORIES_MARGARITAS
                + VALID_INSTRUCTION_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS,
                expectedMessage);

        // missing image prefix
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + VALID_CALORIES_MARGARITAS
                        + VALID_INSTRUCTION_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + VALID_RECIPE_IMAGE_MARGARITAS,
                expectedMessage);

        // missing tag prefix
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + VALID_CALORIES_MARGARITAS
                        + VALID_INSTRUCTION_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                        + VALID_TAG_MARGARITAS,
                expectedMessage);

        // missing calories prefix
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + VALID_CALORIES_MARGARITAS
                        + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MARGARITAS + VALID_INGREDIENT_MARGARITAS + VALID_CALORIES_MARGARITAS
                        + VALID_INSTRUCTION_MARGARITAS + VALID_RECIPE_IMAGE_MARGARITAS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                + TAG_DESC_MARGARITAS, Name.MESSAGE_CONSTRAINTS);

        // invalid ingredients
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INVALID_INGREDIENT_DESC + CALORIES_DESC_MARGARITAS
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                + TAG_DESC_MARGARITAS, ParserUtil.MESSAGE_CONSTRAINTS);

        // invalid calories
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + INVALID_CALORIES_DESC
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS,
                Calories.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                + INVALID_TAG_DESC + VALID_TAG_MARGARITAS, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                        + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                        + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                        + INGREDIENT_DESC_MARGARITAS + TAG_DESC_MARGARITAS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
    }
}
