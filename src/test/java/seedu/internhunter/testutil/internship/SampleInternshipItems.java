package seedu.internhunter.testutil.internship;

import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_FACEBOOK;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_GOLDMAN;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_LAZADA;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_SHOPEE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_BA;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_DS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_FE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_MAY_TO_JULY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_SUMMER;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_THREE_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_TWO_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_GRAPHQL;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_HTML;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_PYTHON;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_R;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_RN;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_TENSOR;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3500;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_4000;

import seedu.internhunter.model.internship.InternshipItem;

/**
 * A utility class containing a list of {@code InternshipItem} objects to be used in tests.
 */
public abstract class SampleInternshipItems {

    public static final InternshipItem GOLDMAN_BA = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_GOLDMAN)
            .withJobTitle(VALID_JOB_TITLE_BA)
            .withWage(VALID_WAGE_4000)
            .withPeriod(VALID_PERIOD_SUMMER)
            .withRequirements(VALID_REQUIREMENT_GRAPHQL, VALID_REQUIREMENT_R)
            .build();
    public static final InternshipItem GOLDMAN_FE = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_GOLDMAN)
            .withJobTitle(VALID_JOB_TITLE_FE)
            .withWage(VALID_WAGE_3000)
            .withPeriod(VALID_PERIOD_MAY_TO_JULY)
            .build();
    public static final InternshipItem SHOPEE_SWE = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_SHOPEE)
            .withJobTitle(VALID_JOB_TITLE_SWE)
            .withWage(VALID_WAGE_2000)
            .withPeriod(VALID_PERIOD_TWO_MONTHS)
            .withRequirements(VALID_REQUIREMENT_RN)
            .build();
    public static final InternshipItem LAZADA_DS = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_LAZADA)
            .withJobTitle(VALID_JOB_TITLE_DS)
            .withWage(VALID_WAGE_3000)
            .withPeriod(VALID_PERIOD_THREE_MONTHS)
            .withRequirements(VALID_REQUIREMENT_PYTHON, VALID_REQUIREMENT_TENSOR)
            .build();
    public static final InternshipItem FACEBOOK_FE = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
            .withJobTitle(VALID_JOB_TITLE_FE)
            .withWage(VALID_WAGE_3500)
            .withPeriod(VALID_PERIOD_MAY_TO_JULY)
            .build();
    public static final InternshipItem FACEBOOK_SWE = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
            .withJobTitle(VALID_JOB_TITLE_SWE)
            .withWage(VALID_WAGE_4000)
            .withPeriod(VALID_PERIOD_SUMMER)
            .withRequirements(VALID_REQUIREMENT_HTML)
            .build();
    public static final InternshipItem FACEBOOK_BA = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
            .withJobTitle(VALID_JOB_TITLE_BA)
            .withWage(VALID_WAGE_3000)
            .withPeriod(VALID_PERIOD_SUMMER)
            .build();

    // Manually added
    public static final InternshipItem GOOGLE_SWE = new InternshipItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_GOOGLE)
            .withJobTitle(VALID_JOB_TITLE_SWE)
            .withWage(VALID_WAGE_4000)
            .withPeriod(VALID_PERIOD_SUMMER)
            .build();
}
