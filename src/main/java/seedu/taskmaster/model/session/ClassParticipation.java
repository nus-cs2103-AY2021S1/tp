package seedu.taskmaster.model.session;
import java.util.logging.*; // to test logging

public class ClassParticipation {
    int score = 0;
    int maxScore;
    private static Logger logger = Logger.getLogger("LoggingTest");

    public ClassParticipation() {
        logger.log(Level.INFO, "Making Class Participation");
        this.maxScore = 0;
    }

    public void setScore(int score) {
        assert score > 0;
        this.score = score;
    }

    public void setMaxScore(int maxScore) {
        assert maxScore >= score;
        this.maxScore = maxScore;
    }

    @Override
    public String toString() {
        return "Class Participation Score:" +
                + score + " out of " + maxScore;
    }

    public String description() {
        return "To be implemented";
    }
}
