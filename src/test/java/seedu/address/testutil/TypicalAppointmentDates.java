package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.appointment.AppointmentDate;

public class TypicalAppointmentDates {
    public static final AppointmentDate FUTURE_APPOINTMENT_DATE = getFutureDate();
    public static final String FUTURE_DATE_STRING = "2022-01-01";
    public static final String PAST_DATE_STRING = "2019-01-01";
    private static final LocalDate currentDate = LocalDate.now();

    public static AppointmentDate getPastDate() {
        AppointmentDate pastAppointmentDate = new AppointmentDate(PAST_DATE_STRING);
        assert pastAppointmentDate.date.isBefore(currentDate);
        return pastAppointmentDate;
    }

    public static AppointmentDate getFutureDate() {
        AppointmentDate futureAppointmentDate = new AppointmentDate(FUTURE_DATE_STRING);
        assert futureAppointmentDate.date.isAfter(currentDate);
        return futureAppointmentDate;
    }

}
