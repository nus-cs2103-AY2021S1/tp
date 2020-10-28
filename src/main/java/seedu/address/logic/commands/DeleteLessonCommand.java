package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

/**
 * Deletes a lesson identified using it's displayed index from PlaNus.
 */
public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "delete-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the lesson identified by the index number used in the displayed lesson list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

    private final Index[] targetIndexes;

    /**
     * Creates an DeleteLessonCommand to to delete the lessons with {@code targetIndexes} from system.
     */
    public DeleteLessonCommand(Index[] targetIndexes) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();
        Lesson[] lessonsToDelete = new Lesson[targetIndexes.length];
        if (Index.hasDuplicateIndex(targetIndexes)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TASK_INDEX);
        }
        for (int i = 0; i < targetIndexes.length; i++) {
            if (targetIndexes[i].getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
            }
            lessonsToDelete[i] = lastShownList.get(targetIndexes[i].getZeroBased());
        }

        model.deleteLesson(lessonsToDelete);
        return new CommandResult(buildMessage(lessonsToDelete));
    }

    /**
     * @param lessons that is been deleted.
     * returns message built by the list of lessons deleted.
     */
    public static String buildMessage(Lesson[] lessons) {
        StringBuilder message = new StringBuilder();
        for (Lesson lesson : lessons) {
            message.append(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lesson.getTitle())).append("\n");
        }
        return message.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLessonCommand // instanceof handles nulls
                && Arrays.equals(targetIndexes, ((DeleteLessonCommand) other).targetIndexes)); // state check
    }
}
