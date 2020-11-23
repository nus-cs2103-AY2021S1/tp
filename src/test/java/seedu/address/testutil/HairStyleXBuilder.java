package seedu.address.testutil;

import seedu.address.model.HairStyleX;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class HairStyleXBuilder {

    private HairStyleX hairStyleX;

    public HairStyleXBuilder() {
        hairStyleX = new HairStyleX();
    }

    public HairStyleXBuilder(HairStyleX hairStyleX) {
        this.hairStyleX = hairStyleX;
    }

    //    /**
    //     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
    //     */
    //    public AddressBookBuilder withPerson(Person person) {
    //        addressBook.addPerson(person);
    //        return this;
    //    }

    public HairStyleX build() {
        return hairStyleX;
    }
}
