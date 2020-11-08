package seedu.address.logic.commands;

import java.util.ArrayList;

import seedu.address.model.student.Student;
import seedu.address.model.student.academic.exam.Exam;

/**
 * Abstract class of exam commands such as AddExam and DeleteExam.
 */
public abstract class ExamCommand extends Command {
    public static final String COMMAND_WORD = "exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, deletes an exam "
            + "or view exam statistics of student "
            + "to/from a student in Reeve.\n\n"
            + "SUPPORTED COMMANDS: add, delete, stats";

    /**
     * Updates a specific students exam list when an exam is being added or deleted.
     * @param studentToUpdate selected student to be be updated.
     * @param exams the updated array list of exams.
     * @return selected student with the updated exams.
     */
    public Student updateStudentExam(Student studentToUpdate, ArrayList<Exam> exams) {
        return new Student(studentToUpdate.getName(), studentToUpdate.getPhone(),
                studentToUpdate.getSchool(), studentToUpdate.getYear(), studentToUpdate.getAdmin(),
                studentToUpdate.getQuestions(), exams, studentToUpdate.getAttendance());
    }
}
