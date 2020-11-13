package seedu.internhunter.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.internhunter.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void createOneBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    @Test
    public void minusOne() {
        // zero index 1 one -> gives back zero index
        assertEquals(Index.fromZeroBased(0), Index.fromOneBased(1).minusOne());
        assertEquals(Index.fromZeroBased(0), Index.fromZeroBased(0).minusOne());

        // random index minus
        assertEquals(Index.fromZeroBased(4), Index.fromZeroBased(5).minusOne());
        assertEquals(Index.fromZeroBased(4), Index.fromOneBased(6).minusOne());
    }

    @Test
    public void equals() {
        final Index fifthIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertEquals(Index.fromOneBased(5), fifthIndex);
        assertEquals(Index.fromZeroBased(4), fifthIndex);

        // same object -> returns true
        assertEquals(fifthIndex, fifthIndex);

        // null -> returns false
        assertNotEquals(fifthIndex, null);

        // different types -> returns false
        assertNotEquals(fifthIndex, 5.0f);

        // different index -> returns false
        assertNotEquals(Index.fromOneBased(1), fifthIndex);
    }
}
