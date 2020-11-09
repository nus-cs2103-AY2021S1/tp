package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactDescriptor;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.contact.EditContactDescriptorBuilder;

public class EditContactParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE);

    private EditContactParser parser = new EditContactParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditContactCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, ContactName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS); // invalid telegram
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        // invalid email followed by valid telegram
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC + TELEGRAM_DESC_AMY, Email.MESSAGE_CONSTRAINTS);

        // valid telegram followed by invalid telegram.
        assertParseFailure(parser, "1" + TELEGRAM_DESC_BOB + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Contact} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_TELEGRAM_AMY,
                ContactName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CONTACT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + TELEGRAM_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTelegram(VALID_TELEGRAM_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_CONTACT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // telegram
        userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditContactDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditContactDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + TELEGRAM_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_CONTACT;
        String userInput = targetIndex.getOneBased() + INVALID_TELEGRAM_DESC + TELEGRAM_DESC_BOB;
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB).build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC
                + TELEGRAM_DESC_BOB;
        descriptor = new EditContactDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        expectedCommand = new EditContactCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_CONTACT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withTags().build();
        EditContactCommand expectedCommand = new EditContactCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
