package seedu.address.testutil;

import static seedu.address.commons.util.DateTimeUtil.DATE_FORMATTER;
import static seedu.address.commons.util.DateTimeUtil.TIME_FORMATTER;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;


/**
 * A utility class to help with building Task objects.
 */
public class LessonBuilder {

    public static final String DEFAULT_TITLE = "Lecture";
    public static final String DEFAULT_START_DATE = "01-01-2020";
    public static final String DEFAULT_START_TIME = "12:00";
    public static final int DEFAULT_DAY_OF_WEEK = 1;
    public static final String DEFAULT_END_DATE = "01-01-2020";
    public static final String DEFAULT_END_TIME = "14:00";
    public static final String DEFAULT_DESCRIPTION = "great module";
    public static final String DEFAULT_TAG = "CS1101S";

    private Title title;
    private Description description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;
    private Tag tag;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        startDate = LocalDate.parse(DEFAULT_START_DATE, DATE_FORMATTER);
        endDate = LocalDate.parse(DEFAULT_END_DATE, DATE_FORMATTER);
        startTime = LocalTime.parse(DEFAULT_START_TIME, TIME_FORMATTER);
        endTime = LocalTime.parse(DEFAULT_END_TIME, TIME_FORMATTER);
        dayOfWeek = DayOfWeek.of(DEFAULT_DAY_OF_WEEK);
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        title = lessonToCopy.getTitle();
        startDate = lessonToCopy.getStartDate();
        endDate = lessonToCopy.getEndDate();
        startTime = lessonToCopy.getStartTime();
        endTime = lessonToCopy.getEndTime();
        description = lessonToCopy.getDescription();
        dayOfWeek = lessonToCopy.getDayOfWeek();
        tag = lessonToCopy.getTag();
    }

    /**
     * Sets the {@code Title} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, DATE_FORMATTER);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime, TIME_FORMATTER);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate, DATE_FORMATTER);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime, TIME_FORMATTER);
        return this;
    }

    /**
     * Sets the {@code dayOfWeek} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = DayOfWeek.of(dayOfWeek);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Lesson} that we are building to be default description.
     * Simulates the situation that the task is created without a description field.
     */
    public LessonBuilder withDefaultDescription() {
        this.description = Description.defaultDescription();
        return this;
    }


    public Lesson build() {
        return new Lesson(title, tag, description, dayOfWeek, startTime, endTime, startDate, endDate);
    }
}
