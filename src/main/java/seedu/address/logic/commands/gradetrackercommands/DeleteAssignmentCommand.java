package seedu.address.logic.commands.gradetrackercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.GradeTracker;

/**
 * Encapsulates methods and information to delete an assignment from the gradetracker of a module at a specified index.
 */
public class DeleteAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "deleteassignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the assignment at the specified position in the specified module.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "CS2100";

    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Deleted assignment %1$s from module: %2$s";
    public static final String MESSAGE_ASSIGNMENT_NOT_DELETED =
            "Assignment to be deleted or module specified is invalid.";

    private final Logger logger = LogsCenter.getLogger(DeleteAssignmentCommand.class);

    private final Index targetIndex;
    private final ModuleName targetModule;

    /**
     * Creates a DeleteAssignmentCommand to remove the specified assignment in the specified module.
     *
     * @param targetIndex the index of the assignment to be removed.
     * @param targetModule the module to remove the assignment from.
     */
    public DeleteAssignmentCommand(Index targetIndex, ModuleName targetModule) {
        requireNonNull(targetIndex);
        requireNonNull(targetModule);
        logger.info("Deleting assignment " + targetIndex.getOneBased() + " from:" + targetModule.toString() + "");
        this.targetIndex = targetIndex;
        this.targetModule = targetModule;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = null;
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module eachModule : lastShownList) {
            if (eachModule.getName().equals(targetModule)) {
                module = eachModule;
                break;
            }
        }
        if (module == null) {
            throw new CommandException(MESSAGE_ASSIGNMENT_NOT_DELETED);
        }
        GradeTracker gradeTracker = module.getGradeTracker();
        if (targetIndex.getOneBased() > gradeTracker.getSortedAssignments().size() || targetIndex.getOneBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }
        Assignment assignmentToDelete = gradeTracker.getAssignments().get(targetIndex.getZeroBased());
        gradeTracker.removeAssignment(assignmentToDelete);
        gradeTracker.calculateNewGrade();
        Module updatedModule = module.setGradeTracker(gradeTracker);
        logger.info("Assignment has been deleted: " + assignmentToDelete.toString());
        model.setModule(module, updatedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete, module));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object

                || (other instanceof DeleteAssignmentCommand // instanceof handles nulls

                && targetIndex.equals(((DeleteAssignmentCommand) other).targetIndex)
                && targetModule.equals(((DeleteAssignmentCommand) other).targetModule)); // state check
    }

}
