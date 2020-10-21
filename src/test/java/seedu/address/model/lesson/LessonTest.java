package seedu.address.model.lesson;

import org.junit.jupiter.api.Test;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_CS2103T;

public class LessonTest {
    Title title = new Title(VALID_TITLE_CS2103T);
    Description description = new Description(VALID_DESC_CS2103T);
    DayOfWeek day = DayOfWeek.MONDAY;
    LocalTime startTime = new Time(VALID_START_TIME_CS2103T).value;
    LocalTime endTime = new Time(VALID_END_TIME_CS2103T).value;
    LocalDate startDate = new Date(VALID_START_DATE_CS2103T).value;
    LocalDate endDate = new Date(VALID_END_DATE_CS2103T).value;
    LocalTime differentStartTime = new Time("10:00").value;

    Description differentDescription = new Description("Different description");
    Title differentTitle = new Title("CS1101S Lecture");
    DayOfWeek differentDay = DayOfWeek.FRIDAY;

    public final Lesson CS_LESSON = new Lesson(title, description, day, startTime, endTime, startDate, endDate);
    public final Lesson CS_LESSON_DIFF_DESC = new Lesson(title, differentDescription, day, startTime,
            endTime, startDate, endDate);
    public final Lesson CS_LESSON_DIFF_TITLE = new Lesson(differentTitle, description, day, startTime, endTime,
            startDate, endDate);
    public final Lesson CS_LESSON_DIFF_START_TIME = new Lesson(title, description, day, differentStartTime, endTime,
            startDate, endDate);
    public final Lesson CS_LESSON_DIFF_DAY = new Lesson(title, description, differentDay, startTime, endTime,
            startDate, endDate);
    
    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(CS_LESSON.isSameLesson(CS_LESSON));

        // null -> returns false
        assertFalse(CS_LESSON.isSameLesson(null));

        // different description -> returns true
        assertTrue(CS_LESSON.isSameLesson(CS_LESSON_DIFF_DESC));

        // different title -> returns false
        assertFalse(CS_LESSON.isSameLesson(CS_LESSON_DIFF_TITLE));

        // same title, different start time -> returns false
        assertFalse(CS_LESSON.isSameLesson(CS_LESSON_DIFF_START_TIME));
        
        //same attributes, different day -> returns false
        assertFalse(CS_LESSON.isSameLesson(CS_LESSON_DIFF_DAY));
    }
}
