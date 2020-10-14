package seedu.flashcard.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FlashcardDeck} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[] {
            new Flashcard(new Question("What does SDLC stand for?"), new Answer("Software development life cycle"),
                new Category("SDLC")),
            new Flashcard(new Question("What is a revision control software?"),
                new Answer("It is the software tool that automate the process of Revision Control"),
                new Category("Revision history"))
        };
    }

    public static ReadOnlyFlashcardDeck getSampleFlashcardDeck() {
        FlashcardDeck sampleAb = new FlashcardDeck();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
