package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

//import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.commands.AddIngredientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteRecipeCommand;
//import seedu.address.logic.commands.EditCommand;
//import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListRecipesCommand;
import seedu.address.logic.commands.SearchRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.testutil.IngredientBuilder;
import seedu.address.testutil.IngredientUtil;
/*import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditRecipeDescriptorBuilder;
import seedu.address.testutil.RecipeBuilder;
import seedu.address.testutil.RecipeUtil;*/

public class WishfulShrinkingParserTest {

    private final WishfulShrinkingParser parser = new WishfulShrinkingParser();

    /*@Test
    public void parseCommand_add() throws Exception {
        Recipe recipe = new RecipeBuilder().build();
        AddRecipeCommand command = (AddRecipeCommand) parser.parseCommand(RecipeUtil.getAddRecipeCommand(recipe));
        assertEquals(new AddRecipeCommand(recipe), command);
    }*/

    @Test
    public void parseCommand_addIngredient() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        AddIngredientCommand command = (AddIngredientCommand) parser.parseCommand(IngredientUtil
                .getAddIngredientCommand(ingredient));
        assertEquals(new AddIngredientCommand(ingredient), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteRecipeCommand command = (DeleteRecipeCommand) parser.parseCommand(
                DeleteRecipeCommand.COMMAND_WORD + " " + INDEX_FIRST_RECIPE.getOneBased());
        assertEquals(new DeleteRecipeCommand(INDEX_FIRST_RECIPE), command);
    }

    /*@Test
    public void parseCommand_edit() throws Exception {
        Recipe recipe = new RecipeBuilder().build();
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(recipe).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_RECIPE.getOneBased() + " " + RecipeUtil.getEditRecipeDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_RECIPE, descriptor), command);
    }*/

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
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
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListRecipesCommand.COMMAND_WORD) instanceof ListRecipesCommand);
        assertTrue(parser.parseCommand(ListRecipesCommand.COMMAND_WORD + " 3") instanceof ListRecipesCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
