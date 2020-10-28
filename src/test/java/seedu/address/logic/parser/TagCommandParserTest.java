package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.FILE_ADDRESS_DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.FILE_ADDRESS_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FILE_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TypicalTags;

public class TagCommandParserTest {
    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        String commandString = FILE_ADDRESS_DESC_CS2101 + TAG_DESC_CS2101 + VALID_LABEL_DESC;
        Tag toTag = TypicalTags.CS2101;
        TagCommand expectedTagCommand = new TagCommand(toTag);
        assertParseSuccess(new TagCommandParser(), commandString, expectedTagCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

        // missing tag name prefix
        assertParseFailure(parser, VALID_TAG_NAME_CS2103 + FILE_ADDRESS_DESC_CS2103,
                expectedMessage);

        // missing file address prefix
        assertParseFailure(parser, TAG_DESC_CS2103 + VALID_FILE_ADDRESS_CS2103,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TAG_NAME_CS2103 + VALID_FILE_ADDRESS_CS2103,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TAG_DESC + FILE_ADDRESS_DESC_CS2103,
                TagName.MESSAGE_CONSTRAINTS);


        // invalid fileaddress
        assertParseFailure(parser, TAG_DESC_CS2103 + INVALID_FILE_ADDRESS_DESC,
                FileAddress.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TAG_DESC + INVALID_FILE_ADDRESS_DESC,
                TagName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TAG_DESC_CS2103
                + FILE_ADDRESS_DESC_CS2103,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
    }
}
