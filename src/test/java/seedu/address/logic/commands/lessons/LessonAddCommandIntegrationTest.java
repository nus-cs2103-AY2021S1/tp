package seedu.address.logic.commands.lessons;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
 import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.testutil.LessonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code LessonAddCommand}.
 */
public class LessonAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitNus(), new UserPrefs());
    }

    @Test
    public void execute_newLesson_success() {
        Lesson validLesson = new LessonBuilder().build();

        Model expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.addLesson(validLesson);

        assertCommandSuccess(new LessonAddCommand(validLesson), model,
                String.format(LessonAddCommand.MESSAGE_SUCCESS, validLesson), expectedModel);
    }

    @Test
    public void execute_duplicateLesson_throwsCommandException() {
        Lesson lessonInList = model.getFitNus().getLessonList().get(0);
        assertCommandFailure(new LessonAddCommand(lessonInList), model, LessonAddCommand.MESSAGE_DUPLICATE_LESSON);
    }

}
