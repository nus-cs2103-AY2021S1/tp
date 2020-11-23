package seedu.address.logic.parser.deck.entry;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRANSLATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TRANSLATION_DESC_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.TRANSLATION_DESC_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORD_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.WORD_DESC_JAPANESE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.entry.EditCommand;
import seedu.address.logic.commands.entry.EditCommand.EditEntryDescriptor;
import seedu.address.logic.parser.entry.EditCommandParser;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;
import seedu.address.testutil.entry.EditEntryDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_WORD_JAPANESE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + WORD_DESC_JAPANESE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + WORD_DESC_JAPANESE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_WORD_DESC, Word.MESSAGE_CONSTRAINTS); // invalid word
        assertParseFailure(parser, "1" + INVALID_TRANSLATION_DESC,
                Translation.MESSAGE_CONSTRAINTS); // invalid translation

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_WORD_DESC + INVALID_TRANSLATION_DESC,
                Word.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + TRANSLATION_DESC_JAPANESE + WORD_DESC_JAPANESE;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withWord(VALID_WORD_JAPANESE)
                .withTranslation(VALID_TRANSLATION_JAPANESE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TRANSLATION_DESC_JAPANESE;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withTranslation(VALID_TRANSLATION_JAPANESE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // word
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + WORD_DESC_JAPANESE;
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withWord(VALID_WORD_JAPANESE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // translation
        userInput = targetIndex.getOneBased() + TRANSLATION_DESC_JAPANESE;
        descriptor = new EditEntryDescriptorBuilder().withTranslation(VALID_TRANSLATION_JAPANESE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TRANSLATION_DESC_JAPANESE
                + TRANSLATION_DESC_JAPANESE + TRANSLATION_DESC_SPANISH;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withTranslation(VALID_TRANSLATION_SPANISH).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
