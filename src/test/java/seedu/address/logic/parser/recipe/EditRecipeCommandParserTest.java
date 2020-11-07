package seedu.address.logic.parser.recipe;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MARGARITAS_USER_INPUT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOODLE_USER_INPUT;
import static seedu.address.logic.commands.CommandTestUtil.Field;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECIPE_IMAGE_DESC_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECIPE_IMAGE_SANDWICH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MISSING_INSTRUCTIONS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.NEGATIVE_CALORIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NON_INTEGER_CALORIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_CLEAR_TAGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.ZERO_CALORIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.invalidRecipeCalorieField;
import static seedu.address.logic.commands.CommandTestUtil.invalidRecipeField;
import static seedu.address.logic.commands.CommandTestUtil.invalidRecipeIndexField;
import static seedu.address.logic.commands.CommandTestUtil.missingRecipeField;
import static seedu.address.logic.commands.CommandTestUtil.missingRecipeFieldWithoutPrefix;
import static seedu.address.logic.commands.CommandTestUtil.recipeDescriptor;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_RECIPE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.recipe.EditRecipeCommand;
import seedu.address.logic.commands.recipe.EditRecipeCommand.EditRecipeDescriptor;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Calories;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Tag;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

public class EditRecipeCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRecipeCommand.MESSAGE_USAGE);

    private EditRecipeCommandParser parser = new EditRecipeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        String recipeWithoutIndex = missingRecipeField(Field.INDEX);
        assertParseFailure(parser, recipeWithoutIndex, MESSAGE_INVALID_FORMAT);

        // no field to edit specified
        assertParseFailure(parser, "1", EditRecipeCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        String recipeWithNegativeIndex = invalidRecipeIndexField(CommandTestUtil.Number.NEGATIVE);
        assertParseFailure(parser, recipeWithNegativeIndex, MESSAGE_INVALID_FORMAT);

        // zero index
        String recipeWithZeroIndex = invalidRecipeIndexField(CommandTestUtil.Number.ZERO);
        assertParseFailure(parser, recipeWithZeroIndex, MESSAGE_INVALID_FORMAT);

        // non integer index
        String recipeWithNonIntegerIndex = invalidRecipeIndexField(CommandTestUtil.Number.NON_INTEGER);
        assertParseFailure(parser, recipeWithNonIntegerIndex, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 prefix/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {

        //The test cases testing for all valid fields except one
        // invalid ingredient specified
        String recipeWithInvalidIngredient = invalidRecipeField(Field.INGREDIENT);
        assertParseFailure(parser, recipeWithInvalidIngredient, Ingredient.MESSAGE_CONSTRAINTS);

        //empty ingredient names
        String recipeWithInvalidIngredient1 = RECIPE_INDEX
                + missingRecipeField(Field.EMPTY_IG_NAME_1);
        assertParseFailure(parser, recipeWithInvalidIngredient1, Ingredient.MESSAGE_CONSTRAINTS);

        String recipeWithInvalidIngredient2 = RECIPE_INDEX + missingRecipeField(Field.EMPTY_IG_NAME_2);
        assertParseFailure(parser, recipeWithInvalidIngredient2, Ingredient.MESSAGE_CONSTRAINTS);

        String recipeWithInvalidIngredient3 = RECIPE_INDEX + missingRecipeField(Field.EMPTY_IG_NAME_3);
        assertParseFailure(parser, recipeWithInvalidIngredient3, Ingredient.MESSAGE_CONSTRAINTS);

        String recipeWithInvalidIngredient4 = RECIPE_INDEX + missingRecipeField(Field.EMPTY_IG_NAME_4);
        assertParseFailure(parser, recipeWithInvalidIngredient4, Ingredient.MESSAGE_CONSTRAINTS);


        // invalid tag specified
        String recipeWithInvalidTag = invalidRecipeField(Field.TAG);
        assertParseFailure(parser, recipeWithInvalidTag, Tag.MESSAGE_CONSTRAINTS);

        // invalid recipe name specified
        String recipeWithInvalidName = invalidRecipeField(Field.RECIPE_NAME);
        assertParseFailure(parser, recipeWithInvalidName, Name.MESSAGE_CONSTRAINTS);

        // invalid recipe calorie specified: zero
        String recipeWithZeroCalories = invalidRecipeCalorieField(CommandTestUtil.Number.ZERO);
        assertParseFailure(parser, recipeWithZeroCalories, Calories.MESSAGE_CONSTRAINTS);

        // invalid recipe calorie specified: non integer
        String recipeWithNonIntegerCalories = invalidRecipeCalorieField(CommandTestUtil.Number.NON_INTEGER);
        assertParseFailure(parser, recipeWithNonIntegerCalories, Calories.MESSAGE_CONSTRAINTS);

        // invalid recipe calorie specified: negative
        String recipeWithNegativeCalories = invalidRecipeCalorieField(CommandTestUtil.Number.NEGATIVE);
        assertParseFailure(parser, recipeWithNegativeCalories, Calories.MESSAGE_CONSTRAINTS);


        //The test cases testing for one invalid field
        // invalid ingredient specified
        assertParseFailure(parser, RECIPE_INDEX + " " + INVALID_INGREDIENT_DESC,
                Ingredient.MESSAGE_CONSTRAINTS);

        // invalid tag specified
        assertParseFailure(parser, RECIPE_INDEX + " " + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid recipe name specified
        assertParseFailure(parser, RECIPE_INDEX + " " + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // invalid recipe calorie specified: zero
        assertParseFailure(parser, RECIPE_INDEX + " " + ZERO_CALORIES_DESC,
                Calories.MESSAGE_CONSTRAINTS);

        // invalid recipe calorie specified: non integer
        assertParseFailure(parser, RECIPE_INDEX + " " + NON_INTEGER_CALORIES_DESC,
                Calories.MESSAGE_CONSTRAINTS);

        // invalid recipe calorie specified: negative
        assertParseFailure(parser, RECIPE_INDEX + " " + NEGATIVE_CALORIES_DESC,
                Calories.MESSAGE_CONSTRAINTS);


        // while parsing {@code PREFIX_TAG} alone will reset the tags
        // of the {@code Recipe} being edited,
        // parsing it together with a invalid tag results in error
        assertParseFailure(parser, RECIPE_INDEX + TAG_DESC_MARGARITAS + TAG_DESC_NOODLE
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, RECIPE_INDEX + INVALID_TAG_DESC + TAG_DESC_NOODLE
                + TAG_DESC_MARGARITAS, Tag.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the invalid value captured is ordered
        // take note that missing instructions in edit command is valid
        // by: 1. Name 2. Ingredient 3. Calories 4. Instructions 5. Recipe Image 6. Tags
        assertParseFailure(parser, RECIPE_INDEX + INVALID_NAME_DESC + INVALID_INGREDIENT_DESC,
                Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, RECIPE_INDEX + NEGATIVE_CALORIES_DESC + INVALID_INGREDIENT_DESC,
                Ingredient.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, RECIPE_INDEX + MISSING_INSTRUCTIONS_DESC + NEGATIVE_CALORIES_DESC,
                Calories.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, RECIPE_INDEX + INVALID_TAG_DESC + MISSING_INSTRUCTIONS_DESC,
               Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInputNoodle = targetIndex.getOneBased() + DESC_NOODLE_USER_INPUT;
        EditRecipeDescriptor descriptorNoodle = VALID_DESCRIPTOR_NOODLE;
        EditRecipeCommand expectedCommandNoodle = new EditRecipeCommand(targetIndex, descriptorNoodle);
        assertParseSuccess(parser, userInputNoodle, expectedCommandNoodle);

        String userInputMargaritas = targetIndex.getOneBased() + DESC_MARGARITAS_USER_INPUT;
        EditRecipeDescriptor descriptorMargaritas = VALID_DESCRIPTOR_MARGARITAS;
        EditRecipeCommand expectedCommandMargaritas = new EditRecipeCommand(targetIndex, descriptorMargaritas);
        assertParseSuccess(parser, userInputMargaritas, expectedCommandMargaritas);

    }

    /*@Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INGREDIENT_DESC_BOB
        <<<<<<< HEAD
        =======
                + CALORIES_DESC_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB;
        >>>>>>> 28ef26c6db44f8717ea885749a69ad068c33a886

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withIngredient(VALID_INGREDIENT_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }*/

    @Test
    public void parse_oneMissingField_success() {
        // no recipe name
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInputNoRecipeName = targetIndex.getOneBased()
                + missingRecipeFieldWithoutPrefix(Field.RECIPE_NAME);
        EditRecipeDescriptor descriptorNoRecipeName = recipeDescriptor(Field.RECIPE_NAME);
        EditRecipeCommand expectedCommandNoRecipeName = new EditRecipeCommand(targetIndex, descriptorNoRecipeName);
        assertParseSuccess(parser, userInputNoRecipeName, expectedCommandNoRecipeName);

        // no tag specified
        String userInputNoTag = targetIndex.getOneBased()
                + missingRecipeFieldWithoutPrefix(Field.TAG);
        EditRecipeDescriptor descriptorNoTag = recipeDescriptor(Field.TAG);
        EditRecipeCommand expectedCommandNoTag = new EditRecipeCommand(targetIndex, descriptorNoTag);
        assertParseSuccess(parser, userInputNoTag, expectedCommandNoTag);

        // no instruction specified
        String userInputNoInstruction = targetIndex.getOneBased()
                + missingRecipeFieldWithoutPrefix(Field.INSTRUCTIONS);
        EditRecipeDescriptor descriptorNoInstruction = recipeDescriptor(Field.INSTRUCTIONS);
        EditRecipeCommand expectedCommandNoInstruction = new EditRecipeCommand(targetIndex, descriptorNoInstruction);
        assertParseSuccess(parser, userInputNoInstruction, expectedCommandNoInstruction);

        // no ingredient specified
        String userInputNoIngredient = targetIndex.getOneBased()
                + missingRecipeFieldWithoutPrefix(Field.INGREDIENT);
        EditRecipeDescriptor descriptorNoIngredient = recipeDescriptor(Field.INGREDIENT);
        EditRecipeCommand expectedCommandNoIngredient = new EditRecipeCommand(targetIndex, descriptorNoIngredient);
        assertParseSuccess(parser, userInputNoIngredient, expectedCommandNoIngredient);
    }

    @Test
    public void parse_clearTag_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInputClearTag = targetIndex.getOneBased() + missingRecipeField(Field.TAG);
        EditRecipeDescriptor descriptorClearTag = VALID_DESCRIPTOR_CLEAR_TAGS;
        EditRecipeCommand expectedCommandClearTag = new EditRecipeCommand(targetIndex, descriptorClearTag);
        assertParseSuccess(parser, userInputClearTag, expectedCommandClearTag);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_NOODLE;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_NOODLE)
                .build();
        EditRecipeCommand expectedCommand = new EditRecipeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ingredients
        userInput = targetIndex.getOneBased() + INGREDIENT_DESC_NOODLE;
        descriptor = new EditRecipeDescriptorBuilder()
                .withIngredient(VALID_INGREDIENT_NOODLE, VALID_QUANTITY_NOODLE).build();
        expectedCommand = new EditRecipeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_MARGARITAS;
        descriptor = new EditRecipeDescriptorBuilder().withTags(VALID_TAG_MARGARITAS).build();
        expectedCommand = new EditRecipeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //recipe image
        userInput = targetIndex.getOneBased() + INVALID_RECIPE_IMAGE_DESC_SANDWICH;
        descriptor = new EditRecipeDescriptorBuilder().withImage(INVALID_RECIPE_IMAGE_SANDWICH).build();
        expectedCommand = new EditRecipeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /*@Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INGREDIENT_DESC_AMY
                + TAG_DESC_FRIEND + INGREDIENT_DESC_AMY+ TAG_DESC_FRIEND
                + INGREDIENT_DESC_BOB+ TAG_DESC_HUSBAND;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withIngredient(VALID_INGREDIENT_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }*/

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INVALID_INGREDIENT_DESC + INGREDIENT_DESC_MARGARITAS;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS).build();
        EditRecipeCommand expectedCommand = new EditRecipeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_INGREDIENT_DESC + INGREDIENT_DESC_MARGARITAS;
        descriptor = new EditRecipeDescriptorBuilder()
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS).build();
        expectedCommand = new EditRecipeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /*@Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }*/
}
