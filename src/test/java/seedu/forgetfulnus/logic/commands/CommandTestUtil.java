package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_DIFFICULTY_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GENDER_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhraseContainsKeywordsPredicate;
import seedu.forgetfulnus.testutil.EditFlashCardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_GERMAN_PHRASE_FORGETFULNESS = "Vergesslichkeit";
    public static final String VALID_GERMAN_PHRASE_TABLE = "Tisch";

    public static final String VALID_ENGLISH_PHRASE_FORGETFULNESS = "Forgetfulness";
    public static final String VALID_ENGLISH_PHRASE_TABLE = "Table";

    public static final String VALID_DIFFICULTY_TAG_HARD = "HARD";
    public static final String VALID_DIFFICULTY_TAG_MEDIUM = "MEDIUM";

    public static final String VALID_GENDER_TAG_M = "M";
    public static final String VALID_GENDER_TAG_F = "F";
    public static final String VALID_GENDER_TAG_NONE = "NONE";

    public static final String VALID_TAG_HARD = "hard";
    public static final String VALID_TAG_CHAPTER_ONE = "chapter1";
    public static final String VALID_TAG_CHAPTER_TWO = "chapter2";

    public static final String VALID_ORDER_EIGHT = "8";
    public static final String VALID_ORDER_NINE = "9";

    public static final String GERMAN_DESC_FORGETFULNESS = " "
            + PREFIX_GERMAN_PHRASE + VALID_GERMAN_PHRASE_FORGETFULNESS;
    public static final String GERMAN_DESC_TABLE = " " + PREFIX_GERMAN_PHRASE + VALID_GERMAN_PHRASE_TABLE;

    public static final String ENGLISH_DESC_FORGETFULNESS = " "
            + PREFIX_ENGLISH_PHRASE + VALID_ENGLISH_PHRASE_FORGETFULNESS;
    public static final String ENGLISH_DESC_TABLE = " " + PREFIX_ENGLISH_PHRASE + VALID_ENGLISH_PHRASE_TABLE;

    public static final String DIFFICULTY_TAG_DESC_HARD = " " + PREFIX_DIFFICULTY_TAG + VALID_DIFFICULTY_TAG_HARD;
    public static final String DIFFICULTY_TAG_DESC_MEDIUM = " " + PREFIX_DIFFICULTY_TAG + VALID_DIFFICULTY_TAG_MEDIUM;

    public static final String GENDER_TAG_DESC_F = " " + PREFIX_GENDER_TAG + VALID_GENDER_TAG_F;
    public static final String GENDER_TAG_DESC_M = " " + PREFIX_GENDER_TAG + VALID_GENDER_TAG_M;

    public static final String TAG_DESC_CHAPTER_ONE = " " + PREFIX_TAG + VALID_TAG_CHAPTER_ONE;
    public static final String TAG_DESC_HARD = " " + PREFIX_TAG + VALID_TAG_HARD;

    public static final String INVALID_GERMAN_PHRASE_DESC = " "
            + PREFIX_GERMAN_PHRASE + "James&"; // '&' not allowed in german phrases
    public static final String INVALID_ENGLISH_PHRASE_DESC = " "
            + PREFIX_ENGLISH_PHRASE + "englishphrase@"; // '@' not allowed in english phrases
    public static final String INVALID_DIFFICULTY_TAG_DESC = " "
            + PREFIX_DIFFICULTY_TAG + "easypeasy"; // only certain difficulty tags allowed
    public static final String INVALID_GENDER_TAG_DESC = " "
            + PREFIX_GENDER_TAG + "NONbinary"; // only certain genders tags allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "easy*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFlashCardDescriptor DESC_FORGETFULNESS;
    public static final EditCommand.EditFlashCardDescriptor DESC_TABLE;

    static {
        DESC_FORGETFULNESS = new EditFlashCardDescriptorBuilder().withGermanPhrase(VALID_GERMAN_PHRASE_FORGETFULNESS)
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_FORGETFULNESS)
                .withDifficultyTag(VALID_DIFFICULTY_TAG_MEDIUM)
                .withGenderTag(VALID_GENDER_TAG_NONE)
                .withTags().withOrder(VALID_ORDER_EIGHT).build();

        DESC_TABLE = new EditFlashCardDescriptorBuilder().withGermanPhrase(VALID_GERMAN_PHRASE_TABLE)
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
                .withDifficultyTag(VALID_DIFFICULTY_TAG_MEDIUM)
                .withGenderTag(VALID_GENDER_TAG_M)
                .withTags(VALID_TAG_HARD, VALID_TAG_CHAPTER_ONE).withOrder(VALID_ORDER_NINE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.executeWithChecks(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the glossary, filtered flashcard list and selected flashcard in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Glossary expectedGlossary = new Glossary(actualModel.getGlossary());
        ScoreList expectedScoreList = new ScoreList(actualModel.getScoreList());
        List<FlashCard> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFlashCardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.executeWithChecks(actualModel));
        assertEquals(expectedGlossary, actualModel.getGlossary());
        assertEquals(expectedScoreList, actualModel.getScoreList());
        assertEquals(expectedFilteredList, actualModel.getFilteredFlashCardList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the flashcard at the given {@code targetIndex} in the
     * {@code model}'s glossary.
     */
    public static void showFlashCardsAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashCardList().size());

        FlashCard flashCard = model.getFilteredFlashCardList().get(targetIndex.getZeroBased());
        final String[] splitName = flashCard.getGermanPhrase().toString().split("\\s+");
        model.updateFilteredPhraseList(new GermanPhraseContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFlashCardList().size());
    }

}
