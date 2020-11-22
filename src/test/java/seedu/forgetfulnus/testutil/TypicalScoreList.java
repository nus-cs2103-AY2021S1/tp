package seedu.forgetfulnus.testutil;

import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalFlashCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.score.Score;

/**
 * A utility class containing a list of {@code Score} objects to be used in tests.
 */

public class TypicalScoreList {

    public static final Score SCORE_1 = new Score(0, 5, new ArrayList<>(Arrays.asList(
            TypicalFlashCards.FLASHCARD_1,
            TypicalFlashCards.FLASHCARD_2,
            TypicalFlashCards.FLASHCARD_3,
            TypicalFlashCards.FLASHCARD_4,
            TypicalFlashCards.FLASHCARD_5)));
    public static final Score SCORE_2 = new Score(1, 5, new ArrayList<>(Arrays.asList(
            TypicalFlashCards.FLASHCARD_6,
            TypicalFlashCards.FLASHCARD_7,
            TypicalFlashCards.FLASHCARD_8,
            TypicalFlashCards.FLASHCARD_9,
            TypicalFlashCards.FLASHCARD_10)));
    public static final Score SCORE_3 = new Score(2, 6, new ArrayList<>(Arrays.asList(
            TypicalFlashCards.FLASHCARD_11,
            TypicalFlashCards.FLASHCARD_12,
            TypicalFlashCards.FLASHCARD_13,
            TypicalFlashCards.FLASHCARD_14,
            TypicalFlashCards.FLASHCARD_15,
            TypicalFlashCards.FLASHCARD_16)));
    public static final Score SCORE_4 = new Score(3, 7, new ArrayList<>(Arrays.asList(
            TypicalFlashCards.FLASHCARD_17,
            TypicalFlashCards.FLASHCARD_18,
            TypicalFlashCards.FLASHCARD_19,
            TypicalFlashCards.FLASHCARD_20,
            TypicalFlashCards.FLASHCARD_21,
            TypicalFlashCards.FLASHCARD_22,
            TypicalFlashCards.MORNING)));
    public static final Score SCORE_5 = new Score(15, 22, getTypicalFlashCards());

    public static ScoreList getTypicalScoreList() {
        ScoreList sc = new ScoreList();
        for (Score next : getTypicalScores()) {
            sc.addScore(next);
        }
        return sc;
    }

    public static ScoreList getScoreList(List<Score> scores) {
        ScoreList sc = new ScoreList();
        for (Score next: scores) {
            sc.addScore(next);
        }
        return sc;
    }

    public static List<Score> getTypicalScores() {
        return new ArrayList<>(Arrays.asList(
                SCORE_1,
                SCORE_2,
                SCORE_3,
                SCORE_4,
                SCORE_5
        ));
    }
}
