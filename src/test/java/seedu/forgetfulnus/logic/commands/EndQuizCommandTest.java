package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EndQuizCommand.
 */
public class EndQuizCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGlossary(), new ScoreList(), new UserPrefs());
        expectedModel = new ModelManager(model.getGlossary(), new ScoreList(), new UserPrefs());
    }

    @Test
    public void execute_checkEndQuiz() {
        try {
            new QuizCommand().executeWithChecks(model);
            new NextCommand().executeWithChecks(model);
            CommandResult cr = new EndQuizCommand().executeWithChecks(model);
            String expected = EndQuizCommand.MESSAGE_SUCCESS
                    + " Your score: " + "0" + " / " + model.getQuizTotalQuestions();
            assertEquals(cr.toString(), expected);
        } catch (CommandException e) {
            System.out.println(e);
        }
    }
}
