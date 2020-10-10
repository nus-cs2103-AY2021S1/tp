package seedu.address.testutil;

import seedu.address.model.lesson.Lesson;

public class TypicalLessons {
    public static final Lesson LECTURE = new LessonBuilder().withName("Generic Lecture")
            .withTime("01-01-2020 1200").withModuleCode("CS2103T").build();

    public static final String VALID_MODULE_CODE = "CS2101";
    public static final String VALID_NAME = "Generic Tutorial";
    public static final String VALID_TIME = "30-12-2019 1200";
    public static final String VALID_END_TIME = "30-12-2019 1400";
}
