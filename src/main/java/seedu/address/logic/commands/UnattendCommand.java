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
 * Unattends(Unmarks) a person's attendance.
 */
public class UnattendCommand extends Command {
    public static final String COMMAND_WORD = "unattend";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes attendance for the person identified by the index number used in the displayed person list.\n"
            + "Attendance date should be valid and in dd/MM/yyyy format.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_DATE + "27/03/1998";

    public static final String MESSAGE_ALREADY_UNATTENDED = "%1$s already unattended on %2$s";
    public static final String MESSAGE_UNATTEND_SUCCESS = "Removed attendance for person: %1$s\nYou may view results "
        + "in the Attendance tab.";

    private final Index targetIndex;
    private final Attendance attendance;

    /**
     * Creates an {@code Unattend} with corresponding target index and {@code Attendance}.
     *
     * @param targetIndex of the person in the filtered person list to edit attendance.
     * @param attendance {@code Attendance}.
     */
    public UnattendCommand(Index targetIndex, Attendance attendance) {
        this.targetIndex = targetIndex;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> persons = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= persons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (attendance == null) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE));
        }

        Person personToAttend = persons.get(targetIndex.getZeroBased());
        Person unattendedPerson = updateAttendanceForPerson(personToAttend, attendance);
        model.setPerson(personToAttend, unattendedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNATTEND_SUCCESS, personToAttend));
    }

    /**
     * Helps to unmark the Person's attendance and returns a new Person object.
     *
     * @param personToAttend Person whose attendance is unmarked.
     * @param attendance Attendance object which carries the appropriate date to unattend.
     * @return Person object with the date unattended.
     * @throws CommandException If the attendance date has been already unattended.
     */
    private static Person updateAttendanceForPerson(Person personToAttend, Attendance attendance)
            throws CommandException {
        assert personToAttend != null;
        SortedSet<Attendance> updatedAttendances = new TreeSet<>(personToAttend.getAttendances());
        if (!updatedAttendances.contains(attendance)) {
            throw new CommandException(String.format(MESSAGE_ALREADY_UNATTENDED, personToAttend.getName(), attendance));
        }
        updatedAttendances.remove(attendance);
        return new Person(personToAttend.getName(), personToAttend.getPhone(), personToAttend.getEmail(),
                personToAttend.getTelegram(), personToAttend.getMatricNumber(), personToAttend.getTags(),
                updatedAttendances);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnattendCommand // instanceof handles nulls
                && targetIndex.equals(((UnattendCommand) other).targetIndex)); // state check
    }
}

