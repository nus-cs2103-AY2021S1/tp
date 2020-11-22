package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CONTRACT_EXPIRY_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CONTRACT_EXPIRY_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COUNTRY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COUNTRY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTRACT_EXPIRY_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COUNTRY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMEZONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIMEZONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMEZONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTRACT_EXPIRY_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMEZONE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClientAddCommand;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ContractExpiryDate;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Timezone;
import seedu.address.model.country.CountryCodeVerifier;
import seedu.address.testutil.ClientBuilder;

public class ClientAddCommandParserTest {

    private final ClientAddCommandParser parser = new ClientAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

        // multiple countries - last country accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_AMY + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

        // multiple timezones - last timezone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_AMY + TIMEZONE_DESC_BOB
                + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

        // multiple contract expiry dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_AMY + CONTRACT_EXPIRY_DATE_DESC_BOB,
                new ClientAddCommand(expectedClient));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no contract expiry date
        Client expectedClient2 = new ClientBuilder(AMY).withContractExpiryDate(ContractExpiryDate.NULL_DATE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + COUNTRY_DESC_AMY + TIMEZONE_DESC_AMY,
                new ClientAddCommand(expectedClient2));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientAddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, expectedMessage);

        // missing country prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_COUNTRY_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, expectedMessage);

        // missing timezone prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + VALID_TIMEZONE_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_COUNTRY_BOB + VALID_TIMEZONE_BOB + VALID_CONTRACT_EXPIRY_DATE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid country
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_COUNTRY_DESC + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                CountryCodeVerifier.MESSAGE_CONSTRAINTS);

        // invalid timezone
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + INVALID_TIMEZONE_DESC + CONTRACT_EXPIRY_DATE_DESC_BOB,
                Timezone.MESSAGE_CONSTRAINTS);

        // invalid contract expiry date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + INVALID_CONTRACT_EXPIRY_DATE_DESC,
                ContractExpiryDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + INVALID_COUNTRY_DESC + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + COUNTRY_DESC_BOB + TIMEZONE_DESC_BOB + CONTRACT_EXPIRY_DATE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClientAddCommand.MESSAGE_USAGE));
    }

}
