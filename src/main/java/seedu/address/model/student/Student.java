package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

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
    private final ClassVenue classVenue;
    private final ClassTime classTime;
    private final AdditionalDetails additionalDetails;
    private final MeetingLink meetingLink;
    private final Subject subject;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, School school, Year year, ClassVenue classVenue,
                   ClassTime classTime, AdditionalDetails additionalDetails, MeetingLink meetingLink,
                   Subject subject, Set<Tag> tags) {
        requireAllNonNull(name, phone, meetingLink, classVenue, tags);
        this.name = name;
        this.phone = phone;
        this.school = school;
        this.year = year;
        this.classVenue = classVenue;
        this.classTime = classTime;
        this.additionalDetails = additionalDetails;
        this.meetingLink = meetingLink;
        this.subject = subject;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public MeetingLink getMeetingLink() {
        return meetingLink;
    }

    public School getSchool() {
        return school;
    }

    public Year getYear() {
        return year;
    }

    public ClassTime getClassTime() {
        return classTime;
    }

    public AdditionalDetails getAdditionalDetails() {
        return additionalDetails;
    }

    public Subject getSubject() {
        return subject;
    }

    public ClassVenue getClassVenue() {
        return classVenue;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
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
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
                && otherStudent.getMeetingLink().equals(getMeetingLink())
                && otherStudent.getClassVenue().equals(getClassVenue())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, school, year, classVenue, classTime, additionalDetails,
                meetingLink, subject, tags);
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
                .append(getYear())
                .append(" Class Venue: ")
                .append(getClassVenue())
                .append(" Class Time: ")
                .append(getClassTime())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
