package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CliniCal;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment APP1 = new AppointmentBuilder().withPatientName("Alice Pauline")
            .withStartTime("2020-10-24 10:20").withEndTime("2020-10-24 10:50").build();

    public static final Appointment APP2 = new AppointmentBuilder().withPatientName("Benson Meier")
            .withStartTime("2020-10-25 14:00").withEndTime("2020-10-25 14:20").build();

    public static final Appointment APP3 = new AppointmentBuilder().withPatientName("Carl Kurz")
            .withStartTime("2020-10-25 15:50").withEndTime("2020-10-25 16:10").build();

    public static final Appointment APP4 = new AppointmentBuilder().withPatientName("Daniel Meier")
            .withStartTime("2020-10-25 16:20").withEndTime("2020-10-25 16:50").build();

    public static final Appointment APP5 = new AppointmentBuilder().withPatientName("Elle Meyer")
            .withStartTime("2020-10-25 17:00").withEndTime("2020-10-25 17:10").build();

    // Manually added - Appointment's details found in {@code CommandTestUtil}
    public static final Appointment FIRST_APP = new AppointmentBuilder().withPatientName(VALID_PATIENT_NAME_FIRST)
            .withStartTime(VALID_START_TIME_FIRST).withEndTime(VALID_END_TIME_FIRST).build();
    public static final Appointment SECOND_APP = new AppointmentBuilder().withPatientName(VALID_PATIENT_NAME_SECOND)
            .withStartTime(VALID_START_TIME_SECOND).withEndTime(VALID_END_TIME_SECOND).build();

    // Appointments for checking conflicts
    public static final Appointment CONFLICTING_APPOINTMENT1 = new AppointmentBuilder()
            .withPatientName("Conflict One").withStartTime("2020-03-10 01:00").withEndTime("2020-03-10 02:00").build();
    public static final Appointment CONFLICTING_APPOINTMENT2 = new AppointmentBuilder()
            .withPatientName("Conflict Two").withStartTime("2020-03-10 01:59").withEndTime("2020-03-10 02:59").build();

    public static final Appointment NOCONFLICT_APPOINTMENT1 = new AppointmentBuilder()
            .withPatientName("NoConflict One").withStartTime("2020-03-11 01:00").withEndTime("2020-03-11 02:00")
            .build();
    public static final Appointment NOCONFLICT_APPOINTMENT2 = new AppointmentBuilder()
            .withPatientName("NoConflict One").withStartTime("2020-03-11 02:00").withEndTime("2020-03-11 03:00")
            .build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code CliniCal} with all the typical appointments.
     */
    public static CliniCal getTypicalCliniCalWithAppointments() {
        CliniCal ab = new CliniCal();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APP1, APP2, APP3, APP4, APP5));
    }
}
