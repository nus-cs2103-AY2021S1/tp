package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Job;
import seedu.address.testutil.JobBuilder;

public class JobVacancyComparatorTest {

    private final JobVacancyComparator vacancyComparator = new JobVacancyComparator();

    @Test
    public void equals() {
        JobVacancyComparator firstComparator = new JobVacancyComparator();
        JobVacancyComparator secondComparator = new JobVacancyComparator();

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
        int result = vacancyComparator.compare(firstJob, secondJob);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Job firstJob = new JobBuilder().withVacancy("7").build();
        Job secondJob = new JobBuilder().withVacancy("3").build();
        int result = vacancyComparator.compare(firstJob, secondJob);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Job firstJob = new JobBuilder().withVacancy("4").build();
        Job secondJob = new JobBuilder().withVacancy("5").build();
        int result = vacancyComparator.compare(firstJob, secondJob);
        assertEquals(result, -1);
    }
}
