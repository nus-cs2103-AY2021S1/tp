package seedu.taskmaster.model.record;

import java.util.logging.Level;
import java.util.logging.Logger; // to test logging

public class ClassParticipation {
    public static final String MESSAGE_CONSTRAINTS = "The score of a student needs to be an integer between"
            + "0 and the maximum score (default 10), inclusive.";
    private static Logger logger = Logger.getLogger("LoggingTest");

    private int score = 0;
    private int maxScore;

    /**
     * Constructor method for Class Participation
     * Uses the default maximum score, which is 10
     * The initial score is set to 0
     */
    public ClassParticipation() {
        logger.log(Level.INFO, "Making Class Participation");
        this.maxScore = 10;
    }

    /**
     * Constructor method for Class Participation using score
     * Uses the default maximum score, which is 10
     * The score is set to the wanted score
     */
    public ClassParticipation(int score) {
        logger.log(Level.INFO, "Making Class Participation");
        this.maxScore = 10;
        this.score = score;
    }

    /**
     * Constructor method for Class Participation with score and maxScore
     * The maximum score is given and set accordingly
     * The score is given and set accordingly
     */
    public ClassParticipation(int score, int maxScore) {
        logger.log(Level.INFO, "Making Class Participation");
        this.maxScore = maxScore;
        this.score = score;
    }

    public void setScore(int score) {
        assert score >= 0;
        this.score = score;
    }

    public void setMaxScore(int maxScore) {
        assert maxScore >= score;
        assert maxScore != 0;
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "Class Participation Score:"
                + score + " out of " + maxScore;
    }

    public String description() {
        return this.toString();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassParticipation)) {
            return false;
        }

        // state check
        ClassParticipation classPart = (ClassParticipation) other;
        return (this.score == classPart.score)
                && (this.maxScore == classPart.maxScore);
    }
}
