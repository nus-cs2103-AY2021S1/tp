package seedu.address.storage;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.patient.Appointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private final String appointmentTime;
    private final String appointmentDescription;

    /**
     * Constructs a {@code JsonAdaptedAppointment}
     * with the given {@code appointment}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointment) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(appointment, PREFIX_DESCRIPTION);
        String timing = argMultimap.getPreamble().trim();
        String description;
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
        } else {
            description = "";
        }
        appointmentTime = timing;
        appointmentDescription = description;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        String appointment = source.toString();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(appointment, PREFIX_DESCRIPTION);
        String timing = argMultimap.getPreamble().trim();
        String description;
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
        } else {
            description = "";
        }
        appointmentTime = timing;
        appointmentDescription = description;
    }

    @JsonValue
    public String getAppointmentName() {
        return appointmentTime + " " + PREFIX_DESCRIPTION + appointmentDescription;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (!Appointment.isValidDateTime(appointmentTime)) {
            logger.warning("Appointment toModelType Error!");
            throw new IllegalValueException(Appointment.MESSAGE_CONSTRAINTS);
        }
        return new Appointment().setTime(appointmentTime).setDescription(appointmentDescription);
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

}
