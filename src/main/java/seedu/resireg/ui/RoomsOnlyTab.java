package seedu.resireg.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import seedu.resireg.ui.room.RoomListPanel;

public class RoomsOnlyTab extends UiPart<Tab> implements RoomsTab {
    private static final String FXML = "RoomsOnlyTab.fxml";

    @FXML
    private StackPane roomListPanelPlaceholder;

    public RoomsOnlyTab() {
        super(FXML);
    }

    @Override
    public void setRoomListPanel(RoomListPanel roomListPanel) {
        roomListPanelPlaceholder.getChildren().clear();
        roomListPanelPlaceholder.getChildren().add(roomListPanel.getRoot());
    }

    @Override
    public Tab getTab() {
        return getRoot();
    }
}
