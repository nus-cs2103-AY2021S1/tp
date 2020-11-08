package seedu.address.testutil.gradetracker;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_PERCENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gradetrackercommands.AddAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.DeleteAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;

/**
 * A utility class for Assignment.
 */
public class AssignmentUtil {

    /**
     * Returns an add assignment command string for adding the {@code assignment}.
     */
    public static String getAddAssignmentCommand(ModuleName moduleName, Assignment assignment) {
        return AddAssignmentCommand.COMMAND_WORD + " " + PREFIX_NAME + moduleName.fullName
                + " " + getAssignmentDetails(assignment);
    }

    public static String getDeleteAssignmentCommand(Index index, ModuleName moduleName) {
        return DeleteAssignmentCommand.COMMAND_WORD + " " + index.getOneBased()
                + " " + PREFIX_NAME + moduleName.fullName;
    }

    public static String getEditAssignmentCommand(Index index, ModuleName moduleName, Assignment assignment) {
        return EditAssignmentCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_NAME + moduleName.fullName
                + " " + getAssignmentDetails(assignment);
    }

    /**
     * Returns the part of command string for the given {@code assignment}'s details.
     */
    public static String getAssignmentDetails(Assignment assignment) {
        StringBuilder sb = new StringBuilder();
        if (assignment.getAssignmentName().isPresent()) {
            sb.append(PREFIX_ASSIGNMENT_NAME + assignment.getAssignmentName().get().assignmentName + " ");
        }
        sb.append(PREFIX_ASSIGNMENT_PERCENTAGE + Double.toString(assignment.getAssignmentPercentage()
                .get().assignmentPercentage) + " ");
        sb.append(PREFIX_ASSIGNMENT_RESULT + Double.toString(assignment.getAssignmentResult()
                .get().assignmentResult) + " ");
        return sb.toString();
    }
}
