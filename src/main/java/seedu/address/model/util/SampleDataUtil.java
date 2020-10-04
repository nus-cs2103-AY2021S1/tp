package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FlashcardDeck;
import seedu.address.model.ReadOnlyFlashcardDeck;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Category;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Question;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
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

    public static ReadOnlyFlashcardDeck getSampleAddressBook() {
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
