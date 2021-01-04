package seedu.homerce.testutil.appointment;

import seedu.homerce.model.appointment.AppointmentTemp;
import seedu.homerce.model.appointment.TimeOfDay;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Date;

public class AppointmentTempBuilder {
    public static final Date DEFAULT_DATE = new Date("25-10-2020");
    public static final TimeOfDay DEFAULT_TIME = new TimeOfDay("1330");
    public static final Phone DEFAULT_CLIENT = new Phone("91234567");
    public static final ServiceCode DEFAULT_SERVICE_CODE = new ServiceCode("SC001");
    private Date date;
    private TimeOfDay timeOfDay;
    private Phone phone;
    private ServiceCode serviceCode;

    /**
     * Creates a {@code AppointmentTempBuilder} with the default details.
     */
    public AppointmentTempBuilder() {
        date = DEFAULT_DATE;
        timeOfDay = DEFAULT_TIME;
        phone = DEFAULT_CLIENT;
        serviceCode = DEFAULT_SERVICE_CODE;
    }
    /**
     * Initializes the AppointmentTempBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentTempBuilder(AppointmentTemp appointmentToCopy) {
        date = appointmentToCopy.getAppointmentDate();
        timeOfDay = appointmentToCopy.getAppointmentStartTime();
        phone = appointmentToCopy.getPhone();
        serviceCode = appointmentToCopy.getServiceCode();
    }

    /**
     * Sets the {@code Date} of the {@code AppointmentTemp} that we are building.
     */
    public AppointmentTempBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }
    /**
     * Sets the {@code TimeOfDay} of the {@code AppointmentTemp} that we are building.
     */
    public AppointmentTempBuilder withTimeOfDay(String timeOfDay) {
        this.timeOfDay = new TimeOfDay(timeOfDay);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code AppointmentTemp} that we are building.
     */
    public AppointmentTempBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code ServiceCode} of the {@code AppointmentTemp} that we are building.
     */
    public AppointmentTempBuilder withServiceCode(String serviceCode) {
        this.serviceCode = new ServiceCode(serviceCode);
        return this;
    }

    public AppointmentTemp build() {
        return new AppointmentTemp(date, timeOfDay, phone, serviceCode);
    }
}
