package seedu.address.model.student.academic.exam;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an Exam in Reeve that can be assigned to a {@code Student}.
 */
public class Exam {
    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");
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
    public Exam(String examName, String examDate, Score score) {
        this.examName = examName;
        LocalDate formattedDate;
        try {
            formattedDate = LocalDate.parse(examDate, INPUT_DEF);
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

    /**
     * Returns true if a given string is a valid date for {@code Exam}.
     */
    public static boolean isValidDate(String date) {
        Pattern validationRegex =
                Pattern.compile("(?<day>[0-9]{1,2})(/)(?<month>[0-9]{1,2})(/)(?<year>[0-9]{2}|[0-9]{4})");

        Matcher matcher = validationRegex.matcher(date);

        if (!matcher.matches()) {
            return false;
        }
        String day = matcher.group("day");
        String month = matcher.group("month");
        String year = matcher.group("year");

        if (year.length() < 4) {
            year = "20" + year;
        }

        int parseDay = Integer.parseInt(day);
        int parseMonth = Integer.parseInt(month);
        int parseYear = Integer.parseInt(year);

        try {
            LocalDate.of(parseYear, parseMonth, parseDay);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
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
        return "- " + examName + "\n\t-> Date: " + examDate.format(OUTPUT) + "\n\t-> Score: "
                + score + " (" + score.getScorePercentage() + "%)\n";
    }
}
