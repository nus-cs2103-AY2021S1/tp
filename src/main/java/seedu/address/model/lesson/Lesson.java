package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.Time;

public class Lesson extends Task {
    private final Time endTime;

    /**
     * Every field must be present and not null.
     */
    public Lesson(Name name, Time time, Time endTime, ModuleCode moduleCode) {
        super(name, time, moduleCode);
        this.endTime = endTime;
        requireAllNonNull(name, time, endTime, moduleCode);
    }

    public Time getEndTime() {
        return this.endTime;
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
                && otherLesson.getTime().equals(getTime())
                && otherLesson.getEndTime().equals(getEndTime());
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
                && otherLesson.getTime().equals(getTime())
                && otherLesson.getEndTime().equals(getEndTime())
                && otherLesson.getModuleCode().equals(getModuleCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.getName(), super.getTime(), getEndTime(), super.getModuleCode());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Time: ")
                .append(getTime())
                .append(" End Time: ")
                .append(getEndTime())
                .append(" Module: ")
                .append(getModuleCode());
        return builder.toString();
    }
}
