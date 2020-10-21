package seedu.address.model.student.academic.exam;

import java.util.Objects;

public class Exam {
    private final ExamName examName;
    private final ExamDate examDate;
    private final Score expectedScore;
    private final Score score;

    public Exam(ExamName examName, ExamDate examDate, Score expectedScore, Score score) {
        this.examName = examName;
        this.examDate = examDate;
        this.expectedScore = expectedScore;
        this.score = score;
    }

    public ExamName getName() {
        return examName;
    }

    public ExamDate getDate() {
        return examDate;
    }

    public Score getScore() {
        return score;
    }

    public Score getExpectedScore() {
        return expectedScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(examName, examDate, score, expectedScore);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Exam)) {
            return false;
        }

        Exam other = (Exam) obj;
        return other.getName().equals(getName());
    }

    @Override
    public String toString() {
        return " -> Name: " +
                examName +
                "\n\tDate: " +
                examDate +
                "\n\tExpected Score: " +
                expectedScore +
                "\n\tActual Score: " +
                score;
    }
}
