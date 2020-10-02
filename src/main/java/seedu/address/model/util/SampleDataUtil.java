package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Choice;
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
        Choice choice1 = new Choice("Software Engineering module for NUS students");
        Choice choice2 = new Choice("A non-compulsory module for NUS Computer Science students");
        Choice choice3 = new Choice("A module that can be S/Ued");
        Choice choice4 = new Choice("A module that doesn't involve a brown field project");
        Choice choice5 = new Choice("Computer Organization module for NUS students");
        Choice choice6 = new Choice("A non-compulsory module for NUS Computer Science students");
        Choice choice7 = new Choice("A module that can be S/Ued");
        Choice choice8 = new Choice("A module that doesn't involve assembly language");
        Choice choice9 = new Choice("Introductory module to computer networks for NUS students");
        Choice choice10 = new Choice("A non-compulsory module for NUS Computer Science students");
        Choice choice11 = new Choice("A module that can be S/Ued");
        Choice choice12 = new Choice("A module that doesn't involve TCP");
        return new Flashcard[] {
            new Flashcard(new MultipleChoiceQuestion("What is CS2103T?",
                    choice1,
                    choice2,
                    choice3,
                    choice4
                    ),
                    new Answer("Software Engineering module for NUS students"),
                    getTagSet("MCQ", "Good Question")),
            new Flashcard(new MultipleChoiceQuestion("What is CS2100?",
                    choice5,
                        choice6,
                        choice7,
                        choice8
                        ),
                        new Answer("Computer Organization module for NUS students"),
                        getTagSet("MCQ", "Assembly")),
            new Flashcard(new MultipleChoiceQuestion("What is CS2105?",
                        choice9,
                        choice10,
                        choice11,
                        choice12
                        ),
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
