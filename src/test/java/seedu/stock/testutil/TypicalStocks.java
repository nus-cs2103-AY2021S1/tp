package seedu.stock.testutil;


import seedu.stock.model.stock.Stock;

/**
 * A utility class containing a list of {@code Stock} objects to be used in tests.
 */
public class TypicalStocks {
    public static final Stock APPLE = new StockBuilder().withName("Apple Juice")
            .withSerialNumber("Ntuc1").withSource("Ntuc")
            .withQuantity("2000").withLocation("Fruit Section, Subsection C")
            .build();
    public static final Stock BANANA = new StockBuilder().withName("Banana Bun")
            .withSerialNumber("Fairprice1").withSource("Fairprice")
            .withQuantity("1000").withLocation("Fruits section, Subsection B")
            .build();
}
