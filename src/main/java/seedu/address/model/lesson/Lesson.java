package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Lesson {

    // Identity fields
    private final LessonName name;
    private final Time time;
    private final ModuleCode moduleCode;

    /**
     * Every field must be present and not null.
     */
    public Lesson(LessonName name, Time time, ModuleCode moduleCode) {
        requireAllNonNull(name, time, moduleCode);
        this.name = name;
        this.time = time;
        this.moduleCode = moduleCode;
    }

    public LessonName getLessonName() {
        return name;
    }

    public Time getTime() {
        return time;
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
                && otherLesson.getLessonName().equals(getLessonName())
                && (otherLesson.getTime().equals(getTime()));
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
        return otherLesson.getLessonName().equals(getLessonName())
                && otherLesson.getTime().equals(getTime())
                && otherLesson.getModuleCode().equals(getModuleCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, time, moduleCode);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getLessonName())
                .append(" Time: ")
                .append(getTime())
                .append(" Module: ")
                .append(getModuleCode());
        return builder.toString();
    }
}
