package seedu.resireg.ui;

import java.util.Arrays;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.logic.Logic;
import seedu.resireg.logic.commands.TabView;
import seedu.resireg.ui.binitem.BinItemListPanel;
import seedu.resireg.ui.room.RoomListPanel;
import seedu.resireg.ui.student.StudentListPanel;

/**
 * Represents the tabbed main panel for the {@code MainWindow}.
 */
public class MainPanel extends UiPart<Region> {
    private static final String FXML = "MainPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(MainPanel.class);

    private boolean studentsAndRoomsAreCombined;

    private StudentsTab studentsTab;
    private RoomsTab roomsTab;

    @FXML
    private Tab binsTab;

    private StudentListPanel studentListPanel;
    private RoomListPanel roomListPanel;
    private BinItemListPanel binItemListPanel;

    @FXML
    private TabPane tabPane;
    @FXML
    private StackPane binItemListPanelPlaceholder;

    /**
     * Creates a new MainPanel. The rooms and students lists will be in separate tabs.
     */
    public MainPanel() {
        super(FXML);

        addSeparateStudentsRoomsTabs();
        showStudentPanel();
        logger.info("Created MainPanel");
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
     * Changes the tab displayed based on the toggle.
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
        case BIN_ITEMS:
            showTab(binsTab);
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

        binItemListPanel = new BinItemListPanel(logic.getFilteredBinItemList());

        studentsTab.setStudentListPanel(studentListPanel);
        roomsTab.setRoomListPanel(roomListPanel);

        binItemListPanelPlaceholder.getChildren()
                .add(binItemListPanel.getRoot());
    }

    /**
     * Adds a combined students and rooms tab as the first tab in the tab pane. Does not check whether such a tab or
     * separate rooms and students tabs already exist.
     */
    private void addCombinedStudentsRoomsTab() {
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
    private void addSeparateStudentsRoomsTabs() {
        StudentsOnlyTab studentsOnlyTab = new StudentsOnlyTab();
        RoomsOnlyTab roomsOnlyTab = new RoomsOnlyTab();
        tabPane.getTabs().addAll(0, Arrays.asList(
                studentsOnlyTab.getRoot(), roomsOnlyTab.getRoot()));

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
            addCombinedStudentsRoomsTab();
            showStudentPanel();
        }
    }

    /**
     * Shows students and rooms in two separate tabs. Does nothing if students and rooms are already shown in
     * separate tabs.
     */
    void separateStudentsAndRooms() {
        if (studentsAndRoomsAreCombined) {
            tabPane.getTabs().remove(0);
            addSeparateStudentsRoomsTabs();
            showStudentPanel();
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
