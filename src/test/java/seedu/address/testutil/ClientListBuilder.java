package seedu.address.testutil;

import seedu.address.model.ClientList;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Clientlist objects.
 * Example usage: <br>
 *     {@code ClientList ab = new ClientListBuilder().withPerson("John", "Doe").build();}
 */
public class ClientListBuilder {

    private ClientList clientList;

    public ClientListBuilder() {
        clientList = new ClientList();
    }

    public ClientListBuilder(ClientList clientList) {
        this.clientList = clientList;
    }

    /**
     * Adds a new {@code Person} to the {@code ClientList} that we are building.
     */
    public ClientListBuilder withPerson(Person person) {
        clientList.addPerson(person);
        return this;
    }

    public ClientList build() {
        return clientList;
    }
}
