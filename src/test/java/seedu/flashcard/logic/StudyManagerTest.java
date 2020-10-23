package seedu.flashcard.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_2;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.Flashcard;


public class StudyManagerTest {

    private Model model;
    private StudyManager studyManager;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());
        studyManager = new StudyManager(model.getFilteredFlashcardList());
    }

    @Test
    public void execute_getFirstFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_1;
        assertEquals(expectedFlashcard, studyManager.getCurrentFlashcard());
    }

    @Test
    public void execute_reviewNextFlashcardFromFirstFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_2;
        assertEquals(expectedFlashcard, studyManager.getNextFlashcard());
    }

    @Test
    public void execute_reviewPreviousFlashcardFromFirstFlashcard_success() {
        assertEquals(null, studyManager.getPrevFlashcard());
    }

    @Test
    public void execute_reviewPreviousFlashcardFromSecondFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_1;
        // Set currentIndex of reviewManager to 1.
        studyManager.getNextFlashcard();
        // Return flashcard at index 0.
        assertEquals(expectedFlashcard, studyManager.getPrevFlashcard());
    }

}
