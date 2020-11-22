package seedu.forgetfulnus.model.score;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.forgetfulnus.model.score.exceptions.DuplicateScoreException;
import seedu.forgetfulnus.model.score.exceptions.ScoreNotFoundException;

public class UniqueScoreList implements Iterable<Score> {

    private final ObservableList<Score> internalList = FXCollections.observableArrayList();
    private final ObservableList<Score> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent score as the given argument.
     */
    public boolean contains(Score toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameScore);
    }

    /**
     * Returns an equivalent {@code Score} to the given argument,
     * if such a {@code Score} is present in the list.
     */
    public Optional<Score> findMatch(Score toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().filter(toCheck::isSameScore)
                .findAny();
    }

    /**
     * Adds a score to the list.
     * If the score contains the same flashcard list as any other score
     * already in the list, the original score is overwritten.
     */
    public void add(Score toAdd) {
        requireNonNull(toAdd);
        Optional<Score> previousMatch = findMatch(toAdd);
        previousMatch.ifPresentOrElse(score -> setScore(score, toAdd), () -> internalList.add(toAdd));
    }

    /**
     * Replaces the Score {@code target} in the list with {@code editedScore}.
     * {@code target} must exist in the list.
     * The Score identity of {@code editedScore} must not be the same as another existing Score in the list.
     */
    public void setScore(Score target, Score editedScore) {
        requireAllNonNull(target, editedScore);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ScoreNotFoundException();
        }

        if (!target.isSameScore(editedScore) && contains(editedScore)) {
            throw new DuplicateScoreException();
        }

        internalList.set(index, editedScore);
    }

    /**
     * Removes the equivalent score from the list.
     * The score must exist in the list.
     */
    public void remove(Score toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ScoreNotFoundException();
        }
    }

    public void setScores(UniqueScoreList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code scores}.
     * {@code scores} must not contain duplicate scores.
     */
    public void setScores(List<Score> scores) {
        requireAllNonNull(scores);
        if (!scoresAreUnique(scores)) {
            throw new DuplicateScoreException();
        }

        internalList.setAll(scores);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Score> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Score> iterator() {
        return internalList.iterator();
    }

    public ListIterator<Score> getReverseIterator() {
        return internalList.listIterator(internalList.size());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueScoreList // instanceof handles nulls
                && internalList.equals(((UniqueScoreList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code scores} contains only unique scores.
     */
    private boolean scoresAreUnique(List<Score> scores) {
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = i + 1; j < scores.size(); j++) {
                if (scores.get(i).isSameScore(scores.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
