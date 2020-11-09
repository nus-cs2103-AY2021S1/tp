package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACANCY;

import java.util.Set;

import seedu.address.logic.commands.AddJobCommand;
import seedu.address.logic.commands.EditJobCommand.EditJobDescriptor;
import seedu.address.model.information.Job;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Job.
 */
public class JobUtil {

    /**
     * Returns an add command string for adding the {@code job}.
     */
    public static String getAddJobCommand(Job job) {
        return AddJobCommand.COMMAND_WORD + " " + getJobDetails(job);
    }

    /**
     * Returns the part of command string for the given {@code job}'s details.
     */
    public static String getJobDetails(Job job) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_JOB_TITLE + job.getJobTitle().fullName + " ");
        sb.append(PREFIX_COMPANY_NAME + job.getCompanyName().fullCompanyName + " ");
        sb.append(PREFIX_PHONE + job.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + job.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + job.getAddress().value + " ");
        job.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        sb.append(PREFIX_PRIORITY + job.getPriority().value + " ");
        sb.append(PREFIX_VACANCY + job.getVacancy().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditJobDescriptor}'s details.
     */
    public static String getEditJobDescriptorDetails(EditJobDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getJobTitle().ifPresent(
            jobTitle -> sb.append(PREFIX_JOB_TITLE).append(jobTitle.fullName).append(" "));
        descriptor.getCompanyName().ifPresent(
            companyName -> sb.append(PREFIX_COMPANY_NAME).append(companyName.fullCompanyName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG + " ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getPriority().ifPresent(priority -> sb.append(PREFIX_PRIORITY).append(priority.value).append(" "));
        descriptor.getVacancy().ifPresent(vacancy -> sb.append(PREFIX_VACANCY).append(vacancy.value).append(" "));
        return sb.toString();
    }
}
