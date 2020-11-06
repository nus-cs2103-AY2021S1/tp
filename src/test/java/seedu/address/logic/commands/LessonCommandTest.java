package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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
        Lesson lesson = VALID_LESSON_CS2100;
        LessonCommand lessonCommand = new LessonCommand(lesson);
        expectedModel.addLesson(lesson);
        String expectedMessage = String.format(MESSAGE_SUCCESS, lesson);
        assertCommandSuccess(lessonCommand, model, expectedMessage, expectedModel);
    }
}
