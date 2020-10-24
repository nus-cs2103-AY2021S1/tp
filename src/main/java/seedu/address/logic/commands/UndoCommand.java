package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.model.account.ActiveAccount.PREDICATE_SHOW_ALL_REVENUE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;

/**
 * Undo the latest command executed in CommonCents.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Previous command undone!";
    public static final String MESSAGE_NO_PREVIOUS_COMMAND = "You did not input any command prior to this!";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);

        activeAccount.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        activeAccount.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);

        if (activeAccount.hasNoPreviousState()) {
            throw new CommandException(MESSAGE_NO_PREVIOUS_COMMAND);
        }

        activeAccount.returnToPreviousState();
        model.setAccount(activeAccount.getAccount());
        return CommandResultFactory.createCommandResultForEntryListChangingCommand(MESSAGE_SUCCESS);
    }
}
