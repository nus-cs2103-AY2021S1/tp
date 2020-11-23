package seedu.address.model.play.scoring;

public class BinaryScoring implements Scoring {
    @Override
    public double computeScore(String answer, String guess) {
        return answer.equals(guess) ? 1.0 : 0.0;
    }
}
