package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;

/**
 * Adds a entry to the Common Cents.
 */
public class AddAccountCommand extends Command {
    public static final String COMMAND_WORD = "newacc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Account to "
            + "Common Cents\n"
            + "Parameters: " + PREFIX_NAME + "ACCOUNT NAME\n"
            + "Example: " + PREFIX_NAME + "My Non-Biz Account";
    public static final String PREFIXES = PREFIX_NAME + "ACCOUNT NAME";


    public static final String MESSAGE_SUCCESS = "New account added! %1$s";
    public static final String MESSAGE_DUPLICATE_ACCOUNT = "This account already exists in the app!";

    private final Account account;

    /**
     * Creates an AddAccountCommand to add the specified {@code Account}
     */
    public AddAccountCommand(Account account) {
        requireNonNull(account);
        this.account = account;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireAllNonNull(model, activeAccount);

        if (model.hasAccount(this.account)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACCOUNT);
        }
        model.addAccount(this.account);
        return CommandResultFactory
            .createCommandResultForEntryListChangingCommand(String.format(MESSAGE_SUCCESS, this.account));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAccountCommand // instanceof handles nulls
                && account.getName().equals(((AddAccountCommand) other).account.getName()));
    }

}
