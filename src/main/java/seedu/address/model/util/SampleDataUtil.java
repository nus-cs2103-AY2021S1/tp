package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Flashcard;
import seedu.address.flashcard.MultipleChoiceQuestion;
import seedu.address.flashcard.Tag;
import seedu.address.model.QuickCache;
import seedu.address.model.ReadOnlyQuickCache;

/**
 * Contains utility methods for populating {@code QuickCache} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[] {
            new Flashcard(new MultipleChoiceQuestion("What is CS2103T?",
                    "Software Engineering module for NUS students",
                    "A non-compulsory module for NUS Computer Science students",
                    "A module that can be S/Ued",
                    "A module that doesn't involve a brown field project"),
                    new Answer("Software Engineering module for NUS students"),
                    getTagSet("MCQ", "Good Question")),
            new Flashcard(new MultipleChoiceQuestion("What is CS2100?",
                    "Computer Organization module for NUS students",
                        "A non-compulsory module for NUS Computer Science students",
                        "A module that can be S/Ued",
                        "A module that doesn't involve assembly language"),
                        new Answer("Computer Organization module for NUS students"),
                        getTagSet("MCQ", "Assembly")),
            new Flashcard(new MultipleChoiceQuestion("What is CS2105?",
                        "Introductory module to computer networks for NUS students",
                        "A non-compulsory module for NUS Computer Science students",
                        "A module that can be S/Ued",
                        "A module that doesn't involve TCP"),
                        new Answer("Introductory module to computer networks for NUS students"),
                        getTagSet("MCQ", "TCPforLife"))
        };
    }

    public static ReadOnlyQuickCache getSampleQuickCache() {
        QuickCache sampleQc = new QuickCache();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleQc.addFlashcard(sampleFlashcard);
        }
        return sampleQc;
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
