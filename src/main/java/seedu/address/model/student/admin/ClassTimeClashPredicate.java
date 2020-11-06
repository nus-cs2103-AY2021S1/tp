package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests if a {@code Student}'s {@code ClassTime} clashes with the given {@code ClassTime}.
 * Two {@code ClassTime} objects do not clash if and only if one does not start before the other ends and
 * does not end after the other starts.
 */
public class ClassTimeClashPredicate implements Predicate<Student> {

    private ClassTime classTime;

    /**
     * Constructs a ClassTimeClashPredicate.
     */
    public ClassTimeClashPredicate(Student student) {
        requireNonNull(student);
        this.classTime = student.getClassTime();
    }

    /**
     * Returns true if the given student has a ClassTime that clashes with the given one.
     */
    @Override
    public boolean test(Student student) {
        ClassTime compare = student.getClassTime();
        if (!classTime.dayOfWeek.equals(compare.dayOfWeek)) {
            return false;
        }

        LocalTime start = compare.startTime;
        LocalTime end = compare.endTime;

        LocalTime otherStart = classTime.startTime;
        LocalTime otherEnd = classTime.endTime;

        return end.isAfter(otherStart) && start.isBefore(otherEnd);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof ClassTimeClashPredicate
                && ((ClassTimeClashPredicate) obj).classTime.equals(classTime));
    }

}
