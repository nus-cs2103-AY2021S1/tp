package nustorage.logic.commands;

import org.junit.jupiter.api.Test;

import static nustorage.testutil.Assert.assertThrows;

public class EditInventoryCommandTest {

    @Test
    public void constructor_nullEditInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditInventoryCommand(null, null));
    }


}
