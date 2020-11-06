package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2000;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LessonCommand.MESSAGE_DUPLICATE_LESSON;
import static seedu.address.logic.commands.LessonCommand.MESSAGE_OVERLAP;
import static seedu.address.logic.commands.LessonCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lesson.Lesson;

/**
 * Contains integration tests with overlap class methods.
 */
public class LessonCommandTest {
    //start with empty models
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void executeSuccessfully() {
        //lesson added
        Lesson lesson = VALID_LESSON_CS2100;
        LessonCommand lessonCommand = new LessonCommand(lesson);
        expectedModel.addLesson(lesson);
        String expectedMessage = String.format(MESSAGE_SUCCESS, lesson);
        assertCommandSuccess(lessonCommand, model, expectedMessage, expectedModel);
        //another lesson added
        Lesson lesson2 = VALID_LESSON_CS2103T;
        LessonCommand lessonCommand2 = new LessonCommand(lesson2);
        expectedModel.addLesson(lesson2);
        String expectedMessage2 = String.format(MESSAGE_SUCCESS, lesson2);
        assertCommandSuccess(lessonCommand2, model, expectedMessage2, expectedModel);
    }
    @Test
    public void duplicateLesson_executeFail() {
        //duplicate lesson
        Lesson lesson = VALID_LESSON_CS2100;
        LessonCommand lessonCommand = new LessonCommand(lesson);
        model.addLesson(lesson);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_LESSON, lesson);
        assertCommandFailure(lessonCommand, model, expectedMessage);
        //another duplicate lesson
        Lesson lesson2 = VALID_LESSON_CS2103T;
        LessonCommand lessonCommand2 = new LessonCommand(lesson2);
        model.addLesson(lesson2);
        String expectedMessage2 = String.format(MESSAGE_DUPLICATE_LESSON, lesson2);
        assertCommandFailure(lessonCommand2, model, expectedMessage);
    }
    @Test
    public void overlappingLesson_executeFail() {
        //overlapping lesson
        model.addLesson(VALID_LESSON_CS2103T);
        Lesson lesson = VALID_LESSON_CS2000;
        LessonCommand lessonCommand = new LessonCommand(lesson);
        String expectedMessage = String.format(MESSAGE_OVERLAP, lesson);
        assertCommandFailure(lessonCommand, model, expectedMessage);
    }
    @Test
    public void overlappingEventAndLesson_executeFail() {
        //lesson to be added clashes with existing event
        model.addTask(VALID_EVENT_MEETING);
        Lesson lesson = VALID_LESSON_CS2100;
        LessonCommand lessonCommand = new LessonCommand(lesson);
        String expectedMessage = String.format(MESSAGE_OVERLAP, lesson);
        assertCommandFailure(lessonCommand, model, expectedMessage);
    }
}
