package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Attendance}'s participation score is less than {@code specifiedScore}.
 */
public class ParticipationBelowSpecifiedScorePredicate implements Predicate<Student> {
    private final int specifiedScore;

    public ParticipationBelowSpecifiedScorePredicate(int specifiedScore) {
        this.specifiedScore = specifiedScore;
    }

    @Override
    public boolean test(Student student) {
        return student.getAttendance().getParticipationScore() < specifiedScore;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticipationBelowSpecifiedScorePredicate // instanceof handles nulls
                && specifiedScore == ((ParticipationBelowSpecifiedScorePredicate) other).specifiedScore); // state check
    }
}
