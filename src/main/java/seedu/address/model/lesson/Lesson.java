package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;

public class Lesson {

    // Identity fields
    private final Name name;
    private final Deadline deadline;

    // Data fields
    private final ModuleCode moduleCode;

    /**
     * Every field must be present and not null.
     */
    public Lesson(Name name, Deadline deadline, ModuleCode moduleCode) {
        requireAllNonNull(name, deadline, moduleCode);
        this.name = name;
        this.deadline = deadline;
        this.moduleCode = moduleCode;
    }

    public Name getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns true if both lessons of the same name and same time.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getName().equals(getName())
                && (otherLesson.getDeadline().equals(getDeadline()));
    }

    /**
     * Returns true if both lessons have the same identity.
     * This defines a stronger notion of equality between two lessons.
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
                && otherLesson.getDeadline().equals(getDeadline())
                && otherLesson.getModuleCode().equals(getModuleCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, moduleCode);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Time: ")
                .append(getDeadline())
                .append(" Module: ")
                .append(getModuleCode());
        return builder.toString();
    }
}
