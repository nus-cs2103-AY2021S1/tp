package seedu.cc.logic.commands.accountlevel;

import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.cc.model.account.ActiveAccount.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.cc.model.account.ActiveAccount.PREDICATE_SHOW_ALL_REVENUE;

import java.util.List;

import seedu.cc.commons.core.Messages;
import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.ActiveAccount;

/**
 * Switches the account details on the UI to that of the targeted account
 * identified using it's displayed index from CommonCents.
 */
public class SwitchAccountCommand extends Command {
    public static final String COMMAND_WORD = "switchacc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Switches the current account displayed to another account at the targeted index.\n"
            + "Parameters: INDEX (must be a positive integer). "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SWITCH_ACCOUNT_SUCCESS = "Account switch! Current account: %1$s";
    public static final String MESSAGE_ACTIVE_ACCOUNT = "You are already on the account!";

    private final Index targetIndex;

    /**
     * Creates an SwitchAccountCommand to delete the specified {@code Account}
     */
    public SwitchAccountCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);

        List<Account> accounts = model.getFilteredAccountList();

        int index = targetIndex.getZeroBased();
        boolean isInvalidIndex = index >= accounts.size();
        if (isInvalidIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }

        Account currentActiveAccount = activeAccount.getAccount();
        Account toBeSwitched = accounts.get(index);
        boolean isAccountActive = currentActiveAccount.isSameAccount(toBeSwitched);
        if (isAccountActive) {
            throw new CommandException(MESSAGE_ACTIVE_ACCOUNT);
        }

        activeAccount.setActiveAccount(toBeSwitched);
        activeAccount.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        activeAccount.updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);
        activeAccount.removePreviousState();
        return CommandResultFactory.createCommandResultForEntryListChangingCommand(
                String.format(MESSAGE_SWITCH_ACCOUNT_SUCCESS, toBeSwitched));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SwitchAccountCommand // instanceof handles nulls
                && targetIndex.equals(((SwitchAccountCommand) other).targetIndex)); // state check
    }

}
