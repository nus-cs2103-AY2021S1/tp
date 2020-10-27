package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103T;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;

public class LessonTest {
    @Test
    public void isSameTask() {
        Title title = new Title(VALID_TITLE_CS2103T);
        Description description = new Description(VALID_DESC_CS2103T);
        DayOfWeek day = DayOfWeek.MONDAY;
        LocalTime startTime = new Time(VALID_START_TIME_CS2103T).value;
        LocalTime endTime = new Time(VALID_END_TIME_CS2103T).value;
        LocalDate startDate = DateUtil.toLocalDate(VALID_START_DATE_CS2103T);
        LocalDate endDate = DateUtil.toLocalDate(VALID_END_DATE_CS2103T);
        LocalTime differentStartTime = new Time("10:00").value;
        Tag tag = new Tag(VALID_TAG);
        Description differentDescription = new Description("Different description");
        Title differentTitle = new Title("CS1101S Lecture");
        DayOfWeek differentDay = DayOfWeek.FRIDAY;

        Lesson lesson = new Lesson(title, tag, description, day, startTime, endTime, startDate, endDate);
        Lesson lessonWithDifferentDescription = new Lesson(title, tag, differentDescription, day, startTime,
                endTime, startDate, endDate);
        Lesson lessonWithDifferentTitle = new Lesson(differentTitle, tag, description, day, startTime, endTime,
                startDate, endDate);
        Lesson lessonWithStartTime = new Lesson(title, tag, description, day, differentStartTime, endTime,
                startDate, endDate);
        Lesson lessonWithDifferentDay = new Lesson(title, tag, description, differentDay, startTime, endTime,
                startDate, endDate);
        // same object -> returns true
        assertTrue(lesson.isSameLesson(lesson));

        // null -> returns false
        assertFalse(lesson.isSameLesson(null));

        // different description -> returns true
        assertTrue(lesson.isSameLesson(lessonWithDifferentDescription));

        // different title -> returns false
        assertFalse(lesson.isSameLesson(lessonWithDifferentTitle));

        // same title, different start time -> returns false
        assertFalse(lesson.isSameLesson(lessonWithStartTime));
        //same attributes, different day -> returns false
        assertFalse(lesson.isSameLesson(lessonWithDifferentDay));
    }
}
