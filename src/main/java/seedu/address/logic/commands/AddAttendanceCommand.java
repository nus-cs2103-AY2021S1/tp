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
 * Records the attendance of an existing student in Trackr.
 */
public class AddAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "addAttendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Records the attendance of the student identified by the index number used in the "
            + "displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_WEEK + "WEEK NUMBER "
            + "[" + PREFIX_WEEK + "WEEK NUMBER]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEK + "2";

    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "%s is present on %s";
    public static final String MESSAGE_WRONG_VIEW = "You are currently not in the Student view";

    private final Index index;
    private final int[] weeksToAdd;

    /**
     * Creates an AddAttendanceCommand to update the attendance.
     */
    public AddAttendanceCommand(Index index, int[] weeksToAdd) {
        requireNonNull(index);
        requireNonNull(weeksToAdd);
        this.index = index;
        this.weeksToAdd = weeksToAdd;
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

        Student studentToAddAttendance = lastShownList.get(index.getZeroBased());
        Student studentWithAddedAttendance = lastShownList.get(index.getZeroBased());
        for (int i : weeksToAdd) {
            String weekNum = String.valueOf(i);
            try {
                studentWithAddedAttendance.getAttendance().addAttendance(weekNum);
            } catch (IllegalArgumentException e) {
                throw new CommandException(e.getMessage());
            }
        }

        model.setStudent(studentToAddAttendance, studentWithAddedAttendance);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(
                MESSAGE_ADD_ATTENDANCE_SUCCESS,
                studentWithAddedAttendance.getName().fullName,
                studentWithAddedAttendance.getAttendance().listOutAttendedWeeks())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAttendanceCommand // instanceof handles nulls
                && index.equals(((AddAttendanceCommand) other).index)
                && Arrays.equals(weeksToAdd, ((AddAttendanceCommand) other).weeksToAdd));
    }
}
