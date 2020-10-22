package seedu.address.commons.util;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.model.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


/**
 * Utility methods for manipulation of Appointment and VEvent objects.
 */
public class VEventUtil {

    /**
     * Maps all appointments in @param appointments to a list of vEvents
     */
    public static ArrayList<VEvent> appsToVEventsMapper(List<Appointment> appointments) {
        ArrayList<VEvent> resultVEventList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            resultVEventList.add(appToVEventMapper(appointment));
        }
        return resultVEventList;
    }

    /**
     * Maps all vEvents in @param vEvents to a list of appointments
     */
    public static ArrayList<Appointment> vEventsToAppsMapper(List<VEvent> vEvents) {
        ArrayList<Appointment> resultAppList = new ArrayList<>();
        for (VEvent vEvent : vEvents) {
            resultAppList.add(VEventUtil.vEventToAppMapper(vEvent));
        }
        return resultAppList;
    }

    /**
     * Maps an appointment to vEvent
     */
    public static VEvent appToVEventMapper(Appointment appToMap) {
        VEvent resultVEvent = new VEvent();
        resultVEvent.setSummary(appToMap.getPatientName());
        resultVEvent.setDateTimeStart(appToMap.getStartTime());
        resultVEvent.setDateTimeEnd(appToMap.getEndTime());
//        resultVEvent.setUniqueIdentifier(appToMap.getId());

//        if (appToMap.getRecurrenceType() == RecurrenceType.DAILY) {
//            resultVEvent.setRecurrenceRule(DAILY_RECUR_RULE_STRING);
//        } else if (appToMap.getRecurrenceType() == RecurrenceType.WEEKLY) {
//            resultVEvent.setRecurrenceRule(WEEKLY_RECUR_RULE_STRING);
//        } else if (appToMap.getRecurrenceType() == RecurrenceType.NONE) {
//            resultVEvent.setRecurrenceRule(NONE_RECUR_RULE_STRING);
//        }

//        resultVEvent.withCategories(appToMap.getColorCategory());

        return resultVEvent;
    }

    /**
     * Maps a vEvent to appointment
     */
    public static Appointment vEventToAppMapper(VEvent vEvent) {
        Appointment resultAppointment = new Appointment();
        String patientName = vEvent.getSummary().getValue();
        LocalDateTime startDateTime = LocalDateTime.parse(vEvent.getDateTimeStart().getValue().toString());
        LocalDateTime endDateTime = LocalDateTime.parse(vEvent.getDateTimeEnd().getValue().toString());
        String uniqueIdentifier = vEvent.getUniqueIdentifier().getValue();
        resultAppointment.setPatientName(patientName);
        resultAppointment.setStartTime(startDateTime);
        resultAppointment.setEndTime(endDateTime);
//        resultAppointment.setUniqueIdentifier(uniqueIdentifier);

//        RecurrenceRule currentRule = vEvent.getRecurrenceRule();
//        if (currentRule.toString().contains("DAILY")) {
//            resultAppointment.setRecurrenceType(RecurrenceType.DAILY);
//        } else if (currentRule.toString().contains("WEEKLY")) {
//            resultAppointment.setRecurrenceType(RecurrenceType.WEEKLY);
//        } else {
//            resultAppointment.setRecurrenceType(RecurrenceType.NONE);
//        }

//        String colorCategory = vEvent.getCategories().get(0).getValue().get(0);
//        resultAppointment.setColorCategory(colorCategory);

        return resultAppointment;
    }

    /**
     * Compares between 2 vEvents to see whether they are the same. Attributes used to determine this include
     * patient name, start, and end time
     * @param vEventOne first event to be compared
     * @param vEventTwo second event to be compared
     * @return true if both vEvents are the same
     */
    public static boolean isSameVEvent(VEvent vEventOne, VEvent vEventTwo) {
        return vEventOne.getSummary().equals(vEventTwo.getSummary())
                && vEventOne.getDateTimeStart().equals(vEventTwo.getDateTimeStart())
                && vEventOne.getDateTimeEnd().equals(vEventTwo.getDateTimeEnd());
    }

}