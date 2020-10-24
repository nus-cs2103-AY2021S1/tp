package seedu.address.testutil;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.util.VEventUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.Name;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class to help with building VEvent objects.
 */
public class VEventBuilder {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String DEFAULT_NAME = "Danny Williams";
    public static final String DEFAULT_START_TIME = "2020-02-02 14:20";
    public static final String DEFAULT_END_TIME = "2020-02-02 14:35";

    private String patientName;
    private String startTime;
    private String endTime;

    /**
     * Creates a {@code VEventBuilder} with the default details.
     */
    public VEventBuilder() {
        patientName = DEFAULT_NAME;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
    }

    /**
     * Initializes the VEventBuilder with the data of {@code vEventToCopy}.
     */
    public VEventBuilder(VEvent vEventToCopy) {
        patientName = vEventToCopy.getSummary().getValue();
        startTime = vEventToCopy.getDateTimeStart().getValue().toString();
        endTime = vEventToCopy.getDateTimeEnd().getValue().toString();
    }

    /**
     * Sets the {@code patientName} of the {@code VEvent} that we are building.
     */
    public VEventBuilder withPatientName(String patientName) {
        this.patientName = patientName;
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code VEvent} that we are building.
     */
    public VEventBuilder withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code VEvent} that we are building.
     */
    public VEventBuilder withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Builds {@code VEvent} with the given fields.
     */
    public VEvent build() {
        VEvent resultVEvent = new VEvent();
        resultVEvent.setSummary(patientName);
        resultVEvent.setDateTimeStart(LocalDateTime.parse(startTime, formatter));
        resultVEvent.setDateTimeEnd(LocalDateTime.parse(endTime, formatter));
        return resultVEvent;
    }

}
