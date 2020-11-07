package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.timetable.Slot;

/**
 * Panel containing the list of slots.
 */
public class SlotListPanel extends UiPart<Region> {
    private static final String FXML = "SlotListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SlotListPanel.class);

    @FXML
    private ListView<Slot> slotListView;

    /**
     * Creates a {@code SlotListPanel} with the given {@code ObservableList}.
     */
    public SlotListPanel(ObservableList<Slot> slotList) {
        super(FXML);
        slotListView.setItems(slotList);
        slotListView.setOrientation(Orientation.HORIZONTAL);
        slotListView.setCellFactory(listView -> new SlotListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Slot} using a {@code SlotCard}.
     */
    class SlotListViewCell extends ListCell<Slot> {
        @Override
        protected void updateItem(Slot slot, boolean empty) {
            super.updateItem(slot, empty);

            if (empty || slot == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SlotCard(slot, getIndex() + 1).getRoot());
            }
        }
    }

}
