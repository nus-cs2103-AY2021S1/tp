package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Shows the week(s) where the student identified using it's displayed index is present.
 */
public class ViewAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "viewAttendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the student's attendance identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "%s is present on %s";
    public static final String MESSAGE_WRONG_VIEW = "You are currently not in the Student view";

    private final Index targetIndex;

    public ViewAttendanceCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isInStudentView()) {
            throw new CommandException(MESSAGE_WRONG_VIEW);
        }

        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                student.getName().toString(),
                student.getAttendance().listOutAttendedWeeks())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAttendanceCommand // instanceof handles nulls
                && targetIndex.equals(((ViewAttendanceCommand) other).targetIndex)); // state check
    }
}
