package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.tag.Tag;

/**
 * Adds a new category tag into the tag list if the given argument is valid.
 * Duplicate category tag is not allowed.
 */
public class AddCategoryCommand extends Command {

    public static final String COMMAND_WORD = "addCat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a category to the expense book. "
            + "Parameters: "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Food";

    public static final String MESSAGE_SUCCESS = "New category added: %s ";
    public static final String MESSAGE_DUPLICATE_CATEGORY = "This category already exists in the expense book.";

    private final Tag toAdd;

    /**
     * Creates an AddCategoryCommand to add the specified {@code categoryBudget}.
     */
    public AddCategoryCommand(Tag tag) {
        requireNonNull(tag);
        toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCategory(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CATEGORY);
        }

        model.addCategory(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof AddCategoryCommand // instanceof handles nulls
                && toAdd.equals(((AddCategoryCommand) other).toAdd);
    }
}
