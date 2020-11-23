package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.appointment.AppointmentDate;

public class TypicalAppointmentDates {
    public static final AppointmentDate PAST_APPOINTMENT_DATE = getPastDate();
    public static final AppointmentDate FUTURE_APPOINTMENT_DATE = getFutureDate();
    public static final AppointmentDate FUTURE_APPOINTMENT_DATE_2 = getFutureDate2();
    public static final String FUTURE_DATE_STRING = "2022-01-01";
    public static final String FUTURE_DATE_STRING_2 = "2022-01-02";
    public static final String PAST_DATE_STRING = "2019-01-01";

    public static AppointmentDate getPastDate() {
        final LocalDate currentDate = LocalDate.now();
        AppointmentDate pastAppointmentDate = new AppointmentDate(PAST_DATE_STRING);
        assert pastAppointmentDate.date.isBefore(currentDate);
        return pastAppointmentDate;
    }

    public static AppointmentDate getFutureDate() {
        final LocalDate currentDate = LocalDate.now();
        AppointmentDate futureAppointmentDate = new AppointmentDate(FUTURE_DATE_STRING);
        assert futureAppointmentDate.date.isAfter(currentDate);
        return futureAppointmentDate;
    }

    public static AppointmentDate getFutureDate2() {
        final LocalDate currentDate = LocalDate.now();
        AppointmentDate futureAppointmentDate = new AppointmentDate(FUTURE_DATE_STRING_2);
        assert futureAppointmentDate.date.isAfter(currentDate);
        return futureAppointmentDate;
    }

}
