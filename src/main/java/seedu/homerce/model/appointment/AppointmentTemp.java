package seedu.homerce.model.appointment;

import static seedu.homerce.commons.util.CollectionUtil.requireAllNonNull;

import seedu.homerce.model.client.Phone;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Date;

/**
 * Temporary Appointment Object that contains the user-input phone number and Service Code.
 * Appointment object will be created using this object when appointment commands are executed.
 */
public class AppointmentTemp {
    private final Date appointmentDate;
    private final TimeOfDay timeOfDay;
    private final ServiceCode serviceCode;
    private final Phone phone;

    /**
     * Constructor for an Temporary Appointment object to be parsed in Appointment parsers.
     */
    public AppointmentTemp(Date appointmentDate, TimeOfDay timeOfDay, Phone phone, ServiceCode serviceCode) {
        requireAllNonNull(phone, serviceCode, appointmentDate, timeOfDay);
        this.appointmentDate = appointmentDate;
        this.timeOfDay = timeOfDay;
        this.phone = phone;
        this.serviceCode = serviceCode;
    }

    public Phone getPhone() {
        return phone;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public TimeOfDay getAppointmentStartTime() {
        return timeOfDay;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof AppointmentTemp)) {
            return false;
        } else {
            AppointmentTemp other = (AppointmentTemp) obj;
            return phone.equals(other.phone)
                && serviceCode.equals((other.serviceCode))
                && appointmentDate.equals(other.appointmentDate)
                && timeOfDay.equals(other.timeOfDay);
        }
    }
}
