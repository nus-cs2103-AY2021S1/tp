package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;

public class EditAccountCommand extends Command {
    public static final String COMMAND_WORD = "editacc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the current Account's name\n"
            + "Parameters: " + PREFIX_NAME + "ACCOUNT NAME ";

    public static final String MESSAGE_SUCCESS = "Name for account changed from %1$s -> %2$s!";

    public final Account account;

    /**
     * Creates an EditAccountCommand to edit the current {@code ActiveAccount}
     */
    public EditAccountCommand(Account account) {
        requireNonNull(account);
        this.account = account;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);
        Account currentAccount = activeAccount.getAccount();

        Name previousName = currentAccount.getName();
        Name newName = account.getName();

        List<Expense> currentExpenses = currentAccount.getExpenseList();
        List<Revenue> currentRevenues = currentAccount.getRevenueList();
        this.account.setExpenses(currentExpenses);
        this.account.setRevenues(currentRevenues);

        model.setAccount(currentAccount, account);
        activeAccount.setActiveAccount(this.account);

        return new CommandResult(String.format(MESSAGE_SUCCESS, previousName.toString(), newName.toString()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditAccountCommand // instanceof handles nulls
                && account.equals(((EditAccountCommand) other).account));
    }

}
