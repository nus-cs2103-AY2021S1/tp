package seedu.address.testutil;

import seedu.address.model.PatientRecords;
import seedu.address.model.patient.Patient;
//@@author AB3
/**
 * A utility class to help with building CovigentApp objects.
 * Example usage: <br>
 *     {@code CovigentApp ab = new PatientRecordsBuilder().withPatient("John", "Doe").build();}
 */
public class PatientRecordsBuilder {

    private PatientRecords patientRecords;

    public PatientRecordsBuilder() {
        patientRecords = new PatientRecords();
    }

    public PatientRecordsBuilder(PatientRecords covigentApp) {
        this.patientRecords = covigentApp;
    }

    /**
     * Adds a new {@code Patient} to the {@code CovigentApp} that we are building.
     */
    public PatientRecordsBuilder withPatient(Patient patient) {
        patientRecords.addPatient(patient);
        return this;
    }

    public PatientRecords build() {
        return patientRecords;
    }
}
