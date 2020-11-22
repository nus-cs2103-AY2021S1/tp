package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_MULTIPLE_PREFIX;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.ENGLISH_DESC_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.ENGLISH_DESC_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.GERMAN_DESC_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_ENGLISH_PHRASE_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_GERMAN_PHRASE_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.TAG_DESC_CHAPTER_ONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.TAG_DESC_HARD;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.forgetfulnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.forgetfulnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.forgetfulnus.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.forgetfulnus.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static seedu.forgetfulnus.testutil.TypicalIndexes.INDEX_THIRD_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.EditCommand;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.Tag;
import seedu.forgetfulnus.testutil.EditFlashCardDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_GERMAN_PHRASE_FORGETFULNESS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + GERMAN_DESC_FORGETFULNESS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + GERMAN_DESC_FORGETFULNESS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_GERMAN_PHRASE_DESC,
                GermanPhrase.MESSAGE_CONSTRAINTS); // invalid German phrase
        assertParseFailure(parser, "1" + INVALID_ENGLISH_PHRASE_DESC,
                EnglishPhrase.MESSAGE_CONSTRAINTS); // invalid English phrase
        assertParseFailure(parser, "1" + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code FlashCard} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_CHAPTER_ONE
                + TAG_DESC_HARD + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_CHAPTER_ONE
                + TAG_EMPTY + TAG_DESC_HARD, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_HARD, Tag.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ENGLISH_DESC_TABLE + TAG_DESC_HARD
                + GERMAN_DESC_FORGETFULNESS + TAG_DESC_CHAPTER_ONE;

        EditCommand.EditFlashCardDescriptor descriptor = new EditFlashCardDescriptorBuilder()
                .withGermanPhrase(VALID_GERMAN_PHRASE_FORGETFULNESS)
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
                .withTags(VALID_TAG_CHAPTER_ONE, VALID_TAG_HARD).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ENGLISH_DESC_TABLE;

        EditCommand.EditFlashCardDescriptor descriptor = new EditFlashCardDescriptorBuilder()
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + GERMAN_DESC_FORGETFULNESS;
        EditCommand.EditFlashCardDescriptor descriptor = new EditFlashCardDescriptorBuilder()
                .withGermanPhrase(VALID_GERMAN_PHRASE_FORGETFULNESS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + ENGLISH_DESC_FORGETFULNESS;
        descriptor = new EditFlashCardDescriptorBuilder().withEnglishPhrase(VALID_ENGLISH_PHRASE_FORGETFULNESS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CHAPTER_ONE;
        descriptor = new EditFlashCardDescriptorBuilder().withTags(VALID_TAG_CHAPTER_ONE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_throwsParseException() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ENGLISH_DESC_FORGETFULNESS
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_CHAPTER_ONE
                + ENGLISH_DESC_TABLE;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_MULTIPLE_PREFIX);
    }

    @Test
    public void parse_multipleRepeatedTagFields_acceptsAll() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ENGLISH_DESC_TABLE
                + TAG_DESC_CHAPTER_ONE + TAG_DESC_CHAPTER_ONE
                + TAG_DESC_HARD;

        EditCommand.EditFlashCardDescriptor descriptor = new EditFlashCardDescriptorBuilder()
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
                .withTags(VALID_TAG_HARD, VALID_TAG_CHAPTER_ONE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_FLASHCARD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditFlashCardDescriptor descriptor = new EditFlashCardDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
