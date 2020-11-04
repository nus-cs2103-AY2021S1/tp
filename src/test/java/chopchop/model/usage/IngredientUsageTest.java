package chopchop.model.usage;

import static chopchop.testutil.Assert.assertThrows;
import static chopchop.testutil.TypicalUsages.Date.USAGE_DATE_A;
import org.junit.jupiter.api.Test;

class IngredientUsageTest {
    @Test
    void constructor_nullQty_nullPtrException() {
        assertThrows(NullPointerException.class, () -> new IngredientUsage("A", USAGE_DATE_A, null));
    }
}
