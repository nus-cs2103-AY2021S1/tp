package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the animal identified by the ID in the displayed animal list.\n"
            + "Parameters: ID (must be a positive integer > 3 digits)\n"
            + "Example: " + COMMAND_WORD + " 123";

    public static final String MESSAGE_DELETE_ANIMAL_SUCCESS = "Deleted Animal: %1$s";

    private final Id targetID;

    public DeleteCommand(Id targetID) {
        this.targetID = targetID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person animalToDelete = null;
        for (Person person : lastShownList) {
            if (person.getId().equals(targetID)) {
                animalToDelete = person;
                break;
            }
        }

        if (animalToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
        }
        model.deletePerson(animalToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ANIMAL_SUCCESS, animalToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetID.equals(((DeleteCommand) other).targetID)); // state check
    }
}
