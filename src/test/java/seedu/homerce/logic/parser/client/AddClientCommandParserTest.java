package seedu.homerce.logic.parser.client;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.homerce.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.homerce.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.homerce.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.homerce.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.homerce.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.homerce.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.homerce.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.homerce.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.homerce.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.homerce.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.homerce.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.homerce.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.homerce.testutil.client.TypicalClients.AMY;
import static seedu.homerce.testutil.client.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.client.AddClientCommand;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Email;
import seedu.homerce.model.client.Name;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.util.attributes.Tag;
import seedu.homerce.testutil.client.ClientBuilder;

public class AddClientCommandParserTest {
    private AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple addresses - last homerce accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddClientCommand(expectedClient));

        // multiple tags - all accepted
        Client expectedClientMultipleTags = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddClientCommand(expectedClientMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Client expectedClient = new ClientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddClientCommand(expectedClient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
    }
}
