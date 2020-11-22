package seedu.forgetfulnus.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.ListIterator;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.score.Score;
import seedu.forgetfulnus.model.score.UniqueScoreList;


public class ScoreList implements ReadOnlyScoreList {

    private final UniqueScoreList scores;

    public ScoreList() {
        scores = new UniqueScoreList();
    }

    /**
     * Wraps all score data.
     * Duplicates are not allowed (by .isSameScore comparison)
     */
    public ScoreList(ReadOnlyScoreList initialScores) {
        this();
        resetData(initialScores);
    }

    /**
     * Resets the existing data of this {@code ScoreList} with {@code scoreList}.
     */
    public void resetData(ReadOnlyScoreList scoreList) {
        requireNonNull(scoreList);
        setScores(scoreList.getScoreList());
    }

    private void setScores(List<Score> scoreList) {
        this.scores.setScores(scoreList);
    }

    @Override
    public ObservableList<Score> getScoreList() {
        return scores.asUnmodifiableObservableList();
    }

    public void addScore(Score score) {
        scores.add(score);
    }

    /**
     * Returns String containing the numeric score and German words tested for each
     * {@code Score} in the {@code ScoreList}.
     * @return String describing each numeric score and German words tested
     */
    public String asViewable() {
        StringBuilder sb = new StringBuilder();
        ListIterator<Score> iterator = scores.getReverseIterator();
        while (iterator.hasPrevious()) {
            Score previous = iterator.previous();
            sb.append(String.format("\n\nScore: [%s / %s]", previous.getScore(), previous.getNumQuestions()));
            sb.append("\nWords tested:");
            List<FlashCard> fc = previous.getFlashcards();
            ListIterator<FlashCard> fcIterator = fc.listIterator();
            while (fcIterator.hasNext()) {
                sb.append("\n");
                sb.append(String.format("%d. ", fcIterator.nextIndex() + 1));
                sb.append(fcIterator.next().getGermanPhrase());
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScoreList // instanceof handles nulls
                && scores.equals(((ScoreList) other).scores));
    }
}
