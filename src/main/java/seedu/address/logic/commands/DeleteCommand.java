package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_ID_MISSING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "deletePerson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: " + PREFIX_PERSON_ID + "PERSON_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " pid/1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";


    private final Integer personId;

    public DeleteCommand(Integer personId) {
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonWithId(personId)) {
            throw new CommandException(MESSAGE_PERSON_ID_MISSING);
        }

        Person personToDelete = model.getPersonWithId(personId);
        model.deletePerson(personToDelete);

        model.deleteBookingByPersonId(personId);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete),
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && personId.equals(((DeleteCommand) other).personId)); // state check
    }
}
