package seedu.address.model.play.scoring;

public class QuestionAttempt {

    private String answer;
    private String guess;
    private double score;

    /**
     * @param answer
     * @param guess
     * @param scoringMethod
     */
    public QuestionAttempt(String answer, String guess, Scoring scoringMethod) {
        this.answer = answer;
        this.guess = guess;
        score = scoringMethod.computeScore(answer, guess);
    }

    /**
     * @param answer
     * @param guess
     * @param score
     */
    public QuestionAttempt(String answer, String guess, double score) {
        this.answer = answer;
        this.guess = guess;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public String getAnswer() {
        return answer;
    }

    public String getGuess() {
        return guess;
    }
}
