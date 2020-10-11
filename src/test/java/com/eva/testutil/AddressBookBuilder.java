package com.eva.testutil;

import com.eva.model.EvaDatabase;
import com.eva.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code EvaDatabase ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private EvaDatabase addressBook;

    public AddressBookBuilder() {
        addressBook = new EvaDatabase();
    }

    public AddressBookBuilder(EvaDatabase addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code EvaDatabase} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public EvaDatabase build() {
        return addressBook;
    }
}
