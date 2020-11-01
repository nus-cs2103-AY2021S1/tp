package trackitnus.logic.parser.contact;

import static trackitnus.commons.core.Messages.MESSAGE_NOT_EDITED;
import static trackitnus.logic.parser.CliSyntax.PREFIX_TAG;
import static trackitnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackitnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.logic.commands.contact.EditContactCommand;
import trackitnus.model.commons.Name;
import trackitnus.model.contact.Email;
import trackitnus.model.contact.Phone;
import trackitnus.model.tag.Tag;
import trackitnus.testutil.builder.EditContactDescriptorBuilder;
import trackitnus.testutil.typical.TypicalIndexes;

public class EditContactCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE);

    private final EditContactCommandParser parser = new EditContactCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, ContactCommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ContactCommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ContactCommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + ContactCommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); //
        // invalid name
        assertParseFailure(parser, "1" + ContactCommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); //
        // invalid phone
        assertParseFailure(parser, "1" + ContactCommandTestUtil.INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); //
        // invalid email
        assertParseFailure(parser, "1" + ContactCommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); //
        // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser,
            "1" + ContactCommandTestUtil.INVALID_PHONE_DESC + ContactCommandTestUtil.EMAIL_DESC_AMY,
            Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser,
            "1" + ContactCommandTestUtil.PHONE_DESC_BOB + ContactCommandTestUtil.INVALID_PHONE_DESC,
            Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Contact} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser,
            "1" + ContactCommandTestUtil.TAG_DESC_FRIEND + ContactCommandTestUtil.TAG_DESC_HUSBAND + TAG_EMPTY,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
            "1" + ContactCommandTestUtil.TAG_DESC_FRIEND + TAG_EMPTY + ContactCommandTestUtil.TAG_DESC_HUSBAND,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
            "1" + TAG_EMPTY + ContactCommandTestUtil.TAG_DESC_FRIEND + ContactCommandTestUtil.TAG_DESC_HUSBAND,
            Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
            "1" + ContactCommandTestUtil.INVALID_NAME_DESC + ContactCommandTestUtil.INVALID_EMAIL_DESC
                + ContactCommandTestUtil.VALID_ADDRESS_AMY + ContactCommandTestUtil.VALID_PHONE_AMY,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND;
        String userInput =
            targetIndex.getOneBased() + ContactCommandTestUtil.PHONE_DESC_BOB + ContactCommandTestUtil.TAG_DESC_HUSBAND
                + ContactCommandTestUtil.EMAIL_DESC_AMY + ContactCommandTestUtil.NAME_DESC_AMY
                + ContactCommandTestUtil.TAG_DESC_FRIEND;

        EditContactCommand.EditContactDescriptor descriptor =
            new EditContactDescriptorBuilder().withName(ContactCommandTestUtil.VALID_NAME_AMY)
                .withPhone(ContactCommandTestUtil.VALID_PHONE_BOB).withEmail(ContactCommandTestUtil.VALID_EMAIL_AMY)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND, ContactCommandTestUtil.VALID_TAG_FRIEND).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput =
            targetIndex.getOneBased() + ContactCommandTestUtil.PHONE_DESC_BOB + ContactCommandTestUtil.EMAIL_DESC_AMY;

        EditContactCommand.EditContactDescriptor descriptor =
            new EditContactDescriptorBuilder().withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
                .withEmail(ContactCommandTestUtil.VALID_EMAIL_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + ContactCommandTestUtil.NAME_DESC_AMY;
        EditContactCommand.EditContactDescriptor descriptor =
            new EditContactDescriptorBuilder().withName(ContactCommandTestUtil.VALID_NAME_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + ContactCommandTestUtil.PHONE_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withPhone(ContactCommandTestUtil.VALID_PHONE_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + ContactCommandTestUtil.EMAIL_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withEmail(ContactCommandTestUtil.VALID_EMAIL_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + ContactCommandTestUtil.TAG_DESC_FRIEND;
        descriptor = new EditContactDescriptorBuilder().withTags(ContactCommandTestUtil.VALID_TAG_FRIEND).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput =
            targetIndex
                .getOneBased() + ContactCommandTestUtil.PHONE_DESC_AMY + ContactCommandTestUtil.ADDRESS_DESC_AMY
                + ContactCommandTestUtil.EMAIL_DESC_AMY
                + ContactCommandTestUtil.TAG_DESC_FRIEND + ContactCommandTestUtil.PHONE_DESC_AMY
                + ContactCommandTestUtil.EMAIL_DESC_AMY
                + ContactCommandTestUtil.TAG_DESC_FRIEND
                + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.TAG_DESC_HUSBAND;

        EditContactCommand.EditContactDescriptor descriptor =
            new EditContactDescriptorBuilder().withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
                .withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB)
                .withTags(ContactCommandTestUtil.VALID_TAG_FRIEND, ContactCommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST;
        String userInput =
            targetIndex
                .getOneBased() + ContactCommandTestUtil.INVALID_PHONE_DESC + ContactCommandTestUtil.PHONE_DESC_BOB;
        EditContactCommand.EditContactDescriptor descriptor =
            new EditContactDescriptorBuilder().withPhone(ContactCommandTestUtil.VALID_PHONE_BOB).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput =
            targetIndex
                .getOneBased() + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.INVALID_PHONE_DESC
                + ContactCommandTestUtil.ADDRESS_DESC_BOB
                + ContactCommandTestUtil.PHONE_DESC_BOB;
        descriptor =
            new EditContactDescriptorBuilder().withPhone(ContactCommandTestUtil.VALID_PHONE_BOB)
                .withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withTags().build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
