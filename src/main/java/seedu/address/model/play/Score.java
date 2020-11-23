package seedu.address.model.play;

public class Score {

    public static final String MESSAGE_CONSTRAINTS = "Score cannot be negative";

    private double maxScore;
    private double yourScore;

    /**
     * @param maxScore
     * @param yourScore
     */
    public Score(double maxScore, double yourScore) {
        this.maxScore = maxScore;
        this.yourScore = yourScore;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public double getYourScore() {
        return yourScore;
    }

    public double getScoreInPercentage() {
        return (yourScore / maxScore) * 100.0;
    }

    /**
     * @param score
     * @return
     */
    public static boolean isValidScore(double score) {
        return !(score < 0);
    }

    @Override
    public String toString() {
        return "Score{"
                + "maxScore=" + maxScore
                + ", yourScore=" + yourScore
                + '}';
    }
}
