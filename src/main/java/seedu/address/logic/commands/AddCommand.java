package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.parameter.Parameter;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private Parameter<Name> nameParameter = this.addParameter(
        "name",
        "n",
        "Name of person to add",
        "John Doe",
        ParserUtil::parseName
    );
    private Parameter<Phone> phoneParameter = this.addParameter(
        "phone",
        "p",
        "Phone number of person to add",
        "98765432",
        ParserUtil::parsePhone
    );
    private Parameter<Email> emailParameter = this.addParameter(
        "email",
        "e",
        "Email address of person to add",
        "johnd@example.com",
        ParserUtil::parseEmail

    );

    void setParameters(Parameter<Name> nameParameter, Parameter<Phone> phoneParameter,
                       Parameter<Email> emailParameter) {
        this.nameParameter = nameParameter;
        this.phoneParameter = phoneParameter;
        this.emailParameter = emailParameter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // rewriting this class as an example, address and tags not implemented.
        Person toAdd = new Person(nameParameter.consume(), phoneParameter.consume(), emailParameter.consume(),
            new Address("dummy value"), new HashSet<>());

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
