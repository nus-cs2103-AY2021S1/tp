package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.StockBook;
import seedu.address.model.ReadOnlyStockBook;
import seedu.address.model.stock.Stock;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Stock[] getSamplePersons() {
//        return new Stock[] {
//            new Stock(new Name("Alex Yeoh"), new Quantity("87438807"), new Source("alexyeoh@example.com"),
//                new Location("Blk 30 Geylang Street 29, #06-40"),
//                getTagSet("friends")),
//            new Stock(new Name("Bernice Yu"), new Quantity("99272758"), new Source("berniceyu@example.com"),
//                new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
//                getTagSet("colleagues", "friends")),
//            new Stock(new Name("Charlotte Oliveiro"), new Quantity("93210283"), new Source("charlotte@example.com"),
//                new Location("Blk 11 Ang Mo Kio Street 74, #11-04"),
//                getTagSet("neighbours")),
//            new Stock(new Name("David Li"), new Quantity("91031282"), new Source("lidavid@example.com"),
//                new Location("Blk 436 Serangoon Gardens Street 26, #16-43"),
//                getTagSet("family")),
//            new Stock(new Name("Irfan Ibrahim"), new Quantity("92492021"), new Source("irfan@example.com"),
//                new Location("Blk 47 Tampines Street 20, #17-35"),
//                getTagSet("classmates")),
//            new Stock(new Name("Roy Balakrishnan"), new Quantity("92624417"), new Source("royb@example.com"),
//                new Location("Blk 45 Aljunied Street 85, #11-31"),
//                getTagSet("colleagues"))
//        };
        return new Stock[]{};
    }

    public static ReadOnlyStockBook getSampleAddressBook() {
        StockBook sampleAb = new StockBook();
        for (Stock sampleStock : getSamplePersons()) {
            sampleAb.addStock(sampleStock);
        }
        return sampleAb;
    }

}
