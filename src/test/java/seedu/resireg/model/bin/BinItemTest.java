package seedu.resireg.model.bin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_DATE_DELETED_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ITEM_B;
import static seedu.resireg.testutil.TypicalBinItems.BIN_ITEM_A;
import static seedu.resireg.testutil.TypicalBinItems.BIN_ITEM_B;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.BinItemBuilder;

public class BinItemTest {
    @Test
    public void equals() {
        // same values -> returns true
        BinItem binItemACopy = new BinItemBuilder(BIN_ITEM_A).build();
        assertTrue(BIN_ITEM_A.equals(binItemACopy));

        // same object -> returns true
        assertTrue(BIN_ITEM_A.equals(BIN_ITEM_A));

        // null -> returns false
        assertFalse(BIN_ITEM_A.equals(null));

        // different type -> returns false
        assertFalse(BIN_ITEM_A.equals(7));

        // different bin item -> returns false
        assertFalse(BIN_ITEM_A.equals(BIN_ITEM_B));

        // different date deleted -> returns false
        BinItem editedBinItemA = new BinItemBuilder(BIN_ITEM_A).withDateDeleted(VALID_DATE_DELETED_B).build();
        assertFalse(BIN_ITEM_A.equals(editedBinItemA));

        // different item -> returns false
        editedBinItemA = new BinItemBuilder(BIN_ITEM_A).withItem(VALID_ITEM_B).build();
        assertFalse(BIN_ITEM_A.equals(editedBinItemA));

    }
}
