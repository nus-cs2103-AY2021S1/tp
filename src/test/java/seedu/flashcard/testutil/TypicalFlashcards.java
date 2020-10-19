package seedu.flashcard.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard FLASHCARD_1 = new FlashcardBuilder().withQuestion("What does SDLC stand for?")
            .withAnswer("Software development life cycle").withCategory("SDLC")
            .withRating("2")
            .withAnswer("Software development life cycle").withCategory("SDLC").withFavouriteStatus(false)
            .build();

    public static final Flashcard FLASHCARD_2 = new FlashcardBuilder()
            .withQuestion("What is a revision control software?")
            .withAnswer("It is the software tool that automate the process of Revision Control")
            .withCategory("Revision History").withFavouriteStatus(true)
            .build();

    public static final Flashcard FLASHCARD_3 = new FlashcardBuilder()
            .withQuestion("It is recommended that assertions to be used liberally in the code. True or False?")
            .withAnswer("True").build();

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical flashcards.
     */
    public static FlashcardDeck getTypicalFlashcardDeck() {
        FlashcardDeck ab = new FlashcardDeck();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            ab.addFlashcard(flashcard);
        }
        return ab;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(FLASHCARD_1, FLASHCARD_2, FLASHCARD_3));
    }
}
