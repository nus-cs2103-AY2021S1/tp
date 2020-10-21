package seedu.address.model.student.academic.exam;

import seedu.address.model.student.academic.exam.exceptions.DuplicateExamException;

import java.util.ArrayList;

public class ExamList {
    private ArrayList<Exam> exams;

    public ExamList() {
        this.exams = new ArrayList<>();
    }

    public void addExam(Exam exam) {
        if (exams.contains(exam)) {
            throw new DuplicateExamException();
        }
        this.exams.add(exam);
    }

    @Override
    public int hashCode() {
        return exams.hashCode();
    }

    @Override
    public String toString() {
        String result = "";
        for (Exam exam : exams) {
            result = result + exam.toString() + "\n";
        }
        return result;
    }
}
