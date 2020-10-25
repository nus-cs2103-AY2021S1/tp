package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fma.model.Model.PREDICATE_SHOW_ALL_LOGS;

import seedu.fma.model.Model;

/**
 * Lists all logs
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all logs";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLogList(PREDICATE_SHOW_ALL_LOGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
