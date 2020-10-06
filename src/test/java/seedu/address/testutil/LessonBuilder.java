package seedu.address.testutil;

import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;
import seedu.address.model.lesson.Lesson;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final String DEFAULT_NAME = "CS2103T Lecture";
    public static final String DEFAULT_DEADLINE = "01-01-2020 1200";
    public static final String DEFAULT_MODULE_CODE = "CS2103T";

    private Name name;
    private Deadline deadline;
    private ModuleCode moduleCode;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        name = lessonToCopy.getName();
        deadline = lessonToCopy.getDeadline();
        moduleCode = lessonToCopy.getModuleCode();
    }

    /**
     * Sets the {@code Name} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withDeadline(String time) {
        this.deadline = new Deadline(time);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    public Lesson build() {
        return new Lesson(name, deadline, moduleCode);
    }
}
