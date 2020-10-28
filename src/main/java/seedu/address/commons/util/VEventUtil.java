package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.List;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.model.appointment.Appointment;


/**
 * Utility methods for manipulation of Appointment and VEvent objects.
 */
public class VEventUtil {

    /**
     * Maps all appointments in @param appointments to a list of vEvents
     */
    public static ArrayList<VEvent> appsToVEventsMapper(List<? extends Appointment> appointments) {
        ArrayList<VEvent> resultVEventList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            resultVEventList.add(appToVEventMapper(appointment));
        }
        return resultVEventList;
    }

    /**
     * Maps an appointment to vEvent
     */
    public static VEvent appToVEventMapper(Appointment appToMap) {
        VEvent resultVEvent = new VEvent();
        resultVEvent.setSummary(appToMap.getPatientName().fullName);
        resultVEvent.setDateTimeStart(appToMap.getStartTime().dateTime);
        resultVEvent.setDateTimeEnd(appToMap.getEndTime().dateTime);
        resultVEvent.setUniqueIdentifier();

        return resultVEvent;
    }

    /**
     * Compares between 2 vEvents to see whether they are the same. Attributes used to determine this include
     * patient name, start, and end time
     *
     * @param vEventOne first event to be compared
     * @param vEventTwo second event to be compared
     * @return true if both vEvents are the same
     */
    public static boolean isSameVEvent(VEvent vEventOne, VEvent vEventTwo) {
        if (vEventOne == vEventTwo) {
            return true;
        }

        return vEventOne != null
                && vEventTwo != null
                && vEventOne.getSummary().equals(vEventTwo.getSummary())
                && vEventOne.getDateTimeStart().equals(vEventTwo.getDateTimeStart())
                && vEventOne.getDateTimeEnd().equals(vEventTwo.getDateTimeEnd());
    }

}
