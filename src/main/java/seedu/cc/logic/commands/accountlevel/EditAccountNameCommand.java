package seedu.cc.logic.commands.accountlevel;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_NAME;

import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.Name;

/**
 * Edits the details of an existing account in Common Cents.
 */
public class EditAccountNameCommand extends Command {
    public static final String COMMAND_WORD = "editacc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the current Account's name\n"
            + "Parameters: " + PREFIX_NAME + "ACCOUNT NAME\n"
            + "Example: " + PREFIX_NAME + "My flower shop";

    public static final String PREFIXES = PREFIX_NAME + "ACCOUNT NAME";

    public static final String MESSAGE_SUCCESS = "Name for account changed from %1$s -> %2$s!";

    public static final String MESSAGE_DUPLICATED_NAME = "There is already another account with this name!";

    public static final String MESSAGE_NAME_UNCHANGED = "Your current account already has this name!";

    public final Name name;

    /**
     * Creates an EditAccountCommand to edit the current {@code ActiveAccount}
     */
    public EditAccountNameCommand(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);
        Account previousAccount = activeAccount.getAccount();
        Name previousName = previousAccount.getName();

        Account accountForCheck = new Account(name);

        if (name.equals(previousName)) {
            throw new CommandException(MESSAGE_NAME_UNCHANGED);
        }

        if (model.hasAccount(accountForCheck)) {
            throw new CommandException(MESSAGE_DUPLICATED_NAME);
        }
        activeAccount.setName(name);
        Account newAccount = activeAccount.getAccount();
        model.setAccount(previousAccount, newAccount);
        Name newName = newAccount.getName();
        return CommandResultFactory
            .createDefaultCommandResult(String.format(MESSAGE_SUCCESS, previousName.toString(), newName.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditAccountNameCommand // instanceof handles nulls
                && name.equals(((EditAccountNameCommand) other).name));
    }

}
