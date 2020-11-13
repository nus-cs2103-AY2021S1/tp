package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ICNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Patient;

/**
 * A utility class for Patient.
 */
public class PatientUtil {

    /**
     * Returns an add command string for adding the {@code patient}.
     */
    public static String getAddCommand(Patient patient) {
        return AddCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");
        sb.append(PREFIX_ICNUMBER + patient.getIcNumber().value + " ");
        sb.append(PREFIX_ADDRESS + patient.getAddress().value + " ");
        sb.append(PREFIX_EMAIL + patient.getEmail().value + " ");
        sb.append(PREFIX_SEX + patient.getSex().value + " ");
        sb.append(PREFIX_BLOODTYPE + patient.getBloodType().type + " ");
        patient.getAllergies().stream().forEach(
            s -> sb.append(PREFIX_ALLERGY + s.allergyName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditCommand.EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getIcNumber().ifPresent(icNumber -> sb.append(PREFIX_ICNUMBER).append(icNumber.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getSex().ifPresent(sex -> sb.append(PREFIX_SEX).append(sex.value).append(" "));
        descriptor.getBloodType().ifPresent(bloodType -> sb.append(PREFIX_BLOODTYPE).append(bloodType.type)
                .append(" "));
        if (descriptor.getAllergies().isPresent()) {
            Set<Allergy> allergies = descriptor.getAllergies().get();
            if (allergies.isEmpty()) {
                sb.append(PREFIX_ALLERGY);
            } else {
                allergies.forEach(s -> sb.append(PREFIX_ALLERGY).append(s.allergyName).append(" "));
            }
        }
        return sb.toString();
    }
}
