package seedu.stock.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.stock.model.ReadOnlySerialNumberSetsBook;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.stock.SerialNumberSet;
import seedu.stock.model.stock.Stock;

public class SampleDataUtilTest {

    @Test
    public void getSampleStock() {
        Stock[] expectedStockBook = SampleDataUtil.getSampleStocks();
        assertTrue(expectedStockBook.length == 0);
    }

    @Test
    public void getSerialNumberSets() {
        SerialNumberSet[] expectedSerialNumberSets = SampleDataUtil.getSampleSerialNumberSets();
        assertTrue(expectedSerialNumberSets.length == 0);
    }

    @Test
    public void getSampleStockBook() {
        ReadOnlyStockBook expectedStockBook = SampleDataUtil.getSampleStockBook();
        assertTrue(expectedStockBook.getStockList().isEmpty());
    }

    @Test
    public void getSampleSerialNumberSetsBook() {
        ReadOnlySerialNumberSetsBook expectedSerialNumberSets = SampleDataUtil.getSampleSerialNumberSetsBook();
        assertTrue(expectedSerialNumberSets.getSerialNumberSetsList().isEmpty());
    }
}
