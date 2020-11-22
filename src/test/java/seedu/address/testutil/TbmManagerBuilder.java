package seedu.address.testutil;

import seedu.address.model.TbmManager;
import seedu.address.model.client.Client;

/**
 * A utility class to help with building TbmManager objects.
 * Example usage: <br>
 *     {@code TbmManager tm = new TbmManagerBuilder().withClient("John", "Doe").build();}
 */
public class TbmManagerBuilder {

    private final TbmManager tbmManager;

    public TbmManagerBuilder() {
        tbmManager = new TbmManager();
    }

    public TbmManagerBuilder(TbmManager tbmManager) {
        this.tbmManager = tbmManager;
    }

    /**
     * Adds a new {@code Client} to the {@code TbmManager} that we are building.
     */
    public TbmManagerBuilder withClient(Client client) {
        tbmManager.addClient(client);
        return this;
    }

    public TbmManager build() {
        return tbmManager;
    }
}
