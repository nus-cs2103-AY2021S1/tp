package quickcache.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import quickcache.model.QuickCache;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Choice;
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
        Choice choice5 = new Choice("Computer Organization module for NUS students");
        Choice choice6 = new Choice("A non-compulsory module for NUS Computer Science students");
        Choice choice7 = new Choice("A module that can be S/Ued");
        Choice choice8 = new Choice("A module that doesn't involve assembly language");
        Choice choice9 = new Choice("Introductory module to computer networks for NUS students");
        Choice choice10 = new Choice("A non-compulsory module for NUS Computer Science students");
        Choice choice11 = new Choice("A module that can be S/Ued");
        Choice choice12 = new Choice("A module that doesn't involve TCP");
        return new Flashcard[]{
                new Flashcard(new OpenEndedQuestion("Are heparins safe in pregnancy?",
                        new Answer("Yes, They dont cross the placenta")
                ), getTagSet("LSM1301")),
                new Flashcard(new OpenEndedQuestion("What is the big downside for LMWH, "
                        + "direct thrombin and anti Xa inhibitors?",
                        new Answer("No reversal agent")
                ), getTagSet("LSM1301")),
                new Flashcard(new OpenEndedQuestion("What is the binary representation of 4 5/8?",
                        new Answer("110.101")
                ), getTagSet("CS2100", "CS")),
                new Flashcard(new OpenEndedQuestion("What is a digital circuit "
                        + "capable of holding a single digit",
                        new Answer("Flip-flop")
                ), getTagSet("CS2100", "CS")),
                new Flashcard(new MultipleChoiceQuestion("Which of the following storage "
                        + "systems is best suited for "
                        + "storing and retrieving long strings of data that are processed "
                        + "in their sequential order?",
                        new Answer("Optical CDs and DVDs"),
                        new Choice("Magnetic disk"),
                        new Choice("Main memory"),
                        new Choice("Optical CDs and DVDs")
                ), getTagSet()),
                new Flashcard(new MultipleChoiceQuestion("What is a means of compressing images "
                        + "by blurring the boundaries between different colors while "
                        + "maintaining all brightness information",
                        new Answer("JPEG"),
                        new Choice("JPEG"),
                        new Choice("LZW"),
                        new Choice("MIDI"),
                        new Choice("GIF")
                ), getTagSet()),
                new Flashcard(new MultipleChoiceQuestion("Which of the following is not one of "
                        + "the three major classes of information systems?",
                        new Answer("Collaboration system"),
                        new Choice("Decision support system"),
                        new Choice("Collaboration system"),
                        new Choice("Management information system"),
                        new Choice("Transaction processing system")
                ), getTagSet())

//            new Flashcard(new MultipleChoiceQuestion("What is CS2103T?",
//                    new Answer("Software Engineering module for NUS students"),
//                choice1,
//                choice2,
//                choice3,
//                choice4
//            ), getTagSet("MCQ", "GoodQuestion"), new Difficulty("Low")),
//            new Flashcard(new MultipleChoiceQuestion("What is CS2100?",
//                    new Answer("Computer Organization module for NUS students"),
//                choice5,
//                choice6,
//                choice7,
//                choice8
//            ), getTagSet("MCQ", "Assembly"), new Difficulty("Low")),
//            new Flashcard(new MultipleChoiceQuestion("What is CS2105?",
//                    new Answer("Introductory module to computer networks for NUS students"),
//                choice9,
//                choice10,
//                choice11,
//                choice12
//            ), getTagSet("MCQ", "TCPforLife"))
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
