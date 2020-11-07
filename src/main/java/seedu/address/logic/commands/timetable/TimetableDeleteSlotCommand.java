package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.timetable.Slot;

/**
 * Deletes a slot from the timetable in fitNUS.
 */
public class TimetableDeleteSlotCommand extends Command {

    public static final String COMMAND_WORD = "timetable_delete_slot";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the slot identified by its day and time. "
            + "Parameters: "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "TIME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DAY + "Monday "
            + PREFIX_TIME + "1600-1800";

    public static final String MESSAGE_DELETE_SLOT_SUCCESS = "Deleted Slot: %1$s";
    public static final String MESSAGE_MISSING_SLOT = "This slot does not exist in your timetable.";

    private final Slot slotToFind;

    /**
     * Creates a TimetableDeleteSlotCommand to delete the specified {@code Slot} from Timetable.
     */
    public TimetableDeleteSlotCommand(Slot slot) {
        requireNonNull(slot);
        slotToFind = slot;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Slot> lastShownList = model.getFilteredSlotList();
        boolean hasSlot = lastShownList.stream().anyMatch(slotToFind::isSameSlot);
        if (!hasSlot) {
            throw new CommandException(MESSAGE_MISSING_SLOT);
        }

        Slot slotToDelete = lastShownList.stream().filter(slotToFind::isSameSlot).findFirst().get();

        model.deleteSlotFromTimetable(slotToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SLOT_SUCCESS, slotToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimetableDeleteSlotCommand // instanceof handles nulls
                && slotToFind.equals(((TimetableDeleteSlotCommand) other).slotToFind));
    }
}
