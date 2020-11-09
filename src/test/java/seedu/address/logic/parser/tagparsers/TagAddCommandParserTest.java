package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_NAME_DESC_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_NAME_DESC_HANDBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_COLLEAGUE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTags.COLLEAGUE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tagcommands.TagAddCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TagBuilder;

public class TagAddCommandParserTest {
    private TagAddCommandParser parser = new TagAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tag expectedTag = new TagBuilder(COLLEAGUE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_NAME_DESC_COLLEAGUE, new TagAddCommand(expectedTag));

        // multiple tagnames - last tagname accepted
        assertParseSuccess(parser, TAG_NAME_DESC_HANDBALL + TAG_NAME_DESC_COLLEAGUE, new TagAddCommand(expectedTag));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagAddCommand.MESSAGE_USAGE);

        // missing tag prefix == all prefixes missing
        assertParseFailure(parser, VALID_TAG_NAME_COLLEAGUE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tagname
        assertParseFailure(parser, INVALID_TAG_NAME_DESC, TagName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TAG_NAME_DESC_COLLEAGUE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagAddCommand.MESSAGE_USAGE));
    }
}
