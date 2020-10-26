package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.Attendance;

public class EditAttendanceCommand extends AttendanceCommand {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "";
    public static final String MESSAGE_SUCCESS = "Attendance edited for %s: %s";
    public static final String MESSAGE_INVALID_ATTENDANCE_DATE = "There is no existing attendance for the entered date";

    private static Logger logger = Logger.getLogger("Edit Attendance Log");

    private final Index index;
    private final Attendance updatedAttendance;
    private final LocalDate attendanceDate;

    /**
     * Creates an EditAdditionalDetailCommand to add the specified {@code AdditionalDetail} to the student
     * at the specified {@code Index}.
     */
    public EditAttendanceCommand(Index index, Attendance updatedAttendance, LocalDate attendanceDate) {
        requireAllNonNull(index, updatedAttendance, attendanceDate);
        this.index = index;
        this.updatedAttendance = updatedAttendance;
        this.attendanceDate = attendanceDate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getFilteredStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Invalid student index input error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToEditAttendance = lastShownList.get(index.getZeroBased());

        List<Attendance> attendanceList = new ArrayList<>(studentToEditAttendance.getAttendance());
        List<Attendance> updatedAttendanceList = this.updateAttendanceList(attendanceList);

        Student updatedStudent = super.updateStudentAttendance(studentToEditAttendance, updatedAttendanceList);

        model.setStudent(studentToEditAttendance, updatedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        logger.log(Level.INFO, "Execution complete");
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent.getName(), updatedAttendance));
    }

    private List<Attendance> updateAttendanceList(List<Attendance> attendanceList) throws CommandException {
        boolean containsAttendanceAtDate = attendanceList
                .stream()
                .anyMatch(attendance -> attendance.lessonDate.equals(attendanceDate));

        if (!containsAttendanceAtDate) {
            throw new CommandException(MESSAGE_INVALID_ATTENDANCE_DATE);
        }

        Stream<Attendance> matchingAttendance =  attendanceList.stream()
                .filter(attendance -> attendance.lessonDate.equals(attendanceDate));
        Attendance attendanceToUpdate = matchingAttendance.findFirst().get();
        int attendanceIndex = attendanceList.indexOf(attendanceToUpdate);
        attendanceList.set(attendanceIndex, updatedAttendance);

        return attendanceList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EditAttendanceCommand)) {
            return false;
        }

        EditAttendanceCommand other = (EditAttendanceCommand) obj;
        return index.equals(other.index) && updatedAttendance.equals(other.updatedAttendance);
    }
}
