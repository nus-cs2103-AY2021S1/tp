package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BlockTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Block(null));
    }

    @Test
    public void constructor_invalidBlock_throwsIllegalArgumentException() {
        String invalidBlock = "";
        assertThrows(IllegalArgumentException.class, () -> new Block(invalidBlock));
    }

    @Test
    public void isValidBlock() {
        // null block
        assertThrows(NullPointerException.class, () -> Block.isValidBlock(null));

        // blank block
        assertFalse(Block.isValidBlock("")); // empty string
        assertFalse(Block.isValidBlock(" ")); // spaces only

        // incorrect length
        assertFalse(Block.isValidBlock("ABCDE")); // incorrect length
        assertFalse(Block.isValidBlock("FFED")); // incorrect length
        assertFalse(Block.isValidBlock("AA")); // incorrect length

        // not an alphabet
        assertFalse(Block.isValidBlock("4"));
        assertFalse(Block.isValidBlock("5"));
        assertFalse(Block.isValidBlock("9"));
        assertFalse(Block.isValidBlock("-"));
        assertFalse(Block.isValidBlock("+"));

        // trailing space
        assertFalse(Block.isValidBlock("A ")); // trailing space
        assertFalse(Block.isValidBlock("B ")); // trailing space
        assertFalse(Block.isValidBlock("C ")); // trailing space

        // valid block
        assertTrue(Block.isValidBlock("A"));
        assertTrue(Block.isValidBlock("B"));
        assertTrue(Block.isValidBlock("C"));
        assertTrue(Block.isValidBlock("D"));
    }
}
