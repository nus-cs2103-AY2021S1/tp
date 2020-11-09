package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.information.Person;

/**
 * Lists all persons in the person address book to the user.
 */
public class ListPersonCommand extends Command {

    public static final String COMMAND_WORD = "list can";

    public static final String MESSAGE_SUCCESS = "Listed all candidates";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, Person.TAB_NAME);
    }
}
