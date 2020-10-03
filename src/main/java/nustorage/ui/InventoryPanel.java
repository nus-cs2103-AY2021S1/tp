package nustorage.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import nustorage.commons.core.LogsCenter;
import nustorage.model.record.InventoryRecord;

public class InventoryPanel extends UiPart<Region> {
    private static final String FXML = "InventoryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InventoryPanel.class);

    @FXML
    private ListView<InventoryRecord> inventoryView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public InventoryPanel(ObservableList<InventoryRecord> recordList) {
        super(FXML);
        inventoryView.setItems(recordList);
        inventoryView.setCellFactory(listView -> new InventoryPanel.InventoryViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class InventoryViewCell extends ListCell<InventoryRecord> {
        @Override
        protected void updateItem(InventoryRecord record, boolean empty) {
            super.updateItem(record, empty);

            if (empty || record == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InventoryRecordCard(record, getIndex() + 1).getRoot());
            }
        }
    }

}
