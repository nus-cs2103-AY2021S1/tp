package seedu.bookmark.model.wordstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalWords.CHAMBER;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_HARRY;
import static seedu.bookmark.testutil.TypicalWords.HARRY;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.wordstore.exceptions.WordNotFoundException;
import seedu.bookmark.testutil.TypicalWords;

public class WordStoreTest {

    private WordStore wordStore = new WordStore();

    //------------------- contains() ------------------------------//

    @Test
    public void contains_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wordStore.contains(null));
    }

    @Test
    public void contains_wordNotInList_returnsFalse() {
        assertFalse(wordStore.contains(HARRY));
    }

    @Test
    public void contains_wordInList_returnsTrue() {
        wordStore.wordAdder(HARRY);
        String harry = CORRECT_HARRY.getWord();
        assertTrue(wordStore.contains(harry));
    }

    //--------------------- wordAdder() ---------------------------//

    @Test
    public void add_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wordStore.wordAdder(null));
    }

    @Test
    public void add_newWord_countOne() {
        wordStore.wordAdder(HARRY);
        Word harry = wordStore.getWord(HARRY);
        assertEquals(1, harry.getCount());
    }

    @Test
    public void add_existingWord_increaseCount() {
        wordStore.wordAdder(HARRY);
        wordStore.wordAdder(HARRY);
        Word harry = wordStore.getWord(HARRY);
        assertEquals(2, harry.getCount());

    }

    //-------------------- wordDeleter() -------------------------//

    @Test
    public void delete_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wordStore.wordDeleter(null));
    }

    @Test
    public void delete_bookDoesNotExist() {
        wordStore.wordAdder(HARRY);
        assertThrows(WordNotFoundException.class, () -> wordStore.wordDeleter(CHAMBER));
    }

    @Test
    public void delete_existingWord_countZero() {
        wordStore.wordAdder(HARRY);
        wordStore.wordDeleter(HARRY);
        assertFalse(wordStore.contains(HARRY));
    }
    @Test
    public void delete_existingBook_countNonZero() {
        wordStore.wordAdder(HARRY);
        wordStore.wordAdder(HARRY); //increase count to 2
        wordStore.wordDeleter(HARRY);
        Word harry = wordStore.getWord(HARRY);
        assertEquals(1, harry.getCount());
    }

    //--------------------- addWords() ---------------------------//

    @Test
    public void addWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        wordStore.addWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(wordStore.contains(word));
        }
    }

    @Test
    public void add_existingWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        wordStore.addWords(listOfWords);
        wordStore.addWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(wordStore.contains(word));
            Word targetWord = wordStore.getWord(word);
            assertEquals(2, targetWord.getCount());
        }
    }

    //---------------------- deleteWords() -----------------------//

    @Test
    public void deleteWords_countOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        wordStore.addWords(listOfWords);
        wordStore.deleteWords(listOfWords);
        for (String word : listOfWords) {
            assertFalse(wordStore.contains(word));
        }
    }

    @Test
    public void deleteWords_countBiggerThanOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        wordStore.addWords(listOfWords);
        wordStore.addWords(listOfWords);
        wordStore.deleteWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(wordStore.contains(word));
            Word targetWord = wordStore.getWord(word);
            assertEquals(1, targetWord.getCount());
        }
    }

    //---------------------- getWordStore() ------------------------//

    @Test
    public void getWordStore() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        List<Word> listOfWordObjs = TypicalWords.getTypicalWords();
        wordStore.addWords(listOfWords);
        for (Word word : listOfWordObjs) {
            assertEquals(word, wordStore.getWord(word.getWord()));
        }
    }
}

