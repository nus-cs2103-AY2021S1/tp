package seedu.address.model.play.scoring;

public interface Scoring {
    public abstract double computeScore(String answer, String guess);
}
