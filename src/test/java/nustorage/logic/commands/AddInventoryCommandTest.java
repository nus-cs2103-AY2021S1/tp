package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.testutil.Assert.assertThrows;

import nustorage.model.Inventory;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.record.InventoryRecord;
import nustorage.testutil.stub.ModelStub;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AddInventoryCommandTest {

    @Test
    public void constructor_nullInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInventoryRecordCommand(null, null));
    }

    @Test
    public void execute_InventoryRecordAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingRecordAdded extends ModelStub {

        final ArrayList<InventoryRecord> recordsAdded = new ArrayList<>();

        @Override
        public void addInventoryRecord(InventoryRecord record) {
            requireNonNull(record);
            recordsAdded.add(record);
        }

        @Override
        public ReadOnlyInventory getReadOnlyInventory() {
            return new Inventory();
        }

    }

}
