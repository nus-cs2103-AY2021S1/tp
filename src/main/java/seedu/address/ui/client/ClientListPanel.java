package seedu.address.ui.client;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.client.Client;
import seedu.address.ui.EntityListPanel;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of clients.
 */
public class ClientListPanel extends EntityListPanel<Client> {

    private final Logger logger = LogsCenter.getLogger(ClientListPanel.class);

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public ClientListPanel(ObservableList<Client> clientList) {
        super(clientList);
        labelName.setText("Clients");
    }

    @Override
    public UiPart<Region> getCard(Client client, int index) {
        return new ClientCard(client, index);
    }



}
