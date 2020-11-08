package seedu.cc.logic.commands.entrylevel;

import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;

/**
 * Calculates the profit in an account.
 */
public class GetProfitCommand extends Command {
    public static final String COMMAND_WORD = "profit";
    public static final String MESSAGE_SUCCESS = "Profit: ";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        assert (model != null) && (activeAccount != null);

        double profits = activeAccount.getProfits();
        return CommandResultFactory.createDefaultCommandResult(MESSAGE_SUCCESS + String.format("%.2f", profits));
    }
}
