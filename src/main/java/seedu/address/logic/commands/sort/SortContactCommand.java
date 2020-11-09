package seedu.address.logic.commands.sort;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameComparator;
/**
 * Sorts contact list in alphabetical order.
 */
public class SortContactCommand extends SortCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts contacts by name ";
    public static final String MESSAGE_SUCCESS = "Sorted contacts by name";
    public static final String MESSAGE_EMPTY_FILTERED_PERSON_LIST = "The list is empty. Displaying an unfiltered"
            + " sorted person"
            + " list instead.";
    public static final String MESSAGE_EMPTY_PERSON_LIST = "The person list is empty. Please add persons to sort them.";
    public static final Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Comparator<Person> comparator = new PersonNameComparator();
        model.updateSortedPersonList(comparator);
        if (model.filteredAddressBookIsEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            model.updateSortedPersonList(comparator);
            if (model.filteredAddressBookIsEmpty()) {
                return new CommandResult(MESSAGE_EMPTY_PERSON_LIST, "CONTACT");
            } else {
                return new CommandResult(MESSAGE_EMPTY_FILTERED_PERSON_LIST, "CONTACT");
            }
        } else {
            return new CommandResult(MESSAGE_SUCCESS, "CONTACT");
        }
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortContactCommand);
    }
}
