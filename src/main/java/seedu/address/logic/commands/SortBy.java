package seedu.address.logic.commands;
import java.util.Comparator;

import seedu.address.model.patient.Patient;

/**
 * A comparator that takes in a predicate to compare patients
 * and sort in ascending order based on the predicate.
 */
public class SortBy implements Comparator<Patient> {

    private String predicate;

    public SortBy(String predicate) {
        this.predicate = predicate;
    }

    /**
     * Compare method that compares based on the predicate provided.
     */
    public int compare(Patient patient1, Patient patient2) {
        if (predicate.equals("name")) {
            String name1 = patient1.getName().toString().toLowerCase();
            String name2 = patient2.getName().toString().toLowerCase();
            return name1.compareTo(name2);
        } else {
            String nric1 = patient1.getNric().toString().toLowerCase();
            String nric2 = patient2.getNric().toString().toLowerCase();
            return nric1.compareTo(nric2);
        }
    }
}
