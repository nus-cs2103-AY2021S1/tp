package seedu.address.state.expenditureindex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ExpenditureIndexManagerTest {
    public static final int VALID_EXPENDITURE_INDEX = 0;
    @Test
    void setIndex_validInput() {
        ExpenditureIndex actual = new ExpenditureIndexManager(1);
        actual.setIndex(VALID_EXPENDITURE_INDEX);
        ExpenditureIndex expected = new ExpenditureIndexStub().getTypicalExpenditureIndex();
        assertEquals(expected, actual);
    }

    @Test
    void getIndex() {
        ExpenditureIndex expected = new ExpenditureIndexStub().getTypicalExpenditureIndex();
        assertEquals(expected.getExpenditureIndex(), Optional.of(0));
    }

    @Test
    void equals_sameObject_true() {
        ExpenditureIndex expenditureIndex = new ExpenditureIndexStub().getTypicalExpenditureIndex();
        assertEquals(true, expenditureIndex.equals(expenditureIndex));
    }

    class ExpenditureIndexStub {
        public final ExpenditureIndex getTypicalExpenditureIndex() {
            return new ExpenditureIndexManager(VALID_EXPENDITURE_INDEX);
        }
    }
}
