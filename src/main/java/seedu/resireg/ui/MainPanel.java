package seedu.resireg.ui;

import java.util.Arrays;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.logic.Logic;
import seedu.resireg.logic.commands.TabView;

/**
 * Represents the main tabbed panel for the {@code MainWindow}.
 */
public class MainPanel extends UiPart<Region> {
    private static final String FXML = "MainPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(RoomListPanel.class);
    private boolean studentsAndRoomsAreCombined;

    private StudentsTab studentsTab;
    private RoomsTab roomsTab;

    private StudentListPanel studentListPanel;
    private RoomListPanel roomListPanel;

    @FXML
    private TabPane tabPane;

    /**
     * Creates a new MainPanel. The rooms and students lists will be in separate tabs.
     */
    public MainPanel() {
        super(FXML);
        addSeparateTabs();
        studentsAndRoomsAreCombined = false;
    }

    private void showTab(Tab tab) {
        tabPane.getSelectionModel().select(tab);
    }

    private void showStudentPanel() {
        showTab(studentsTab.getTab());
    }

    private void showRoomsPanel() {
        showTab(roomsTab.getTab());
    }

    /**
     * Sets what is displayed in the listPanelStackPane based on the toggle.
     *
     * @param toggleView Enum representing what should be displayed.
     */
    void handleToggle(TabView toggleView) {
        switch (toggleView) {
        case ROOMS:
            showRoomsPanel();
            break;
        case STUDENTS:
            showStudentPanel();
            break;
        default:
            logger.warning("Unhandled TabView");
            assert false : "Unhandled TabView";
            break;
        }
    }

    /**
     * Updates the contents of all the lists in the UI based on the given {@code logic}.
     */
    void updatePanels(Logic logic) {
        studentListPanel = new StudentListPanel(
                logic.getFilteredStudentList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredRoomList());

        roomListPanel = new RoomListPanel(
                logic.getFilteredRoomList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredStudentList());

        studentsTab.setStudentListPanel(studentListPanel);
        roomsTab.setRoomListPanel(roomListPanel);
    }

    /**
     * Adds a combined students and rooms tab as the first tab in the tab pane. Does not check whether such a tab or
     * separate rooms and students tabs already exist.
     */
    private void addCombinedTab() {
        StudentsRoomsCombinedTab combinedTab = new StudentsRoomsCombinedTab();
        tabPane.getTabs().add(0, combinedTab.getRoot());

        studentsTab = combinedTab;
        roomsTab = combinedTab;

        studentsAndRoomsAreCombined = true;
    }

    /**
     * Adds a students tab and a rooms tab as the first and second tab respectively in the tab pane.
     * Does not check whether such a tab or separate rooms and students tabs already exist.
     */
    private void addSeparateTabs() {
        StudentsOnlyTab studentsOnlyTab = new StudentsOnlyTab();
        RoomsOnlyTab roomsOnlyTab = new RoomsOnlyTab();
        tabPane.getTabs().addAll(0, Arrays.asList(studentsOnlyTab.getRoot(), roomsOnlyTab.getRoot()));

        studentsTab = studentsOnlyTab;
        roomsTab = roomsOnlyTab;

        studentsAndRoomsAreCombined = false;
    }

    /**
     * Shows both students and rooms in one tab. Does nothing if students and rooms are already shown in one
     * tab.
     */
    void combineStudentsAndRooms() {
        if (!studentsAndRoomsAreCombined) {
            tabPane.getTabs().remove(0, 2); // remove first 2 tabs (students and room)
            addCombinedTab();
        }
    }

    /**
     * Shows students and rooms in two separate tabs. Does nothing if students and rooms are already shown in
     * separate tabs.
     */
    void separateStudentsAndRooms() {
        if (studentsAndRoomsAreCombined) {
            tabPane.getTabs().remove(0);
            addSeparateTabs();
        }
    }

    /**
     * Toggles between showing students and rooms in two separate tabs, and showing students and rooms in one tab.
     */
    void toggleStudentsRoomsTabSplit() {
        if (studentsAndRoomsAreCombined) {
            separateStudentsAndRooms();
        } else {
            combineStudentsAndRooms();
        }
    }
}
