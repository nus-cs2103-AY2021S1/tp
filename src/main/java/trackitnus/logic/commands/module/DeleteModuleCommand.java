package trackitnus.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;

import java.util.Optional;

import javafx.collections.ObservableList;
import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.commons.Code;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;

public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = Module.TYPE + " " + COMMAND_WORD
        + ": Deletes the module identified by the module code.\n"
        + "Parameters: " + PREFIX_CODE + "MODULE_CODE (must be an existing code)\n"
        + String.format("Example: %s %s %sCS1231S", Module.TYPE, COMMAND_WORD, PREFIX_CODE);

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final Code targetCode;

    public DeleteModuleCommand(Code targetCode) {
        this.targetCode = targetCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Module> moduleToDelete = model.getModule(targetCode);
        if (moduleToDelete.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        // delete all the related tasks
        ObservableList<Task> moduleTasks = model.getModuleTasks(targetCode);
        for (Task task : moduleTasks) {
            model.deleteTask(task);
        }

        // delete all the related lessons
        ObservableList<Lesson> moduleLessons = model.getModuleLessons(targetCode);
        for (Lesson lesson : moduleLessons) {
            model.deleteLesson(lesson);
        }

        // delete the module
        model.deleteModule(moduleToDelete.get());
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteModuleCommand // instanceof handles nulls
            && targetCode.equals(((DeleteModuleCommand) other).targetCode)); // state check
    }
}
