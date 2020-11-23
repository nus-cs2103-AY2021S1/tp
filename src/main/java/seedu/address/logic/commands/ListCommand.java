package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BUGS;

import seedu.address.model.Model;

/**
 * Lists all bugs in the bug tracker to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all bugs";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBugList(PREDICATE_SHOW_ALL_BUGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
