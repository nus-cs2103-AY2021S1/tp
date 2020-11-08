package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.timetable.TimetableAddLessonCommand;
import seedu.address.logic.commands.timetable.TimetableAddRoutineCommand;
import seedu.address.logic.commands.timetable.TimetableDeleteSlotCommand;
import seedu.address.model.timetable.Slot;

/**
 * A utility class for Slot.
 */
public class SlotUtil {

    /**
     * Returns a timetable add lesson command string for adding the {@code slot}.
     */
    public static String getTimetableAddLessonCommand(Slot slot) {
        return TimetableAddLessonCommand.COMMAND_WORD + " " + getLessonDetails(slot);
    }

    /**
     * Returns the part of command string for the given {@code slot}'s details.
     */
    public static String getLessonDetails(Slot slot) {

        return PREFIX_LESSON + slot.getActivity().getName().fullName + " "
                + PREFIX_DAY + slot.getDay().getDay() + " "
                + PREFIX_TIME + slot.getDuration().toString();
    }

    /**
     * Returns a timetable add routine command string for adding the {@code slot}.
     */
    public static String getTimetableAddRoutineCommand(Slot slot) {
        return TimetableAddRoutineCommand.COMMAND_WORD + " " + getRoutineDetails(slot);
    }

    /**
     * Returns the part of command string for the given {@code slot}'s details.
     */
    public static String getRoutineDetails(Slot slot) {

        return PREFIX_ROUTINE + slot.getActivity().getName().fullName + " "
                + PREFIX_DAY + slot.getDay().getDay() + " "
                + PREFIX_TIME + slot.getDuration().toString();
    }

    /**
     * Returns a timetable delete slot command string for deleting the {@code slot}.
     */
    public static String getTimetablDeleteSlotCommand(Slot slot) {
        return TimetableDeleteSlotCommand.COMMAND_WORD + " " + getSlotDetails(slot);
    }

    /**
     * Returns the part of command string for the given {@code slot}'s details.
     */
    public static String getSlotDetails(Slot slot) {

        return PREFIX_DAY + slot.getDay().getDay() + " "
                + PREFIX_TIME + slot.getDuration().toString();
    }
}
