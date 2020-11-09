package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL_LINK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.information.Person;

/**
 * Adds a person to the address book.
 */
public class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "add can";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the person address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_EXPERIENCE + "EXPERIENCE "
            + PREFIX_DATE_OF_APPLICATION + "DATE OF APPLICATION "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_URL_LINK + "URL LINK] "
            + "[" + PREFIX_SALARY + "EXPECTED SALARY] "
            + "[" + PREFIX_BLACKLIST + "IS BLACKLISTED] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_EXPERIENCE + "3.0 "
            + PREFIX_DATE_OF_APPLICATION + "31-12-20 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_URL_LINK + "linkedin.com/in/johndoe "
            + PREFIX_SALARY + "5500 "
            + PREFIX_BLACKLIST + "false "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New candidate added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This candidate already exists in the address book.";

    private final Person toAdd;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddPersonCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), Person.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonCommand) other).toAdd));
    }
}
