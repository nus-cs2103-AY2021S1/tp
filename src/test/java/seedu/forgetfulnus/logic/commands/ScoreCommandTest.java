package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;

public class ScoreCommandTest {
    @Test
    public void execute_emptyScoreList_showsEmptyMessage() {
        Model model = new ModelManager(new Glossary(), new ScoreList(), new UserPrefs());
        try {
            CommandResult actualResult = new ScoreCommand().executeWithChecks(model);
            CommandResult expectedErrorResult = new CommandResult(ScoreCommand.MESSAGE_EMPTY_SCORE_LIST);
            assertEquals(actualResult, expectedErrorResult);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }
}
