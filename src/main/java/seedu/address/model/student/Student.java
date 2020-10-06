package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.admin.Admin;

/**
 * Represents a Student in Reeve.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final School school;
    private final Year year;
    private final Admin admin;
    /**
     *  name, phone, school, year, must be present and not null.
     */
    public Student(Name name, Phone phone, School school, Year year, Admin admin) {
        requireAllNonNull(name, phone, school, year, admin);
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.year = year;
        this.admin = admin;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public School getSchool() {
        return school;
    }

    public Year getYear() {
        return year;
    }

    public Admin getAdmin() {
        return admin;
    }

    /**
     * Returns true if both student of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both student have the same identity and data fields.
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
                && otherStudent.getSchool().equals(getSchool())
                && otherStudent.getYear().equals(getYear());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, school, year);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" School: ")
                .append(getSchool())
                .append(" Year: ")
                .append(getYear());
        return builder.toString();
    }

}
