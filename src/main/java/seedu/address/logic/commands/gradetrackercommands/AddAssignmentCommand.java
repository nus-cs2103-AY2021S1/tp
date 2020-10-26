package seedu.address.logic.commands.gradetrackercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_NEW_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERCENTAGE_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT_ASSIGNMENT;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.grade.Assignment;

public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "addassignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the grade tracker for a module. "
            + "Parameters: "
            + PREFIX_NAME + "MODULE NAME "
            + PREFIX_ADD_NEW_ASSIGNMENT + "ASSIGNMENT NAME "
            + PREFIX_PERCENTAGE_ASSIGNMENT + " PERCENTAGE OF FINAL GRADE "
            + PREFIX_RESULT_ASSIGNMENT + " RESULT ACHIEVED "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2100 "
            + PREFIX_ADD_NEW_ASSIGNMENT + "Quiz 1"
            + PREFIX_PERCENTAGE_ASSIGNMENT + " 15"
            + PREFIX_RESULT_ASSIGNMENT + " 0.85";

    public static final String MESSAGE_SUCCESS = "New assignment %1$s added.";
    public static final String MESSAGE_ASSIGNMENT_NOT_ADDED = "Module to add to not found.";

    private final String moduleToAdd;
    private final Assignment assignmentToAdd;

    /**
     * Creates an AddAssignmentCommand to add the specified {@code Assignment}
     */
    public AddAssignmentCommand(String moduleToAdd, Assignment assignment) {
        requireNonNull(assignment);
        this.moduleToAdd = moduleToAdd;
        this.assignmentToAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = null;
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module eachModule : lastShownList) {
            if (eachModule.getName().fullName.equals(moduleToAdd)) {
                module = eachModule;
                break;
            }
        }
        if (module == null) {
            throw new CommandException(MESSAGE_ASSIGNMENT_NOT_ADDED);
        }
        module.addAssignment(assignmentToAdd);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, assignmentToAdd));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
