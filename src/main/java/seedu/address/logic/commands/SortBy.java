package seedu.address.logic.commands;
import java.util.Comparator;

import seedu.address.model.patient.Patient;


public class SortBy implements Comparator<Patient> {

    private String sortBy;

    public SortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int compare(Patient patient1, Patient patient2) {
        if (sortBy.equals("name")) {
            System.out.println("name");
            String name1 = patient1.getName().toString();
            String name2 = patient2.getName().toString();
            return name1.compareTo(name2);
        } else {
            System.out.print("nric");
            String nric1 = patient1.getNric().toString();
            String nric2 = patient2.getNric().toString();
            return nric1.compareTo(nric2);
        }
    }
}
