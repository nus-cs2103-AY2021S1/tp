package seedu.forgetfulnus.model.score.exceptions;

public class ScoreNotFoundException extends RuntimeException {
    public ScoreNotFoundException() {
        super("Operation would result in duplicate flashcards");
    }
}
