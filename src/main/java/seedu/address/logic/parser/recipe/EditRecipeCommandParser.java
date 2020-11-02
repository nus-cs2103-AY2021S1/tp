package seedu.address.logic.parser.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.EditRecipeCommand;
import seedu.address.logic.commands.recipe.EditRecipeCommand.EditRecipeDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.data.ImageParser;
import seedu.address.logic.parser.data.IngredientParser;
import seedu.address.logic.parser.data.InstructionParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Calories;
import seedu.address.model.recipe.Instruction;
import seedu.address.model.recipe.RecipeImage;
import seedu.address.model.recipe.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditRecipeCommandParser implements Parser<EditRecipeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditRecipeCommand parse(String args) throws ParseException, IOException, URISyntaxException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INGREDIENT, PREFIX_CALORIES,
                        PREFIX_INSTRUCTION, PREFIX_RECIPE_IMAGE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRecipeCommand.MESSAGE_USAGE), pe);
        }

        EditRecipeDescriptor editRecipeDescriptor = new EditRecipeDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editRecipeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_INGREDIENT).isPresent()) {
            String ingredientString = ParserUtil.parseIngredient(argMultimap
                    .getValue(PREFIX_INGREDIENT).get());
            ArrayList<Ingredient> ingredients = IngredientParser.parse(ingredientString);
            editRecipeDescriptor.setIngredient(ingredients);
        }
        if (argMultimap.getValue(PREFIX_INSTRUCTION).isPresent()) {
            String instructionString = argMultimap.getValue(PREFIX_INSTRUCTION).get();
            ArrayList<Instruction> instructions = InstructionParser.parse(instructionString);
            editRecipeDescriptor.setInstruction(instructions);
        }
        if (argMultimap.getValue(PREFIX_CALORIES).isPresent()) {
            Calories calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES).get());
            editRecipeDescriptor.setCalories(calories);
        }
        if (argMultimap.getValue(PREFIX_RECIPE_IMAGE).isPresent()) {
            String recipeImageString = argMultimap.getValue(PREFIX_RECIPE_IMAGE).get();
            ImageParser imageParser = new ImageParser();
            RecipeImage recipeImage = imageParser.parse(recipeImageString);
            editRecipeDescriptor.setRecipeImage(recipeImage);
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            editRecipeDescriptor.setTags(tagList);
        }

        if (!editRecipeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRecipeCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRecipeCommand(index, editRecipeDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
