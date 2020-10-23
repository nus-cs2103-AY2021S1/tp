package seedu.flashcard.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatisticsTest {
    @Test
    public void constructor_invalidReviewFrequency_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Statistics(-3, 1));
    }

    @Test
    public void constructor_invalidSuccessFrequency_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Statistics(3, -1));
    }

    @Test
    public void constructor_reviewFrequencyLesserThanSuccessFrequency_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Statistics(3, 5));
    }

    @Test
    public void getSuccessRate() {
        Statistics newStatistics = new Statistics();
        assertEquals(newStatistics.getSuccessFrequency(), 0.0);

        Statistics updatedStatistics = new Statistics(3, 2);
        double actualSuccessRate = ((double) 2 / (double) 3) * 100.0;
        assertEquals(updatedStatistics.getSuccessRate(), actualSuccessRate);
    }

    @Test
    public void incrementReviewFrequency() {
        Statistics statistics = new Statistics();

        Statistics updatedStatistics = statistics.incrementReviewFrequency();
        assertEquals(updatedStatistics.getReviewFrequency(), statistics.getReviewFrequency() + 1);
    }

    @Test
    public void incrementSuccessFrequency() {
        Statistics statistics = new Statistics(3, 2);

        Statistics updatedStatistics = statistics.incrementSuccessFrequency();
        assertEquals(updatedStatistics.getSuccessFrequency(), statistics.getSuccessFrequency() + 1);
    }

    @Test
    public void isValidStatistics() {
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid review frequency
        assertFalse(Statistics.isValidStatistics(-3, 0));

        // invalid success frequency
        assertFalse(Statistics.isValidStatistics(3, -1));

        // invalid relationship between review and success frequencies
        assertFalse(Statistics.isValidStatistics(3, 5));

        // valid statistics
        assertTrue(Statistics.isValidStatistics(0, 0));
        assertTrue(Statistics.isValidStatistics(5, 3));
    }
}
