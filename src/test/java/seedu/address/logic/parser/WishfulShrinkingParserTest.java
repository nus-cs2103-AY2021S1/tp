package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONSUMPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.consumption.ClearConsumptionCommand;
import seedu.address.logic.commands.consumption.DeleteConsumptionCommand;
import seedu.address.logic.commands.consumption.EatRecipeCommand;
import seedu.address.logic.commands.consumption.ListConsumptionCommand;
import seedu.address.logic.commands.ingredient.AddIngredientCommand;
import seedu.address.logic.commands.ingredient.ClearIngredientCommand;
import seedu.address.logic.commands.ingredient.DeleteIngredientCommand;
import seedu.address.logic.commands.ingredient.GetEditIngredientCommand;
import seedu.address.logic.commands.ingredient.ListIngredientsCommand;
import seedu.address.logic.commands.ingredient.SearchIngredientCommand;
import seedu.address.logic.commands.recipe.AddRecipeCommand;
import seedu.address.logic.commands.recipe.ClearRecipeCommand;
import seedu.address.logic.commands.recipe.CloseCommand;
import seedu.address.logic.commands.recipe.DeleteRecipeCommand;
import seedu.address.logic.commands.recipe.GetEditRecipeCommand;
import seedu.address.logic.commands.recipe.ListRecipesCommand;
import seedu.address.logic.commands.recipe.RecommendCommand;
import seedu.address.logic.commands.recipe.SearchRecipeCommand;
import seedu.address.logic.commands.recipe.SelectRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientContainsKeywordsPredicate;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.IngredientBuilder;
import seedu.address.testutil.IngredientUtil;
import seedu.address.testutil.RecipeBuilder;
import seedu.address.testutil.RecipeUtil;

public class WishfulShrinkingParserTest {

    private final WishfulShrinkingParser parser = new WishfulShrinkingParser();

    @Test
    public void parseCommand_addRecipe() throws Exception {
        Recipe recipe = new RecipeBuilder().build();
        AddRecipeCommand command = (AddRecipeCommand) parser.parseCommand(RecipeUtil.getAddRecipeCommand(recipe));
        AddRecipeCommand expectedCommand = new AddRecipeCommand(recipe);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_addIngredient() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        AddIngredientCommand command = (AddIngredientCommand) parser.parseCommand(IngredientUtil
                .getAddIngredientCommand(ingredient));
        assertEquals(new AddIngredientCommand(ingredients), command);
    }

    @Test
    public void parseCommand_deleteIngredient() throws Exception {
        DeleteIngredientCommand command = (DeleteIngredientCommand) parser.parseCommand(
                DeleteIngredientCommand.COMMAND_WORD + " " + INDEX_FIRST_INGREDIENT.getOneBased());
        assertEquals(new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT), command);
    }

    @Test
    public void parseCommand_deleteRecipe() throws Exception {
        DeleteRecipeCommand command = (DeleteRecipeCommand) parser.parseCommand(
                DeleteRecipeCommand.COMMAND_WORD + " " + INDEX_FIRST_RECIPE.getOneBased());
        assertEquals(new DeleteRecipeCommand(INDEX_FIRST_RECIPE), command);
    }

    @Test
    public void parseCommand_searchRecipe() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        SearchRecipeCommand command = (SearchRecipeCommand) parser.parseCommand(
                SearchRecipeCommand.COMMAND_WORD + " " + PREFIX_NAME
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new SearchRecipeCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_searchIngredient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        SearchIngredientCommand command = (SearchIngredientCommand) parser.parseCommand(
                SearchIngredientCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new SearchIngredientCommand(new IngredientContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_eatRecipe() throws Exception {
        EatRecipeCommand command = (EatRecipeCommand) parser.parseCommand(
                EatRecipeCommand.COMMAND_WORD + " " + INDEX_FIRST_CONSUMPTION.getOneBased());
        assertEquals(new EatRecipeCommand(INDEX_FIRST_CONSUMPTION), command);
    }

    @Test
    public void parseCommand_deleteConsumption() throws Exception {
        DeleteConsumptionCommand command = (DeleteConsumptionCommand) parser.parseCommand(
                DeleteConsumptionCommand.COMMAND_WORD + " " + INDEX_FIRST_CONSUMPTION.getOneBased());
        assertEquals(new DeleteConsumptionCommand(INDEX_FIRST_CONSUMPTION), command);
    }

    @Test
    public void parseCommand_selectRecipe() throws Exception {
        SelectRecipeCommand command = (SelectRecipeCommand) parser.parseCommand(
                SelectRecipeCommand.COMMAND_WORD + " " + INDEX_FIRST_CONSUMPTION.getOneBased());
        assertEquals(new SelectRecipeCommand(INDEX_FIRST_CONSUMPTION), command);
    }

    /*@Test
    public void parseCommand_edit() throws Exception {
        Recipe recipe = new RecipeBuilder().build();
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(recipe).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
        assertEquals(new EditCommand(INDEX_FIRST_RECIPE, descriptor), command);
    }*/


    @Test
    public void parseCommand_getEditRecipe() throws Exception {
        GetEditRecipeCommand command = (GetEditRecipeCommand) parser.parseCommand(
                GetEditRecipeCommand.COMMAND_WORD + " " + INDEX_FIRST_RECIPE.getOneBased());
        assertEquals(new GetEditRecipeCommand(INDEX_FIRST_RECIPE), command);
    }

    @Test
    public void parseCommand_getEditIngredient() throws Exception {
        GetEditIngredientCommand command = (GetEditIngredientCommand) parser.parseCommand(
                GetEditIngredientCommand.COMMAND_WORD + " " + INDEX_FIRST_INGREDIENT.getOneBased());
        assertEquals(new GetEditIngredientCommand(INDEX_FIRST_INGREDIENT), command);
    }

    @Test
    public void parseCommand_clearRecipe() throws Exception {
        assertTrue(parser.parseCommand(ClearRecipeCommand.COMMAND_WORD) instanceof ClearRecipeCommand);
    }

    @Test
    public void parseCommand_clearIngredient() throws Exception {
        assertTrue(parser.parseCommand(ClearIngredientCommand.COMMAND_WORD) instanceof ClearIngredientCommand);
    }

    @Test
    public void parseCommand_clearConsumption() throws Exception {
        assertTrue(parser.parseCommand(ClearConsumptionCommand.COMMAND_WORD) instanceof ClearConsumptionCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listRecipe() throws Exception {
        assertTrue(parser.parseCommand(ListRecipesCommand.COMMAND_WORD) instanceof ListRecipesCommand);
    }

    @Test
    public void parseCommand_listIngredient() throws Exception {
        assertTrue(parser.parseCommand(ListIngredientsCommand.COMMAND_WORD) instanceof ListIngredientsCommand);
    }

    @Test
    public void parseCommand_listConsumption() throws Exception {
        assertTrue(parser.parseCommand(ListConsumptionCommand.COMMAND_WORD) instanceof ListConsumptionCommand);
    }

    @Test
    public void parseCommand_recommend() throws Exception {
        assertTrue(parser.parseCommand(RecommendCommand.COMMAND_WORD) instanceof RecommendCommand);
    }

    @Test
    public void parseCommand_close() throws Exception {
        assertTrue(parser.parseCommand(CloseCommand.COMMAND_WORD) instanceof CloseCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_noArgumentCommand_throwsParseException() {
        String noArgumentCommandUsage = CloseCommand.COMMAND_WORD
                + ": should not have any arguments\n"
                + "Example: " + CloseCommand.COMMAND_WORD;
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, noArgumentCommandUsage), ()
            -> parser.parseCommand(CloseCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
