package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_ROLE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ModelPerson;
import seedu.address.model.person.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to the contact list.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ROLE + "stk "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_DESCRIPTION + "End user";

    public static final String MESSAGE_SUCCESS = "Added contact: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This contact already exists in the contact list.";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(ModelPerson modelPerson) throws CommandException {
        requireNonNull(modelPerson);

        if (modelPerson.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        modelPerson.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
