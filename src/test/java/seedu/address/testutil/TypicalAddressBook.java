package seedu.address.testutil;


import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

public class TypicalAddressBook {

    /**
     * Returns an {@code AddressBook} with all the typical persons and modules.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }
}
