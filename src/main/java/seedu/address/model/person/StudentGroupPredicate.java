package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.studentgroup.StudentGroup;

/**
 * Tests that a {@code Person} contains the {@code Set<StudentGroup>}.
 */
public class StudentGroupPredicate implements Predicate<Person> {
    private final Set<StudentGroup> studentGroupSet;

    public StudentGroupPredicate(Set<StudentGroup> studentGroupSet) {
        this.studentGroupSet = studentGroupSet;
    }

    @Override
    public boolean test(Person person) {
        return person.getStudentGroups().containsAll(studentGroupSet);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof StudentGroupPredicate // instanceof handles nulls
            && studentGroupSet.equals(((StudentGroupPredicate) other).studentGroupSet)); // state check
    }
}
