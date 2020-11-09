package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.RoomTaskAssociation;

/**
 * Panel containing the list of room with tasks.
 */
public class RoomTaskListPanel extends UiPart<Region> {

    private static final String FXML = "RoomTaskListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(RoomTaskListPanel.class);

    @FXML
    private ListView<RoomTaskAssociation> roomTaskListView;

    /**
     * Creates a {@code RoomTaskListPanel} with the given {@code ObservableList}.
     */
    public RoomTaskListPanel(ObservableList<RoomTaskAssociation> roomTaskAssociations) {
        super(FXML);
        roomTaskListView.setItems(roomTaskAssociations);
        roomTaskListView.setCellFactory(listView -> new RoomTaskListViewCell());
        logger.info("RoomTaskListPanel has been initialized with tasks from all rooms.");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code RoomTaskAssociation} using a {@code TaskCard}.
     */
    class RoomTaskListViewCell extends ListCell<RoomTaskAssociation> {
        @Override
        protected void updateItem(RoomTaskAssociation roomTaskAssociation, boolean empty) {
            super.updateItem(roomTaskAssociation, empty);

            if (empty || roomTaskAssociation == null) {
                setGraphic(null);
                setText(null);
            } else {
                int roomNumber = roomTaskAssociation.getRoomNumber();
                int taskIndex = roomTaskAssociation.getTaskIndex();
                int totalNumberOfTasksInRoom = roomTaskAssociation.getTotalTasksInRoom();
                setGraphic(new TaskCard(roomNumber, taskIndex, totalNumberOfTasksInRoom,
                        roomTaskAssociation.getTask()).getRoot());
            }
        }
    }
}
