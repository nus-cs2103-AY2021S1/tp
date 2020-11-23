package seedu.stock.model.util;

import seedu.stock.model.ReadOnlySerialNumberSetsBook;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.stock.SerialNumberSet;
import seedu.stock.model.stock.Stock;

/**
 * Contains utility methods for populating {@code StockBook} with sample data.
 */
public class SampleDataUtil {
    public static Stock[] getSampleStocks() {
        return new Stock[]{};
    }

    public static SerialNumberSet[] getSampleSerialNumberSets() {
        return new SerialNumberSet[]{};
    }

    public static ReadOnlyStockBook getSampleStockBook() {
        StockBook sampleAb = new StockBook();
        for (Stock sampleStock : getSampleStocks()) {
            sampleAb.addStock(sampleStock);
        }
        return sampleAb;
    }

    public static ReadOnlySerialNumberSetsBook getSampleSerialNumberSetsBook() {
        SerialNumberSetsBook sampleAb = new SerialNumberSetsBook();
        for (SerialNumberSet sampleSerialNumberSet : getSampleSerialNumberSets()) {
            sampleAb.addSerialNumberSet(sampleSerialNumberSet);
        }
        return sampleAb;
    }

}
