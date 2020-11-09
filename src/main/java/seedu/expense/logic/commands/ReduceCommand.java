package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;

import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.tag.Tag;

/**
 * Reduces the budget by a specified amount.
 */
public class ReduceCommand extends Command {

    public static final String COMMAND_WORD = "reduce";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reduces the category-budget by a specified amount.\n"
            + "If reducing by the full amount would result in a negative amount, reduces to zero instead.\n"
            + "If category is unspecified, reduces the default category-budget. "
            + "Parameters: "
            + PREFIX_AMOUNT + " AMOUNT "
            + "[" + PREFIX_TAG + " CATEGORY] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "59.90 "
            + PREFIX_TAG + "Food";

    public static final String MESSAGE_SUCCESS = "New budget amount for %s: $%s";
    public static final String MESSAGE_INVALID_CATEGORY = "The \"%s\" category does not exist in the expense book. "
            + "If you need to, please add it using the \"" + AddCategoryCommand.COMMAND_WORD + "\" command first.";
    public static final String MESSAGE_INSUFFICIENT_BUDGET = "The budget amount for %s was insufficient -- "
            + "amount reduced to zero instead";
    public static final String MESSAGE_INVALID_AMOUNT = "Amount to reduce the budget by cannot be negative. Please "
            + "specify the non-negative amount to reduce the budget by.\n"
            + "If you wish to increase the amount in the budget, use the \""
            + TopupCommand.COMMAND_WORD + "\" command instead.";

    private final Amount toSubtract;
    private final Tag category;

    /**
     * Creates a ReduceCommand to reduce the {@code defaultBudget} by the specified {@code Amount}.
     */
    public ReduceCommand(Amount amount) {
        requireNonNull(amount);
        toSubtract = amount;
        category = DEFAULT_TAG;
    }

    /**
     * Creates a ReduceCommand to reduce the {@code CategoryBudget} that matches the {@code tag} by the specified
     * {@code amount}.
     */
    public ReduceCommand(Amount amount, Tag tag) {
        requireAllNonNull(amount, tag);
        toSubtract = amount;
        category = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasCategory(category)) {
            throw new CommandException(String.format(MESSAGE_INVALID_CATEGORY, category));
        }

        if (toSubtract.smallerThan(Amount.zeroAmount())) {
            throw new CommandException(MESSAGE_INVALID_AMOUNT);
        }

        if (!model.categoryBudgetHasAmount(category, toSubtract)) {
            model.getCategoryBudget(category).reset();
            return new CommandResult(String.format(MESSAGE_INSUFFICIENT_BUDGET, category.tagName));
        }

        model.reduceCategoryBudget(category, toSubtract);
        return new CommandResult(String.format(MESSAGE_SUCCESS, category.tagName,
                model.getCategoryBudget(category).getAmount()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReduceCommand // instanceof handles nulls
                && toSubtract.equals(((ReduceCommand) other).toSubtract))
                && category.equals(((ReduceCommand) other).category);
    }
}
