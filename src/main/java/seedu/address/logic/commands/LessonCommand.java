package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.LESSON_OVERLAP_CONSTRAINTS;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.util.Overlap;


/**
 * Adds a lesson to the PlaNus lesson list.
 */
public class LessonCommand extends Command {
    public static final String COMMAND_WORD = "lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to PlaNus.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_TAG + "TAG "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + PREFIX_DAY + "DAY "
            + PREFIX_START_TIME + "TIME "
            + PREFIX_END_TIME + "TIME "
            + PREFIX_START_DATE + "DATE "
            + PREFIX_END_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "CS2103T Lecture "
            + PREFIX_TAG + "CS2103T "
            + PREFIX_DESCRIPTION + "Most exciting lecture in NUS! "
            + PREFIX_DAY + "Friday "
            + PREFIX_START_TIME + "16:00 "
            + PREFIX_END_TIME + "18:00 "
            + PREFIX_START_DATE + "01-08-2020 "
            + PREFIX_END_DATE + "15-11-2020 ";

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";

    private final Lesson lesson;

    /**
     * Creates an LessonCommand to add the specified {@code Task}
     */
    public LessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        this.lesson = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasLesson(lesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }
        if (Overlap.overlapWithOtherTimeSlots(model, lesson)) {
            throw new CommandException(LESSON_OVERLAP_CONSTRAINTS);
        }
        model.addLesson(lesson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, lesson));
    }

    @Override
    public boolean equals(Object other) {
        requireNonNull(other);
        return other == this // short circuit if same object
                || (other instanceof LessonCommand // instanceof handles nulls
                && lesson.equals(((LessonCommand) other).lesson));
    }
}
