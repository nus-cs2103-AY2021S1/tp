package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACANCY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.PersonAddressBook;
import seedu.address.model.information.Job;
import seedu.address.model.information.Person;
import seedu.address.model.information.predicate.JobJobTitleContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonNameContainsKeywordsPredicate;
import seedu.address.testutil.EditJobDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Valid Person information
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_EXPERIENCE_AMY = "2.5";
    public static final String VALID_EXPERIENCE_BOB = "0.5";
    public static final String VALID_DATE_AMY = "29-05-18";
    public static final String VALID_DATE_BOB = "18-12-21";
    public static final String VALID_BLACKLIST_STATUS_AMY = "false";
    public static final String VALID_BLACKLIST_STATUS_BOB = "true";
    public static final String VALID_URL_LINK_AMY = "linkedin.com";
    public static final String VALID_URL_LINK_BOB = "facebok.com";
    public static final String VALID_SALARY_AMY = "16000";
    public static final String VALID_SALARY_BOB = "1800";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String EXPERIENCE_DESC_AMY = " " + PREFIX_EXPERIENCE + VALID_EXPERIENCE_AMY;
    public static final String EXPERIENCE_DESC_BOB = " " + PREFIX_EXPERIENCE + VALID_EXPERIENCE_BOB;
    public static final String DATE_DESC_AMY = " " + PREFIX_DATE_OF_APPLICATION + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + PREFIX_DATE_OF_APPLICATION + VALID_DATE_BOB;
    public static final String BLACKLIST_STATUS_DESC_AMY = " " + PREFIX_BLACKLIST + VALID_BLACKLIST_STATUS_AMY;
    public static final String BLACKLIST_STATUS_DESC_BOB = " " + PREFIX_BLACKLIST + VALID_BLACKLIST_STATUS_BOB;
    public static final String URL_LINK_DESC_AMY = " " + PREFIX_URL_LINK + VALID_URL_LINK_AMY;
    public static final String URL_LINK_DESC_BOB = " " + PREFIX_URL_LINK + VALID_URL_LINK_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String EMPTY_NAME_DESC = " " + PREFIX_NAME + ""; // Field cannot be empty
    public static final String EMPTY_COMPANY_NAME_DESC = " " + PREFIX_COMPANY_NAME + ""; // Field cannot be empty

    // Invalid information inputs
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_JOB_TITLE_DESC = " " + PREFIX_JOB_TITLE + "James&"; // '&' not allowed
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + "12"; // too short
    public static final String INVALID_EXPERIENCE_DESC = " " + PREFIX_EXPERIENCE + "-8.0";
    // negative numbers not allowed
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE_OF_APPLICATION + "11 Jun 2020"; // wrong format
    public static final String INVALID_BLACKLIST_STATUS_DESC = " " + PREFIX_BLACKLIST + "yes"; // not true or false
    public static final String INVALID_URL_LINK_DESC = " " + PREFIX_URL_LINK + "rubbish"; // not a url link
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "-8000"; // negative numbers not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_JOB_PRIORITY_DESC = " " + PREFIX_PRIORITY + "nonsense";
    //only 'low', 'moderate', 'high' allowed for priority
    public static final String INVALID_VACANCY_DESC = " " + PREFIX_VACANCY + "two"; // characters not allowed

    // Valid Job information
    public static final String VALID_JOB_TITLE_IRAS = "Tax Officer";
    public static final String VALID_JOB_TITLE_MAYBANK = "Bank Teller";
    public static final String VALID_COMPANY_NAME_IRAS = "IRAS";
    public static final String VALID_COMPANY_NAME_MAYBANK = "Maybank";
    public static final String VALID_PHONE_IRAS = "63568300";
    public static final String VALID_PHONE_MAYBANK = "66292265";
    public static final String VALID_EMAIL_IRAS = "taxenquiry@iras.gov.sg";
    public static final String VALID_EMAIL_MAYBANK = "enquiries@maybank.com.sg";
    public static final String VALID_ADDRESS_IRAS = "55 Newton Rd, Revenue House, Singapore 307987";
    public static final String VALID_ADDRESS_MAYBANK = "23 Serangoon Central, # B2 - 27, Singapore 556083";
    public static final String VALID_PRIORITY_IRAS = "high";
    public static final String VALID_PRIORITY_MAYBANK = "low";
    public static final String VALID_VACANCY_IRAS = "1";
    public static final String VALID_VACANCY_MAYBANK = "2";
    public static final String VALID_TAG_IRAS = "Tax";
    public static final String VALID_TAG_MAYBANK = "Banking";

    public static final String JOB_TITLE_DESC_IRAS = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_IRAS;
    public static final String JOB_TITLE_DESC_MAYBANK = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_MAYBANK;
    public static final String COMPANY_NAME_DESC_IRAS = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_IRAS;
    public static final String COMPANY_NAME_DESC_MAYBANK = " " + PREFIX_COMPANY_NAME + VALID_COMPANY_NAME_MAYBANK;
    public static final String PHONE_DESC_IRAS = " " + PREFIX_PHONE + VALID_PHONE_IRAS;
    public static final String PHONE_DESC_MAYBANK = " " + PREFIX_PHONE + VALID_PHONE_MAYBANK;
    public static final String EMAIL_DESC_IRAS = " " + PREFIX_EMAIL + VALID_EMAIL_IRAS;
    public static final String EMAIL_DESC_MAYBANK = " " + PREFIX_EMAIL + VALID_EMAIL_MAYBANK;
    public static final String ADDRESS_DESC_IRAS = " " + PREFIX_ADDRESS + VALID_ADDRESS_IRAS;
    public static final String ADDRESS_DESC_MAYBANK = " " + PREFIX_ADDRESS + VALID_ADDRESS_MAYBANK;
    public static final String PRIORITY_DESC_IRAS = " " + PREFIX_PRIORITY + VALID_PRIORITY_IRAS;
    public static final String PRIORITY_DESC_MAYBANK = " " + PREFIX_PRIORITY + VALID_PRIORITY_MAYBANK;
    public static final String VACANCY_DESC_IRAS = " " + PREFIX_VACANCY + VALID_VACANCY_IRAS;
    public static final String VACANCY_DESC_MAYBANK = " " + PREFIX_VACANCY + VALID_VACANCY_MAYBANK;
    public static final String TAG_DESC_IRAS = " " + PREFIX_TAG + VALID_TAG_IRAS;
    public static final String TAG_DESC_MAYBANK = " " + PREFIX_TAG + VALID_TAG_MAYBANK;

    // Valid sorting values
    public static final String VALID_TYPE_PERSON_NAME = "n";
    public static final String VALID_TYPE_PERSON_EXP = "exp";
    public static final String VALID_TYPE_PERSON_BLACKLIST = "bl";
    public static final String VALID_TYPE_PERSON_SAL = "sal";
    public static final String VALID_TYPE_PERSON_DOA = "doa";
    public static final String VALID_TYPE_JOB_TITLE = "n";
    public static final String VALID_TYPE_JOB_COMPANY = "c";
    public static final String VALID_TYPE_JOB_VACANCY = "v";
    public static final String VALID_TYPE_JOB_PRIORITY = "pr";
    public static final String VALID_SORT_ORDER_ASCENDING = "asc";
    public static final String VALID_SORT_ORDER_DESCENDING = "desc";

    public static final String SORT_TYPE_PERSON_NAME = " " + PREFIX_SORT_TYPE + VALID_TYPE_PERSON_NAME;
    public static final String SORT_TYPE_PERSON_EXP = " " + PREFIX_SORT_TYPE + VALID_TYPE_PERSON_EXP;
    public static final String SORT_TYPE_PERSON_BLACKLIST = " " + PREFIX_SORT_TYPE + VALID_TYPE_PERSON_BLACKLIST;
    public static final String SORT_TYPE_PERSON_SAL = " " + PREFIX_SORT_TYPE + VALID_TYPE_PERSON_SAL;
    public static final String SORT_TYPE_PERSON_DOA = " " + PREFIX_SORT_TYPE + VALID_TYPE_PERSON_DOA;
    public static final String SORT_TYPE_JOB_TITLE = " " + PREFIX_SORT_TYPE + VALID_TYPE_JOB_TITLE;
    public static final String SORT_TYPE_JOB_COMPANY = " " + PREFIX_SORT_TYPE + VALID_TYPE_JOB_COMPANY;
    public static final String SORT_TYPE_JOB_VACANCY = " " + PREFIX_SORT_TYPE + VALID_TYPE_JOB_VACANCY;
    public static final String SORT_TYPE_JOB_PRIORITY = " " + PREFIX_SORT_TYPE + VALID_TYPE_JOB_PRIORITY;
    public static final String SORT_ORDER_ASCENDING = " " + PREFIX_SORT_ORDER + VALID_SORT_ORDER_ASCENDING;
    public static final String SORT_ORDER_DESCENDING = " " + PREFIX_SORT_ORDER + VALID_SORT_ORDER_DESCENDING;

    // Invalid sorting inputs
    public static final String INVALID_SORT_ORDER_DESC = " " + PREFIX_SORT_ORDER + "high to low"; // only asc or desc

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditJobCommand.EditJobDescriptor DESC_IRAS;
    public static final EditJobCommand.EditJobDescriptor DESC_MAYBANK;

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withDateOfApplication(VALID_DATE_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withExperience(VALID_EXPERIENCE_AMY).withTags(VALID_TAG_FRIEND)
                .withBlacklistStatus(VALID_BLACKLIST_STATUS_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withDateOfApplication(VALID_DATE_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withExperience(VALID_EXPERIENCE_BOB).withUrlLink(VALID_URL_LINK_BOB).withSalary(VALID_SALARY_BOB)
                .withBlacklistStatus(VALID_BLACKLIST_STATUS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    static {
        DESC_IRAS = new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_IRAS)
                .withPhone(VALID_PHONE_IRAS).withEmail(VALID_EMAIL_IRAS).withAddress(VALID_ADDRESS_IRAS)
                .withPriority(VALID_PRIORITY_IRAS).withTags(VALID_TAG_IRAS).withVacancy(VALID_VACANCY_IRAS).build();
        DESC_MAYBANK = new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_MAYBANK)
                .withPhone(VALID_PHONE_MAYBANK).withEmail(VALID_EMAIL_MAYBANK).withAddress(VALID_ADDRESS_MAYBANK)
                .withPriority(VALID_PRIORITY_MAYBANK).withTags(VALID_TAG_MAYBANK)
                .withVacancy(VALID_VACANCY_MAYBANK).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PersonAddressBook expectedAddressBook = new PersonAddressBook(actualModel.getPersonAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getPersonAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s person address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new PersonNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the job at the given {@code targetIndex} in the
     * {@code model}'s job address book.
     */
    public static void showJobAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredJobList().size());

        Job job = model.getFilteredJobList().get(targetIndex.getZeroBased());
        final String[] splitName = job.getJobTitle().fullName.split("\\s+");
        model.updateFilteredJobList(new JobJobTitleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredJobList().size());
    }
}
