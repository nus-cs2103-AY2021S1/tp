package guitests.guihandles;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.client.Client;

/**
 * Provides a handle for {@code PersonListPanel} containing the list of {@code PersonCard}.
 */
public class ClientListPanelHandle extends NodeHandle<ListView<Client>> {
    public static final String CLIENT_LIST_VIEW_ID = "#clientListView";

    private static final String CARD_PANE_ID = "#cardPane";

    public ClientListPanelHandle(ListView<Client> clientListPanelNode) {
        super(clientListPanelNode);
    }

    /**
     * Navigates the listview to display {@code person}.
     */
    public void navigateToCard(Client client) {
        if (!getRootNode().getItems().contains(client)) {
            throw new IllegalArgumentException("Client does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(client);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Navigates the listview to {@code index}.
     */
    public void navigateToCard(int index) {
        if (index < 0 || index >= getRootNode().getItems().size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(index);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Returns the client card handle of a client associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public ClientCardHandle getClientCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(ClientCardHandle::new)
                .filter(handle -> handle.equals(getClient(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Client getClient(int index) {
        return getRootNode().getItems().get(index);
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
