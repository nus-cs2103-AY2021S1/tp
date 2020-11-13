package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.ModelPerson.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ModelPerson;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all contact(s)!";

    @Override
    public CommandResult execute(ModelPerson modelPerson) throws CommandException {
        requireNonNull(modelPerson);

        if (modelPerson.getInternalPersonList().size() == 0) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_CONTACT_LIST_EMPTY, COMMAND_WORD));
        }

        modelPerson.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
