package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nustorage.model.FinanceAccount;
import nustorage.model.ReadOnlyFinanceAccount;
import nustorage.model.record.FinanceRecord;
import nustorage.testutil.FinanceRecordBuilder;
import nustorage.testutil.stub.ModelStub;

public class AddFinanceCommandTest {

    @Test
    public void constructor_nullFinanceRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddFinanceCommand(null));
    }

    @Test
    public void execute_financeRecordAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFinanceRecordAdded modelStub = new ModelStubAcceptingFinanceRecordAdded();
        FinanceRecord financeRecord = new FinanceRecordBuilder().build();

        CommandResult commandResult = new AddFinanceCommand(financeRecord).execute(modelStub);


        assertEquals(String.format(AddFinanceCommand.MESSAGE_SUCCESS, financeRecord),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(financeRecord), modelStub.financeRecordAdded);

    }

    @Test
    public void equals() {
        FinanceRecord record1 = new FinanceRecordBuilder().withAmount(200).build();
        FinanceRecord record2 = new FinanceRecordBuilder().withAmount(10000).build();
        AddFinanceCommand addFinanceCommand1 = new AddFinanceCommand(record1);
        AddFinanceCommand addFinanceCommand2 = new AddFinanceCommand(record2);

        // same object -> returns true
        assertTrue(addFinanceCommand1.equals(addFinanceCommand1));

        // same values -> returns true
        AddFinanceCommand addFinanceCommand1Copy = new AddFinanceCommand(record1);
        assertTrue(addFinanceCommand1.equals(addFinanceCommand1Copy));

        // different types -> returns false
        assertFalse(addFinanceCommand1.equals(1));

        // null -> returns false
        assertFalse(addFinanceCommand1.equals(null));

        // different finance records -> returns false
        assertFalse(addFinanceCommand1.equals(addFinanceCommand2));
    }

    /**
     * A Model stub that always accept the finance record being added.
     */
    private class ModelStubAcceptingFinanceRecordAdded extends ModelStub {
        final ArrayList<FinanceRecord> financeRecordAdded = new ArrayList<>();

        @Override
        public void addFinanceRecord(FinanceRecord financeRecord) {
            requireNonNull(financeRecord);
            financeRecordAdded.add(financeRecord);
        }

        @Override
        public ReadOnlyFinanceAccount getFinanceAccount() {
            return new FinanceAccount();
        }
    }

}
