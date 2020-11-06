package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

//import java.util.Set;
import seedu.address.logic.commands.contactlistcommands.AddContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactDescriptor;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.EditModuleDescriptor;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

import java.util.Set;
//import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Contact person) {
        return AddContactCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Contact contact) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + contact.getName().toString() + " ");
        sb.append(PREFIX_EMAIL + contact.getEmail().toString() + " ");
        if (contact.getTelegram().isPresent()) {
            sb.append(PREFIX_TELEGRAM + contact.getTelegram().get().toString() + " ");
        }
        contact.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditContactDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditContactDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.toString()).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.toString()).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(PREFIX_TELEGRAM).append(telegram.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        // descriptor.getZoomLink().ifPresent(zoomLink -> sb.append(PREFIX_EMAIL).append(zoomLink.value).append(" "));
        return sb.toString();
    }
}
