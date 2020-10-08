package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_TYPE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.AttendanceType;
import seedu.address.model.student.Student;

/**
 * Marks the attendance of a student identified using its displayed index from the student list.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks attendance of the student identified by the index number used in the displayed student list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_ATTENDANCE_TYPE + "ATTENDANCE_TYPE (must be 'present' or 'absent') \n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_ATTENDANCE_TYPE + "present";

    public static final String MESSAGE_MARK_STUDENT_SUCCESS = "Marked %1$s as %2$s";

    private final Index targetIndex;
    private final AttendanceType attendanceType;

    /**
     * @param targetIndex of the student in the filtered student list to mark
     * @param attendanceType to mark the student with
     */
    public MarkCommand(Index targetIndex, AttendanceType attendanceType) {
        this.targetIndex = targetIndex;
        this.attendanceType = attendanceType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToMark = lastShownList.get(targetIndex.getZeroBased());
        model.markStudent(studentToMark, attendanceType);
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_SUCCESS, studentToMark, attendanceType));
    }
}
