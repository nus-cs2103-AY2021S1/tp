package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Job;
import seedu.address.testutil.JobBuilder;

public class JobPriorityComparatorTest {

    private final JobPriorityComparator priorityComparator = new JobPriorityComparator();

    @Test
    public void equals() {
        JobPriorityComparator firstComparator = new JobPriorityComparator();
        JobPriorityComparator secondComparator = new JobPriorityComparator();

        // same comparator -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // different comparators -> returns true
        assertTrue(firstComparator.equals(secondComparator));

        // different types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));
    }

    @Test
    public void testEqual() {
        Job firstJob = new JobBuilder().build();
        Job secondJob = new JobBuilder().build();
        int result = priorityComparator.compare(firstJob, secondJob);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Job firstJob = new JobBuilder().withPriority("high").build();
        Job secondJob = new JobBuilder().withPriority("low").build();
        int result = priorityComparator.compare(firstJob, secondJob);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Job firstJob = new JobBuilder().withPriority("moderate").build();
        Job secondJob = new JobBuilder().withPriority("high").build();
        int result = priorityComparator.compare(firstJob, secondJob);
        assertEquals(result, -1);
    }
}
