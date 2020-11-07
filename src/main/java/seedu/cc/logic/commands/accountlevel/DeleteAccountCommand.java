package seedu.cc.logic.commands.accountlevel;

import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

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
 * Deletes an account identified using it's displayed index from CommonCents
 */
public class DeleteAccountCommand extends Command {
    public static final String COMMAND_WORD = "deleteacc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Account identified by the index number used in the displayed account list.\n"
            + "Parameters: INDEX (must be a positive integer). "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ACCOUNT_SUCCESS = "Deleted Account: %1$s";
    public static final String MESSAGE_ONE_ACCOUNT_LEFT = "You can't delete your only account left!";
    public static final String MESSAGE_ACTIVE_ACCOUNT = "The account you intend to delete is currently active!";

    private static final int SIZE_OF_ACCOUNT_LIST_WITH_ONE_ACCOUNT = 1;

    private final Index targetIndex;

    /**
     * Creates an DeleteAccountCommand to delete the specified {@code Account}
     */
    public DeleteAccountCommand(Index targetIndex) {
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

        boolean leftWithOneAccount = accounts.size() == SIZE_OF_ACCOUNT_LIST_WITH_ONE_ACCOUNT;
        if (leftWithOneAccount) {
            throw new CommandException(MESSAGE_ONE_ACCOUNT_LEFT);
        }

        Account currentActiveAccount = activeAccount.getAccount();
        Account toBeDeleted = model.getAccountFromFilteredList(index);
        boolean isAccountActive = currentActiveAccount.isSameAccount(toBeDeleted);
        if (isAccountActive) {
            throw new CommandException(MESSAGE_ACTIVE_ACCOUNT);
        }

        model.deleteAccount(toBeDeleted);
        return CommandResultFactory
            .createDefaultCommandResult(String.format(MESSAGE_DELETE_ACCOUNT_SUCCESS, toBeDeleted));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAccountCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAccountCommand) other).targetIndex)); // state check
    }

}
