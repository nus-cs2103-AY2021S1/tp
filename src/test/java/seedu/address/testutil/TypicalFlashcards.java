package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.flashcard.Flashcard;
import seedu.address.model.QuickCache;

public class TypicalFlashcards {

    public static final Flashcard RANDOM1 = new FlashcardBuilder().withQuestion("Are heparins safe in pregnancy?")
            .withAnswer("Yes, They dont cross the placenta").build();
    public static final Flashcard RANDOM2 = new FlashcardBuilder()
            .withQuestion("What is the big downside for LMWH, direct thrombin and anti Xa inhibitors?")
            .withAnswer("No reversal agent").build();
    public static final Flashcard RANDOM3 = new FlashcardBuilder()
            .withQuestion("What is the binary representation of 4 5/8?")
            .withAnswer("110.101").build();
    public static final Flashcard RANDOM4 = new FlashcardBuilder()
            .withQuestion("What is a digital circuit capable of holding a single digit")
            .withAnswer("Flip-flop").build();

    public static final Flashcard RANDOM5 = new FlashcardBuilder()
            .withMultipleChoiceQuestion("Which of the following storage systems is best suited for "
                            + "storing and retrieving long strings of data that are processed "
                            + "in their sequential order?",
                    new String[]{"Magnetic disk", "Main memory", "Optical CDs and DVDs" })
            .withAnswer("Optical CDs and DVDs").build();
    public static final Flashcard RANDOM6 = new FlashcardBuilder()
            .withMultipleChoiceQuestion("What is a means of compressing images "
                            + "by blurring the boundaries between different colors while "
                            + "maintaining all brightness information",
                    new String[]{"JPEG", "LZW", "MIDI", "GIF" })
            .withAnswer("JPEG").build();
    public static final Flashcard RANDOM7 = new FlashcardBuilder()
            .withMultipleChoiceQuestion("Which of the following is not one of "
                            + "the three major classes of information systems?",
                    new String[]{"Decision support system",
                                    "Collaboration system",
                                    "Management information system",
                                    "Transaction processing system"})
            .build();

    /**
     * Returns a {@code Quickcache} with all the typical flashcards.
     */
    public static QuickCache getTypicalQuickCache() {
        QuickCache qc = new QuickCache();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            qc.addFlashcard(flashcard);
        }
        return qc;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(RANDOM1, RANDOM2, RANDOM3, RANDOM4, RANDOM5, RANDOM6, RANDOM7));
    }
}
