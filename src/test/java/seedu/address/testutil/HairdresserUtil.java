package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import seedu.address.logic.commands.hairdresser.AddHairdresserCommand;
import seedu.address.logic.commands.hairdresser.EditHairdresserCommand.EditHairdresserDescriptor;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.specialisation.Specialisation;

/**
 * A utility class for Hairdresser.
 */
public class HairdresserUtil {

    /**
     * Returns an add command string for adding the {@code hairdresser}.
     */
    public static String getAddHairdresserCommand(Hairdresser hairdresser) {
        return AddHairdresserCommand.COMMAND_WORD + " " + getHairdresserDetails(hairdresser);
    }

    /**
     * Returns the part of command string for the given {@code hairdresser}'s details.
     */
    public static String getHairdresserDetails(Hairdresser hairdresser) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + hairdresser.getName().fullName + " ");
        sb.append(PREFIX_PHONE + hairdresser.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + hairdresser.getEmail().value + " ");
        sb.append(PREFIX_GENDER + hairdresser.getGender().toString() + " ");
        sb.append(PREFIX_TITLE + hairdresser.getTitle().value + " ");
        hairdresser.getSpecs().stream().forEach(
            s -> sb.append(PREFIX_SPECIALISATION + s.getSpecInString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditHairdresserDescriptor}'s details.
     */
    public static String getEditHairdresserDescriptorDetails(EditHairdresserDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.toString()).append(" "));
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.value).append(" "));
        if (descriptor.getSpecs().isPresent()) {
            Set<Specialisation> specialisations = descriptor.getSpecs().get();
            if (specialisations.isEmpty()) {
                sb.append(PREFIX_SPECIALISATION);
            } else {
                specialisations.forEach(s -> sb.append(PREFIX_SPECIALISATION).append(s.getSpecInString()).append(" "));
            }
        }
        return sb.toString();
    }
}
