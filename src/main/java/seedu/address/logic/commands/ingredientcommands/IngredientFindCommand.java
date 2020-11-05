package seedu.address.logic.commands.ingredientcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.IngredientNameContainsKeywordsPredicate;

/**
 * View the level of a single specific ingredient.
 */
public class IngredientFindCommand extends Command {

    public static final String COMMAND_WORD = "i-find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " : Finds all ingredients that have names containing "
            + "any of the specified keywords (case-insensitive) and displays them in the ingredient tracker.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " milk boba";

    private final IngredientNameContainsKeywordsPredicate predicate;

    public IngredientFindCommand(IngredientNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the IngredientFindCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INGREDIENTS_LISTED_OVERVIEW, model.getFilteredIngredientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientFindCommand // instanceof handles nulls
                && predicate.equals(((IngredientFindCommand) other).predicate)); // state check
    }
}
