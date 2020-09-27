package seedu.stock.model.util;

import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.stock.Stock;

/**
 * Contains utility methods for populating {@code StockBook} with sample data.
 */
public class SampleDataUtil {
    public static Stock[] getSampleStocks() {
        //return new Stock[] {
        //    new Stock(new Name("Alex Yeoh"), new Quantity("87438807"), new Source("alexyeoh@example.com"),
        //        new Location("Blk 30 Geylang Street 29, #06-40"),
        //        getTagSet("friends")),
        //    new Stock(new Name("Bernice Yu"), new Quantity("99272758"), new Source("berniceyu@example.com"),
        //        new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
        //        getTagSet("colleagues", "friends")),
        //    new Stock(new Name("Charlotte Oliveiro"), new Quantity("93210283"), new Source("charlotte@example.com"),
        //        new Location("Blk 11 Ang Mo Kio Street 74, #11-04"),
        //        getTagSet("neighbours")),
        //    new Stock(new Name("David Li"), new Quantity("91031282"), new Source("lidavid@example.com"),
        //        new Location("Blk 436 Serangoon Gardens Street 26, #16-43"),
        //        getTagSet("family")),
        //    new Stock(new Name("Irfan Ibrahim"), new Quantity("92492021"), new Source("irfan@example.com"),
        //        new Location("Blk 47 Tampines Street 20, #17-35"),
        //        getTagSet("classmates")),
        //    new Stock(new Name("Roy Balakrishnan"), new Quantity("92624417"), new Source("royb@example.com"),
        //        new Location("Blk 45 Aljunied Street 85, #11-31"),
        //        getTagSet("colleagues"))}

        return new Stock[]{};
    }

    public static ReadOnlyStockBook getSampleStockBook() {
        StockBook sampleAb = new StockBook();
        for (Stock sampleStock : getSampleStocks()) {
            sampleAb.addStock(sampleStock);
        }
        return sampleAb;
    }

}
