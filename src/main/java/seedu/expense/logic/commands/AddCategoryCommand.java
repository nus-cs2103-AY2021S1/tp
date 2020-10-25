package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.model.budget.CategoryBudget;

public class AddCategoryCommand extends Command {

    public static final String COMMAND_WORD = "addCat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a category to the expense book. "
            + "Parameters: "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "Food";

    public static final String MESSAGE_SUCCESS = "New category added: %s";

    private final CategoryBudget toAdd;

    /**
     * Creates an AddCategoryCommand to add the specified {@code categoryBudget}.
     */
    public AddCategoryCommand(CategoryBudget categoryBudget) {
        requireNonNull(categoryBudget);
        toAdd = categoryBudget;
    }


}
