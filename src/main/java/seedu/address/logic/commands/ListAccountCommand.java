package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;

/**
 * Lists all the account in Common Cents oto the user.
 */
public class ListAccountCommand extends Command {
    public static final String COMMAND_WORD = "listacc";
    public static final String MESSAGE_SUCCESS_INTRO = "Listed all accounts:";

    private static final String NEW_LINE = "\n";
    private static final String EMPTY_SPACE = " ";
    private static final String DOT = ".";
    private static final int STARTING_ACC_INDEX = 1;


    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        requireAllNonNull(model, activeAccount);
        List<Account> accounts = model.getFilteredAccountList();
        String messageSuccess = accountsToString(accounts);
        return new CommandResult(messageSuccess);
    }

    /**
     * Generates a meaningful String message containing all the accounts.
     * @param accounts Account list of the model.
     * @return String containing names of the accounts.
     */
    public String accountsToString(List<Account> accounts) {
        StringBuilder sb = new StringBuilder();
        sb.append(MESSAGE_SUCCESS_INTRO).append(NEW_LINE);
        int index = STARTING_ACC_INDEX;
        for (Account account: accounts) {
            // Account index
            sb.append(index).append(DOT).append(EMPTY_SPACE);
            // Account details
            sb.append(account.toString()).append(EMPTY_SPACE).append(NEW_LINE);
            index++;
        }
        return sb.toString();
    }

}
