package seedu.address.model.student.academic;

import seedu.address.model.student.academic.exam.ExamList;

import java.util.Objects;

/**
 * Represents all academic details of a Student in Reeve.
 * Consists of exams, homework and lesson records.
 */
public class Academic {
    private ExamList examList;

    public Academic() {
        this.examList = new ExamList();
    }

    public ExamList getExamList() {
        return examList;
    }


    @Override
    public int hashCode() {
        return Objects.hash(examList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Academic)) {
            return false;
        }

        Academic other = (Academic) obj;
        return other.getExamList().equals(getExamList());
    }

    @Override
    public String toString() {
        return " Exams : " +
                examList;
    }
}
