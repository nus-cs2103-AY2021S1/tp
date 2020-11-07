package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.timetable.Activity;
import seedu.address.model.util.Name;

/**
 * Represents a Lesson in user's timetable.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson extends Activity {

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Lesson(Name name, Set<Tag> tags) {
        super(name);
        requireNonNull(tags);
        this.tags.addAll(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both lessons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getName().equals(getName());
    }

    /**
     * Returns true if both lessons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two lessons.
     */
    @Override
    public boolean isSameActivity(Activity otherActivity) {
        if (otherActivity == this) {
            return true;
        }

        if (!(otherActivity instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) otherActivity;
        return otherLesson.getName().equals(getName());
    }

    @Override
    public boolean isRoutine() {
        return false;
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two exercises.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getName().equals(getName())
                && otherLesson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
