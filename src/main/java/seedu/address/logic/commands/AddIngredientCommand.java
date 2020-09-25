package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.Arrays;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Ingredient;

/**
 * Adds an ingredient to the the fridge.
 */
public class AddIngredientCommand extends Command {

    public static final String COMMAND_WORD = "addF";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the fridge. "
            + "Parameters: "
            + PREFIX_INGREDIENT + "INGREDIENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT + "bread, oranges, cheese ";

    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This ingredient already exists in the fridge";

    private final Ingredient[] toAdd;

    /**
     * Creates an AddIngredientCommand to add the specified {@code Ingredients}
     */
    public AddIngredientCommand(Ingredient ... ingredients) {
        requireNonNull(ingredients);
        toAdd = ingredients;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        for (Ingredient ingredient : toAdd) {
            if (model.hasIngredient(ingredient)) {
                throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
            }

            model.addIngredient(ingredient);
        }

        String ingredientsAdded = Arrays.toString(toAdd);
        ingredientsAdded = ingredientsAdded.substring(1, ingredientsAdded.length() - 1);
        return new CommandResult(String.format(MESSAGE_SUCCESS, ingredientsAdded));
    }

    private boolean checkEachIngredient(Ingredient[] firstToCheck, Ingredient[] otherToCheck) {
        if (firstToCheck.length != otherToCheck.length) {
            return false;
        }
        for (int i = 0; i < firstToCheck.length; i++) {
            if (!firstToCheck[i].equals(otherToCheck[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIngredientCommand // instanceof handles nulls
                && checkEachIngredient(toAdd, ((AddIngredientCommand) other).toAdd));
    }
}
