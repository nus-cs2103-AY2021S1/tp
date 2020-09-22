package seedu.address.testutil;

import seedu.address.model.StockBook;
import seedu.address.model.stock.Stock;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private StockBook stockBook;

    public AddressBookBuilder() {
        stockBook = new StockBook();
    }

    public AddressBookBuilder(StockBook stockBook) {
        this.stockBook = stockBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Stock stock) {
        stockBook.addStock(stock);
        return this;
    }

    public StockBook build() {
        return stockBook;
    }
}
