package seedu.address.logic.commands.ingredientcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Set the level of one specific ingredient to a specific level.
 */
public class IngredientListCommand extends Command {

    public static final char LINE_SEPARATOR = '\n';

    public static final String COMMAND_WORD = "i-list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the ingredient levels in tCheck.\n"
            + "Parameters: There are no parameters.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all ingredients.\n";

    private String ingredientList = "";

    /**
     * Constructs an Ingredient List command.
     */
    public IngredientListCommand() {
        super();
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IngredientListCommand)) {
            return false;
        }
        return true;
    }

}
