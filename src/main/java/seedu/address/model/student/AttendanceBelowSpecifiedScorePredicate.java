package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Attendance}'s score is less than {@code specifiedScore}.
 */
public class AttendanceBelowSpecifiedScorePredicate implements Predicate<Student> {
    private final int specifiedScore;

    public AttendanceBelowSpecifiedScorePredicate(int specifiedScore) {
        this.specifiedScore = specifiedScore;
    }

    @Override
    public boolean test(Student student) {
        return student.getAttendance().getAttendanceScore() < specifiedScore;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttendanceBelowSpecifiedScorePredicate // instanceof handles nulls
                && specifiedScore == ((AttendanceBelowSpecifiedScorePredicate) other).specifiedScore); // state check
    }
}
