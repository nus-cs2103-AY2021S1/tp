package seedu.forgetfulnus.model.score;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.forgetfulnus.model.flashcard.FlashCard;

public class Score {

    private int score;
    private final int numQuestions;
    private final List<FlashCard> flashCards = new ArrayList<>();

    /**
     * Represents a Score from a round of quizzing.
     * Guarantees: details are present and not null.
     */
    public Score(int score, int numQuestions, List<FlashCard> testedFlashCards) {
        this.score = score;
        this.numQuestions = numQuestions;
        this.flashCards.addAll(testedFlashCards);
    }

    /**
     * Represents a Score from a round of quizzing.
     * Duplicates data from another Score object.
     */
    public Score(Score other) {
        requireNonNull(other);
        this.score = other.score;
        this.numQuestions = other.numQuestions;
        this.flashCards.addAll(other.flashCards);
    }

    public int getScore() {
        return score;
    }

    public int getNumQuestions() {
        return this.numQuestions;
    }

    public List<FlashCard> getFlashcards() {
        return flashCards;
    }

    public void addFlashcard(FlashCard next) {
        flashCards.add(next);
    }

    public void incrementScore() {
        score++;
    }

    public int getIndex() {
        return this.flashCards.size();
    }

    public boolean isSameScore(Score editedScore) {
        return this.flashCards.equals(editedScore.flashCards);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof Score // instanceof handles nulls
                && score == ((Score) obj).score)
                && numQuestions == ((Score) obj).numQuestions
                && flashCards.equals(((Score) obj).flashCards);
    }
}
