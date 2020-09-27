package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.parameter.OptionalParameter;
import seedu.address.logic.parser.parameter.Parameter;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Parameter<Index> indexParameter = this.addParameter(
        "index",
        "",
        "index number used in the displayed person list.",
        "2",
        ParserUtil::parseIndex
    );
    private final OptionalParameter<Name> nameParameter = this.addOptionalParameter(
        "name",
        "n",
        "Name of person to add",
        "John Doe",
        ParserUtil::parseName
    );
    private final OptionalParameter<Phone> phoneParameter = this.addOptionalParameter(
        "phone",
        "p",
        "Phone number of person to add",
        "98765432",
        ParserUtil::parsePhone
    );
    private final OptionalParameter<Email> emailParameter = this.addOptionalParameter(
        "email",
        "e",
        "Email address of person to add",
        "johnd@example.com",
        ParserUtil::parseEmail
    );

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Index index = indexParameter.consume();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Name newName = this.nameParameter.getValue().orElseGet(personToEdit::getName);
        Phone newPhone = this.phoneParameter.getValue().orElseGet(personToEdit::getPhone);
        Email newEmail = this.emailParameter.getValue().orElseGet(personToEdit::getEmail);

        // as with AddCommand, address and get tags left as exercises
        Person editedPerson = new Person(newName, newPhone, newEmail,
            personToEdit.getAddress(), personToEdit.getTags());

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }
}
