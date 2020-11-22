package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.forgetfulnus.testutil.TypicalScoreList.getTypicalScoreList;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.ReadOnlyScoreList;
import seedu.forgetfulnus.model.ReadOnlyUserPrefs;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;


public class ResetScoreCommandTest {

    @Test
    public void execute_emptyScoreList_success() {
        ReadOnlyGlossary initialData = new Glossary();
        ReadOnlyScoreList initialScores = new ScoreList();
        ReadOnlyUserPrefs userPrefs = new UserPrefs();
        Model model = new ModelManager(initialData, initialScores, userPrefs);
        Model expectedModel = new ModelManager(initialData, initialScores, userPrefs);

        assertCommandSuccess(new ResetScoreCommand(), model, ResetScoreCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyScoreList_success() {
        Model model = new ModelManager(new Glossary(), getTypicalScoreList(), new UserPrefs());
        Model expectedModel = new ModelManager(new Glossary(), getTypicalScoreList(), new UserPrefs());
        expectedModel.setScoreList(new ScoreList());

        assertCommandSuccess(new ResetScoreCommand(), model, ResetScoreCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_quizMode_showsQuizModeReminder() {
        Model model = new ModelManager(new Glossary(), getTypicalScoreList(), new UserPrefs());
        model.setQuizMode(true);
        try {
            CommandResult actualResult = new ResetScoreCommand().executeWithChecks(model);
            CommandResult expectedErrorResult = new CommandResult(ResetScoreCommand.QUIZ_MODE_REMINDER);
            assertEquals(actualResult, expectedErrorResult);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

}
