package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.OpenEndedQuestion;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;

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

        commandResult = new CommandResult("feedback", question, false, false);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", question,
                false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", question,
                false, false)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true,
                 false, false, question, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true,
                 false, question, false)));
    }

    @Test
    public void constructors_invalidArguments_throwException() {

        // null values that are not caught by static typing but are actually not allowed
        assertThrows(NullPointerException.class, () -> new CommandResult(null));
        assertThrows(NullPointerException.class, () -> new CommandResult("Feedback", null));
        assertThrows(NullPointerException.class, () -> new CommandResult("Feedback", null, new Statistics()));

        // exceptions thrown when values don't make sense for GUI to process
        assertThrows(IllegalArgumentException.class, () -> new CommandResult("Feedback", true, true, false));
        assertThrows(IllegalArgumentException.class, () -> new CommandResult("Feedback", true, false, true));
        assertThrows(IllegalArgumentException.class, () -> new CommandResult("Feedback", false, true, true));

        Question validQuestion = new OpenEndedQuestion("Valid Question", new Answer("Valid Answer"));
        assertThrows(IllegalArgumentException.class, () -> new CommandResult("Feedback", validQuestion, true, false));
    }

    @Test
    public void getters() {
        CommandResult helpTrueCommandResult =
                new CommandResult("feedback", true, false, false);
        CommandResult exitTrueCommandResult =
                new CommandResult("feedback", false, true, false);
        CommandResult changeWindowTrueCommandResult =
                new CommandResult("feedback", false, false, true);
        CommandResult allFalseCommandResult =
                new CommandResult("feedback", false, false, false);


        assertTrue(changeWindowTrueCommandResult.isChangeWindow());
        assertTrue(exitTrueCommandResult.isExit());
        assertTrue(helpTrueCommandResult.isShowHelp());

        assertFalse(allFalseCommandResult.isChangeWindow());
        assertFalse(allFalseCommandResult.isExit());
        assertFalse(allFalseCommandResult.isShowHelp());

        assertEquals(helpTrueCommandResult.getFeedback(), allFalseCommandResult.getFeedback());
        assertEquals(helpTrueCommandResult.getFeedbackToUser(), allFalseCommandResult.getFeedbackToUser());
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
