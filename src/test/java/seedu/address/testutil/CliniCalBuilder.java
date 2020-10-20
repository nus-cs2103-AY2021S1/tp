package seedu.address.testutil;

import seedu.address.model.CliniCal;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building CliniCal objects.
 * Example usage: <br>
 *     {@code CliniCal ab = new CliniCalBuilder().withPatient("John", "Doe").build();}
 */
public class CliniCalBuilder {

    private CliniCal cliniCal;

    public CliniCalBuilder() {
        cliniCal = new CliniCal();
    }

    public CliniCalBuilder(CliniCal cliniCal) {
        this.cliniCal = cliniCal;
    }

    /**
     * Adds a new {@code Patient} to the {@code CliniCal} that we are building.
     */
    public CliniCalBuilder withPatient(Patient patient) {
        cliniCal.addPatient(patient);
        return this;
    }

    public CliniCal build() {
        return cliniCal;
    }
}
