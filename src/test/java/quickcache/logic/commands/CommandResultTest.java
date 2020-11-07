package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.OpenEndedQuestion;
import quickcache.model.flashcard.Question;

public class CommandResultTest {

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false,
                false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                false, true, false)));

        Answer answer = new Answer("answer");
        Question question = new OpenEndedQuestion("question", answer);

        commandResult = new CommandResult("feedback", question, true, false);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", question,
                true, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", question,
                true, false)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true,
                 false, false, question, true)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true,
                 false, question, true)));

        commandResult = new CommandResult("feedback", question, true, false);
    }

    @Test
    public void getters() {
        CommandResult allTrueCommandResult =
                new CommandResult("feedback", true, true, true);
        CommandResult allFalseCommandResult =
                new CommandResult("feedback", false, false, false);


        assertTrue(allTrueCommandResult.isChangeWindow());
        assertTrue(allTrueCommandResult.isExit());
        assertTrue(allTrueCommandResult.isShowHelp());

        assertFalse(allFalseCommandResult.isChangeWindow());
        assertFalse(allFalseCommandResult.isExit());
        assertFalse(allFalseCommandResult.isShowHelp());

        assertEquals(allTrueCommandResult.getFeedback(), allFalseCommandResult.getFeedback());
        assertEquals(allTrueCommandResult.getFeedbackToUser(), allFalseCommandResult.getFeedbackToUser());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false).hashCode());
    }

}
