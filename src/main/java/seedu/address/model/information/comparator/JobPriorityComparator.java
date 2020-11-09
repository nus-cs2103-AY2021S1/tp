package seedu.address.model.information.comparator;

import java.util.Comparator;

import seedu.address.model.information.Job;

/**
 * Compares any two {@code Job}'s {@code Priority} using the the supplied sorting criteria.
 */
public class JobPriorityComparator extends JobComparator implements Comparator<Job> {

    public static final String SORT_CRITERIA = "pr";

    @Override
    public int compare(Job job1, Job job2) {

        int priority1 = job1.getPriority().parsePriorityToInt();
        int priority2 = job2.getPriority().parsePriorityToInt();

        if (priority1 < priority2) {
            return -1;
        } else if (priority1 > priority2) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "by priority ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobPriorityComparator); // instanceof handles nulls
    }
}

