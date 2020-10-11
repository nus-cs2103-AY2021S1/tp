package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_TAG;

import java.util.HashSet;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.Account;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;


/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an entry (revenue or expense) to " +
                "Common Cents\n"
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_DESCRIPTION + "PHONE "
            + PREFIX_AMOUNT + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "Revenue "
            + PREFIX_DESCRIPTION + "Sale of clothes "
            + PREFIX_AMOUNT + "200 "
            + PREFIX_TAG + "blogshop "
            + PREFIX_TAG + "eCommerce";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";

    public final Entry entry;

    public Account account;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Entry entry) {
        requireNonNull(entry);
        this.entry = entry;
        this.account = new Account();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        /*
        requireNonNull(model);

        if (model.hasAccount(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
         */

        requireNonNull(model);

        if (this.entry instanceof Expense) {
            //todo: add expense to expenseList and model
            return new CommandResult(String.format(MESSAGE_SUCCESS, (Expense)this.entry));
        }
        else {
            //todo: add revenue to revenueList and model
            return new CommandResult(String.format(MESSAGE_SUCCESS, (Revenue)this.entry));
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && entry.equals(((AddCommand) other).entry));
    }
}
