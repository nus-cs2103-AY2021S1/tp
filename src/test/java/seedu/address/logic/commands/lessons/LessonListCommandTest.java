package seedu.address.logic.commands.lessons;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLessonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LessonListCommand.
 */
public class LessonListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitNus(), new UserPrefs());
        expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
    }

    @Test
    public void execute_lessonListIsNotFiltered_showsSameLessonList() {
        assertCommandSuccess(new LessonListCommand(), model, LessonListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_lessonListIsFiltered_showsEverything() {
        showLessonAtIndex(model, INDEX_FIRST_LESSON);
        assertCommandSuccess(new LessonListCommand(), model, LessonListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
