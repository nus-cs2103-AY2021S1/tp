package seedu.address.testutil;

import seedu.address.model.allergy.Allergy;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.patient.Sex;
import seedu.address.model.tag.ColorTag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "Danny Williams";
    public static final String DEFAULT_START_TIME = "2020-02-02 14:20";
    public static final String DEFAULT_END_TIME = "2020-02-02 14:35";

    private Name patientName;
    private AppointmentDateTime startTime;
    private AppointmentDateTime endTime;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        patientName = new Name(DEFAULT_NAME);
        startTime = new AppointmentDateTime(DEFAULT_START_TIME);
        endTime = new AppointmentDateTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        patientName = appointmentToCopy.getPatientName();
        startTime = appointmentToCopy.getStartTime();
        endTime = appointmentToCopy.getEndTime();
    }

    /**
     * Sets the {@code patientName} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientName(String patientName) {
        this.patientName = new Name(patientName);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartTime(String startTime) {
        this.startTime = new AppointmentDateTime(startTime);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withEndTime(String endTime) {
        this.endTime = new AppointmentDateTime(endTime);
        return this;
    }

    /**
     * Builds {@code Appointment} with the given fields.
     */
    public Appointment build() {
        return new Appointment(patientName, startTime, endTime);
    }

}
