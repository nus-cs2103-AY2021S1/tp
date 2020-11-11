package seedu.bookmark.model.wordstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_AND;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_CHAMBER;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_HARRY;
import static seedu.bookmark.testutil.TypicalWords.HARRY;
import static seedu.bookmark.testutil.TypicalWords.MISSPELT_HARRY;

import org.junit.jupiter.api.Test;

public class WordTest {

    @Test
    public void getFieldValues_validFields_distanceSet() {
        Word validWordWithDistance = new Word("Word", 4);
        final String word = "Word";
        final int count = 1;
        final int distance = 4;

        assertEquals(validWordWithDistance.getWord(), word);
        assertEquals(validWordWithDistance.getCount(), count);
        assertEquals(validWordWithDistance.getDistance(), distance);
    }

    @Test
    public void getFieldValues_validFields_noDistanceSet() {
        Word validWord = new Word("Word");
        final String word = "Word";
        final int count = 1;
        final int distance = 0;
        assertEquals(validWord.getWord(), word);
        assertEquals(validWord.getCount(), count);
        assertEquals(validWord.getDistance(), distance);
    }

    @Test
    public void addCount() {
        Word validWord = new Word("Word");
        int counter = 1;
        counter++;
        validWord.addCount();
        assertEquals(validWord.getCount(), counter);
    }

    @Test
    public void minusCount() {
        Word validWord = new Word("Word");
        int counter = 1;
        counter--;
        validWord.minusCount();
        assertEquals(validWord.getCount(), counter);
    }

    @Test
    public void setDistance() {
        Word validWord = new Word("Word");
        int distance = 3;
        validWord.setDistance(3);
        assertEquals(validWord.getDistance(), distance);
    }

    @Test
    public void isSameWord() {
        // same object -> returns true
        assertTrue(CORRECT_HARRY.isSameWord(CORRECT_HARRY));

        // different word -> returns false
        assertFalse(CORRECT_HARRY.isSameWord(CORRECT_AND));

        // same word with typo -> return false
        Word misspeltHarry = new Word(MISSPELT_HARRY);
        assertFalse(CORRECT_HARRY.isSameWord(misspeltHarry));

        // same word with different distance -> returns true
        Word distanceHarry = new Word(HARRY, 3);
        assertTrue(CORRECT_HARRY.isSameWord(distanceHarry));

        // same word with different count -> returns false
        String harry = CORRECT_HARRY.getWord();
        Word countChangedHarry = new Word(harry);
        countChangedHarry.addCount();
        assertTrue(CORRECT_HARRY.isSameWord(countChangedHarry));

        // different word with same count -> returns false
        String chamber = CORRECT_CHAMBER.getWord();
        Word wordChamber = new Word(chamber);
        assertEquals(wordChamber.getCount(), CORRECT_HARRY.getCount());
        assertFalse(wordChamber.isSameWord(CORRECT_HARRY));

        // null -> returns false
        assertFalse(HARRY_POTTER.isSameBook(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(CORRECT_HARRY.equals(CORRECT_HARRY));

        // different word -> returns false
        assertFalse(CORRECT_HARRY.equals(CORRECT_AND));

        // same word with typo -> return false
        Word misspeltHarry = new Word(MISSPELT_HARRY);
        assertFalse(CORRECT_HARRY.equals(misspeltHarry));

        // different distance -> returns false
        Word distanceHarry = new Word(HARRY, 3);
        assertFalse(CORRECT_HARRY.equals(distanceHarry));

        // different count -> returns false
        String harry = CORRECT_HARRY.getWord();
        Word countChangedHarry = new Word(harry);
        countChangedHarry.addCount();
        assertFalse(CORRECT_HARRY.equals(countChangedHarry));

        // different object -> returns false
        assertFalse(CORRECT_HARRY.equals("harry"));

        // null -> returns false
        assertFalse(HARRY_POTTER.equals(null));
    }
}
