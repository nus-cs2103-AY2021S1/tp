package seedu.address.logic.commands.ingredientcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;

/**
 * Set the level of one specific ingredient to a specific level.
 */
public class IngredientResetAllCommand extends Command {

    public static final String COMMAND_WORD = "i-reset-all";
    public static final String RESET_AMOUNT = "0";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets all ingredients' levels to zero in "
            + "tCheck.\nIf the input contains extra words, those extra words will be ignored. For example, "
            + "if the input is 'i-reset-all 121', \n"
            + "then tCheck will ignore the extra input '121' and resets all ingredients' levels to zero.\n"
            + "Parameters: There are no parameters.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "All ingredients' levels are reset to 0.";
    public static final String MESSAGE_NO_CHANGE = "All Ingredients' levels have been already at 0 before the "
            + "resetting all ingredient's levels command (i-reset-all) is entered.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        List<Ingredient> listOfIngredients = model.getFilteredIngredientList();

        boolean isNotAlreadyReset = false;
        Amount resetAmount = new Amount(RESET_AMOUNT);
        for (Ingredient i : listOfIngredients) {
            if (!i.getAmount().equals(resetAmount)) {
                isNotAlreadyReset = true;
                break;
            }
        }

        if (!isNotAlreadyReset) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }

        for (Ingredient i : listOfIngredients) {
            if (!i.getAmount().equals(resetAmount)) {
                Ingredient updatedIngredient = createResetIngredient(i);
                model.setIngredient(i, updatedIngredient);
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private static Ingredient createResetIngredient(Ingredient target) {
        assert target != null;

        Amount updatedAmount = new Amount(RESET_AMOUNT);

        return new Ingredient(target.getIngredientName(), updatedAmount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IngredientResetAllCommand)) {
            return false;
        }
        return true;
    }

}
