package seedu.homerce.testutil.appointment;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.TimeOfDay;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.testutil.client.TypicalClients;
import seedu.homerce.testutil.service.TypicalServices;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final Date DEFAULT_DATE = new Date("25-10-2020");
    public static final TimeOfDay DEFAULT_TIME = new TimeOfDay("1330");
    public static final Client DEFAULT_CLIENT = TypicalClients.ALICE;
    public static final Service DEFAULT_SERVICE = TypicalServices.HAIR_TREATMENT;
    private Date date;
    private TimeOfDay timeOfDay;
    private Client client;
    private Service service;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        date = DEFAULT_DATE;
        timeOfDay = DEFAULT_TIME;
        client = DEFAULT_CLIENT;
        service = DEFAULT_SERVICE;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        date = appointmentToCopy.getAppointmentDate();
        timeOfDay = appointmentToCopy.getAppointmentStartTime();
        client = appointmentToCopy.getClient();
        service = appointmentToCopy.getService();
    }

    /**
     * Sets the {@code Date} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }
    /**
     * Sets the {@code TimeOfDay} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimeOfDay(String timeOfDay) {
        this.timeOfDay = new TimeOfDay(timeOfDay);
        return this;
    }

    /**
     * Sets the {@code Client} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    /**
     * Sets the {@code Service} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withService(Service service) {
        this.service = service;
        return this;
    }

    public Appointment build() {
        return new Appointment(date, timeOfDay, client, service);
    }
}
