package tp.acecs2103.logic.commands;

import static java.util.Objects.requireNonNull;

import tp.acecs2103.model.Model;
import tp.acecs2103.model.TaskList;

/**
 * Clears the address book.
 * To be modified.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Task List has been cleared!";

    public ClearCommand(){};

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaskList(new TaskList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
