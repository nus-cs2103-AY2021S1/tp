package seedu.homerce.model.manager;

import javafx.collections.ObservableList;
import seedu.homerce.model.client.Client;

/**
 * Unmodifiable view of an homerce
 */
public interface ReadOnlyClientManager {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();


}
