package seedu.address.testutil;

import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.model.student.Attendance;

public class AttendanceUtil {
    /**
     * Returns an add command string for adding the {@code Attendance}.
     */
    public static String getAddAttendanceCommand(Attendance attendance) {
        String correctArgument = " 1 week/2";
        return AddAttendanceCommand.COMMAND_WORD + " " + correctArgument;
    }

}
