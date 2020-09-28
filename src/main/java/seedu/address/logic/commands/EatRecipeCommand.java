package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.recipe.Recipe;

public class EatRecipeCommand extends Command {
    public static final String COMMAND_WORD = "eatR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the daily consumption. "
            + "Parameters:  INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_EAT_RECIPE_SUCCESS = "Eat %1$s";

    private final Index targetIndex;

    /**
     * Creates an AddIngredientCommand to add the specified {@code Ingredients}
     */
    public EatRecipeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEat = lastShownList.get(targetIndex.getZeroBased());
        model.addConsumption(new Consumption(recipeToEat));
        return new CommandResult(String.format(MESSAGE_EAT_RECIPE_SUCCESS, recipeToEat));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EatRecipeCommand // instanceof handles nulls
                && targetIndex.equals(((EatRecipeCommand) other).targetIndex)); // state check
    }
}
