package seedu.bagel.testutil;

import static seedu.bagel.logic.commands.CommandTestUtil.VALID_DESC_AMY;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_SET_AMY;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_SET_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.bagel.model.Bagel;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard ALICE = new FlashcardBuilder().withTitle("Alice Pauline")
            .withDescription("123, Jurong West Ave 6, #08-111").withSet("1")
            .withTags("friends").build();
    public static final Flashcard BENSON = new FlashcardBuilder().withTitle("Benson Meier")
            .withDescription("311, Clementi Ave 2, #02-25").withSet("2")
            .withTags("owesMoney", "friends").build();
    public static final Flashcard CARL = new FlashcardBuilder().withTitle("Carl Kurz")
            .withDescription("wall street").withSet("1").build();
    public static final Flashcard DANIEL = new FlashcardBuilder().withTitle("Daniel Meier")
            .withDescription("10th street").withSet("2").withTags("friends").build();
    public static final Flashcard ELLE = new FlashcardBuilder().withTitle("Elle Meyer")
            .withDescription("michegan ave").withSet("1").build();
    public static final Flashcard FIONA = new FlashcardBuilder().withTitle("Fiona Kunz")
            .withDescription("little tokyo").withSet("1").build();
    public static final Flashcard GEORGE = new FlashcardBuilder().withTitle("George Best")
            .withDescription("4th street").withSet("3").build();

    // Manually added
    public static final Flashcard HOON = new FlashcardBuilder().withTitle("Hoon Meier")
            .withDescription("little india").withSet("8").build();
    public static final Flashcard IDA = new FlashcardBuilder().withTitle("Ida Mueller")
            .withDescription("chicago ave").withSet("4").build();

    // Manually added - Flashcard's details found in {@code CommandTestUtil}
    public static final Flashcard AMY = new FlashcardBuilder().withTitle(VALID_TITLE_AMY)
            .withDescription(VALID_DESC_AMY).withSet(VALID_SET_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Flashcard BOB = new FlashcardBuilder().withTitle(VALID_TITLE_BOB)
            .withDescription(VALID_DESC_BOB).withSet(VALID_SET_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashcards() {} // prevents instantiation

    /**
     * Returns an {@code Bagel} with all the typical flashcards.
     */
    public static Bagel getTypicalBagel() {
        Bagel bagel = new Bagel();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            bagel.addFlashcard(flashcard);
        }
        return bagel;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
