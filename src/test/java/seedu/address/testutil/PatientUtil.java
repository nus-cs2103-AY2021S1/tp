package seedu.address.testutil;

import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_COMMENTS;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PERIOD_OF_STAY;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP;

import seedu.address.logic.commands.patient.AddPatientCommand;
import seedu.address.logic.commands.patient.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.patient.Patient;

/**
 * A utility class for Patient.
 */
public class PatientUtil {

    /**
     * Returns an add command string for adding the {@code Patient}.
     */
    public static String getAddPatientCommand(Patient patient) {
        return AddPatientCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code Patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_TEMP + patient.getTemperature().toString() + " ");
        sb.append(PREFIX_PERIOD_OF_STAY + patient.getPeriodOfStay().toString() + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");
        sb.append(PREFIX_AGE + patient.getAge().toString());
        return sb.toString();
    }


    public static String getEditPatientDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getTemperature().ifPresent(temperature -> sb.append(PREFIX_TEMP).append(temperature.getValue())
                                    .append(" "));
        descriptor.getPeriodOfStay().ifPresent(periodOfStay -> sb.append(PREFIX_PERIOD_OF_STAY)
                                    .append(periodOfStay.toString()).append(" "));
        descriptor.getAge().ifPresent(age -> sb.append(PREFIX_AGE).append(age.getValue()).append(" "));
        descriptor.getComment().ifPresent(comment -> sb.append(PREFIX_COMMENTS).append(comment.value).append(" "));
        return sb.toString();
    }
}
