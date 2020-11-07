package seedu.address.testutil;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final String DEFAULT_NAME = "CS2103T Lecture";
    public static final String DEFAULT_TIME = "01-01-2020 1200";
    public static final String DEFAULT_END_TIME = "01-01-2020 1400";
    public static final String DEFAULT_MODULE_CODE = "CS2103T";

    private Name name;
    private Time time;
    private Time endTime;
    private ModuleCode moduleCode;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        name = new Name(DEFAULT_NAME);
        time = new Time(DEFAULT_TIME);
        endTime = new Time(DEFAULT_END_TIME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        name = lessonToCopy.getName();
        time = lessonToCopy.getTime();
        endTime = lessonToCopy.getEndTime();
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
    public LessonBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withEndTime(String endTime) {
        this.endTime = new Time(endTime);
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
        return new Lesson(name, time, endTime, moduleCode);
    }
}
