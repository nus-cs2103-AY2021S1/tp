package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.PersonAddressBook;
import seedu.address.model.information.Person;

/**
 * Clears the candidate list.
 */
public class ClearPersonCommand extends Command {

    public static final String COMMAND_WORD = "clear can";
    public static final String MESSAGE_SUCCESS = "Candidate list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPersonAddressBook(new PersonAddressBook());
        return new CommandResult(MESSAGE_SUCCESS, Person.TAB_NAME);
    }
}
