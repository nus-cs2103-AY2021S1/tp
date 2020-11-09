package seedu.address.model.information.comparator;

import java.util.Comparator;

import seedu.address.model.information.Job;

/**
 * Compares any two {@code Job}'s {@code CompanyName} using the the supplied sorting criteria.
 */
public class JobCompanyComparator extends JobComparator implements Comparator<Job> {

    public static final String SORT_CRITERIA = "c";

    @Override
    public int compare(Job job1, Job job2) {

        String company1 = job1.getCompanyName().fullCompanyName.toLowerCase();
        String company2 = job2.getCompanyName().fullCompanyName.toLowerCase();

        return company1.compareTo(company2);
    }

    @Override
    public String toString() {
        return "by company name ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobCompanyComparator); // instanceof handles nulls
    }
}
