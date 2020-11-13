package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OLD_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.OLD_TAG_DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RetagCommand;
import seedu.address.model.tag.TagName;

public class RetagCommandParserTest {

    private RetagCommandParser parser = new RetagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TagName oldTagName = new TagName(VALID_TAG_NAME_CS2101);
        TagName newTagName = new TagName(VALID_TAG_NAME_CS2103);

        String userInput = OLD_TAG_DESC_CS2101 + TAG_DESC_CS2103;

        RetagCommand retagCommand = new RetagCommand(oldTagName, newTagName);

        assertParseSuccess(parser, userInput, retagCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RetagCommand.MESSAGE_USAGE);

        // missing old tag name prefix
        assertParseFailure(parser, VALID_TAG_NAME_CS2101 + TAG_DESC_CS2103,
                expectedMessage);

        // missing new tag name prefix
        assertParseFailure(parser, OLD_TAG_DESC_CS2101 + VALID_TAG_NAME_CS2103,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TAG_NAME_CS2103 + VALID_TAG_NAME_CS2103, expectedMessage);
    }

    @Test
    public void parse_invalidTagInput_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RetagCommand.MESSAGE_USAGE);

        // wrong old tag format
        assertParseFailure(parser, INVALID_OLD_TAG_DESC + TAG_DESC_CS2103,
                TagName.MESSAGE_CONSTRAINTS);

        // wrong new tag format
        assertParseFailure(parser, VALID_TAG_NAME_CS2101 + INVALID_TAG_DESC,
                expectedMessage);
    }
}
