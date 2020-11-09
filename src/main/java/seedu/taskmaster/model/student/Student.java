package seedu.taskmaster.model.student;

import static seedu.taskmaster.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.taskmaster.model.tag.Tag;

/**
 * Represents a Student in the student list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Telegram telegram;
    private final Email email;

    // Data fields
    private final NusnetId nusnetId;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Telegram telegram, Email email, NusnetId nusnetId, Set<Tag> tags) {
        requireAllNonNull(name, telegram, email, nusnetId, tags);
        this.name = name;
        this.telegram = telegram;
        this.email = email;
        this.nusnetId = nusnetId;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Email getEmail() {
        return email;
    }

    public NusnetId getNusnetId() {
        return nusnetId;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both students of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        if (otherStudent == null) {
            return false;
        }

        return otherStudent.getNusnetId().equals(getNusnetId());
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
                && otherStudent.getTelegram().equals(getTelegram())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getNusnetId().equals(getNusnetId())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, telegram, email, nusnetId, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Telegram: ")
                .append(getTelegram())
                .append(" Email: ")
                .append(getEmail())
                .append(" NusnetId: ")
                .append(getNusnetId())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
