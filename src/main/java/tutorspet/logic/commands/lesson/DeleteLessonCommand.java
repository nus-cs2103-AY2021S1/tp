package tutorspet.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX;
import static tutorspet.commons.util.CollectionUtil.requireAllNonNull;
import static tutorspet.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static tutorspet.logic.util.ModuleClassUtil.deleteLessonFromModuleClass;

import java.util.List;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.Command;
import tutorspet.logic.commands.CommandResult;
import tutorspet.logic.commands.exceptions.CommandException;
import tutorspet.model.Model;
import tutorspet.model.lesson.Lesson;
import tutorspet.model.moduleclass.ModuleClass;

/**
 * Deletes a lesson identified using it's displayed index in the displayed module class list.
 */
public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "delete-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the lesson in a specific class identified by the "
            + "by the index number used in the displayed class list "
            + "and index number in the lesson list.\n"
            + "Parameters: "
            + PREFIX_CLASS_INDEX + "CLASS_INDEX (must be a positive integer) "
            + PREFIX_LESSON_INDEX + "LESSON_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_LESSON_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Deleted lesson:\n%1$s %2$s.";
    public static final String MESSAGE_COMMIT = "Deleted lesson: %1$s %2$s.";

    private final Index moduleClassIndex;
    private final Index lessonIndex;

    /**
     * @param moduleClassIndex in the filtered class list.
     * @param lessonIndex in the specified class list to be deleted.
     */
    public DeleteLessonCommand(Index moduleClassIndex, Index lessonIndex) {
        requireAllNonNull(moduleClassIndex, lessonIndex);

        this.moduleClassIndex = moduleClassIndex;
        this.lessonIndex = lessonIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<ModuleClass> lastShownModuleClassList = model.getFilteredModuleClassList();

        if (moduleClassIndex.getOneBased() > lastShownModuleClassList.size()) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX);
        }

        ModuleClass targetModuleClass = lastShownModuleClassList.get(moduleClassIndex.getZeroBased());

        if (lessonIndex.getOneBased() > targetModuleClass.getLessons().size()) {
            throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToDelete = targetModuleClass.getLessons().get(lessonIndex.getZeroBased());
        ModuleClass modifiedModuleClass = deleteLessonFromModuleClass(targetModuleClass, lessonToDelete);
        model.setModuleClass(targetModuleClass, modifiedModuleClass);

        model.commit(String.format(MESSAGE_COMMIT, targetModuleClass.getName(), lessonToDelete.printLesson()));
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                targetModuleClass.getName(), lessonToDelete.printLesson()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLessonCommand // instance of handles nulls
                && moduleClassIndex.equals(((DeleteLessonCommand) other).moduleClassIndex)
                && lessonIndex.equals(((DeleteLessonCommand) other).lessonIndex));
    }
}
