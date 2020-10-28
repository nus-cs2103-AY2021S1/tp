package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LABEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnlabelCommand;
import seedu.address.model.label.Label;
import seedu.address.model.tag.TagName;

public class UnlabelCommandParserTest {
    private UnlabelCommandParser parser = new UnlabelCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        TagName tagName = new TagName(VALID_TAG_NAME_CS2103);
        Label label = new Label(VALID_LABEL);
        Set<Label> labels = Set.of(label);

        String userInput = TAG_DESC_CS2103 + VALID_LABEL_DESC;

        UnlabelCommand unlabelCommand = new UnlabelCommand(tagName, labels);

        assertParseSuccess(parser, userInput, unlabelCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnlabelCommand.MESSAGE_USAGE);

        // missing tag name prefix
        assertParseFailure(parser, VALID_TAG_NAME_CS2103 + VALID_LABEL_DESC, expectedMessage);

        // missing tag
        assertParseFailure(parser, VALID_LABEL_DESC, expectedMessage);

        // missing label name prefix
        assertParseFailure(parser, TAG_DESC_CS2103 + VALID_LABEL, expectedMessage);

        // missing label
        assertParseFailure(parser, TAG_DESC_CS2103, expectedMessage);

        // has preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TAG_NAME_CS2103 + VALID_LABEL, expectedMessage);
    }

    @Test
    public void parse_invalidTagInput_failure() {
        // wrong tag format
        assertParseFailure(parser, INVALID_TAG_DESC + VALID_LABEL_DESC,
                TagName.MESSAGE_CONSTRAINTS);

        // wrong label format
        assertParseFailure(parser, TAG_DESC_CS2103 + INVALID_LABEL_DESC,
                Label.MESSAGE_CONSTRAINTS);
    }
}
