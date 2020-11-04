package seedu.address.model.student.academic.exam;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateUtil.getInputFormat;
import static seedu.address.commons.util.DateUtil.print;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an Exam in Reeve that can be assigned to a {@code Student}.
 */
public class Exam {

    private final String examName;
    private final LocalDate examDate;
    private final Score score;

    /**
     * Constructs a {@code Exam}.
     *
     * @param examName name of exam.
     * @param examDate date of exam.
     * @param score score obtained.
     */
    public Exam(String examName, LocalDate examDate, Score score) {
        requireAllNonNull(examName, examDate, score);

        this.examName = examName;
        this.examDate = examDate;
        this.score = score;
    }

    public String getName() {
        return examName;
    }

    public LocalDate getDate() {
        return examDate;
    }

    public Score getScore() {
        return score;
    }

    public String getUserInputDate() {
        return getInputFormat(examDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examName, examDate, score);
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
        return " " + examName + "\n\t- Date: " + print(examDate) + "\n\t- Score: "
                + score + " (" + score.getScorePercentage() + "%)";
    }
}
