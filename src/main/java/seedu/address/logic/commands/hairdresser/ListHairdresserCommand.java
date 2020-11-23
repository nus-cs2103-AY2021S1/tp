package seedu.address.logic.commands.hairdresser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_HAIRDRESSERS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all hairdressers in the address book to the user.
 */
public class ListHairdresserCommand extends Command {
    public static final String COMMAND_WORD = "list_hairdresser";

    public static final String MESSAGE_SUCCESS = "Listed all hairdressers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredHairdresserList(PREDICATE_SHOW_ALL_HAIRDRESSERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
