package seedu.resireg.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.logic.Logic;
import seedu.resireg.logic.commands.TabView;

/**
 * Represents the main panel when the app is in {@code AppMode.NEW} mode.
 */
public class RoomCreationMainPanel extends MainPanel {
    private static final String FXML = "RoomCreationMainPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomCreationMainPanel.class);

    @FXML
    private StackPane roomListPanelPlaceholder;

    private RoomListPanel roomListPanel;

    public RoomCreationMainPanel() {
        super(FXML);
    }

    @Override
    void updatePanels(Logic logic) {
        roomListPanel = new RoomListPanel(
                logic.getFilteredRoomList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredStudentList());
        roomListPanelPlaceholder.getChildren().clear();
        roomListPanelPlaceholder.getChildren().add(roomListPanel.getRoot());
    }

    @Override
    void handleToggle(TabView toggleView) {
        if (!(toggleView == TabView.ROOMS)) {
            logger.severe("Should not be toggling to other views!");
            assert false : "Should not be toggling to other view!";
        }
    }

    @Override
    void toggleStudentsRoomsTabSplit() {
        logger.severe("Should not be toggling tab split!");
        assert false : "Should not be toggling tab split!";
    }
}
