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

import jfxtras.icalendarfx.components.VEvent;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalVEvents {

    public static final VEvent VEVENT1 = new VEventBuilder().withPatientName("Alice Pauline")
            .withStartTime("2020-10-24 10:20").withEndTime("2020-10-24 10:50").build();

    public static final VEvent VEVENT2 = new VEventBuilder().withPatientName("Benson Meier")
            .withStartTime("2020-10-25 14:00").withEndTime("2020-10-25 14:20").build();

    public static final VEvent VEVENT3 = new VEventBuilder().withPatientName("Carl Kurz")
            .withStartTime("2020-10-25 15:50").withEndTime("2020-10-25 16:10").build();

    public static final VEvent VEVENT4 = new VEventBuilder().withPatientName("Daniel Meier")
            .withStartTime("2020-10-25 16:20").withEndTime("2020-10-25 16:50").build();

    public static final VEvent VEVENT5 = new VEventBuilder().withPatientName("Elle Meyer")
            .withStartTime("2020-10-25 17:00").withEndTime("2020-10-24 17:10").build();

    // Manually added - VEvent's details found in {@code CommandTestUtil}
    public static final VEvent FIRST_VEVENT = new VEventBuilder().withPatientName(VALID_PATIENT_NAME_FIRST)
            .withStartTime(VALID_START_TIME_FIRST).withEndTime(VALID_END_TIME_FIRST).build();
    public static final VEvent SECOND_VEVENT = new VEventBuilder().withPatientName(VALID_PATIENT_NAME_SECOND)
            .withStartTime(VALID_START_TIME_SECOND).withEndTime(VALID_END_TIME_SECOND).build();

    private TypicalVEvents() {} // prevents instantiation

    //    /**
    //     * Returns an {@code CliniCal} with all the typical appointments.
    //     */
    //    public static CliniCal getTypicalCliniCal() {
    //        CliniCal ab = new CliniCal();
    //        for (VEvent vEvent : getTypicalVEvents()) {
    //            ab.addVEvent(vEvent);
    //        }
    //        return ab;
    //    }

    public static List<VEvent> getTypicalVEvents() {
        return new ArrayList<>(Arrays.asList(VEVENT1, VEVENT2, VEVENT3, VEVENT4, VEVENT5));
    }
}
