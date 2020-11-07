package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.routine.Routine;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.timetable.Slot;

public class TimetableAddRoutineCommand extends Command {
    public static final String COMMAND_WORD = "timetable_add_routine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an existing routine to the timetable in fitNUS. "
            + "Parameters: "
            + PREFIX_ROUTINE + "ROUTINE "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "TIME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROUTINE + "Leg Day Session "
            + PREFIX_DAY + "Monday "
            + PREFIX_TIME + "1600-1800";

    public static final String MESSAGE_SUCCESS = "Slot added to Timetable: %1$s";
    public static final String MESSAGE_MISSING_ROUTINE = "This routine does not exist in fitNUS";
    public static final String MESSAGE_DUPLICATE_SLOT = "This slot already exists in your timetable";
    public static final String MESSAGE_OVERLAP_SLOT = "This slot overlaps with another slot in your timetable";

    private final Routine routine;

    private final Day day;

    private final Duration duration;

    /**
     * Creates a TimetableAddRoutineCommand to add the specified {@code Routine} into Timetable.
     */
    public TimetableAddRoutineCommand(Routine routine, Day day, Duration duration) {
        requireAllNonNull(routine, day, duration);
        this.routine = routine;
        this.day = day;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasRoutine(routine)) {
            throw new CommandException(MESSAGE_MISSING_ROUTINE);
        }

        Routine routineToAdd = model.retrieveRoutine(routine);
        Slot slot = new Slot(routineToAdd, day, duration);
        if (model.hasSlot(slot)) {
            throw new CommandException(MESSAGE_DUPLICATE_SLOT);
        } else if (model.hasOverlappingSlot(slot)) {
            throw new CommandException(MESSAGE_OVERLAP_SLOT);
        }

        model.addSlotToTimetable(slot);
        return new CommandResult(String.format(MESSAGE_SUCCESS, slot));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimetableAddRoutineCommand // instanceof handles nulls
                && routine.equals(((TimetableAddRoutineCommand) other).routine)
                && day.equals(((TimetableAddRoutineCommand) other).day)
                && duration.isSameDuration(((TimetableAddRoutineCommand) other).duration));
    }
}
