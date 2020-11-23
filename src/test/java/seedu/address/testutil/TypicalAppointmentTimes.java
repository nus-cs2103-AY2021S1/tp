package seedu.address.testutil;

import seedu.address.model.appointment.AppointmentTime;

public class TypicalAppointmentTimes {
    public static final AppointmentTime VALID_APPOINTMENT_TIME = getValidTime();
    public static final AppointmentTime VALID_APPOINTMENT_TIME_2 = getValidTime2();
    public static final String TIME_STRING = "09:00";
    public static final String TIME_STRING_2 = "10:00";

    public static AppointmentTime getValidTime() {
        AppointmentTime appointmentTime = new AppointmentTime(TIME_STRING);
        return appointmentTime;
    }

    public static AppointmentTime getValidTime2() {
        AppointmentTime appointmentTime = new AppointmentTime(TIME_STRING_2);
        return appointmentTime;
    }

}
