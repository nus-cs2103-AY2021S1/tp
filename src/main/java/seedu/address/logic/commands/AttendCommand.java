package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Person;

/**
 * Adds attendance for a person identified using it's displayed index from the address book.
 */
public class AttendCommand extends Command {

    public static final String COMMAND_WORD = "attend";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds attendance for the person identified by the index number used in the displayed person list.\n"
            + "Attendance date should be valid and in dd/MM/yyyy format.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_DATE + "27/03/1998";

    public static final String MESSAGE_ALREADY_ATTENDED = "%1$s already attended on %2$s";
    public static final String MESSAGE_ATTEND_SUCCESS = "Added attendance for student: %1$s\nYou may view results in "
        + "the Attendance tab.";

    private final Index targetIndex;
    private final Attendance attendance;

    /**
     * Creates an {@code Attend} with corresponding target index and {@code Attendance}.
     * @param targetIndex of the person in the filtered person list to edit
     * @param attendance {@code Attendance}
     */
    public AttendCommand(Index targetIndex, Attendance attendance) {
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
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE));
        }

        Person personToAttend = lastShownList.get(targetIndex.getZeroBased());
        Person attendedPerson = updateAttendanceForPerson(personToAttend, attendance);
        model.setPerson(personToAttend, attendedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ATTEND_SUCCESS, personToAttend));
    }

    private static Person updateAttendanceForPerson(Person personToAttend, Attendance attendance)
            throws CommandException {
        assert personToAttend != null;
        SortedSet<Attendance> updatedAttendances = new TreeSet<>(personToAttend.getAttendances());
        if (updatedAttendances.contains(attendance)) {
            throw new CommandException(String.format(MESSAGE_ALREADY_ATTENDED, personToAttend.getName(), attendance));
        }
        updatedAttendances.add(attendance);
        return new Person(personToAttend.getName(),
                personToAttend.getPhone(),
                personToAttend.getEmail(),
                personToAttend.getTelegram(),
                personToAttend.getMatricNumber(),
                personToAttend.getTags(),
                updatedAttendances);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttendCommand // instanceof handles nulls
                && targetIndex.equals(((AttendCommand) other).targetIndex)); // state check
    }

    private static class AlreadyAttendedException extends Exception {
    }
}
