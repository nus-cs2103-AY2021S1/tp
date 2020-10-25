package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

/**
 * Calculates the profit in an account.
 */
public class GetProfitCommand extends Command {
    public static final String COMMAND_WORD = "profit";
    public static final String MESSAGE_SUCCESS = "Profit: ";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);

        double profits = activeAccount.getProfits();
        return CommandResultFactory.createDefaultCommandResult(MESSAGE_SUCCESS + String.format("%.2f", profits));
    }
}
