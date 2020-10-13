package seedu.address.testutil;

import seedu.address.model.ModuleList;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ModuleListBuilder {

    private ModuleList moduleList;

    public ModuleListBuilder() {
        moduleList = new ModuleList();
    }

    public ModuleListBuilder(ModuleList moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ModuleListBuilder withPerson(Person person) {
        // moduleList.addPerson(person);
        return this;
    }

    public ModuleList build() {
        return moduleList;
    }
}
