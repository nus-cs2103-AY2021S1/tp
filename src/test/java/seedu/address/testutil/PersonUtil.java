package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_ADDRESS;

import java.util.Set;

import seedu.address.logic.commands.contactcommands.AddCommand;
import seedu.address.logic.commands.contactcommands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.TagName;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM_ADDRESS + person.getTelegramAddress().value + " ");
        person.getTagNames().stream().forEach(tagName -> sb.append(PREFIX_TAG + tagName.tagName + " "));
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
        descriptor.getTelegramAddress().ifPresent(telegramAddress -> sb.append(PREFIX_TELEGRAM_ADDRESS)
                .append(telegramAddress.value).append(" "));
        if (descriptor.getTagNames().isPresent()) {
            Set<TagName> tagNames = descriptor.getTagNames().get();
            if (tagNames.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tagNames.forEach(tagName -> sb.append(PREFIX_TAG).append(tagName.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
