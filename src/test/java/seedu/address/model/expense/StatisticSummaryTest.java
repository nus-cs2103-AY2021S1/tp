package seedu.address.model.expense;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatisticSummaryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }
}
