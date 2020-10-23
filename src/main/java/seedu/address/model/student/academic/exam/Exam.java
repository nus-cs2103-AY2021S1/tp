package seedu.address.model.student.academic.exam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Exam {
    private final String examName;
    private final LocalDate examDate;
    private final Score score;

    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");

    public Exam(String examName, String examDate, Score score) {
        this.examName = examName;
        LocalDate formattedDate;
        try {
            formattedDate= LocalDate.parse(examDate, INPUT_DEF);
        } catch (DateTimeParseException ignored) {
            formattedDate = LocalDate.parse(examDate, INPUT_ALT);
        }
        this.examDate = formattedDate;
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
        return this.examDate.format(INPUT_ALT);
    }

    public static boolean isValidDate(String date) {
        String VALIDATION_REGEX = "(\\d{1,2})(\\/)(\\d{1,2})(\\/)(\\d{2}|\\d{4})";
        if (!date.matches(VALIDATION_REGEX)) {
            return false;
        }

        LocalDate testDate = null;

        for (DateTimeFormatter format : new DateTimeFormatter[] {INPUT_DEF, INPUT_ALT}) {
            try {
                testDate = LocalDate.parse(date, format);
                break;
            } catch (DateTimeParseException ignored) {
                // does not match the DateTimeFormat, try the next
            }
        }
        
        return testDate != null;
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
        return " -> Name: " +
                examName +
                "\n\tDate: " +
                examDate +
                "\n\tActual Score: " +
                score;
    }
}
