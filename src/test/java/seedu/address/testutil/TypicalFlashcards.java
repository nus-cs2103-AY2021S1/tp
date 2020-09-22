package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard ONE = new FlashcardBuilder().withQuestion("What does SDLC stand for?")
            .withAnswer("Software development life cycle").withCategory("SDLC")
            .build();

    public static final Flashcard TWO = new FlashcardBuilder().withQuestion("What is a revision control software?")
            .withAnswer("It is the software tool that automate the process of Revision Control")
            .withCategory("Revision history")
            .build();

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical flashcards.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            ab.addFlashcard(flashcard);
        }
        return ab;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(ONE, TWO));
    }
}
