package seedu.address.logic.commands.ingredientcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

/**
 * Shows the ingredients that have low ingredient levels and need to be restocked.
 */
public class IngredientRestockCommand extends Command {

    public static final char LINE_SEPARATOR = '\n';

    public static final String COMMAND_WORD = "i-restock";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the ingredient's levels of all ingredient types"
            + " that fall below their minimum stock levels and require the user to restock.\n"
            + "Parameters: There are no parameters.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Here is the list of all ingredients that should be restocked: \n\n";

    public static final String MESSAGE_NO_NEED_TO_RESTOCK = "All Ingredients has not fallen below restock levels.";

    private String ingredientList = "";

    /**
     * Constructs an Ingredient List command.
     */
    public IngredientRestockCommand() {
        super();
    }

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
        ArrayList<Ingredient> ingredientInShortage = model.findIngredientInShortage();
        int sizeOfList = ingredientInShortage.size();
        if (sizeOfList == 0) {
            throw new CommandException(MESSAGE_NO_NEED_TO_RESTOCK);
        }
        for (Ingredient i : ingredientInShortage) {
            ingredientList += i.toString() + LINE_SEPARATOR;
        }
        return new CommandResult(MESSAGE_SUCCESS + ingredientList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IngredientRestockCommand)) {
            return false;
        }
        return true;
    }

}

