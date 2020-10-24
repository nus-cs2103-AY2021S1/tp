package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Ingredient;

/**
 * Adds an ingredient to the the fridge.
 */
public class AddIngredientCommand extends Command {

    public static final String COMMAND_WORD = "addF";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the fridge.\n"
            + "Parameters: "
            + PREFIX_INGREDIENT + "INGREDIENT "
            + "[" + PREFIX_QUANTITY + "QUANTITY" + "]" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT + "bread, oranges " + PREFIX_QUANTITY + "2kg" + ", cheese ";

    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This ingredient already exists in the fridge";

    private final ArrayList<Ingredient> toAdd;

    /**
     * Creates an AddIngredientCommand to add the specified {@code Ingredients}
     */
    public AddIngredientCommand(ArrayList<Ingredient> ingredients) {
        for (Ingredient ingredient: ingredients) {
            requireNonNull(ingredient);
        }
        toAdd = ingredients;
    }

    public ArrayList<Ingredient> getToAdd() {
        return toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StringBuilder ingredientsAdded = new StringBuilder();
        for (Ingredient ingredient : toAdd) {
            if (model.hasIngredient(ingredient)) {
                throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
            }
            ingredientsAdded.append(ingredient);
            model.addIngredient(ingredient);
        }
        String ingredientsAddedString = ingredientsAdded.toString();
        //        ingredientsAddedString = ingredientsAddedString.substring(1, ingredientsAddedString.length() - 1);
        return new CommandResult(String.format(MESSAGE_SUCCESS, ingredientsAddedString));
    }

    private boolean checkEachIngredient(ArrayList<Ingredient> firstToCheck, ArrayList<Ingredient> otherToCheck) {
        if (firstToCheck.size() != otherToCheck.size()) {
            return false;
        }
        for (int i = 0; i < firstToCheck.size(); i++) {
            if (!firstToCheck.get(i).equals(otherToCheck.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIngredientCommand // instanceof handles nulls
                && checkEachIngredient(toAdd, ((AddIngredientCommand) other).getToAdd()));
    }
}
