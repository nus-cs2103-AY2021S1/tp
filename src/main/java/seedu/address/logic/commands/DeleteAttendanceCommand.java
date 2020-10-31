package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Student;

/**
 * Removes the attendance of an existing student in Trackr.
 */
public class DeleteAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "deleteAttendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the attendance of the student identified by the index number used in the "
            + "displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_WEEK + "WEEK NUMBER "
            + "[" + PREFIX_WEEK + "WEEK NUMBER]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEK + "2";

    public static final String MESSAGE_DELETE_ATTENDANCE_SUCCESS = "%s is present on %s";
    public static final String MESSAGE_WRONG_VIEW = "You are currently not in the Student view";

    private final Index index;
    private final int[] weeksToDelete;

    /**
     * Creates an DeleteAttendanceCommand to update the attendace.
     */
    public DeleteAttendanceCommand(Index index, int[] weeksToDelete) {
        requireNonNull(index);
        requireNonNull(weeksToDelete);
        this.index = index;
        this.weeksToDelete = weeksToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isInStudentView()) {
            throw new CommandException(MESSAGE_WRONG_VIEW);
        }

        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDeleteAttendance = lastShownList.get(index.getZeroBased());
        Student studentWithDeletedAttendance = lastShownList.get(index.getZeroBased());
        for (int i : weeksToDelete) {
            String weekNum = String.valueOf(i);
            try {
                studentWithDeletedAttendance.getAttendance().deleteAttendance(weekNum);
            } catch (IllegalArgumentException e) {
                throw new CommandException(e.getMessage());
            }
        }

        model.setStudent(studentToDeleteAttendance, studentWithDeletedAttendance);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(
                MESSAGE_DELETE_ATTENDANCE_SUCCESS,
                studentWithDeletedAttendance.getName().fullName,
                studentWithDeletedAttendance.getAttendance().listOutAttendedWeeks())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAttendanceCommand // instanceof handles nulls
                && index.equals(((DeleteAttendanceCommand) other).index)
                && Arrays.equals(weeksToDelete, ((DeleteAttendanceCommand) other).weeksToDelete));
    }
}
