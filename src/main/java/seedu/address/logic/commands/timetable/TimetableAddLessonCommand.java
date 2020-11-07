package seedu.address.logic.commands.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.timetable.Slot;

public class TimetableAddLessonCommand extends Command {

    public static final String COMMAND_WORD = "timetable_add_lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an existing lesson to the timetable in fitNUS. "
            + "Parameters: "
            + PREFIX_LESSON + "LESSON "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "TIME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LESSON + "CS2030 "
            + PREFIX_DAY + "Monday "
            + PREFIX_TIME + "1600-1800";

    public static final String MESSAGE_SUCCESS = "Slot added to Timetable: %1$s";
    public static final String MESSAGE_MISSING_LESSON = "This lesson does not exist in fitNUS";
    public static final String MESSAGE_DUPLICATE_SLOT = "This slot already exists in your timetable";
    public static final String MESSAGE_OVERLAP_SLOT = "This slot overlaps with another slot in your timetable";

    private final Lesson lesson;

    private final Day day;

    private final Duration duration;

    /**
     * Creates a TimetableAddLessonCommand to add the specified {@code Lesson} into Timetable.
     */
    public TimetableAddLessonCommand(Lesson lesson, Day day, Duration duration) {
        requireAllNonNull(lesson, day, duration);
        this.lesson = lesson;
        this.day = day;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasLesson(lesson)) {
            throw new CommandException(MESSAGE_MISSING_LESSON);
        }

        Lesson lessonToAdd = model.retrieveLesson(lesson);
        Slot slot = new Slot(lessonToAdd, day, duration);
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
                || (other instanceof TimetableAddLessonCommand // instanceof handles nulls
                && lesson.equals(((TimetableAddLessonCommand) other).lesson)
                && day.equals(((TimetableAddLessonCommand) other).day)
                && duration.isSameDuration(((TimetableAddLessonCommand) other).duration));
    }
}
