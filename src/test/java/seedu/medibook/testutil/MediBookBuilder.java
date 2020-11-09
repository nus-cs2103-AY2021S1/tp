package seedu.medibook.testutil;

import seedu.medibook.model.MediBook;
import seedu.medibook.model.patient.Patient;

/**
 * A utility class to help with building MediBook objects.
 * Example usage: <br>
 *     {@code MediBook mb = new MediBookBuilder().withPatient("John", "Doe").build();}
 */
public class MediBookBuilder {

    private MediBook mediBook;

    public MediBookBuilder() {
        mediBook = new MediBook();
    }

    public MediBookBuilder(MediBook mediBook) {
        this.mediBook = mediBook;
    }

    /**
     * Adds a new {@code Patient} to the {@code MediBook} that we are building.
     */
    public MediBookBuilder withPatient(Patient patient) {
        mediBook.addPatient(patient);
        return this;
    }

    public MediBook build() {
        return mediBook;
    }
}
