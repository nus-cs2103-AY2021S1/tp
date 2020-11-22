package seedu.forgetfulnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalScoreList.SCORE_1;
import static seedu.forgetfulnus.testutil.TypicalScoreList.SCORE_2;
import static seedu.forgetfulnus.testutil.TypicalScoreList.SCORE_3;
import static seedu.forgetfulnus.testutil.TypicalScoreList.SCORE_4;
import static seedu.forgetfulnus.testutil.TypicalScoreList.SCORE_5;
import static seedu.forgetfulnus.testutil.TypicalScoreList.getScoreList;
import static seedu.forgetfulnus.testutil.TypicalScoreList.getTypicalScoreList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.score.Score;

public class ScoreListTest {
    private final ScoreList scoreList = new ScoreList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scoreList.getScoreList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scoreList.resetData(null));
    }

    @Test
    public void resetData_withValidScoreList_replacesData() {
        ScoreList newData = getTypicalScoreList();
        scoreList.resetData(newData);
        assertEquals(newData, scoreList);
    }

    @Test
    public void addScore_withDuplicateFlashCardList_replacesScore() {
        // Two scores with the same flashcard list
        Score duplicateScore = new Score(SCORE_1);
        duplicateScore.incrementScore();
        ScoreList originalList = getTypicalScoreList();
        ScoreList newList = getScoreList(Arrays.asList(duplicateScore,
                SCORE_2,
                SCORE_3,
                SCORE_4,
                SCORE_5));
        assertNotEquals(originalList, newList);
        //Add the duplicate score into the original list
        originalList.addScore(duplicateScore);
        assertEquals(originalList, newList);
    }

    @Test
    public void getScoreList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scoreList.getScoreList().remove(0));
    }

}
