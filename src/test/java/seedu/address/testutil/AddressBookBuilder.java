package seedu.address.testutil;

import seedu.address.model.HospifyBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code HospifyBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private HospifyBook addressBook;

    public AddressBookBuilder() {
        addressBook = new HospifyBook();
    }

    public AddressBookBuilder(HospifyBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code HospifyBook} that we are building.
     */
    public AddressBookBuilder withPerson(Patient patient) {
        addressBook.addPatient(patient);
        return this;
    }

    public HospifyBook build() {
        return addressBook;
    }
}
