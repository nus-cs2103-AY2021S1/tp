package seedu.address.testutil;

import seedu.address.model.CliniCal;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ICNUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ICNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_PICTURE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_PICTURE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment APP1 = new AppointmentBuilder().withPatientName("Alice Pauline")
            .withStartTime("2020-10-24T10:20").withEndTime("2020-10-24T10:50").build();

    public static final Appointment APP2 = new AppointmentBuilder().withPatientName("Benson Meier")
            .withStartTime("2020-10-25T14:00").withEndTime("2020-10-25T14:20").build();

    public static final Appointment APP3 = new AppointmentBuilder().withPatientName("Carl Kurz")
            .withStartTime("2020-10-25T15:50").withEndTime("2020-10-25T16:10").build();

    public static final Appointment APP4 = new AppointmentBuilder().withPatientName("Daniel Meier")
            .withStartTime("2020-10-25T16:20").withEndTime("2020-10-25T16:50").build();

    public static final Appointment APP5 = new AppointmentBuilder().withPatientName("Elle Meyer")
            .withStartTime("2020-10-25T17:00").withEndTime("2020-10-24T17:10").build();

    // Manually added - Appointment's details found in {@code CommandTestUtil}
    public static final Appointment FIRST = new AppointmentBuilder().withPatientName(VALID_PATIENT_NAME_FIRST)
            .withStartTime(VALID_START_TIME_FIRST).withEndTime(VALID_END_TIME_FIRST).build();
    public static final Appointment SECOND = new AppointmentBuilder().withPatientName(VALID_PATIENT_NAME_SECOND)
            .withStartTime(VALID_START_TIME_SECOND).withEndTime(VALID_END_TIME_SECOND).build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code CliniCal} with all the typical appointments.
     */
    public static CliniCal getTypicalCliniCal() {
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
