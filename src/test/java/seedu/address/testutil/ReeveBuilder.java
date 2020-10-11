package seedu.address.testutil;

import seedu.address.model.Reeve;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class ReeveBuilder {

    private Reeve reeve;

    public ReeveBuilder() {
        reeve = new Reeve();
    }

    public ReeveBuilder(Reeve reeve) {
        this.reeve = reeve;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ReeveBuilder withPerson(Student student) {
        reeve.addStudent(student);
        return this;
    }

    public Reeve build() {
        return reeve;
    }
}
