package seedu.address.model.information.comparator;

import java.util.Comparator;

import seedu.address.model.information.Job;

/**
 * Compares any two {@code Job}'s {@code Name} using the the supplied sorting criteria.
 */
public class JobTitleComparator extends JobComparator implements Comparator<Job> {

    public static final String SORT_CRITERIA = "n";

    @Override
    public int compare(Job job1, Job job2) {

        String title1 = job1.getJobTitle().fullName.toLowerCase();
        String title2 = job2.getJobTitle().fullName.toLowerCase();

        return title1.compareTo(title2);
    }

    @Override
    public String toString() {
        return "by job title ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobTitleComparator); // instanceof handles nulls
    }
}
