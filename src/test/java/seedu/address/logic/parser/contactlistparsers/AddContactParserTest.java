package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.contact.TypicalContacts.AMY;
import static seedu.address.testutil.contact.TypicalContacts.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contactlistcommands.AddContactCommand;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.contact.ContactBuilder;

public class AddContactParserTest {
    private AddContactParser parser = new AddContactParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Contact expectedContact = new ContactBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND, new AddContactCommand(expectedContact));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND, new AddContactCommand(expectedContact));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND, new AddContactCommand(expectedContact));

        // multiple telegrams - last telegram accepted
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_AMY
                + TELEGRAM_DESC_BOB + TAG_DESC_FRIEND, new AddContactCommand(expectedContact));

        // multiple tags - all accepted
        Contact expectedContactMultipleTags = new ContactBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddContactCommand(expectedContactMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Contact expectedContact = new ContactBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + TELEGRAM_DESC_AMY,
                new AddContactCommand(expectedContact));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_EMAIL_BOB + TELEGRAM_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ContactName.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_EMAIL_DESC + TELEGRAM_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid telegram
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Telegram.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC,
                ContactName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + EMAIL_DESC_BOB
                        + TELEGRAM_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
    }
}
