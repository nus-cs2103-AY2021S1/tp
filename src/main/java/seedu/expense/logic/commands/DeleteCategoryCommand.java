package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.tag.Tag;

/**
 * Deletes an existing category tag in the tag list if the given argument is valid.
 * Deleting a category tag sets the expenses with same tag to DEFAULT tag.
 */
public class DeleteCategoryCommand extends Command {

    public static final String COMMAND_WORD = "deleteCat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a category from the expense book. "
        + "Parameters: "
        + PREFIX_TAG + "TAG\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TAG + "Food";

    public static final String MESSAGE_SUCCESS = "Existing category deleted: %s";
    public static final String MESSAGE_INVALID_CATEGORY = "The \"%s\" category does not exist in the expense book. ";

    private final Tag toDelete;

    /**
     * Creates an DeleteCategoryCommand to delete the specified {@code categoryBudget}.
     */
    public DeleteCategoryCommand(Tag tag) {
        requireNonNull(tag);
        toDelete = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasCategory(toDelete)) {
            throw new CommandException(String.format(MESSAGE_INVALID_CATEGORY, toDelete));
        }

        model.deleteCategory(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof DeleteCategoryCommand // instanceof handles nulls
            && toDelete.equals(((DeleteCategoryCommand) other).toDelete);
    }
}
