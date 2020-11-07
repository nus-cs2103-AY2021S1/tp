package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StatisticsTest {

    @Test
    public void constructor_negativeTimesTested_throwsIllegalArgumentException() {
        // negative times tested
        assertThrows(IllegalArgumentException.class, () ->
            new Statistics(-1, 0));

        // negative times tested correct
        assertThrows(
            IllegalArgumentException.class, () ->
                new Statistics(-1, 0));
    }

    @Test
    public void constructor_timesTestedLessThanTimesTestedCorrect_throwsIllegalArgumentException() {
        assertThrows(
            IllegalArgumentException.class, () ->
                new Statistics(0, 1));
    }

    @Test
    public void incrementStats() {
        Statistics statistics = new Statistics(5, 3);

        statistics = statistics.incrementTimesTested();
        Statistics expectedStatistics = new Statistics(6, 3);

        assertEquals(statistics, expectedStatistics);

        statistics = statistics.incrementTimesTestedCorrect();
        expectedStatistics = new Statistics(6, 4);

        assertEquals(statistics, expectedStatistics);
    }

    @Test
    public void getCorrectRate() {
        Statistics newStatistics = new Statistics();
        assertEquals(newStatistics.getCorrectRate(), 0.0);

        Statistics updatedStatistics = new Statistics(3, 2);
        assertEquals(updatedStatistics.getCorrectRate(), (
                (double) updatedStatistics.getTimesTestedCorrect()) / updatedStatistics.getTimesTested() * 100);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Statistics newStatistics = new Statistics(5, 4);
        Statistics newStatisticsDuplicate = new Statistics(5, 4);
        assertTrue(newStatistics.equals(newStatisticsDuplicate));

        // same object -> returns true
        assertTrue(newStatistics.equals(newStatistics));

        // null -> returns false
        assertFalse(newStatistics.equals(null));

        // different type -> returns false
        assertFalse(newStatistics.equals(5));

        Statistics differentStatisticsTimesTested = new Statistics(6, 4);
        assertFalse(newStatistics.equals(differentStatisticsTimesTested));

        Statistics differentStatisticsTimesTestedCorrect = new Statistics(5, 3);
        assertFalse(newStatistics.equals(differentStatisticsTimesTestedCorrect));
    }

    @Test
    void testToString() {
        Statistics statistics = new Statistics();
        String expected = "Times Correct: 0\nTimes Tested: 0\nCorrect Rate: 0.00%\n";
        assertEquals(statistics.toString(), expected);

    }
}
