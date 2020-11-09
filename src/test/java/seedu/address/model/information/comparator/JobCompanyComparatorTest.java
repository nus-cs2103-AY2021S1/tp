package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Job;
import seedu.address.testutil.JobBuilder;

public class JobCompanyComparatorTest {

    private final JobCompanyComparator companyComparator = new JobCompanyComparator();

    @Test
    public void equals() {
        JobCompanyComparator firstComparator = new JobCompanyComparator();
        JobCompanyComparator secondComparator = new JobCompanyComparator();

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
        Job firstJob = new JobBuilder().withCompanyName("A").build();
        Job secondJob = new JobBuilder().withCompanyName("a").build();
        int result = companyComparator.compare(firstJob, secondJob);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Job firstJob = new JobBuilder().withCompanyName("b").build();
        Job secondJob = new JobBuilder().withCompanyName("A").build();
        int result = companyComparator.compare(firstJob, secondJob);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Job firstJob = new JobBuilder().withCompanyName("Fa").build();
        Job secondJob = new JobBuilder().withCompanyName("Fb").build();
        int result = companyComparator.compare(firstJob, secondJob);
        assertEquals(result, -1);
    }
}
