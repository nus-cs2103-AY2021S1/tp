package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLabelCommand;
import seedu.address.logic.commands.AddLabelCommand.LabelPersonDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.LabelPersonDescriptorBuilder;

import java.util.stream.Stream;

public class AddLabelCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLabelCommand.MESSAGE_USAGE);

    private AddLabelCommandParser parser = new AddLabelCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no tags field specified
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, NAME_DESC_AMY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_AMY + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_AMY + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = TAG_DESC_HUSBAND + NAME_DESC_AMY + TAG_DESC_FRIEND;

        LabelPersonDescriptor descriptor = new LabelPersonDescriptorBuilder()
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        AddLabelCommand expectedCommand = new AddLabelCommand(new Name(VALID_NAME_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = NAME_DESC_AMY + TAG_EMPTY;

        LabelPersonDescriptor descriptor = new LabelPersonDescriptorBuilder().withTags().build();
        AddLabelCommand expectedCommand = new AddLabelCommand(new Name(VALID_NAME_AMY), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
