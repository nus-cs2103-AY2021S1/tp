package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.InventoryComponent;
import seedu.address.model.item.DetailedItem;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.PrintableRecipe;

/**
 * Panel containing the list of inventory.
 */
public class InventoryListPanel extends UiPart<Region> {
    private static final String FXML = "InventoryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InventoryListPanel.class);

    private DisplayedInventoryType inventoryType;

    @FXML
    private ListView<InventoryComponent> itemListView;

    /**
     * Creates a {@code InventoryListPanel} with the given {@code ObservableList} and {@code View.InventoryType}.
     */
    public InventoryListPanel(List<InventoryComponent> inventoryList, DisplayedInventoryType inventoryType) {
        super(FXML);
        this.inventoryType = inventoryType;
        ObservableList<InventoryComponent> observableInventoryList = FXCollections.observableList(inventoryList);
        itemListView.setItems(observableInventoryList);
        itemListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Updates the InventoryListPanel to display inventory of the given {@code View.InventoryType}
     * in the given {@code ObservableList}.
     */
    public void refresh(List<InventoryComponent> inventoryList, DisplayedInventoryType inventoryType) {
        this.inventoryType = inventoryType;
        ObservableList<InventoryComponent> observableInventoryList = FXCollections.observableList(inventoryList);
        itemListView.setItems(observableInventoryList);
        itemListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of the current inventory, using suitable cards.
     */
    class ItemListViewCell extends ListCell<InventoryComponent> {

        @Override
        protected void updateItem(InventoryComponent inventoryComponent, boolean empty) {
            super.updateItem(inventoryComponent, empty);
            if (empty || inventoryComponent == null) {
                setGraphic(null);
                setText(null);
            } else {
                switch (inventoryComponent.getType()) {
                case ITEMS:
                    setGraphic(new InventoryCard((Item) inventoryComponent, getIndex() + 1).getRoot());
                    break;
                case RECIPES:
                    setGraphic(new RecipeCard((PrintableRecipe) inventoryComponent, getIndex() + 1).getRoot());
                    break;
                case RECIPES_OFFSET:
                    // offset by 1 since there is a detailed item displayed above
                    setGraphic(new RecipeCard((PrintableRecipe) inventoryComponent, getIndex()).getRoot());
                    break;
                case DETAILED_ITEM:
                    setGraphic(new DetailedItemCard((DetailedItem) inventoryComponent, getIndex() + 1).getRoot());
                    break;
                default:
                    throw new IllegalStateException("This inventoryType is not valid");
                }
            }
        }
    }
}
