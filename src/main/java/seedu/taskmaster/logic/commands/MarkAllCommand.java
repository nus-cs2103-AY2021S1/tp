package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_ATTENDANCE_TYPE;

import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.session.exceptions.SessionException;

public class MarkAllCommand extends MarkCommand {
    public static final String COMMAND_WORD = "mark all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks attendances of all students in the student list.\n"
            + "Parameters: "
            + PREFIX_ATTENDANCE_TYPE + "ATTENDANCE_TYPE (must be 'present', 'absent' or 'no_record') \n"
            + "Example: " + COMMAND_WORD + PREFIX_ATTENDANCE_TYPE + "present";

    public static final String MESSAGE_MARK_ALL_SUCCESS = "Marked all students as %1$s";

    public MarkAllCommand(AttendanceType attendanceType) {
        super(null, attendanceType);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.markAllStudents(attendanceType);
        } catch (SessionException sessionException) {
            throw new CommandException(sessionException.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_MARK_ALL_SUCCESS, attendanceType));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAllCommand)) {
            return false;
        }

        // state check
        MarkAllCommand m = (MarkAllCommand) other;

        return attendanceType.equals(m.attendanceType);
    }
}
