// ItemReferenceTest.java

package chopchop.logic.parser;

import org.junit.jupiter.api.Test;

import chopchop.commons.util.Result;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static chopchop.testutil.Assert.assertThrows;

public class ItemReferenceTest {

    @Test
    void test_itemReference() {

        var zi = ItemReference.ofZeroIndex(3);
        var oi = ItemReference.ofOneIndex(4);
        var nm = ItemReference.ofName("owo");

        assertEquals(Result.error("Empty input"), ItemReference.parse(""));
        assertEquals(Result.of(ItemReference.ofName("#")), ItemReference.parse("#"));
        assertEquals(Result.error("Invalid index (cannot be zero or negative)"), ItemReference.parse("#0"));
        assertEquals(Result.error("Invalid index (cannot be zero or negative)"), ItemReference.parse("#-30"));

        assertEquals(0, ItemReference.ofZeroIndex(0).getZeroIndex());
        assertEquals(3, ItemReference.ofZeroIndex(3).getZeroIndex());
        assertEquals(4, ItemReference.ofZeroIndex(3).getOneIndex());
        assertThrows(IndexOutOfBoundsException.class, () -> ItemReference.ofZeroIndex(-3));
        assertThrows(IndexOutOfBoundsException.class, () -> ItemReference.ofOneIndex(0));

        assertEquals(oi, ItemReference.parse("#4").getValue());
        assertEquals(Result.of(nm), ItemReference.parse("OWO"));

        assertTrue(nm.isNamed());
        assertTrue(zi.isIndexed());
        assertTrue(oi.isIndexed());

        assertEquals(zi.getZeroIndex(), 3);
        assertEquals(oi.getZeroIndex(), 3);
        assertEquals(nm.getName(), "owo");

        assertEquals(ItemReference.ofName("OWO"), ItemReference.ofName("owo"));
        assertEquals(oi, zi);
        assertEquals(zi, zi);

        assertNotEquals(ItemReference.ofZeroIndex(3), ItemReference.ofOneIndex(3));
        assertNotEquals(ItemReference.ofName("owo"), "owo");
        assertNotEquals(zi, nm);
    }
}
