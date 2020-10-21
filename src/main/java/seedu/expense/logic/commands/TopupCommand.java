package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_AMOUNT;

import seedu.expense.model.Model;
import seedu.expense.model.expense.Amount;

/**
 * Tops up the budget by a specified amount.
 */
public class TopupCommand extends Command {

    public static final String COMMAND_WORD = "topup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tops up the budget by a specified amount. "
            + "Parameters: "
            + PREFIX_AMOUNT + " AMOUNT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "59.90";

    public static final String MESSAGE_SUCCESS = "New budget amount: $%.02f";

    private final Amount toAdd;

    /**
     * Creates a TopupCommand to top up the current {@code Budget} by the specified {@code Amount}.
     */
    public TopupCommand(Amount amount) {
        requireNonNull(amount);
        toAdd = amount;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.topupBudget(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getTotalBudget().getAmount().asDouble()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TopupCommand // instanceof handles nulls
                && toAdd.equals(((TopupCommand) other).toAdd));
    }
}
