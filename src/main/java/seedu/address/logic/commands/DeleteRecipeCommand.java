package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_NAME;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;


public class DeleteRecipeCommand extends Command {

    public static final String COMMAND_WORD = "delr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a recipe in the recipe list. "
            + "Parameters: "
            + PREFIX_RECIPE_PRODUCT_NAME + "PRODUCT NAME "
            + PREFIX_RECIPE_ID + "RECIPE ID ";

    public static final String MESSAGE_SUCCESS = "Recipe has been deleted: %1$s";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "Recipe is not found in the recipe list";
    public static final String MESSAGE_INDEX_NOT_FOUND = "Id is out of range";


    private final String productName;
    private final Index index;

    /**
     * Creates an DeleteRecipeCommand to delete the specified {@code Item}
     */
    public DeleteRecipeCommand(String productName, Index index) {
        requireNonNull(productName);
        this.index = index;
        this.productName = productName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> recipelist = new ArrayList<>(model.getFilteredRecipeList());
        if (recipelist.stream().noneMatch(recipe -> recipe.getProductName().equals(productName))) {
            throw new CommandException(MESSAGE_RECIPE_NOT_FOUND); //recipe is not found
        }
        recipelist.removeIf(x -> !x.getProductName().equals(productName) || x.isDeleted());
        Recipe recipeToDelete;
        try {
            recipeToDelete = recipelist.get(index.getOneBased() - 1);
        } catch (Exception e) {
            throw new CommandException(MESSAGE_INDEX_NOT_FOUND); //index out of range
        }
        model.deleteRecipe(recipeToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, recipeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRecipeCommand // instanceof handles nulls
                && productName.equals(((DeleteRecipeCommand) other).productName));
    }
}
