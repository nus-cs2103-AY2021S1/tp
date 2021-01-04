package seedu.homerce.testutil.client;

import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.ClientManager;

/**
 * A utility class to help with building ClientManager objects.
 * Example usage: <br>
 *     {@code ClientManager cm = new ClientManagerBuilder().withClient("John", "Doe").build();}
 */
public class ClientManagerBuilder {

    private ClientManager clientManager;

    public ClientManagerBuilder() {
        clientManager = new ClientManager();
    }

    public ClientManagerBuilder(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    /**
     * Adds a new {@code Client} to the {@code ClientManager} that we are building.
     */
    public ClientManagerBuilder withClient(Client client) {
        clientManager.addClient(client);
        return this;
    }

    public ClientManager build() {
        return clientManager;
    }
}
