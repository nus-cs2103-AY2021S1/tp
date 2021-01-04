package seedu.homerce.model.appointment.uniquelist;

import java.util.Comparator;

import seedu.homerce.model.appointment.Appointment;

public class AppointmentComparator implements Comparator<Appointment> {
    @Override
    public int compare(Appointment t1, Appointment t2) {
        if (t1.getAppointmentDate().equals(t2.getAppointmentDate())) {
            return t1.getAppointmentStartTime().getLocalTime().compareTo(t2.getAppointmentStartTime().getLocalTime());
        } else {
            return t1.getAppointmentDate().getLocalDate().compareTo(t2.getAppointmentDate().getLocalDate());
        }
    }
}
