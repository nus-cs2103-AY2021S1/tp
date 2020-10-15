// TODO or delete

package seedu.fma.testutil;

import static seedu.fma.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_REPS;

import seedu.fma.logic.commands.AddCommand;
import seedu.fma.logic.commands.EditCommand;
import seedu.fma.model.log.Log;


/**
 * A utility class for Log.
 */
public class LogUtil {

    /**
     * Returns an add command string for adding the {@code log}.
     *
     * @param log Log to be added.
     * @return Input command for adding a log.
     */
    public static String getAddCommand(Log log) {
        return AddCommand.COMMAND_WORD + " " + getLogDetails(log);
    }

    /**
     * Returns the part of command string for the given {@code log}'s details.
     */
    public static String getLogDetails(Log log) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EXERCISE + log.getExercise().getName().value + " ");
        sb.append(PREFIX_REPS + log.getReps().value + " ");
        sb.append(PREFIX_COMMENT + log.getComment().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditLogDescriptor}'s details.
     */
    public static String getEditLogDescriptorDetails(EditCommand.EditLogDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getExercise().ifPresent(
            exercise -> sb.append(PREFIX_EXERCISE).append(exercise.getName().value).append(" ")
        );
        descriptor.getRep().ifPresent(rep -> sb.append(PREFIX_REPS).append(rep.value).append(" "));
        descriptor.getComment().ifPresent(comment -> sb.append(PREFIX_COMMENT).append(comment.value).append(" "));
        return sb.toString();
    }
}
