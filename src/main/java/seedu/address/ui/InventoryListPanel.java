package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;



/**
 * Panel containing the list of items.
 */
public class InventoryListPanel extends UiPart<Region> {
    private static final String FXML = "InventoryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InventoryListPanel.class);

    @FXML
    private ListView<Item> itemListView;

    /**
     * Creates a {@code InventoryListPanel} with the given {@code ObservableList}.
     */
    public InventoryListPanel(ObservableList<Item> itemList) {
        super(FXML);
        itemListView.setItems(itemList);
        itemListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code InventoryCard}.
     */
    class ItemListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InventoryCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
