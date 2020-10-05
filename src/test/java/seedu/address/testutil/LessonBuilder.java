package seedu.address.testutil;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.ModuleCode;
import seedu.address.model.lesson.Time;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final String DEFAULT_NAME = "CS2103T Lecture";
    public static final String DEFAULT_TIME = "01-01-2020 1200";
    public static final String DEFAULT_MODULE_CODE = "CS2103T";

    private LessonName name;
    private Time time;
    private ModuleCode moduleCode;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        name = new LessonName(DEFAULT_NAME);
        time = new Time(DEFAULT_TIME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        name = lessonToCopy.getLessonName();
        time = lessonToCopy.getTime();
        moduleCode = lessonToCopy.getModuleCode();
    }

    /**
     * Sets the {@code Name} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withLessonName(String name) {
        this.name = new LessonName(name);
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
     * Sets the {@code ModuleCode} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    public Lesson build() {
        return new Lesson(name, time, moduleCode);
    }
}
