package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.CommandParserTestUtil;
import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.person.Person;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person) + CommandTestUtil.POLICY_NAME_DESC_AMY;
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getClientSources().stream().forEach(
            s -> sb.append(PREFIX_CLIENTSOURCE + s.clientSourceName + " ")
        );
        sb.append(PREFIX_NOTE + person.getNote().noteName + " ");
        sb.append(PREFIX_PRIORITY + person.getPriority().value);
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
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getClientSources().isPresent()) {
            Set<ClientSource> clientSources = descriptor.getClientSources().get();
            clientSources.forEach(s -> sb.append(PREFIX_CLIENTSOURCE).append(s.clientSourceName).append(" "));
        }
        descriptor.getNote().ifPresent(note -> sb.append(PREFIX_NOTE).append(note.noteName).append(" "));
        descriptor.getPriority().ifPresent(priority -> sb.append(PREFIX_PRIORITY).append(priority.value));
        return sb.toString();
    }
}
