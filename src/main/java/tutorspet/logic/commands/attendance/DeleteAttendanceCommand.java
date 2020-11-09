package tutorspet.logic.commands.attendance;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static tutorspet.commons.util.CollectionUtil.requireAllNonNull;
import static tutorspet.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_WEEK;
import static tutorspet.logic.util.ModuleClassUtil.deleteAttendanceFromModuleClass;
import static tutorspet.logic.util.ModuleClassUtil.getLessonFromModuleClass;

import java.util.List;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.Command;
import tutorspet.logic.commands.CommandResult;
import tutorspet.logic.commands.exceptions.CommandException;
import tutorspet.model.Model;
import tutorspet.model.attendance.Week;
import tutorspet.model.lesson.Lesson;
import tutorspet.model.moduleclass.ModuleClass;
import tutorspet.model.student.Student;

/**
 * Deletes an attendance of a student for a specific week identified using it's
 * displayed index in the displayed module class list.
 */
public class DeleteAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "delete-attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the attendance record of a student in a specific week identified by the "
            + "index number used in the displayed class list, student list, and lesson list respectively. "
            + "Note: All indexes must be positive integers.\n"
            + "Parameters: "
            + PREFIX_CLASS_INDEX + "CLASS_INDEX "
            + PREFIX_LESSON_INDEX + "LESSON_INDEX "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX "
            + PREFIX_WEEK + "WEEK NUMBER (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_LESSON_INDEX + "1 "
            + PREFIX_STUDENT_INDEX + "1 "
            + PREFIX_WEEK + "1";

    public static final String MESSAGE_SUCCESS = "Deleted attendance:\n"
            + "%1$s %2$s %3$s attendance in week %4$s.";
    public static final String MESSAGE_COMMIT = "Deleted attendance: %1$s (%2$s %3$s in week %4$s).";

    private final Index moduleClassIndex;
    private final Index lessonIndex;
    private final Index studentIndex;
    private final Week week;

    /**
     * @param moduleClassIndex in the filtered class list.
     * @param lessonIndex in the filtered lesson list.
     * @param studentIndex in the filtered student list.
     * @param week in the specified attendance list to be deleted.
     */
    public DeleteAttendanceCommand(
            Index moduleClassIndex, Index lessonIndex, Index studentIndex, Week week) {
        requireAllNonNull(moduleClassIndex, lessonIndex, studentIndex, week);

        this.moduleClassIndex = moduleClassIndex;
        this.lessonIndex = lessonIndex;
        this.studentIndex = studentIndex;
        this.week = week;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<ModuleClass> lastShownModuleClassList = model.getFilteredModuleClassList();

        if (studentIndex.getOneBased() > lastShownStudentList.size()) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        if (moduleClassIndex.getOneBased() > lastShownModuleClassList.size()) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX);
        }

        Student targetStudent = lastShownStudentList.get(studentIndex.getZeroBased());
        ModuleClass targetModuleClass = lastShownModuleClassList.get(moduleClassIndex.getZeroBased());

        ModuleClass modifiedModuleClass =
                deleteAttendanceFromModuleClass(targetModuleClass, lessonIndex, week, targetStudent);

        Lesson lesson = getLessonFromModuleClass(targetModuleClass, lessonIndex);

        model.setModuleClass(targetModuleClass, modifiedModuleClass);

        model.commit(String.format(MESSAGE_COMMIT, targetStudent.getName(), targetModuleClass.getName(),
                lesson.printLesson(), week));
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetStudent.getName(), modifiedModuleClass.getName(),
                lesson.printLesson(), week));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAttendanceCommand // instance of handles nulls
                && moduleClassIndex.equals(((DeleteAttendanceCommand) other).moduleClassIndex)
                && lessonIndex.equals(((DeleteAttendanceCommand) other).lessonIndex))
                && studentIndex.equals(((DeleteAttendanceCommand) other).studentIndex)
                && week.equals(((DeleteAttendanceCommand) other).week);
    }
}
