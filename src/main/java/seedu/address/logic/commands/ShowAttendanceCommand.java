package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.List;
import java.util.SortedSet;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Person;

/**
 * Shows attendance for a person identified using its index and specified attendance date.
 */
public class ShowAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "showatt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows attendance for the person identified by the index number used in the displayed person list.\n"
            + "Attendance date should be in dd/MM/yyyy format.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_DATE + "27/03/1998";

    public static final String MESSAGE_SUCCESS = "%s was %s on %s\nYou may view results in the Attendance tab.";

    private final Index targetIndex;
    private final Attendance attendance;

    /**
     * Shows attendance of a Person with corresponding target index and {@code Attendance}.
     * @param targetIndex of the person in the filtered person list to show.
     * @param attendance {@code Attendance}
     */
    public ShowAttendanceCommand(Index targetIndex, Attendance attendance) {
        this.targetIndex = targetIndex;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (attendance == null) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Person person = lastShownList.get(targetIndex.getZeroBased());
        SortedSet<Attendance> attendances = person.getAttendances();
        boolean hasAttended = attendances.stream().anyMatch(attendance1 -> attendance1.equals(attendance));
        String attendanceStatus = "absent";
        if (hasAttended) {
            attendanceStatus = "present";
        }
        return new CommandResult(
                String.format(
                        MESSAGE_SUCCESS,
                        person.getName(),
                        attendanceStatus,
                        attendance.date.toString()
                )
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowAttendanceCommand // instanceof handles nulls
                && targetIndex.equals(((ShowAttendanceCommand) other).targetIndex)); // state check
    }

}
