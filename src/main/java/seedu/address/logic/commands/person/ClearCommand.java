package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.ModelPerson;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Cleared all contacts!";


    @Override
    public CommandResult execute(ModelPerson modelPerson) throws CommandException {
        requireNonNull(modelPerson);

        if (modelPerson.getInternalPersonList().size() == 0) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_CONTACT_LIST_EMPTY, COMMAND_WORD));
        }

        modelPerson.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
