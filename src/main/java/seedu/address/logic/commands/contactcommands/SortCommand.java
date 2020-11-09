package seedu.address.logic.commands.contactcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.PersonNameComparator;

/**
 * Sort all the contacts in Projact by their names in alphabetical order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "These are the contacts in your person list "
                                                    + "sorted in alphanumerical order.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedPersonList(new PersonNameComparator());
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true, false, true);
    }
}
