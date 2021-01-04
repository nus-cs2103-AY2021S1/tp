package seedu.homerce.model.appointment.predicate;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.function.Predicate;

import seedu.homerce.model.appointment.Appointment;

public class AppointmentWeekPredicate implements Predicate<Appointment> {
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private final LocalDate startOfWeekDate;
    private final LocalDate endOfWeekDate;

    /**
     * Creates a Predicate that filters appointments such that only those in the same week as
     * {@code referenceDate} will be displayed.
     */
    public AppointmentWeekPredicate(Calendar calendar) {
        requireNonNull(calendar);
        this.startOfWeekDate = calculateStartDateOfWeek(calendar);
        this.endOfWeekDate = calculateEndDateOfWeek(calendar);
    }

    @Override
    public boolean test(Appointment appointment) {
        return (startOfWeekDate.isBefore(appointment.getAppointmentDate().getLocalDate())
            || startOfWeekDate.isEqual(appointment.getAppointmentDate().getLocalDate()))
            && (endOfWeekDate.isAfter(appointment.getAppointmentDate().getLocalDate())
            || endOfWeekDate.isEqual(appointment.getAppointmentDate().getLocalDate()));
    }

    /**
     * Get the start date of the current week.
     * @param inputCalendar the calendar used to determine the start date.
     * @return a LocalDate representing the start date of the current week.
     */
    public LocalDate calculateStartDateOfWeek(Calendar inputCalendar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputCalendar.getTime());
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDate.ofInstant(calendar.toInstant(), zid);
    }

    /**
     *Get the end date of the current week.
     * @param inputCalendar the calendar used to determine the end date.
     * @return a LocalDate representing the end date of the current week.
     */
    public LocalDate calculateEndDateOfWeek(Calendar inputCalendar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputCalendar.getTime());
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDate.ofInstant(calendar.toInstant(), zid);
    }

    @Override
    public String toString() {
        return startOfWeekDate.format(FORMAT_OUTPUT) + " to " + endOfWeekDate.format(FORMAT_OUTPUT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentWeekPredicate // instanceof handles nulls
            && startOfWeekDate.equals(((AppointmentWeekPredicate) other).startOfWeekDate)
            && endOfWeekDate.equals(((AppointmentWeekPredicate) other).endOfWeekDate)); // state check
    }
}
