package seedu.internhunter.logic.parser.add;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY_RANDOM;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.ADDRESS_DESC_AMAZON;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.ADDRESS_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.EMAIL_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INDUSTRY_DESC_FACEBOOK_AI;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INDUSTRY_DESC_FACEBOOK_SM;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_INDUSTRY_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_NAME_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.NAME_DESC_AMAZON;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.NAME_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.PHONE_DESC_AMAZON;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.PHONE_DESC_FACEBOOK;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_SOCIAL_MEDIA;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.add.AddCompanyCommand;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;
import seedu.internhunter.testutil.company.CompanyItemBuilder;

/**
 * Contains the tests for AddCompanyCommandParser.
 */
public class AddCompanyCommandParserTest {

    private AddCompanyCommandParser parser = new AddCompanyCommandParser();
    private String expectedGeneralMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddCompanyCommand.MESSAGE_USAGE);
    private CompanyItem expectedCompany = new CompanyItemBuilder(FACEBOOK).withInternships().build();
    private AddCompanyCommand expectedCommand = new AddCompanyCommand(expectedCompany);

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                        + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, expectedCommand);

        // parameters in different order
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDUSTRY_DESC_FACEBOOK_AI + EMAIL_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK + NAME_DESC_FACEBOOK, expectedCommand);

        // multiple company names - last company name accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMAZON + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, expectedCommand);

        // multiple addresses - last address accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_AMAZON
                + ADDRESS_DESC_FACEBOOK + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI,
                expectedCommand);

        // multiple phones - last phone accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_AMAZON + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI,
                expectedCommand);

        CompanyItem expectedCompanyMultipleIndustries =
                new CompanyItemBuilder(expectedCompany)
                        .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE, VALID_INDUSTRY_SOCIAL_MEDIA)
                        .build();

        AddCompanyCommand expectedCommandMultipleIndustries = new AddCompanyCommand(expectedCompanyMultipleIndustries);

        // multiple industries - all accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI + INDUSTRY_DESC_FACEBOOK_SM,
                expectedCommandMultipleIndustries);
    }

    @Test
    public void parse_optionalIndustryFieldMissing_success() {

        CompanyItem expectedCompanyNoIndustries =
                new CompanyItemBuilder(expectedCompany).withIndustries().build();

        AddCompanyCommand expectedCommandNoIndustries = new AddCompanyCommand(expectedCompanyNoIndustries);

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                        + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK, expectedCommandNoIndustries);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing company name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ADDRESS_DESC_FACEBOOK + PHONE_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, expectedGeneralMessage);

        // missing phone
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, expectedGeneralMessage);

        // missing email
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, expectedGeneralMessage);

        // missing address
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + PHONE_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, expectedGeneralMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY_RANDOM + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, expectedGeneralMessage);

        // invalid company name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_NAME_DESC + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI,
                CompanyName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + INVALID_PHONE_DESC + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + INVALID_EMAIL_DESC + INDUSTRY_DESC_FACEBOOK_AI, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + INVALID_ADDRESS_DESC
                + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI, Address.MESSAGE_CONSTRAINTS);

        // invalid industry
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INVALID_INDUSTRY_DESC, Industry.MESSAGE_CONSTRAINTS);

        // multiple invalid values, only first invalid value reported
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_NAME_DESC + INVALID_ADDRESS_DESC
                + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI,
                CompanyName.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_FACEBOOK + INVALID_ADDRESS_DESC
                        + PHONE_DESC_FACEBOOK + INVALID_EMAIL_DESC + INDUSTRY_DESC_FACEBOOK_AI,
                Email.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_NAME_DESC + INVALID_ADDRESS_DESC
                        + INVALID_PHONE_DESC + INVALID_EMAIL_DESC + INVALID_INDUSTRY_DESC,
                CompanyName.MESSAGE_CONSTRAINTS);
    }
}
