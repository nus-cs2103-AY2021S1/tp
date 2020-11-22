package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_MULTIPLE_PREFIX;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.DIFFICULTY_TAG_DESC_HARD;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.DIFFICULTY_TAG_DESC_MEDIUM;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.ENGLISH_DESC_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.ENGLISH_DESC_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.GENDER_TAG_DESC_F;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.GENDER_TAG_DESC_M;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.GERMAN_DESC_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.GERMAN_DESC_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_DIFFICULTY_TAG_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_ENGLISH_PHRASE_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_GENDER_TAG_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_GERMAN_PHRASE_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.TAG_DESC_CHAPTER_ONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.TAG_DESC_HARD;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.forgetfulnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.forgetfulnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.FORGETFULNESS;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.TABLE;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.AddCommand;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.Tag;
import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        FlashCard expectedFlashCard = new FlashCardBuilder(TABLE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_HARD, new AddCommand(expectedFlashCard));

        // multiple tags - all accepted
        FlashCard expectedFlashCardMultipleTags = new FlashCardBuilder(TABLE)
                .withTags(VALID_TAG_CHAPTER_ONE, VALID_TAG_HARD)
                .build();
        assertParseSuccess(parser, GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + TAG_DESC_HARD + TAG_DESC_CHAPTER_ONE , new AddCommand(expectedFlashCard));
    }

    @Test
    public void parse_multipleFieldsPresent_throwsParseException() {
        // multiple German phrases - ParseException thrown
        assertParseFailure(parser, GERMAN_DESC_FORGETFULNESS + GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_HARD, MESSAGE_INVALID_MULTIPLE_PREFIX);

        // multiple English phrases - ParseException thrown
        assertParseFailure(parser, GERMAN_DESC_TABLE + ENGLISH_DESC_FORGETFULNESS + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_HARD, MESSAGE_INVALID_MULTIPLE_PREFIX);


        // multiple difficulty tags - ParseException thrown
        assertParseFailure(parser, GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_HARD + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_HARD, MESSAGE_INVALID_MULTIPLE_PREFIX);

        // multiple gender tags - ParseException thrown
        assertParseFailure(parser, GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_F + GENDER_TAG_DESC_M
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_HARD, MESSAGE_INVALID_MULTIPLE_PREFIX);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        // default predefined tags. For difficulty, Medium tag. For gender, None tag.
        FlashCard expectedFlashCard = new FlashCardBuilder(FORGETFULNESS).withTags().build();
        assertParseSuccess(parser, GERMAN_DESC_FORGETFULNESS + ENGLISH_DESC_FORGETFULNESS,
                new AddCommand(expectedFlashCard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing German phrase prefix
        assertParseFailure(parser, VALID_GERMAN_PHRASE_TABLE + ENGLISH_DESC_TABLE,
                expectedMessage);

        // missing English phrase prefix
        assertParseFailure(parser, GERMAN_DESC_TABLE + VALID_ENGLISH_PHRASE_TABLE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_GERMAN_PHRASE_TABLE + VALID_ENGLISH_PHRASE_TABLE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid german phrase
        assertParseFailure(parser, INVALID_GERMAN_PHRASE_DESC + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + TAG_DESC_HARD + TAG_DESC_CHAPTER_ONE, GermanPhrase.MESSAGE_CONSTRAINTS);

        // invalid english phrase
        assertParseFailure(parser, GERMAN_DESC_TABLE + INVALID_ENGLISH_PHRASE_DESC
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + TAG_DESC_HARD + TAG_DESC_CHAPTER_ONE, EnglishPhrase.MESSAGE_CONSTRAINTS);

        // invalid difficulty tag
        assertParseFailure(parser, GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + INVALID_DIFFICULTY_TAG_DESC + GENDER_TAG_DESC_M
                + TAG_DESC_HARD + TAG_DESC_CHAPTER_ONE, DifficultyTag.MESSAGE_CONSTRAINTS);

        // invalid gender tag
        assertParseFailure(parser, GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + INVALID_GENDER_TAG_DESC
                + TAG_DESC_HARD + TAG_DESC_CHAPTER_ONE, GenderTag.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                + DIFFICULTY_TAG_DESC_MEDIUM + GENDER_TAG_DESC_M
                + INVALID_TAG_DESC + VALID_TAG_CHAPTER_ONE, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_GERMAN_PHRASE_DESC + INVALID_ENGLISH_PHRASE_DESC,
                GermanPhrase.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GERMAN_DESC_TABLE + ENGLISH_DESC_TABLE
                        + DIFFICULTY_TAG_DESC_MEDIUM + INVALID_GENDER_TAG_DESC
                        + TAG_DESC_HARD + TAG_DESC_CHAPTER_ONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
