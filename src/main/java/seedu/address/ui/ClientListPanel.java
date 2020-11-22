package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;

/**
 * The UI component that displays a panel containing the list of clients.
 */
public class ClientListPanel extends UiPart<Region> {

    private static final String FXML = "ClientListPanel.fxml";

    @FXML
    private ListView<Client> clientListView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public ClientListPanel(ObservableList<Client> clientList) {
        super(FXML);
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> {
            ClientListViewCell clientListViewCell = new ClientListViewCell();
            clientListViewCell.disableProperty().setValue(true);
            return clientListViewCell;
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientListCard}.
     */
    class ClientListViewCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClientListCard(client, getIndex() + 1).getRoot());
            }
        }
    }

}
