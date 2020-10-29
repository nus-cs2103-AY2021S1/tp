package seedu.taskmaster.model.record;

import java.util.logging.Level;
import java.util.logging.Logger; // to test logging

public class ClassParticipation {
    public static final String MESSAGE_CONSTRAINTS = "The score of a student needs to be a positive integer.";
    private static final Logger logger = Logger.getLogger("LoggingTest");

    private int score = 0;

    /**
     * Constructor method for Class Participation
     * Uses the default maximum score, which is 10
     * The initial score is set to 0
     */
    public ClassParticipation() {
        logger.log(Level.INFO, "Making Class Participation");
    }

    /**
     * Constructor method for Class Participation using score
     * Uses the default maximum score, which is 10
     * The score is set to the wanted score
     */
    public ClassParticipation(int score) {
        logger.log(Level.INFO, "Making Class Participation");
        assert score >= 0;
        this.score = score;
    }

    /**
     * Constructor method for Class Participation with score and maxScore
     * The maximum score is given and set accordingly
     * The score is given and set accordingly
     */

    @Override
    public String toString() {
        return "Class Participation Score: " + score;
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
        return (this.score == classPart.score);
    }
}
