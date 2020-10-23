package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;

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

    private static final String MESSAGE_SWITCH_ACCOUNT_SUCCESS = "Account switch! Current account: %1$s";
    private static final String MESSAGE_ACTIVE_ACCOUNT = "You are already on the account!";

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
        assert true;
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

        activeAccount.removePreviousState();
        return new CommandResult(String.format(MESSAGE_SWITCH_ACCOUNT_SUCCESS, toBeSwitched),
            false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SwitchAccountCommand // instanceof handles nulls
                && targetIndex.equals(((SwitchAccountCommand) other).targetIndex)); // state check
    }

}
