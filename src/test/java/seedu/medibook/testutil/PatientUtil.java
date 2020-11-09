package seedu.medibook.testutil;

import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;

import seedu.medibook.logic.commands.AddCommand;
import seedu.medibook.logic.commands.EditCommand;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.patient.Patient;

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
        sb.append(PREFIX_IC + patient.getIc().ic + " ");
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_DATE + patient.getDateOfBirthInputString() + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");

        if (patient.getEmail().isPresent()) {
            sb.append(PREFIX_EMAIL + patient.getStringEmail() + " ");
        }

        if (patient.getAddress().isPresent()) {
            sb.append(PREFIX_ADDRESS + patient.getStringAddress() + " ");
        }

        if (patient.getHeight().isPresent()) {
            sb.append(PREFIX_HEIGHT + patient.getStringHeight() + " ");
        }

        if (patient.getWeight().isPresent()) {
            sb.append(PREFIX_WEIGHT + patient.getStringWeight() + " ");
        }

        if (patient.getBloodType().isPresent()) {
            sb.append(PREFIX_BLOOD_TYPE + patient.getStringBloodType() + " ");
        }

        patient.getAllergies().stream().forEach(
            s -> sb.append(PREFIX_ALLERGY + s.tagName + " ")
        );

        patient.getConditions().stream().forEach(
            s -> sb.append(PREFIX_CONDITION + s.tagName + " ")
        );

        patient.getTreatments().stream().forEach(
            s -> sb.append(PREFIX_TREATMENT + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditCommand.EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getIc().ifPresent(ic -> sb.append(PREFIX_IC).append(ic.ic).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDateOfBirth()
            .ifPresent(dateOfBirth -> sb.append(PREFIX_DATE).append(dateOfBirth.getInputString()).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));

        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getHeight().ifPresent(height -> sb.append(PREFIX_HEIGHT).append(height.value).append(" "));
        descriptor.getWeight().ifPresent(weight -> sb.append(PREFIX_WEIGHT).append(weight.value).append(" "));
        descriptor.getBloodType().ifPresent(bloodType -> sb.append(PREFIX_BLOOD_TYPE)
                .append(bloodType.bloodType.label).append(" "));

        if (descriptor.getAllergies().isPresent()) {
            Set<Allergy> allergies = descriptor.getAllergies().get();
            if (allergies.isEmpty()) {
                sb.append(PREFIX_ALLERGY).append(" ");
            } else {
                allergies.forEach(s -> sb.append(PREFIX_ALLERGY).append(s.tagName).append(" "));
            }
        }

        if (descriptor.getConditions().isPresent()) {
            Set<Condition> conditions = descriptor.getConditions().get();
            if (conditions.isEmpty()) {
                sb.append(PREFIX_CONDITION).append(" ");
            } else {
                conditions.forEach(s -> sb.append(PREFIX_CONDITION).append(s.tagName).append(" "));
            }
        }

        if (descriptor.getTreatments().isPresent()) {
            Set<Treatment> treatments = descriptor.getTreatments().get();
            if (treatments.isEmpty()) {
                sb.append(PREFIX_TREATMENT).append(" ");
            } else {
                treatments.forEach(s -> sb.append(PREFIX_TREATMENT).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
