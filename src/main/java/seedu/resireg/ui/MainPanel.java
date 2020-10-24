package seedu.resireg.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.resireg.logic.Logic;
import seedu.resireg.logic.commands.TabView;

/**
 * Represents the main panel containing the room list and student list of the {@code MainWindow}.
 */
public abstract class MainPanel extends UiPart<Region> {
    protected StudentListPanel studentListPanel;
    protected RoomListPanel roomListPanel;

    @FXML
    protected StackPane studentListPanelPlaceholder;
    @FXML
    protected StackPane roomListPanelPlaceholder;

    public MainPanel(String fxmlFileName) {
        super(fxmlFileName);
    }

    final void updatePanels(Logic logic) {
        studentListPanel = new StudentListPanel(
                logic.getFilteredStudentList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredRoomList());
        studentListPanelPlaceholder.getChildren()
                .add(studentListPanel.getRoot());

        roomListPanel = new RoomListPanel(
                logic.getFilteredRoomList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredStudentList());
        roomListPanelPlaceholder.getChildren()
                .add(roomListPanel.getRoot());
    }

    /**
     * Sets what is displayed in the listPanelStackPane based on the toggle.
     *
     * @param toggleView enum representing what should be displayed
     */
    abstract void handleToggle(TabView toggleView);
}
