package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NextCommand.
 */
public class NextCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new ScoreList(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new ScoreList(), new UserPrefs());
    }

    @Test
    public void execute_quizModeReminderCheck() {
        assertCommandSuccess(new NextCommand(), model, NextCommand.QUIZ_MODE_REMINDER, expectedModel);
    }

    @Test
    public void execute_indexCheck() throws CommandException {
        new QuizCommand().executeWithChecks(model);
        Assertions.assertEquals(model.getQuizModeIndex(), 0);

        model.resetQuiz();
        try {
            int i = model.getFilteredFlashCardList().size();
            while (i > 1) {
                new NextCommand().executeWithChecks(model);
                i--;
            }
            //System.out.println("hng: " + model.getFilteredFlashCardList().size());
            Assertions.assertEquals(model.getQuizModeIndex() + 1, model.getFilteredFlashCardList().size());

            new NextCommand().executeWithChecks(model);
            Assertions.assertEquals(model.getQuizModeIndex(), model.getFilteredFlashCardList().size());
            assertEquals(model.getQuizScore(), 0);
            assertEquals(model.getQuizTotalQuestions(), model.getFilteredFlashCardList().size());
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
