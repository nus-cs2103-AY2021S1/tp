package seedu.address.logic.commands.gradetrackercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_PERCENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.GradeTracker;

/**
 * Encapsulates methods and information to add an assignment to the grade tracker of a module.
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "addassignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the grade tracker for a module. "
            + "Parameters: "
            + PREFIX_NAME + "MODULE NAME "
            + PREFIX_ASSIGNMENT_NAME + "ASSIGNMENT NAME "
            + PREFIX_ASSIGNMENT_PERCENTAGE + " PERCENTAGE OF FINAL GRADE "
            + PREFIX_ASSIGNMENT_RESULT + " RESULT ACHIEVED "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2100 "
            + PREFIX_ASSIGNMENT_NAME + "Quiz 1 "
            + PREFIX_ASSIGNMENT_PERCENTAGE + "15 "
            + PREFIX_ASSIGNMENT_RESULT + "85 ";

    public static final String MESSAGE_SUCCESS = "New assignment %1$s added.";
    public static final String MESSAGE_ASSIGNMENT_NOT_ADDED = "Module to add to not found.";
    public static final String MESSAGE_ASSIGNMENT_PERCENTAGE_THRESHOLD_EXCEEDED = "Adding this assignment would "
            + "exceed the total assignment percentage limit of " + GradeTracker.ASSIGNMENT_PERCENTAGE_TOTAL + "%";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the gradetracker.";


    private final Logger logger = LogsCenter.getLogger(AddAssignmentCommand.class);

    private final ModuleName moduleToAdd;
    private final Assignment assignmentToAdd;

    /**
     * Creates an AddAssignmentCommand to add the specified {@code Assignment}
     */
    public AddAssignmentCommand(ModuleName moduleToAdd, Assignment assignment) {
        requireNonNull(moduleToAdd);
        requireNonNull(assignment);
        logger.info("Adding an assignment: " + assignment.toString());
        this.moduleToAdd = moduleToAdd;
        this.assignmentToAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = null;
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module eachModule : lastShownList) {
            if (eachModule.getName().equals(moduleToAdd)) {
                module = eachModule;
                break;
            }
        }
        if (module == null) {
            throw new CommandException(MESSAGE_ASSIGNMENT_NOT_ADDED);
        }
        if (module.getGradeTracker().exceedsAssignmentPercentageThreshold(assignmentToAdd)) {
            throw new CommandException(MESSAGE_ASSIGNMENT_PERCENTAGE_THRESHOLD_EXCEEDED);
        }
        if (module.getGradeTracker().containsDuplicateAssignment(assignmentToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        Module updatedModule = module.addAssignment(assignmentToAdd);
        logger.info("Assignment has been added: " + assignmentToAdd.toString());
        module.getGradeTracker().calculateNewGrade();
        model.setModule(module, updatedModule);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, assignmentToAdd));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAssignmentCommand)) {
            return false;
        }

        // state check
        AddAssignmentCommand command = (AddAssignmentCommand) other;
        return moduleToAdd.equals(command.moduleToAdd)
                && assignmentToAdd.equals(command.assignmentToAdd);
    }
}
