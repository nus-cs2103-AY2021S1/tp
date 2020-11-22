package seedu.forgetfulnus.testutil;

import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_DIFFICULTY_TAG_MEDIUM;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GENDER_TAG_M;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GENDER_TAG_NONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_HARD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * A utility class containing a list of {@code FlashCard} objects to be used in tests.
 */
public class TypicalFlashCards {

    public static final FlashCard FLASHCARD_1 = new FlashCardBuilder().withGermanPhrase("Start")
            .withEnglishPhrase("start")
            .withDifficultyTag("EASY")
            .withGenderTag("M")
            .withTags("chapter1")
            .withOrder(1)
            .build();
    public static final FlashCard FLASHCARD_2 = new FlashCardBuilder().withGermanPhrase("auf")
            .withEnglishPhrase("in")
            .withDifficultyTag("EASY")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(2)
            .build();
    public static final FlashCard FLASHCARD_3 = new FlashCardBuilder().withGermanPhrase("Deutsch")
            .withEnglishPhrase("German")
            .withDifficultyTag("EASY")
            .withGenderTag("NEUTRAL")
            .withTags("chapter1")
            .withOrder(3)
            .build();
    public static final FlashCard FLASHCARD_4 = new FlashCardBuilder().withGermanPhrase("hier")
            .withEnglishPhrase("here")
            .withDifficultyTag("EASY")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(4)
            .build();
    public static final FlashCard FLASHCARD_5 = new FlashCardBuilder().withGermanPhrase("lernen")
            .withEnglishPhrase("learn")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(5)
            .build();
    public static final FlashCard FLASHCARD_6 = new FlashCardBuilder().withGermanPhrase("Sie")
            .withEnglishPhrase("you")
            .withDifficultyTag("HARD")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(6)
            .build();
    public static final FlashCard FLASHCARD_7 = new FlashCardBuilder().withGermanPhrase("international")
            .withEnglishPhrase("international")
            .withDifficultyTag("EASY")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(7)
            .build();
    public static final FlashCard FLASHCARD_8 = new FlashCardBuilder().withGermanPhrase("Wort")
            .withEnglishPhrase("word")
            .withDifficultyTag("EASY")
            .withGenderTag("NEUTRAL")
            .withTags("chapter1")
            .withOrder(8)
            .build();
    public static final FlashCard FLASHCARD_9 = new FlashCardBuilder().withGermanPhrase("verstehen")
            .withEnglishPhrase("understand")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(9)
            .build();
    public static final FlashCard FLASHCARD_10 = new FlashCardBuilder().withGermanPhrase("jemand")
            .withEnglishPhrase("somebody")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(10)
            .build();
    public static final FlashCard FLASHCARD_11 = new FlashCardBuilder().withGermanPhrase("begrüßen")
            .withEnglishPhrase("greet")
            .withDifficultyTag("HARD")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(11)
            .build();
    public static final FlashCard FLASHCARD_12 = new FlashCardBuilder().withGermanPhrase("sich")
            .withEnglishPhrase("oneself")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(12)
            .build();
    public static final FlashCard FLASHCARD_13 = new FlashCardBuilder().withGermanPhrase("und")
            .withEnglishPhrase("and")
            .withDifficultyTag("EASY")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(13)
            .build();
    public static final FlashCard FLASHCARD_14 = new FlashCardBuilder().withGermanPhrase("andere")
            .withEnglishPhrase("another")
            .withDifficultyTag("HARD")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(14)
            .build();
    public static final FlashCard FLASHCARD_15 = new FlashCardBuilder().withGermanPhrase("vorstellen")
            .withEnglishPhrase("introduce")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(15)
            .build();
    public static final FlashCard FLASHCARD_16 = new FlashCardBuilder().withGermanPhrase("nach")
            .withEnglishPhrase("about")
            .withDifficultyTag("EASY")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(16)
            .build();
    public static final FlashCard FLASHCARD_17 = new FlashCardBuilder().withGermanPhrase("Name")
            .withEnglishPhrase("name")
            .withDifficultyTag("EASY")
            .withGenderTag("M")
            .withTags("chapter1")
            .withOrder(17)
            .build();
    public static final FlashCard FLASHCARD_18 = new FlashCardBuilder().withGermanPhrase("Herkunft")
            .withEnglishPhrase("origin")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("F")
            .withTags("chapter1")
            .withOrder(18)
            .build();
    public static final FlashCard FLASHCARD_19 = new FlashCardBuilder().withGermanPhrase("fragen")
            .withEnglishPhrase("ask")
            .withDifficultyTag("EASY")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(19)
            .build();
    public static final FlashCard FLASHCARD_20 = new FlashCardBuilder().withGermanPhrase("Vorname")
            .withEnglishPhrase("firstname")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("M")
            .withTags("chapter1")
            .withOrder(20)
            .build();
    public static final FlashCard FLASHCARD_21 = new FlashCardBuilder().withGermanPhrase("Nachname")
            .withEnglishPhrase("lastname")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("M")
            .withTags("chapter1")
            .withOrder(21)
            .build();
    public static final FlashCard FLASHCARD_22 = new FlashCardBuilder().withGermanPhrase("buchstabieren")
            .withEnglishPhrase("spell")
            .withDifficultyTag("HARD")
            .withGenderTag("NONE")
            .withTags("chapter1")
            .withOrder(22)
            .build();

    // Manually added
    public static final FlashCard MORNING = new FlashCardBuilder().withGermanPhrase("Morgen")
            .withEnglishPhrase("Morning")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("M")
            .withOrder(23)
            .withTags("misc")
            .build();
    public static final FlashCard NOON = new FlashCardBuilder().withGermanPhrase("Mittag")
            .withEnglishPhrase("Noon")
            .withDifficultyTag("MEDIUM")
            .withGenderTag("M")
            .withTags("misc")
            .withOrder(24)
            .build();

    // Manually added - FlashCard's details found in {@code CommandTestUtil}
    public static final FlashCard FORGETFULNESS = new FlashCardBuilder()
            .withGermanPhrase(VALID_GERMAN_PHRASE_FORGETFULNESS)
            .withEnglishPhrase(VALID_ENGLISH_PHRASE_FORGETFULNESS)
            .withDifficultyTag(VALID_DIFFICULTY_TAG_MEDIUM)
            .withGenderTag(VALID_GENDER_TAG_NONE)
            .withTags()
            .withOrder(23)
            .build();
    public static final FlashCard TABLE = new FlashCardBuilder()
            .withGermanPhrase(VALID_GERMAN_PHRASE_TABLE)
            .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
            .withDifficultyTag(VALID_DIFFICULTY_TAG_MEDIUM)
            .withGenderTag(VALID_GENDER_TAG_M)
            .withTags(VALID_TAG_CHAPTER_ONE, VALID_TAG_HARD)
            .withOrder(24)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFlashCards() {} // prevents instantiation

    /**
     * Returns a {@code Glossary} with all the typical flashcards.
     */
    public static Glossary getTypicalGlossary() {
        Glossary g = new Glossary();
        for (FlashCard flashCard : getTypicalFlashCards()) {
            g.addFlashCard(flashCard);
        }
        return g;
    }

    /**
     * Returns a sorted {@code Glossary} with all the typical flashcards.
     */
    public static Glossary getTypicalSortedGlossary() {
        Glossary g = new Glossary();
        for (FlashCard flashCard : getTypicalGermanSortedFlashCards()) {
            g.addFlashCard(flashCard);
        }
        return g;
    }

    /**
     * A List of FlashCards sorted by chronological order.
     */
    public static List<FlashCard> getTypicalFlashCards() {
        return new ArrayList<>(Arrays.asList(
                FLASHCARD_1,
                FLASHCARD_2,
                FLASHCARD_3,
                FLASHCARD_4,
                FLASHCARD_5,
                FLASHCARD_6,
                FLASHCARD_7,
                FLASHCARD_8,
                FLASHCARD_9,
                FLASHCARD_10,
                FLASHCARD_11,
                FLASHCARD_12,
                FLASHCARD_13,
                FLASHCARD_14,
                FLASHCARD_15,
                FLASHCARD_16,
                FLASHCARD_17,
                FLASHCARD_18,
                FLASHCARD_19,
                FLASHCARD_20,
                FLASHCARD_21,
                FLASHCARD_22));
    }

    /**
     * A List of FlashCards sorted by alphabetical order of their German Phrases.
     */
    public static List<FlashCard> getTypicalGermanSortedFlashCards() {
        return new ArrayList<>(Arrays.asList(
                FLASHCARD_14,
                FLASHCARD_2,
                FLASHCARD_11,
                FLASHCARD_22,
                FLASHCARD_3,
                FLASHCARD_19,
                FLASHCARD_18,
                FLASHCARD_4,
                FLASHCARD_7,
                FLASHCARD_10,
                FLASHCARD_5,
                FLASHCARD_16,
                FLASHCARD_21,
                FLASHCARD_17,
                FLASHCARD_12,
                FLASHCARD_6,
                FLASHCARD_1,
                FLASHCARD_13,
                FLASHCARD_9,
                FLASHCARD_20,
                FLASHCARD_15,
                FLASHCARD_8));
    }
}
