package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FlashcardBook;
import seedu.address.model.person.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard BUBBLE_SORT = new FlashcardBuilder().withTitle("Bubble Sort")
            .withDefinition("<DEFINITION PLACEHOLDER> 1")
            .withTags("difficult").withPriority("low").build();
    public static final Flashcard HEAPING = new FlashcardBuilder().withTitle("Heaping")
            .withDefinition("<DEFINITION PLACEHOLDER> 2")
            .withTags("midterm", "final").withPriority("low").build();
    public static final Flashcard BINARY_TREE =
            new FlashcardBuilder().withTitle("Binary Tree").withPriority("medium")
                    .withDefinition("<DEFINITION PLACEHOLDER> 3").build();
    public static final Flashcard QUICK_SORT =
            new FlashcardBuilder().withTitle("Quick Sort").withPriority("medium")
                    .withDefinition("<DEFINITION PLACEHOLDER> 4").withTags("difficult").build();
    public static final Flashcard MERGE_SORT = new FlashcardBuilder().withTitle("Merge Sort").withPriority("")
            .withDefinition("<DEFINITION PLACEHOLDER> 5").build();
    public static final Flashcard INSERTION_SORT =
            new FlashcardBuilder().withTitle("Insertion Sort").withPriority("high")
                    .withDefinition("<DEFINITION PLACEHOLDER> 6").build();
    public static final Flashcard SELECTION_SORT =
            new FlashcardBuilder().withTitle("Selection Sort").withPriority("high")
                    .withDefinition("<DEFINITION PLACEHOLDER> 7").build();

    // Manually added
    public static final Flashcard HOON =
            new FlashcardBuilder().withTitle("Hoon Meier").withDefinition("<DEFINITION PLACEHOLDER> 8")
                .withPriority("").build();
    public static final Flashcard IDA =
            new FlashcardBuilder().withTitle("Ida Mueller").withDefinition("<DEFINITION PLACEHOLDER> 9")
                .withPriority("").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard AMY = new FlashcardBuilder().withTitle(VALID_NAME_BUBBLE_SORT)
            .withDefinition(VALID_DEFINITION_BUBBLE_SORT).withPriority("").withTags(VALID_TAG_FINAL).build();
    public static final Flashcard BOB = new FlashcardBuilder().withTitle(VALID_NAME_HEAPING)
            .withDefinition(VALID_DEFINITION_HEAPING).withPriority("")
            .withTags(VALID_TAG_DIFFICULT, VALID_TAG_FINAL).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical flashcards.
     */
    public static FlashcardBook getTypicalFlashcardBook() {
        FlashcardBook fb = new FlashcardBook();
        for (Flashcard flashcard : getTypicalFlashcard()) {
            fb.addFlashcard(flashcard);
        }
        return fb;
    }

    public static List<Flashcard> getTypicalFlashcard() {
        return new ArrayList<>(Arrays.asList(BUBBLE_SORT, HEAPING, BINARY_TREE, QUICK_SORT,
            MERGE_SORT, INSERTION_SORT, SELECTION_SORT));
    }
}
