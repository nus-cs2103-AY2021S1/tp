package trackitnus.logic.commands.lesson;

import static java.util.Objects.requireNonNull;

import java.util.List;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.lesson.Lesson;

public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = Lesson.TYPE + " " + COMMAND_WORD
        + ": Deletes the lesson identified by the index number currently displayed on the screen.\n"
        + "Parameters: "
        + "INDEX\n"
        + "Example: " + Lesson.TYPE + " " + COMMAND_WORD + " "
        + "2\n";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteLessonCommand to delete the specified {@code Lesson}
     *
     * @param index
     */
    public DeleteLessonCommand(Index index) {
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteLesson(lessonToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteLessonCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteLessonCommand) other).targetIndex)); // state check
    }
}
