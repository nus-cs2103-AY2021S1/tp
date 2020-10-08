package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_2;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;


public class ReviewManagerTest {

    private Model model;
    private ReviewManager reviewManager;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());
        reviewManager = new ReviewManager(model.getFilteredFlashcardList());
    }

    @Test
    public void execute_getFirstFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_1;
        assertEquals(expectedFlashcard, reviewManager.getCurrentFlashcard());
    }

    @Test
    public void execute_reviewNextFlashcardFromFirstFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_2;
        assertEquals(expectedFlashcard, reviewManager.getNextFlashcard());
    }

    @Test
    public void execute_reviewPreviousFlashcardFromFirstFlashcard_success() {
        assertEquals(null, reviewManager.getPrevFlashcard());
    }

    @Test
    public void execute_reviewPreviousFlashcardFromSecondFlashcard_success() {
        Flashcard expectedFlashcard = FLASHCARD_1;
        // Set currentIndex of reviewManager to 1.
        reviewManager.getNextFlashcard();
        // Return flashcard at index 0.
        assertEquals(expectedFlashcard, reviewManager.getPrevFlashcard());
    }

}
