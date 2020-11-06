package seedu.flashcard.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.ReadOnlyFlashcardDeck;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Note;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FlashcardDeck} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[]{
            new Flashcard(new Question("What does SDLC stand for?"), new Answer("Software development life cycle"),
                    new Category("SDLC"), new Note(""), new Rating(""),
                    getTagSet(), new Diagram("")),

            new Flashcard(new Question("What is a revision control software?"),
                    new Answer("It is the software tool that automates the process of Revision Control"),
                    new Category("Revision history"), new Note(""), new Rating("2"),
                    getTagSet("preloaded"), new Diagram("")),

            new Flashcard(new Question("Non-functional requirements specify the constraints under "
                    + "which system is developed and operated. T/F?"),
                    new Answer("T"),
                    new Category("Non functional requirements"), new Note(""), new Rating("2"),
                    getTagSet(), new Diagram("")),

            new Flashcard(new Question("Explain software architecture."),
                    new Answer("The software architecture shows the overall organization of the system and can be "
                            + "viewed as a very high-level design. It usually consists of a set of interacting "
                            + "components that fit together to achieve the required functionality. It should be a "
                            + "simple and technically viable structure that is well-understood and agreed-upon "
                            + "by everyone in the development team, "
                            + "and it forms the basis for the implementation."),
                    new Category("Architecture Diagrams"), new Note(""), new Rating("1"),
                    getTagSet("definitions"), new Diagram("")),

            new Flashcard(new Question("Explain use cases."),
                    new Answer("A use case describes an interaction between the user and the system for a specific "
                            + "functionality of the system."),
                    new Category("Use Cases"), new Note(""), new Rating("1"),
                    getTagSet("definitions"), new Diagram("")),

            new Flashcard(new Question("Use cases do not capture the functional requirements of a system. T/F?"),
                    new Answer("F. Use cases capture the functional requirements of a system."),
                    new Category("Use Cases"), new Note(""), new Rating("1"),
                    getTagSet(), new Diagram("")),

            new Flashcard(new Question("Can a use case include another use case? "
                    + "If so, how is it represented?"),
                    new Answer("A use case can include another use case. "
                            + "Underlined text is commonly used to show an inclusion of a use case."),
                    new Category("Use Cases"), new Note(""), new Rating("2"),
                    getTagSet(), new Diagram("")),

            new Flashcard(new Question("List down some factors that determines the suitability of "
                    + "defensive programming"),
                    new Answer("1. How critical is the system? "
                            + "2. Will the code be used by programmers other than the author? "
                            + "3. The level of programming language support for defensive programming. "
                            + "4. The overhead of being defensive."),
                    new Category("Defensive programming"), new Note(""), new Rating("3"),
                    getTagSet(), new Diagram("")),

            new Flashcard(new Question("A set of 20 test cases that finds 8 defects is more effective than another "
                    + "set of 40 test cases that finds the same 8 defects.T/F?"),
                    new Answer("F. It is more efficient rather than effective."),
                    new Category("Test Cases"), new Note(""), new Rating("3"),
                    getTagSet(), new Diagram("")),

            new Flashcard(new Question("Define Boundary Value Analysis (BVA)."),
                    new Answer("Boundary Value Analysis (BVA) is test case design heuristic that is based on the "
                            + "observation that bugs often result from incorrect handling of boundaries of equivalence "
                            + "partitions. This is not surprising, as the end points of the boundary are often used in "
                            + "branching instructions etc. where the programmer can make mistakes. BVA "
                            + "suggests that when picking test inputs from an equivalence partition, values "
                            + "near boundaries (i.e. boundary values) "
                            + "are more likely to find bugs."),
                    new Category("Test Cases"), new Note(""), new Rating("2"),
                    getTagSet("definitions"), new Diagram("")),

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
