package trackitnus.logic.parser.contact;

import static trackitnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackitnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static trackitnus.testutil.typical.TypicalContacts.AMY;
import static trackitnus.testutil.typical.TypicalContacts.BOB;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.contact.AddContactCommand;
import trackitnus.logic.commands.contact.ContactCommandTestUtil;
import trackitnus.model.commons.Name;
import trackitnus.model.contact.Contact;
import trackitnus.model.contact.Email;
import trackitnus.model.contact.Phone;
import trackitnus.model.tag.Tag;
import trackitnus.testutil.builder.ContactBuilder;

public class AddContactCommandParserTest {
    private final AddContactCommandParser parser = new AddContactCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Contact expectedContact = new ContactBuilder(BOB).withTags(ContactCommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser,
            ContactCommandTestUtil.PREAMBLE_WHITESPACE + ContactCommandTestUtil.NAME_DESC_BOB
                + ContactCommandTestUtil.PHONE_DESC_BOB + ContactCommandTestUtil.EMAIL_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_FRIEND,
            new AddContactCommand(expectedContact));

        // multiple names - last name accepted
        assertParseSuccess(parser,
            ContactCommandTestUtil.NAME_DESC_AMY + ContactCommandTestUtil.NAME_DESC_BOB
                + ContactCommandTestUtil.PHONE_DESC_BOB + ContactCommandTestUtil.EMAIL_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_FRIEND,
            new AddContactCommand(expectedContact));

        // multiple phones - last phone accepted
        assertParseSuccess(parser,
            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.PHONE_DESC_AMY
                + ContactCommandTestUtil.PHONE_DESC_BOB + ContactCommandTestUtil.EMAIL_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_FRIEND,
            new AddContactCommand(expectedContact));

        // multiple emails - last email accepted
        assertParseSuccess(parser,
            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_AMY + ContactCommandTestUtil.EMAIL_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_FRIEND,
            new AddContactCommand(expectedContact));

        // multiple addresses - last address accepted
        assertParseSuccess(parser,
            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.TAG_DESC_FRIEND,
            new AddContactCommand(expectedContact));

        // multiple tags - all accepted
        Contact expectedContactMultipleTags = new ContactBuilder(BOB).withTags(ContactCommandTestUtil.VALID_TAG_FRIEND,
            ContactCommandTestUtil.VALID_TAG_HUSBAND)
            .build();
        assertParseSuccess(parser,
            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_HUSBAND + ContactCommandTestUtil.TAG_DESC_FRIEND,
            new AddContactCommand(expectedContactMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Contact expectedContact = new ContactBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
            ContactCommandTestUtil.NAME_DESC_AMY + ContactCommandTestUtil.PHONE_DESC_AMY
                + ContactCommandTestUtil.EMAIL_DESC_AMY,
            new AddContactCommand(expectedContact));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
            AddContactCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
            ContactCommandTestUtil.VALID_NAME_BOB + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.ADDRESS_DESC_BOB,
            expectedMessage);

//        // missing phone prefix
//        assertParseFailure(parser,
//            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.VALID_PHONE_BOB
//                + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.ADDRESS_DESC_BOB,
//            expectedMessage);

//        // missing email prefix
//        assertParseFailure(parser,
//            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.PHONE_DESC_BOB
//                + ContactCommandTestUtil.VALID_EMAIL_BOB + ContactCommandTestUtil.ADDRESS_DESC_BOB,
//            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            ContactCommandTestUtil.VALID_NAME_BOB + ContactCommandTestUtil.VALID_PHONE_BOB
                + ContactCommandTestUtil.VALID_EMAIL_BOB + ContactCommandTestUtil.VALID_ADDRESS_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
            ContactCommandTestUtil.INVALID_NAME_DESC + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.ADDRESS_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_HUSBAND + ContactCommandTestUtil.TAG_DESC_FRIEND,
            Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.INVALID_PHONE_DESC
                + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.ADDRESS_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_HUSBAND + ContactCommandTestUtil.TAG_DESC_FRIEND,
            Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.INVALID_EMAIL_DESC + ContactCommandTestUtil.ADDRESS_DESC_BOB
                + ContactCommandTestUtil.TAG_DESC_HUSBAND + ContactCommandTestUtil.TAG_DESC_FRIEND,
            Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
            ContactCommandTestUtil.NAME_DESC_BOB + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_BOB
                + ContactCommandTestUtil.INVALID_TAG_DESC + ContactCommandTestUtil.VALID_TAG_FRIEND,
            Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
            ContactCommandTestUtil.INVALID_NAME_DESC + ContactCommandTestUtil.PHONE_DESC_BOB
                + ContactCommandTestUtil.EMAIL_DESC_BOB + ContactCommandTestUtil.INVALID_ADDRESS_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            ContactCommandTestUtil.PREAMBLE_NON_EMPTY + ContactCommandTestUtil.NAME_DESC_BOB
                + ContactCommandTestUtil.PHONE_DESC_BOB + ContactCommandTestUtil.EMAIL_DESC_BOB
                + ContactCommandTestUtil.ADDRESS_DESC_BOB + ContactCommandTestUtil.TAG_DESC_HUSBAND
                + ContactCommandTestUtil.TAG_DESC_FRIEND,
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
    }
}
