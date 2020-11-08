package seedu.address.testutil;

import seedu.address.model.HospifyBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building Hospify objects.
 * Example usage: <br>
 *     {@code HospifyBook hb = new HospifyBuilder().withPatient("John", "Doe").build();}
 */
public class HospifyBuilder {

    private HospifyBook hospifyBook;

    public HospifyBuilder() {
        hospifyBook = new HospifyBook();
    }

    public HospifyBuilder(HospifyBook hospifyBook) {
        this.hospifyBook = hospifyBook;
    }

    /**
     * Adds a new {@code Patient} to the {@code HospifyBook} that we are building.
     */
    public HospifyBuilder withPatient(Patient patient) {
        hospifyBook.addPatient(patient);
        return this;
    }

    public HospifyBook build() {
        return hospifyBook;
    }
}
