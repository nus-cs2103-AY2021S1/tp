package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;


public class TabBar extends UiPart<Region> {

    private static final String FXML = "TabBar.fxml";
    private static final String FXML2 = "PersonListPanel.fxml";
    private static final String FXML3 = "BidListPanel.fxml";
    private static final String FXML4 = "CalendarListPanel.fxml";

    private Logic logic;

    @FXML
    private TabPane personCalenderBidTabBar;

    @FXML
    private Tab personTab;

    @FXML
    private Tab bidTab;

    @FXML
    private Tab calenderTab;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane bidListPanelPlaceholder;

    @FXML
    private StackPane meetingListPanelPlaceholder;


    /**
     * Creates a {@code TabBar} with the given {@code Logic}.
     */
    public TabBar(Logic logic) {
        super(FXML);
        this.logic = logic;
        setPersonCalenderBidTabBar();
        populateTab();
    }

    /**
     * Initialises the two tabs on the tabpane.
     */
    private void setPersonCalenderBidTabBar() {
        personTab.setText("AddressBooks");
        bidTab.setText("Bids");
        calenderTab.setText("Calendar");
        personCalenderBidTabBar.setTabMinWidth(335);
        personCalenderBidTabBar.setTabMaxWidth(335);
    }

    /**
     * Fills the tabs with a list of applicants or jobs.
     */
    private void populateTab() {
        PersonListPanel personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        BidListPanel bidListPanel = new BidListPanel(logic.getFilteredBidList());
        CalendarListPanel calendarListPanel = new CalendarListPanel(logic.getFilteredMeetingList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        bidListPanelPlaceholder.getChildren().add(bidListPanel.getRoot());
        meetingListPanelPlaceholder.getChildren().add(calendarListPanel.getRoot());
    }
}

