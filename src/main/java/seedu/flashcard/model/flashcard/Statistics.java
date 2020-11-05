package seedu.flashcard.model.flashcard;

import static seedu.flashcard.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents the Statistics on the Flashcard.
 */
public class Statistics {
    public static final String STATISTICS_CONSTRAINT = "Review frequency and success frequency should be positive "
            + "integers, and review frequency should be greater than or equal to success frequency";

    private final int reviewFrequency;
    private final int successFrequency;

    /**
     * Constructs a {@code Statistics} object.
     */
    public Statistics() {
        this.reviewFrequency = 0;
        this.successFrequency = 0;
    }

    /**
     * Constructs a {@code Statistics} object.
     *
     * @param reviewFrequency Review frequency of the flashcard.
     * @param successFrequency Success frequency of the flashcard.
     */
    public Statistics(int reviewFrequency, int successFrequency) {
        checkArgument(isValidStatistics(reviewFrequency, successFrequency), STATISTICS_CONSTRAINT);
        this.reviewFrequency = reviewFrequency;
        this.successFrequency = successFrequency;
    }

    public Statistics incrementReviewFrequency() {
        return new Statistics(this.reviewFrequency + 1, this.successFrequency);
    }

    public Statistics incrementSuccessFrequency() {
        return new Statistics(this.reviewFrequency, this.successFrequency + 1);
    }

    public int getReviewFrequency() {
        return this.reviewFrequency;
    }

    public int getSuccessFrequency() {
        return this.successFrequency;
    }

    /**
     * Returns true if a given statistics is a valid statistics.
     */
    public static boolean isValidStatistics(int reviewFrequency, int successFrequency) {
        boolean isReviewFrequencyValid = reviewFrequency >= 0;
        boolean isSuccessFrequencyValid = successFrequency >= 0;
        boolean isReviewFrequencyGreaterThanOrEqualToSuccessFrequency = reviewFrequency >= successFrequency;
        return isReviewFrequencyValid && isSuccessFrequencyValid
                && isReviewFrequencyGreaterThanOrEqualToSuccessFrequency;
    }

    /**
     * Gets the rate at which the user answers the flashcard's question correctly, rounded to 2 decimal places.
     *
     * @return The rate at which the user answers the flashcard's question correctly.
     */
    public double getSuccessRate() {
        if (reviewFrequency == 0) {
            return 0.0;
        }
        double reviewFreqAsDouble = reviewFrequency;
        double successFreqAsDouble = successFrequency;
        return ((successFreqAsDouble / reviewFreqAsDouble) * 100.0);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Statistics) {
            Statistics otherStatistics = (Statistics) other;
            return otherStatistics.getReviewFrequency() == getReviewFrequency()
                    && otherStatistics.getSuccessFrequency() == getSuccessFrequency();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewFrequency, successFrequency);
    }

    @Override
    public String toString() {
        return String.format("Review Frequency: %d\nSuccess Frequency: %d\nSuccess Rate: %.2f%%\n",
                reviewFrequency, successFrequency, getSuccessRate());
    }
}
