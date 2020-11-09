package seedu.address.model.information.comparator;

import java.util.Comparator;

import seedu.address.model.information.Job;

/**
 * Compares any two {@code Job}'s {@code Vacancy} using the the supplied sorting criteria.
 */
public class JobVacancyComparator extends JobComparator implements Comparator<Job> {

    public static final String SORT_CRITERIA = "v";

    @Override
    public int compare(Job job1, Job job2) {

        int vacancy1 = job1.getVacancy().vacancyNumber;
        int vacancy2 = job2.getVacancy().vacancyNumber;

        if (vacancy1 < vacancy2) {
            return -1;
        } else if (vacancy1 > vacancy2) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "by vacancy ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobVacancyComparator); // instanceof handles nulls
    }
}
