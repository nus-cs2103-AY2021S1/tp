package seedu.address.flashcard;

public class Statistics {
    private final int timesTested;
    private final int timesTestedCorrect;

    public static final String TIMES_TESTED_CONSTRAINTS = "Times tested should be a positive integer";
    public static final String TIMES_TESTED_LESS_THAN_TIMES_TESTED_CORRECT_CONSTRAINT = "Times tested should be more "
            + "than times tested correctly";

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

    public double getCorrectRate() {
        return ((double)timesTestedCorrect) / timesTested;
    }

    /**
     * A method to check if otherFlashcard is the same with the current one.
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
}
