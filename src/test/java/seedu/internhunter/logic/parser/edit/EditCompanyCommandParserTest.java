package seedu.internhunter.logic.parser.edit;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.ADDRESS_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.EMAIL_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INDUSTRY_DESC_FACEBOOK_AI;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INDUSTRY_DESC_FACEBOOK_SM;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_INDUSTRY_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_NAME_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.NAME_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.PHONE_DESC_AMAZON;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.PHONE_DESC_FACEBOOK;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_INDUSTRY;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_SOCIAL_MEDIA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_FACEBOOK;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.commands.edit.EditCompanyCommand;
import seedu.internhunter.logic.commands.edit.EditCompanyCommand.EditCompanyDescriptor;
import seedu.internhunter.logic.commands.util.company.EditCompanyDescriptorBuilder;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;

public class EditCompanyCommandParserTest {

    private static final String INDUSTRY_EMPTY = " " + PREFIX_INDUSTRY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCompanyCommand.MESSAGE_USAGE);

    private EditCompanyCommandParser parser = new EditCompanyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NAME_DESC_FACEBOOK, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PHONE_DESC_FACEBOOK, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + PHONE_DESC_FACEBOOK, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + PHONE_DESC_FACEBOOK, MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string" + PHONE_DESC_FACEBOOK, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // invalid industry
        assertParseFailure(parser, "1" + INVALID_INDUSTRY_DESC, Industry.MESSAGE_CONSTRAINTS);

        // invalid value followed by valid value of a different type
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_FACEBOOK, Phone.MESSAGE_CONSTRAINTS);

        // valid value followed by invalid value of the same type. The test case for invalid value followed
        // by valid value of the same type is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + EMAIL_DESC_FACEBOOK + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_INDUSTRY} alone will reset the industries of the {@code CompanyItem} being
        // edited, parsing it together with a valid industry results in error
        assertParseFailure(parser, "1" + INDUSTRY_DESC_FACEBOOK_AI + INDUSTRY_DESC_FACEBOOK_SM + INDUSTRY_EMPTY,
                Industry.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INDUSTRY_DESC_FACEBOOK_AI + INDUSTRY_EMPTY + INDUSTRY_DESC_FACEBOOK_SM,
                Industry.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INDUSTRY_EMPTY + INDUSTRY_DESC_FACEBOOK_AI + INDUSTRY_DESC_FACEBOOK_SM,
                Industry.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC + INVALID_ADDRESS_DESC, Email.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + NAME_DESC_FACEBOOK + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK
                + ADDRESS_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .withPhone(VALID_PHONE_FACEBOOK)
                .withEmail(VALID_EMAIL_FACEBOOK)
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE)
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withPhone(VALID_PHONE_FACEBOOK)
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NAME_DESC_FACEBOOK;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_FACEBOOK;

        descriptor = new EditCompanyDescriptorBuilder()
                .withPhone(VALID_PHONE_FACEBOOK)
                .build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_FACEBOOK;

        descriptor = new EditCompanyDescriptorBuilder()
                .withEmail(VALID_EMAIL_FACEBOOK)
                .build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_FACEBOOK;

        descriptor = new EditCompanyDescriptorBuilder()
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // industry
        userInput = targetIndex.getOneBased() + INDUSTRY_DESC_FACEBOOK_AI;

        descriptor = new EditCompanyDescriptorBuilder()
                .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE)
                .build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFieldsNotIndustry_acceptsLast() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_FACEBOOK + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK
                + ADDRESS_DESC_FACEBOOK + PHONE_DESC_AMAZON + INDUSTRY_DESC_FACEBOOK_AI;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .withPhone(VALID_PHONE_AMAZON)
                .withEmail(VALID_EMAIL_FACEBOOK)
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE)
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedIndustries_acceptsAll() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_FACEBOOK + PHONE_DESC_FACEBOOK + EMAIL_DESC_FACEBOOK
                + ADDRESS_DESC_FACEBOOK + INDUSTRY_DESC_FACEBOOK_AI + INDUSTRY_DESC_FACEBOOK_SM;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .withPhone(VALID_PHONE_FACEBOOK)
                .withEmail(VALID_EMAIL_FACEBOOK)
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE, VALID_INDUSTRY_SOCIAL_MEDIA)
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_FACEBOOK;
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withPhone(VALID_PHONE_FACEBOOK)
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + NAME_DESC_FACEBOOK + PHONE_DESC_FACEBOOK
                + ADDRESS_DESC_FACEBOOK;

        descriptor = new EditCompanyDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .withPhone(VALID_PHONE_FACEBOOK)
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetIndustries_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + INDUSTRY_EMPTY;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withIndustries()
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
