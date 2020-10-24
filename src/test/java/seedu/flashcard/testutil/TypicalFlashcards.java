package seedu.flashcard.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Statistics;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard FLASHCARD_1 = new FlashcardBuilder().withQuestion("What does SDLC stand for?")
            .withAnswer("Software development life cycle").withCategory("SDLC")
            .withRating("2").withTags("revise").withDiagram("").withFavouriteStatus(false)
            .build();

    public static final Flashcard FLASHCARD_2 = new FlashcardBuilder()
            .withQuestion("What is a revision control software?")
            .withAnswer("It is the software tool that automate the process of Revision Control")
            .withCategory("Revision History").withDiagram("")
            .withFavouriteStatus(true)
            .build();

    public static final Flashcard FLASHCARD_3 = new FlashcardBuilder()
            .withQuestion("It is recommended that assertions to be used liberally in the code. True or False?")
            .withAnswer("True").withFavouriteStatus(true).build();

    public static final Flashcard FLASHCARD_4 = new FlashcardBuilder()
            .withQuestion("Can the singleton pattern can reduce testability?")
            .withAnswer("Yes").withCategory("Singleton")
            .withStatistics(new Statistics(3, 2))
            .build();

    public static final Flashcard FLASHCARD_5 = new FlashcardBuilder()
            .withQuestion("The Command pattern uses polymorphism. True or False?")
            .withAnswer("True")
            .withCategory("Command Pattern")
            .withStatistics(new Statistics(1, 0))
            .build();

    public static final Flashcard FLASHCARD_6 = new FlashcardBuilder()
            .withQuestion("IMore test cases is always better. True or False?")
            .withStatistics(new Statistics(5, 2))
            .withAnswer("True").build();



    private TypicalFlashcards() {
    } // prevents instantiation

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

    /**
     * Returns an {@code AddressBook} with flashcards that have been reviewed before.
     */
    public static FlashcardDeck getReviewedFlashcardDeck() {
        FlashcardDeck ab = new FlashcardDeck();
        for (Flashcard flashcard : getReviewedFlashcards()) {
            ab.addFlashcard(flashcard);
        }
        return ab;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(FLASHCARD_1, FLASHCARD_2, FLASHCARD_3));
    }

    public static List<Flashcard> getReviewedFlashcards() {
        return new ArrayList<>(Arrays.asList(FLASHCARD_4, FLASHCARD_5, FLASHCARD_6));
    }
}
