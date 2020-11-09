package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TagBuilder;

class TagNameComparatorTest {
    @Test
    public void equals() {
        TagNameComparator firstComparator = new TagNameComparator();
        TagNameComparator secondComparator = new TagNameComparator();

        // same comparator -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // different data types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));

        // different comparators -> returns false
        assertFalse(firstComparator.equals(secondComparator));
    }

    @Test
    public void test_personNameEqual_returns0() {
        TagNameComparator comparator = new TagNameComparator();
        Tag p1 = new TagBuilder().build();
        Tag p2 = new TagBuilder().build();
        assertEquals(comparator.compare(p1, p2), 0);
    }

    @Test
    public void test_personNameGreaterThan_returnsNegativeValues() {
        TagNameComparator comparator = new TagNameComparator();
        Tag p1 = new TagBuilder().withTagName("CS2103").build();
        Tag p2 = new TagBuilder().withTagName("friends").build();
        assertTrue(comparator.compare(p1, p2) < 0);
    }

    @Test
    public void test_personNameSmallerThan_returnsPositiveValues() {
        TagNameComparator comparator = new TagNameComparator();
        Tag p1 = new TagBuilder().withTagName("friends").build();
        Tag p2 = new TagBuilder().withTagName("CS2103").build();
        assertTrue(comparator.compare(p1, p2) > 0);
    }
}
