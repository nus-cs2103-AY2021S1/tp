package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.TutorialGroup;
import seedu.address.model.person.Module;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    private final StudentId studentId;

    private final Module module;

    private TutorialGroup tutorialGroup;

    /**
     * Constructor for StudentId.
     * @param studentId a valid string representing a Student's id.
     */
    public Student(Name name, Phone phone, Email email, Set<Tag> tags, StudentId studentId, Module module) {
        super(name, phone, email, tags);
        this.module = module;
        requireAllNonNull(studentId);
        this.studentId = studentId;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Module getModule() {
        return module;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    /**
     * Returns true if both students of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && (otherStudent.getPhone().equals(getPhone()) || otherStudent.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getModule().equals(getModule());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getTags(), studentId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        // to add module and tut group
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Student ID: ")
                .append(getStudentId())
                .append(" Tags: ")
                .append(" Module: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
