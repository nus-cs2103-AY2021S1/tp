package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL_LINK;

import java.util.Set;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.model.information.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddPersonCommand(Person person) {
        return AddPersonCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_EXPERIENCE + person.getExperience().toString() + " ");
        sb.append(PREFIX_DATE_OF_APPLICATION + person.getDateOfApplication().dateString + " ");
        person.getAddressOptional().ifPresent(address -> sb.append(PREFIX_SALARY + address.value + " "));
        person.getUrlLinkOptional().ifPresent(link -> sb.append(PREFIX_URL_LINK + link.value + " "));
        person.getSalaryOptional().ifPresent(salary -> sb.append(PREFIX_SALARY + salary.toString() + " "));
        sb.append(PREFIX_BLACKLIST + person.getBlacklistStatus().toString() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getExperience().ifPresent(experience -> sb.append(PREFIX_EXPERIENCE)
                .append(experience.toString()).append(" "));
        descriptor.getDateOfApplication().ifPresent(date -> sb.append(PREFIX_DATE_OF_APPLICATION)
                .append(date.dateString).append(" "));
        descriptor.getBlackListStatus().ifPresent(status -> sb
                .append(PREFIX_BLACKLIST).append(status.toString()).append(" "));
        descriptor.getAddressOptional().ifPresent(addressOptional -> {
            sb.append(PREFIX_ADDRESS);
            addressOptional.ifPresent(address -> sb.append(address.value));
            sb.append(" ");
            }
        );
        descriptor.getUrlLinkOptional().ifPresent(linkOptional -> {
            sb.append(PREFIX_URL_LINK);
            linkOptional.ifPresent(link -> sb.append(link.value));
            sb.append(" ");
            }
        );
        descriptor.getSalaryOptional().ifPresent(salaryOptional -> {
            sb.append(PREFIX_SALARY);
            salaryOptional.ifPresent(salary -> sb.append(salary.toString()));
            sb.append(" ");
            }
        );
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG + " ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
