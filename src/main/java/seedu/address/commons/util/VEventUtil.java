package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.List;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.model.appointment.Appointment;

//Referenced from
//https://github.com/AY1920S1-CS2103T-W13-2/main/blob/master/src/main/java/seedu/address/commons/util/EventUtil.java

/**
 * Maps Appointment objects to VEvent objects
 */
public class VEventUtil {

    /**
     * Maps all Appointment objects in @param appointments to a list of VEvent objects
     */
    public static ArrayList<VEvent> appsToVEventsMapper(List<? extends Appointment> appointments) {
        ArrayList<VEvent> resultVEventList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            resultVEventList.add(appToVEventMapper(appointment));
        }
        return resultVEventList;
    }

    /**
     * Maps an Appointment object to a VEvent object
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
     * Returns true if {@code vEventOne} and {@code vEventTwo} are the same by comparing
     * summary, starting time, and ending time
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
