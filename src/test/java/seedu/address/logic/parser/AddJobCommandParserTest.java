package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VACANCY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VACANCY_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VACANCY_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_MAYBANK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalJobs.IRAS;
import static seedu.address.testutil.TypicalJobs.MAYBANK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddJobCommand;
import seedu.address.model.information.Address;
import seedu.address.model.information.CompanyName;
import seedu.address.model.information.Email;
import seedu.address.model.information.Job;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Vacancy;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.JobBuilder;

public class AddJobCommandParserTest {
    private AddJobCommandParser parser = new AddJobCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Job expectedJob = new JobBuilder(MAYBANK).withTags(VALID_TAG_MAYBANK).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK
                + PHONE_DESC_MAYBANK + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));

        // multiple job titles - last job title accepted
        assertParseSuccess(parser, JOB_TITLE_DESC_IRAS + JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK
                + PHONE_DESC_MAYBANK + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));

        // multiple company names - last company name accepted
        assertParseSuccess(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_IRAS + COMPANY_NAME_DESC_MAYBANK
                + PHONE_DESC_MAYBANK + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_IRAS
                + PHONE_DESC_MAYBANK + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));

        // multiple emails - last email accepted
        assertParseSuccess(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_IRAS + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_IRAS + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));

        // multiple tags - all accepted
        Job expectedJobMultipleTags = new JobBuilder(MAYBANK).withTags(VALID_TAG_MAYBANK, VALID_TAG_IRAS).build();
        assertParseSuccess(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJobMultipleTags));

        // multiple priorities - last priority accepted
        assertParseSuccess(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + PRIORITY_DESC_IRAS
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));

        // multiple vacancies - last vacancy accepted
        assertParseSuccess(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + PRIORITY_DESC_MAYBANK
                + VACANCY_DESC_IRAS + VACANCY_DESC_MAYBANK,
                new AddJobCommand(expectedJob));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags, default moderate priority
        Job expectedJob = new JobBuilder(IRAS).withTags().withPriority("moderate").build();
        assertParseSuccess(parser, JOB_TITLE_DESC_IRAS + COMPANY_NAME_DESC_IRAS + PHONE_DESC_IRAS
                + EMAIL_DESC_IRAS + ADDRESS_DESC_IRAS + VACANCY_DESC_IRAS, new AddJobCommand(expectedJob));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJobCommand.MESSAGE_USAGE);

        // missing job title prefix
        assertParseFailure(parser, VALID_JOB_TITLE_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + VACANCY_DESC_MAYBANK, expectedMessage);

        // missing company name prefix
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + VALID_COMPANY_NAME_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + VACANCY_DESC_MAYBANK, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + VALID_PHONE_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + VACANCY_DESC_MAYBANK, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + VALID_EMAIL_MAYBANK + ADDRESS_DESC_MAYBANK + VACANCY_DESC_MAYBANK, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + VALID_ADDRESS_MAYBANK + VACANCY_DESC_MAYBANK, expectedMessage);

        // missing vacancy prefix
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
            + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + VALID_VACANCY_MAYBANK, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_JOB_TITLE_MAYBANK + VALID_COMPANY_NAME_MAYBANK + VALID_PHONE_MAYBANK
                + VALID_EMAIL_MAYBANK + VALID_ADDRESS_MAYBANK + VALID_VACANCY_MAYBANK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid job title
        assertParseFailure(parser, INVALID_JOB_TITLE_DESC + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS + PRIORITY_DESC_MAYBANK
                + VACANCY_DESC_MAYBANK,
                Name.MESSAGE_CONSTRAINTS);

        // invalid company name
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + EMPTY_COMPANY_NAME_DESC + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS + PRIORITY_DESC_MAYBANK
                + VACANCY_DESC_MAYBANK,
                CompanyName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + INVALID_PHONE_DESC
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS + PRIORITY_DESC_MAYBANK
                + VACANCY_DESC_MAYBANK,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + INVALID_EMAIL_DESC + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS + PRIORITY_DESC_MAYBANK
                + VACANCY_DESC_MAYBANK,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + INVALID_ADDRESS_DESC + TAG_DESC_MAYBANK + TAG_DESC_IRAS + PRIORITY_DESC_MAYBANK
                + VACANCY_DESC_MAYBANK,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + INVALID_TAG_DESC + TAG_DESC_MAYBANK
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS
                + INVALID_JOB_PRIORITY_DESC + VACANCY_DESC_MAYBANK,
                Priority.MESSAGE_CONSTRAINTS);

        // invalid vacancy
        assertParseFailure(parser, JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS
                + PRIORITY_DESC_MAYBANK + INVALID_VACANCY_DESC,
                Vacancy.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_JOB_TITLE_DESC + COMPANY_NAME_DESC_MAYBANK + PHONE_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + INVALID_ADDRESS_DESC + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + JOB_TITLE_DESC_MAYBANK + COMPANY_NAME_DESC_MAYBANK
                + PHONE_DESC_MAYBANK + EMAIL_DESC_MAYBANK + ADDRESS_DESC_MAYBANK + TAG_DESC_MAYBANK + TAG_DESC_IRAS
                + PRIORITY_DESC_MAYBANK + VACANCY_DESC_MAYBANK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJobCommand.MESSAGE_USAGE));
    }
}
