package seedu.homerce.storage.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;

public class JsonSerializableAppointmentManager {
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointments list contains duplicate appointment(s).";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentManager } with the given appointments.
     */
    @JsonCreator
    public JsonSerializableAppointmentManager(@JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyAppointmentManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAppointmentManager}.
     */
    public JsonSerializableAppointmentManager(ReadOnlyAppointmentManager source) {
        appointments.addAll(source.getAppointmentList()
                .stream().map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this AppointmentManager into the model's {@code AppointmentManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentManager toModelType() throws IllegalValueException {
        AppointmentManager appointmentManager = new AppointmentManager();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (appointmentManager.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointmentManager.addAppointment(appointment);
        }
        return appointmentManager;
    }
}
