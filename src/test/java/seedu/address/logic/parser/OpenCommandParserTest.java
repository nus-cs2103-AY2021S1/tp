package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTags.CS2103;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.OpenCommand;
import seedu.address.model.label.Label;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class OpenCommandParserTest {
    private OpenCommandParser parser = new OpenCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_correctFieldsPresent_success() {
        Tag expectedTag = new TagBuilder(CS2103).build();
        Label expectedLabel = new Label(VALID_LABEL);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_CS2103,
                new OpenCommand(expectedTag.getTagName()));

        // One tag name
        assertParseSuccess(parser, TAG_DESC_CS2103,
                new OpenCommand(expectedTag.getTagName()));

        // multiple tag names - last tag name accepted
        assertParseSuccess(parser, TAG_DESC_CS2101 + TAG_DESC_CS2103,
                new OpenCommand(expectedTag.getTagName()));

        // One label
        assertParseSuccess(parser, VALID_LABEL_DESC, new OpenCommand(expectedLabel));

        // More than 1 label - last label accepted
        assertParseSuccess(parser, VALID_LABEL_DESC + "2" + VALID_LABEL_DESC,
                new OpenCommand(expectedLabel));
    }

    @Test
    public void parse_bothFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE);

        // missing all prefix
        assertParseFailure(parser, VALID_TAG_NAME_CS2103, expectedMessage);
    }

    @Test
    public void parse_bothFieldPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE);

        // Having both prefixes is not allowed
        assertParseFailure(parser, INVALID_TAG_DESC + VALID_LABEL_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TAG_DESC_CS2101,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
    }

}
