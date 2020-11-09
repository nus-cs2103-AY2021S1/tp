package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Job;
import seedu.address.testutil.JobBuilder;

public class JobTitleComparatorTest {

    private final JobTitleComparator titleComparator = new JobTitleComparator();

    @Test
    public void equals() {
        JobTitleComparator firstComparator = new JobTitleComparator();
        JobTitleComparator secondComparator = new JobTitleComparator();

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
        Job firstJob = new JobBuilder().withJobTitle("A").build();
        Job secondJob = new JobBuilder().withJobTitle("a").build();
        int result = titleComparator.compare(firstJob, secondJob);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Job firstJob = new JobBuilder().withJobTitle("b").build();
        Job secondJob = new JobBuilder().withJobTitle("A").build();
        int result = titleComparator.compare(firstJob, secondJob);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Job firstJob = new JobBuilder().withJobTitle("Fa").build();
        Job secondJob = new JobBuilder().withJobTitle("Fb").build();
        int result = titleComparator.compare(firstJob, secondJob);
        assertEquals(result, -1);
    }
}
