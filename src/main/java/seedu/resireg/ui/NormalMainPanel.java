package seedu.resireg.ui;

import java.util.Arrays;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.logic.Logic;
import seedu.resireg.logic.commands.TabView;

/**
 * Represents the main panel when the app is in {@code AppMode.NORMAL} mode.
 */
public class NormalMainPanel extends MainPanel {
    private static final String FXML = "NormalMainPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(NormalMainPanel.class);
    private boolean studentsAndRoomsAreCombined;

    private StudentsTab studentsTab;
    private RoomsTab roomsTab;
    private PiechartsTab piechartsTab;

    @FXML
    private Tab binsTab;

    private StudentListPanel studentListPanel;
    private RoomListPanel roomListPanel;
    private BinItemListPanel binItemListPanel;
    private PiechartPanel piechartPanel;

    @FXML
    private TabPane tabPane;
    @FXML
    private StackPane binItemListPanelPlaceholder;

    /**
     * Creates a new NormalMainPanel. The rooms and students lists will be in separate tabs.
     */
    public NormalMainPanel() {
        super(FXML);

        // add pie chart tab
        PiechartsOnlyTab piechartsOnlyTab = new PiechartsOnlyTab();
        tabPane.getTabs().add(0, piechartsOnlyTab.getRoot());
        piechartsTab = piechartsOnlyTab;

        addSeparateStudentsRoomsTabs();
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

    private void showPiechartsPanel() {
        showTab(piechartsTab.getTab());
    }

    /**
     * Changes the tab displayed based on the toggle.
     *
     * @param toggleView Enum representing what should be displayed.
     */
    @Override
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
        case PIECHART:
            showPiechartsPanel();
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
    @Override
    void updatePanels(Logic logic) {
        studentListPanel = new StudentListPanel(
                logic.getFilteredStudentList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredRoomList());

        roomListPanel = new RoomListPanel(
                logic.getFilteredRoomList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredStudentList());

        piechartPanel = new PiechartPanel(
                logic.getFilteredStudentList(),
                logic.getFilteredAllocationList(),
                logic.getFilteredRoomList());

        binItemListPanel = new BinItemListPanel(logic.getFilteredBinItemList());

        studentsTab.setStudentListPanel(studentListPanel);
        roomsTab.setRoomListPanel(roomListPanel);
        piechartsTab.setPiechartPanel(piechartPanel);

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
        }
    }

    /**
     * Toggles between showing students and rooms in two separate tabs, and showing students and rooms in one tab.
     */
    @Override
    void toggleStudentsRoomsTabSplit() {
        if (studentsAndRoomsAreCombined) {
            separateStudentsAndRooms();
        } else {
            combineStudentsAndRooms();
        }
    }
}
