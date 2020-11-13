package seedu.internhunter.testutil.company;

public class CompanyItemFieldsUtil {

    // Valid company names
    public static final String VALID_COMPANY_NAME_FACEBOOK = "Facebook";
    public static final String VALID_COMPANY_NAME_GOLDMAN = "Goldman Sachs";
    public static final String VALID_COMPANY_NAME_GOOGLE = "Google";
    public static final String VALID_COMPANY_NAME_GOOGLE_WITH_TRAILING_SPACES = "Google  ";
    public static final String VALID_COMPANY_NAME_AMAZON = "Amazon";
    public static final String VALID_COMPANY_NAME_GARENA = "Garena";

    // Invalid company names
    public static final String INVALID_COMPANY_NAME_EMPTY = "";
    public static final String INVALID_COMPANY_NAME_BLANK = "   ";
    public static final String INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_SPACES = "  Google";
    public static final String INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_AND_TRAILING_SPACES = "  Google  ";
    public static final String INVALID_COMPANY_NAME_SPECIAL_CHAR_PERIOD = "Google Co.";
    public static final String INVALID_COMPANY_NAME_SPECIAL_CHAR_AMPERSAND = "Holland & Barrett";

    // Valid phone numbers
    public static final String VALID_PHONE_THREE_NUMBERS = "123";
    public static final String VALID_PHONE_EIGHT_NUMBERS = "12345678";
    public static final String VALID_PHONE_TEN_NUMBERS = "0123456789";
    public static final String VALID_PHONE_FACEBOOK = "61112111";
    public static final String VALID_PHONE_GOLDMAN = "69792525";
    public static final String VALID_PHONE_GOOGLE = "61231234";
    public static final String VALID_PHONE_AMAZON = "62343434";
    public static final String VALID_PHONE_GARENA = "986876587";

    // Invalid phone numbers
    public static final String INVALID_PHONE_EMPTY = "";
    public static final String INVALID_PHONE_BLANK = "   ";
    public static final String INVALID_PHONE_TWO_NUMBERS = "12";
    public static final String INVALID_PHONE_GOOGLE_LEADING_SPACES = "  61231234";
    public static final String INVALID_PHONE_GOOGLE_TRAILING_SPACES = "61231234  ";
    public static final String INVALID_PHONE_GOOGLE_LEADING_AND_TRAILING_SPACES = "  61231234  ";
    public static final String INVALID_PHONE_WHITESPACE = "6123 1234";
    public static final String INVALID_PHONE_SPECIAL_CHAR_PLUS = "+6560001000";
    public static final String INVALID_PHONE_SPECIAL_CHAR_PARENTHESES = "(555)1234567";
    public static final String INVALID_PHONE_SPECIAL_CHAR_DASH = "1-800-273-8255";
    public static final String INVALID_PHONE_ALPHABETS = "Dial60001000";
    public static final String INVALID_PHONE_ALPHABETS_ONLY = "asdf";

    // Valid emails
    public static final String VALID_EMAIL_FACEBOOK = "FacebookHires@facebook.com";
    public static final String VALID_EMAIL_GOLDMAN = "GoldmanSachsHires@gms.com";
    public static final String VALID_EMAIL_GOOGLE = "GoogleHires@google.com";
    public static final String VALID_EMAIL_GARENA = "GarenaHires@sea.com";
    public static final String VALID_EMAIL_LOCAL_PART_SPECIAL_CHAR_APOSTROPHE = "You'reHired@company.com";
    public static final String VALID_EMAIL_LOCAL_PART_SPECIAL_CHARS = "make$$$&keep100%@company.com";
    public static final String VALID_EMAIL_LOCAL_PART_ALL_SPECIAL_CHARS = "!#$%&'*+/=?`{|}~^.-@company.com";
    public static final String VALID_EMAIL_DOMAIN_NO_PERIOD = "CompanyHires@company";
    public static final String VALID_EMAIL_DOMAIN_HYPHEN = "CompanyHires@tech-company";
    public static final String VALID_EMAIL_SHORTEST = "a@bc";
    public static final String VALID_EMAIL_LOCAL_PART_AND_DOMAIN_SPECIAL_CHARS = "-@a.--.a";
    public static final String VALID_EMAIL_AMAZON = "Amazon@hires.com";

    // Invalid emails
    public static final String INVALID_EMAIL_EMPTY = "";
    public static final String INVALID_EMAIL_BLANK = "   ";
    public static final String INVALID_EMAIL_GOOGLE_LEADING_SPACES = "  GoogleHires@google.com";
    public static final String INVALID_EMAIL_GOOGLE_TRAILING_SPACES = "GoogleHires@google.com  ";
    public static final String INVALID_EMAIL_GOOGLE_LEADING_AND_TRAILING_SPACES = "  GoogleHires@google.com  ";
    public static final String INVALID_EMAIL_NO_AT = "CompanyHirescompany.com";
    public static final String INVALID_EMAIL_LOCAL_PART_SPACE = "Company Hires@company.com";
    public static final String INVALID_EMAIL_LOCAL_PART_SPECIAL_CHAR_ANGLE_BRACKETS = "Company>Hires@company.com";
    public static final String INVALID_EMAIL_DOMAIN_SPACE = "CompanyHires@company com";
    public static final String INVALID_EMAIL_DOMAIN_ONE_CHAR = "a@b";
    public static final String INVALID_EMAIL_DOMAIN_BEGIN_WITH_PERIOD = "CompanyHires@.company.com";
    public static final String INVALID_EMAIL_DOMAIN_END_WITH_PERIOD = "CompanyHires@company.com.";
    public static final String INVALID_EMAIL_DOMAIN_BEGIN_WITH_HYPHEN = "CompanyHires@-company.com";
    public static final String INVALID_EMAIL_DOMAIN_END_WITH_HYPHEN = "CompanyHires@company.com-";

    // Valid addresses
    public static final String VALID_ADDRESS_ONE_ALPHABET = "a";
    public static final String VALID_ADDRESS_ONE_NUMBER = "1";
    public static final String VALID_ADDRESS_ONE_SPECIAL_CHAR = "*";
    public static final String VALID_ADDRESS_SPECIAL_CHARS = "`~!@#$%^&*()_-+=[]{}|;':,.<>?/\\";
    public static final String VALID_ADDRESS_FACEBOOK = "9 Straits View, Marina One";
    public static final String VALID_ADDRESS_GOLDMAN = "60 Anson Rd, #14-01 Mapletree Anson";
    public static final String VALID_ADDRESS_GOOGLE = "70 Pasir Panjang Rd, #03-71";
    public static final String VALID_ADDRESS_GARENA = "201 Victoria St";
    public static final String VALID_ADDRESS_GOOGLE_LEADING_SPACES = "  70 Pasir Panjang Rd, #03-71";
    public static final String VALID_ADDRESS_GOOGLE_TRAILING_SPACES = "70 Pasir Panjang Rd, #03-71  ";
    public static final String VALID_ADDRESS_GOOGLE_LEADING_AND_TRAILING_SPACES = "  70 Pasir Panjang Rd, #03-71  ";
    public static final String VALID_ADDRESS_AMAZON = "201 Tampines street";

    // Invalid addresses
    public static final String INVALID_ADDRESS_EMPTY = "";
    public static final String INVALID_ADDRESS_BLANK = "   ";

    // Valid industries
    public static final String VALID_INDUSTRY_SOCIAL_MEDIA = "Social Media";
    public static final String VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE = "Artificial Intelligence";
    public static final String VALID_INDUSTRY_CLOUD_COMPUTING = "Cloud Computing";
    public static final String VALID_INDUSTRY_BANKING = "Banking";
    public static final String VALID_INDUSTRY_BANKING_WITH_TRAILING_SPACES = "Banking  ";


    // Invalid industries
    public static final String INVALID_INDUSTRY_EMPTY = "";
    public static final String INVALID_INDUSTRY_BLANK = "   ";
    public static final String INVALID_INDUSTRY_BANKING_WITH_LEADING_SPACES = "  Banking";
    public static final String INVALID_INDUSTRY_BANKING_WITH_LEADING_AND_TRAILING_SPACES = "  Banking  ";
    public static final String INVALID_INDUSTRY_SPECIAL_CHAR_PERIOD = "Gaming.";
    public static final String INVALID_INDUSTRY_SPECIAL_CHAR_AMPERSAND = "F&B";

}
