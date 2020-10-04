package seedu.address.testutil;

import seedu.address.model.LogBook;
import seedu.address.model.log.Log;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code LogBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private LogBook logBook;

    public AddressBookBuilder() {
        logBook = new LogBook();
    }

    public AddressBookBuilder(LogBook logBook) {
        this.logBook = logBook;
    }

    /**
     * Adds a new {@code Log} to the {@code LogBook} that we are building.
     */
    public AddressBookBuilder withPerson(Log log) {
        logBook.addPerson(log);
        return this;
    }

    public LogBook build() {
        return logBook;
    }
}
