package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.expense.Expense;

/**
 * Adds an expense to the expense book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to the expense book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + " DESCRIPTION "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Uniqlo Jacket "
            + PREFIX_AMOUNT + "59.90 "
            + PREFIX_DATE + "04-10-2020 "
            + PREFIX_TAG + "friends ";

    public static final String MESSAGE_SUCCESS = "New expense added: %1$s ";
    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in the expense book. "
            + "Expense should be updated ";
    public static final String MESSAGE_DEFAULT = "The category '%s' does not exist yet"
            + " -- tagging as 'Default' instead. ";

    private final Expense toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Expense}
     */
    public AddCommand(Expense expense) {
        requireNonNull(expense);
        toAdd = expense;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        if (!model.hasCategory(toAdd.getTag())) {
            Expense expense = toAdd.resetTag();
            model.addExpense(expense);
            return new CommandResult(String.format(MESSAGE_DEFAULT, toAdd.getTag().tagName));
        }

        model.addExpense(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
