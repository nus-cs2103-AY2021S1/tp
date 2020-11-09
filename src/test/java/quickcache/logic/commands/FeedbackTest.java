package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.OpenEndedQuestion;
import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;

public class FeedbackTest {

    @Test
    public void equals() {
        Question firstQuestion = new OpenEndedQuestion("First Question", new Answer("First Answer"));
        Question secondQuestion = new OpenEndedQuestion("Second Question", new Answer("Second Answer"));

        Statistics firstStatistics = new Statistics(5, 3);
        Statistics secondStatistics = new Statistics(7, 2);

        Feedback firstFeedback = new Feedback("First Feedback");
        Feedback secondFeedback = new Feedback("Second Feedback");

        firstFeedback.setCorrect(true);
        firstFeedback.setQuestion(firstQuestion);
        firstFeedback.setStatistics(firstStatistics);

        secondFeedback.setCorrect(false);
        secondFeedback.setQuestion(secondQuestion);
        secondFeedback.setStatistics(secondStatistics);

        // same object -> returns true
        assertTrue(firstFeedback.equals(firstFeedback));

        // same object -> same toString values
        assertTrue(firstFeedback.toString().equals(firstFeedback.toString()));

        // same values -> returns true
        Feedback firstFeedbackCopy = new Feedback("First Feedback");

        firstFeedbackCopy.setCorrect(true);
        firstFeedbackCopy.setQuestion(firstQuestion);
        firstFeedbackCopy.setStatistics(firstStatistics);

        assertTrue(firstFeedback.equals(firstFeedbackCopy));

        // different types -> returns false
        assertFalse(firstFeedback.equals(1));

        // null -> returns false
        assertFalse(firstFeedback.equals(null));

        // different index -> returns false
        assertFalse(firstFeedback.equals(secondFeedback));
    }

    @Test
    public void gettersAndSetters() {
        Question question = new OpenEndedQuestion("Question", new Answer("Answer"));
        Statistics statistics = new Statistics(5, 3);
        Feedback feedback = new Feedback("Feedback");

        feedback.setCorrect(true);
        feedback.setQuestion(question);
        feedback.setStatistics(statistics);

        assertEquals(feedback.getBody().get(), "Feedback");
        assertEquals(question, feedback.getQuestion().get());
        assertEquals(statistics, feedback.getStatistics().get());
        assertTrue(feedback.isCorrect().get());
    }

    @Test
    public void hashcode() {
        Question question = new OpenEndedQuestion("Question", new Answer("Answer"));
        Statistics statistics = new Statistics(5, 3);
        Feedback feedback = new Feedback("Feedback");
        Feedback feedbackToCheckAgainst = new Feedback("Feedback");

        feedback.setCorrect(true);
        feedback.setQuestion(question);
        feedback.setStatistics(statistics);

        feedbackToCheckAgainst.setCorrect(true);
        feedbackToCheckAgainst.setQuestion(question);
        feedbackToCheckAgainst.setStatistics(statistics);

        // same values -> returns same hashcode
        assertEquals(feedback.hashCode(), feedbackToCheckAgainst.hashCode());

        feedbackToCheckAgainst.setBody("Different");
        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(feedback.hashCode(), feedbackToCheckAgainst.hashCode());

        feedbackToCheckAgainst.setBody("Feedback");
        feedbackToCheckAgainst.setCorrect(false);

        // different isCorrect value -> returns different hashcode
        assertNotEquals(feedback.hashCode(), feedbackToCheckAgainst.hashCode());

        feedbackToCheckAgainst.setCorrect(true);

        Question differentQuestion = new OpenEndedQuestion("Different Question", new Answer("Different Answer"));
        feedbackToCheckAgainst.setQuestion(differentQuestion);

        // different question -> returns different hashcode
        assertNotEquals(feedback.hashCode(), feedbackToCheckAgainst.hashCode());

        feedbackToCheckAgainst.setQuestion(question);

        Statistics differentStatistics = new Statistics(7, 2);
        feedbackToCheckAgainst.setStatistics(differentStatistics);

        // different statistics -> returns different hashcode
        assertNotEquals(feedback.hashCode(), feedbackToCheckAgainst.hashCode());
    }
}
