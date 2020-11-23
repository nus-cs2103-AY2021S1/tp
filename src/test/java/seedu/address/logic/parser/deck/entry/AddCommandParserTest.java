package seedu.address.logic.parser.deck.entry;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRANSLATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TRANSLATION_DESC_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.TRANSLATION_DESC_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORD_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.WORD_DESC_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.WORD_DESC_SPANISH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.entry.TypicalEntries.JAPANESE;
import static seedu.address.testutil.entry.TypicalEntries.SPANISH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.entry.AddCommand;
import seedu.address.logic.parser.entry.AddCommandParser;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;
import seedu.address.testutil.entry.EntryBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Entry expectedEntry = new EntryBuilder(SPANISH).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + WORD_DESC_SPANISH + TRANSLATION_DESC_SPANISH,
                new AddCommand(expectedEntry));
        // multiple words - last word accepted
        assertParseSuccess(parser, WORD_DESC_JAPANESE + WORD_DESC_SPANISH + TRANSLATION_DESC_SPANISH,
                new AddCommand(expectedEntry));

        // multiple translations - last translation accepted
        assertParseSuccess(parser, WORD_DESC_SPANISH + TRANSLATION_DESC_JAPANESE + TRANSLATION_DESC_SPANISH,
                new AddCommand(expectedEntry));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Entry expectedEntry = new EntryBuilder(JAPANESE).build();
        assertParseSuccess(parser, WORD_DESC_JAPANESE + TRANSLATION_DESC_JAPANESE,
                new AddCommand(expectedEntry));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing word prefix
        assertParseFailure(parser, VALID_WORD_SPANISH + TRANSLATION_DESC_SPANISH, expectedMessage);

        // missing translation prefix
        assertParseFailure(parser, WORD_DESC_SPANISH + VALID_TRANSLATION_SPANISH, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_WORD_SPANISH + VALID_TRANSLATION_SPANISH,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid word
        assertParseFailure(parser, INVALID_WORD_DESC + TRANSLATION_DESC_SPANISH, Word.MESSAGE_CONSTRAINTS);
        // invalid translation
        assertParseFailure(parser, WORD_DESC_SPANISH + INVALID_TRANSLATION_DESC, Translation.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_WORD_DESC + TRANSLATION_DESC_SPANISH, Word.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + WORD_DESC_SPANISH + TRANSLATION_DESC_SPANISH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
