package seedu.internhunter.logic.commands.util.company;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_ADDRESS;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_EMAIL;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_INDUSTRY;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_PHONE;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_ADDRESS_EMPTY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_COMPANY_NAME_SPECIAL_CHAR_PERIOD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_END_WITH_HYPHEN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_INDUSTRY_SPECIAL_CHAR_AMPERSAND;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_SPECIAL_CHAR_PLUS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_SOCIAL_MEDIA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_FACEBOOK;

public class CompanyCommandTestUtil {

    // Invalid company index
    public static final String INVALID_COMPANY_INDEX_MESSAGE =
            String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, COMPANY_NAME);

    // Valid Company Names
    public static final String NAME_DESC_FACEBOOK = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_FACEBOOK;
    public static final String NAME_DESC_AMAZON = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_AMAZON;

    // Invalid Company Name
    public static final String INVALID_NAME_DESC = " " + PREFIX_COMPANY_NAME
            + INVALID_COMPANY_NAME_SPECIAL_CHAR_PERIOD;

    // Valid Addresses
    public static final String ADDRESS_DESC_FACEBOOK = " " + PREFIX_ADDRESS + VALID_ADDRESS_FACEBOOK;
    public static final String ADDRESS_DESC_AMAZON = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMAZON;

    // Invalid Address
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + INVALID_ADDRESS_EMPTY;

    // Valid Phones
    public static final String PHONE_DESC_FACEBOOK = " " + PREFIX_PHONE + VALID_PHONE_FACEBOOK;
    public static final String PHONE_DESC_AMAZON = " " + PREFIX_PHONE + VALID_PHONE_AMAZON;

    // Invalid Phone
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + INVALID_PHONE_SPECIAL_CHAR_PLUS;

    // Valid Email
    public static final String EMAIL_DESC_FACEBOOK = " " + PREFIX_EMAIL + VALID_EMAIL_FACEBOOK;

    // Invalid Email
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + INVALID_EMAIL_DOMAIN_END_WITH_HYPHEN;

    // Valid Industries
    public static final String INDUSTRY_DESC_FACEBOOK_AI =
            " " + PREFIX_INDUSTRY + VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE;
    public static final String INDUSTRY_DESC_FACEBOOK_SM =
            " " + PREFIX_INDUSTRY + VALID_INDUSTRY_SOCIAL_MEDIA;

    // Invalid Industry
    public static final String INVALID_INDUSTRY_DESC =
            " " + PREFIX_INDUSTRY + INVALID_INDUSTRY_SPECIAL_CHAR_AMPERSAND;

}
