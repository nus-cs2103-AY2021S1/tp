package seedu.address.logic.commands;

import seedu.address.model.student.Student;
import seedu.address.model.student.academic.exam.Exam;

import java.util.ArrayList;

import static seedu.address.logic.parser.CliSyntax.*;

public abstract class ExamCommand extends Command {
    public static final String COMMAND_WORD = "exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds or deletes an Exam"
            + "from a student in Reeve.\n\n"
            + "Example:\n"
            + "To add an exam (adds an exam to the first student in the list):\n"
            + ExamCommand.COMMAND_WORD
            + " add" + " 1 "
            + PREFIX_EXAM_NAME
            + " End of Year Examination 2020 "
            + PREFIX_EXAM_DATE + "7/11/2020 "
            + PREFIX_SCORE + "50/100 \n\n"
            + "To delete an exam (deletes the first exam of the second student in the list):\n"
            + ExamCommand.COMMAND_WORD
            + " delete" + " 2 "
            + "1";

    public Student updateStudentExam(Student studentToAddExam, ArrayList<Exam> exams) {
        return new Student(studentToAddExam.getName(), studentToAddExam.getPhone(),
                studentToAddExam.getSchool(), studentToAddExam.getYear(), studentToAddExam.getAdmin(),
                studentToAddExam.getQuestions(), exams);
    }
}
