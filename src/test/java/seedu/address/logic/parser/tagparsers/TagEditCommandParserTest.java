package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_NAME_DESC_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_NAME_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TagEditCommand;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.EditTagDescriptorBuilder;


public class TagEditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagEditCommand.MESSAGE_USAGE);

    private TagEditCommandParser parser = new TagEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TAG_NAME_HUSBAND, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", TagEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TAG_NAME_DESC_HUSBAND, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TAG_NAME_DESC_HUSBAND, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TAG_NAME_DESC, TagName.MESSAGE_CONSTRAINTS); // invalid tag
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TAG;
        String userInput = targetIndex.getOneBased() + TAG_NAME_DESC_HUSBAND;

        TagEditCommand.EditTagDescriptor descriptor = new EditTagDescriptorBuilder()
                .withTagName(VALID_TAG_NAME_HUSBAND).build();

        TagEditCommand expectedCommand = new TagEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TAG;
        String userInput = targetIndex.getOneBased() + TAG_NAME_DESC_HUSBAND + TAG_NAME_DESC_COLLEAGUE;

        TagEditCommand.EditTagDescriptor descriptor = new EditTagDescriptorBuilder()
                .withTagName(VALID_TAG_NAME_COLLEAGUE).build();

        TagEditCommand expectedCommand = new TagEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_THIRD_TAG;
        String userInput = targetIndex.getOneBased() + INVALID_TAG_NAME_DESC + TAG_NAME_DESC_HUSBAND;

        TagEditCommand.EditTagDescriptor descriptor = new EditTagDescriptorBuilder()
                .withTagName(VALID_TAG_NAME_HUSBAND).build();

        TagEditCommand expectedCommand = new TagEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
