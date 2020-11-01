package nustorage.logic.commands;

// import static java.util.Objects.requireNonNull;
import static nustorage.testutil.Assert.assertThrows;

/*import nustorage.model.InventoryWindow;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.record.InventoryRecord;
import nustorage.testutil.stub.ModelStub; */
import org.junit.jupiter.api.Test;

// import java.util.ArrayList;

public class CreateInventoryCommandTest {

    @Test
    public void constructor_nullInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateInventoryRecordCommand(null, null));
    }

}
