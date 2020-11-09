package seedu.address.model.book;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StockingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Stocking(null));
    }

    @Test
    void isValidStocking() {
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 10 scienceLb 10 HSSMLb 15"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 10 HSSMLb 15"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 30 scienceLb 45"));
        Assertions.assertTrue(Stocking.isValidStocking("scienceLb 10"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 10"));
        Assertions.assertTrue(Stocking.isValidStocking("scienceLb 10 centralLb 10 HSSMLb 15"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 99999 scienceLb 10 HSSMLb 15"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 0 scienceLb 10 HSSMLb 15"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 0 scienceLb 0 HSSMLb 0"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 30 scienceLb 15 scienceLb 45"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 30scienceLb 45"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb30scienceLb45 "));
        Assertions.assertTrue(Stocking.isValidStocking("       centralLb 30           scienceLb 45 "));
        Assertions.assertTrue(Stocking.isValidStocking(" "));
        Assertions.assertTrue(Stocking.isValidStocking(""));
        Assertions.assertFalse(Stocking.isValidStocking("library 10"));
        Assertions.assertFalse(Stocking.isValidStocking("library"));
        Assertions.assertFalse(Stocking.isValidStocking("centralLb 30 scienceLb 45 scienceLb 45 HSSMLb 15"));
    }
}
