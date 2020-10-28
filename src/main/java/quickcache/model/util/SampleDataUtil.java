package quickcache.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import quickcache.model.QuickCache;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.Difficulty;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.model.flashcard.OpenEndedQuestion;
import quickcache.model.flashcard.Tag;

/**
 * Contains utility methods for populating {@code QuickCache} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        Choice choice1 = new Choice("Software Engineering module for NUS students");
        Choice choice2 = new Choice("A non-compulsory module for NUS Computer Science students");
        Choice choice3 = new Choice("A module that can be S/Ued");
        Choice choice4 = new Choice("A module that doesn't involve a brown field project");
        Choice choice5 = new Choice("bubble tea");
        Choice choice6 = new Choice("oxygen");
        Choice choice7 = new Choice("iphone");
        Choice choice8 = new Choice("pepsi");
        return new Flashcard[]{
            new Flashcard(new OpenEndedQuestion("Complete the following sentence. "
                    + "National University of ______ . (ans: Singapore)",
                    new Answer("Singapore")
            ), getTagSet("OEQ", "General"), new Difficulty("low")),
            new Flashcard(new MultipleChoiceQuestion("All mammals need ______ to survive. (option: 2)",
                    new Answer("oxygen"),
                    choice5,
                    choice6,
                    choice7,
                    choice8
            ), getTagSet("MCQ", "Biology"), new Difficulty("Medium")),
            new Flashcard(new MultipleChoiceQuestion("What is CS2103? (option: 1)",
                    new Answer("Software Engineering module for NUS students"),
                    choice1,
                    choice2,
                    choice3,
                    choice4
            ), getTagSet("MCQ", "CS2103"), new Difficulty("High"))
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
