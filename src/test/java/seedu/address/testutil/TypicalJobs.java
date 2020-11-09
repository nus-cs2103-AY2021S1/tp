package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_MAYBANK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.JobAddressBook;
import seedu.address.model.information.Job;

/**
 * A utility class containing a list of {@code Job} objects to be used in tests.
 */
public class TypicalJobs {

    public static final Job FACEBOOK = new JobBuilder().withJobTitle("Toilet Bowl Cleaner").withCompanyName("Facebook")
            .withAddress("1 Hacker Way, Menlo Park, CA 94025").withEmail("recruitment@facebook.com")
            .withPhone("67438807").withTags("Cleaner").withPriority("low").withVacancy("1").build();
    public static final Job APPLE = new JobBuilder().withJobTitle("Wall Painter").withCompanyName("Apple")
            .withAddress("1 Apple Park Way, Cupertino, California").withEmail("recruitment@apple.com")
            .withPhone("69272758").withTags("Cleaner").withPriority("low").withVacancy("3").build();
    public static final Job AMAZON = new JobBuilder().withJobTitle("Plant Waterer").withCompanyName("Amazon")
            .withAddress("16 Forest Way, Seattle, Washington").withEmail("recruitment@amazon.com")
            .withPhone("63210283").withTags("Cleaner").withPriority("low").withVacancy("2").build();
    public static final Job NETFLIX = new JobBuilder().withJobTitle("Glass Wiper").withCompanyName("Netflix")
            .withAddress("19 Netflix Lane, Los Gatos, California").withEmail("recruitment@netflix.com")
            .withPhone("61031282").withTags("Cleaner").withPriority("moderate").withVacancy("5").build();
    public static final Job GOOGLE = new JobBuilder().withJobTitle("Software Engineer").withCompanyName("Google")
            .withAddress("1600 Google Way, Mountain View, California").withEmail("recruitment@google.com")
            .withPhone("62492021").withTags("SE").withPriority("moderate").withVacancy("2").build();
    public static final Job NUS = new JobBuilder().withJobTitle("CS2103T Lecturer").withCompanyName("NUS")
            .withAddress("21 Lower Kent Ridge Rd, Singapore 119077").withEmail("recruitment@nus.edu.sg")
            .withPhone("62624417").withTags("Teaching").withPriority("high").withVacancy("1").build();

    // Manually added - Job's details found in {@code CommandTestUtil}
    public static final Job IRAS = new JobBuilder().withJobTitle(VALID_JOB_TITLE_IRAS)
            .withCompanyName(VALID_COMPANY_NAME_IRAS).withPhone(VALID_PHONE_IRAS)
            .withEmail(VALID_EMAIL_IRAS).withAddress(VALID_ADDRESS_IRAS).withTags(VALID_TAG_IRAS)
            .withPriority(VALID_PRIORITY_IRAS).withVacancy(VALID_VACANCY_IRAS).build();
    public static final Job MAYBANK = new JobBuilder().withJobTitle(VALID_JOB_TITLE_MAYBANK)
            .withCompanyName(VALID_COMPANY_NAME_MAYBANK).withPhone(VALID_PHONE_MAYBANK)
            .withEmail(VALID_EMAIL_MAYBANK).withAddress(VALID_ADDRESS_MAYBANK).withTags(VALID_TAG_MAYBANK)
            .withPriority(VALID_PRIORITY_MAYBANK).withVacancy(VALID_VACANCY_MAYBANK).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalJobs() {} // prevents instantiation

    /**
     * Returns a {@code JobAddressBook} with all the typical jobs.
     */
    public static JobAddressBook getTypicalJobAddressBook() {
        JobAddressBook jab = new JobAddressBook();
        for (Job job : getTypicalJobs()) {
            jab.addJob(job);
        }
        return jab;
    }

    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(FACEBOOK, APPLE, AMAZON, NETFLIX, GOOGLE, NUS));
    }
}
