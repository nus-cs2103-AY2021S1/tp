package seedu.address.storage;

import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String id;
    private final JsonAdaptedClient client;
    private final JsonAdaptedHairdresser hairdresser;
    private final String dateOfAppt;
    private final String timeOfAppt;
    private final String appointmentStatus;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given {@code appointment}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("id") String id,
                                  @JsonProperty("client") JsonAdaptedClient client,
                                  @JsonProperty("hairdresser") JsonAdaptedHairdresser hairdresser,
                                  @JsonProperty("dateOfAppt") String dateOfAppt,
                                  @JsonProperty("timeOfAppt") String timeOfAppt,
                                  @JsonProperty("appointmentStatus") String appointmentStatus) {
        this.id = id;
        this.client = client;
        this.hairdresser = hairdresser;
        this.dateOfAppt = dateOfAppt;
        this.timeOfAppt = timeOfAppt;
        this.appointmentStatus = appointmentStatus;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        this.id = String.valueOf(source.getId());
        this.client = new JsonAdaptedClient(source.getClient());
        this.hairdresser = new JsonAdaptedHairdresser(source.getHairdresser());
        this.dateOfAppt = source.getDate().date.toString();
        this.timeOfAppt = source.getTime().time.truncatedTo(ChronoUnit.HOURS).toString();
        this.appointmentStatus = source.getAppointmentStatus().name();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentId.class.getSimpleName()));
        }
        final AppointmentId modelAid = new AppointmentId(id);
        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Client.class.getSimpleName()));
        }
        final Client modelClient = client.toModelType();

        if (hairdresser == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Hairdresser.class.getSimpleName()));
        }
        final Hairdresser modelHairdresser = hairdresser.toModelType();

        if (dateOfAppt == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentDate.class.getSimpleName()));
        }
        if (!AppointmentDate.isValidAppointmentDate(dateOfAppt)) {
            throw new IllegalValueException(AppointmentDate.MESSAGE_CONSTRAINTS);
        }
        final AppointmentDate modelAppointmentDate = new AppointmentDate(dateOfAppt);

        if (timeOfAppt == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentTime.class.getSimpleName()));
        }
        if (!AppointmentTime.isValidAppointmentTime(timeOfAppt)) {
            throw new IllegalValueException(AppointmentTime.MESSAGE_CONSTRAINTS);
        }
        final AppointmentTime modelAppointmentTime = new AppointmentTime(timeOfAppt);

        if (appointmentStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentStatus.class.getSimpleName()));
        }
        if (!AppointmentStatus.isValidAppointmentStatus(appointmentStatus)) {
            throw new IllegalValueException(AppointmentStatus.MESSAGE_CONSTRAINTS);
        }
        final AppointmentStatus modelAppointmentStatus = AppointmentStatus.valueOf(appointmentStatus);

        return new Appointment(modelAid, modelClient, modelHairdresser,
                modelAppointmentDate, modelAppointmentTime, modelAppointmentStatus);
    }

}

