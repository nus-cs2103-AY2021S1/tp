package seedu.resireg.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.bin.Binnable;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * Panel containing the list of items in the bin.
 */
public class BinItemListPanel extends UiPart<Region> {

    private static final String FXML = "BinItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BinItemListPanel.class);


    @FXML
    private ListView<BinItem> binItemListView;

    /**
     * Panel that displays the graphics of a {@code BinItem} using a Binnable card.
     */
    public BinItemListPanel(ObservableList<BinItem> binItemList) {
        super(FXML);
        binItemListView.setItems(binItemList);
        binItemListView.setCellFactory(listView -> new BinItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code BinItem} using a {@code Binnable card}.
     */
    class BinItemListViewCell extends ListCell<BinItem> {
        @Override
        protected void updateItem(BinItem binItem, boolean empty) {
            super.updateItem(binItem, empty);

            if (empty || binItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                Binnable item = binItem.getBinnedItem();
                if (item instanceof Student) {
                    Student student = (Student) item;
                    setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
                } else if (item instanceof Room) {
                    Room room = (Room) item;
                    setGraphic(new RoomCard(room, getIndex() + 1).getRoot());
                } else {
                    assert false : "Another Binnable instance must be created!";
                }
            }
        }
    }
}
