package quickcache.model.flashcard;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A class to store information of a flashcard's statistics.
 */
public class Statistics {
    public static final String TIMES_TESTED_CONSTRAINTS = "Times tested should be a positive integer";
    public static final String TIMES_TESTED_LESS_THAN_TIMES_TESTED_CORRECT_CONSTRAINT = "Times tested should be more "
            + "than times tested correctly";

    private final int timesTested;
    private final int timesTestedCorrect;

    /**
     * A constructor to create statistics object.
     */
    public Statistics() {
        this.timesTested = 0;
        this.timesTestedCorrect = 0;
    }

    /**
     * A constructor to create statistics object.
     *
     * @param timesTested Number of times the flashcard is tested.
     * @param timesTestedCorrect Number of times the flashcard is tested correctly.
     */
    public Statistics(int timesTested, int timesTestedCorrect) {
        if (timesTested < 0 || timesTestedCorrect < 0) {
            throw new IllegalArgumentException(TIMES_TESTED_CONSTRAINTS);
        }
        if (timesTested < timesTestedCorrect) {
            throw new IllegalArgumentException(TIMES_TESTED_LESS_THAN_TIMES_TESTED_CORRECT_CONSTRAINT);
        }
        this.timesTested = timesTested;
        this.timesTestedCorrect = timesTestedCorrect;
    }

    public Statistics incrementTimesTested() {
        return new Statistics(timesTested + 1, timesTestedCorrect);
    }

    public Statistics incrementTimesTestedCorrect() {
        return new Statistics(timesTested, timesTestedCorrect + 1);
    }

    public int getTimesTested() {
        return timesTested;
    }

    public int getTimesTestedCorrect() {
        return timesTestedCorrect;
    }

    /**
     * Get the rate at which the user answers the flashcard's question correctly.
     *
     * @return The rate at which the user answers the flashcard's question correctly.
     */
    public double getCorrectRate() {
        if (timesTested == 0) {
            return 0.0;
        }

        return round(((double) timesTestedCorrect) / timesTested * 100, 2);
    }

    // Solution below adapted from https://stackoverflow.com/questions/2808535
    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * A method to check if otherStatistics is the same with the current one.
     */
    @Override
    public boolean equals(Object otherStatistics) {
        if (otherStatistics == this) {
            return true;
        } else if (otherStatistics instanceof Statistics) {
            Statistics other = (Statistics) otherStatistics;
            return other.getTimesTested() == getTimesTested()
                    && other.getTimesTestedCorrect() == getTimesTestedCorrect();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Times Correct: %d\nTimes Tested: %d\nCorrect Rate: %.2f%%\n",
                timesTestedCorrect, timesTested, getCorrectRate());
    }
}
