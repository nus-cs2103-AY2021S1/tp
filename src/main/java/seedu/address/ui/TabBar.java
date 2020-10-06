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

    private Logic logic;

    @FXML
    private TabPane personAndBidTabBar;

    @FXML
    private Tab personTab;

    @FXML
    private Tab bidTab;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane bidListPanelPlaceholder;


    /**
     * Creates a {@code TabBar} with the given {@code Logic}.
     */
    public TabBar(Logic logic) {
        super(FXML);
        this.logic = logic;
        setPersonAndBidTabBar();
        populateTab();
    }

    /**
     * Initialises the two tabs on the tabpane.
     */
    private void setPersonAndBidTabBar() {
        personTab.setText("AddressBooks");
        bidTab.setText("Bids");
        personAndBidTabBar.setTabMinWidth(335);
        personAndBidTabBar.setTabMaxWidth(335);
    }

    /**
     * Fills the tabs with a list of applicants or jobs.
     */
    private void populateTab() {
        PersonListPanel personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        BidListPanel bidListPanel = new BidListPanel(logic.getFilteredBidList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        bidListPanelPlaceholder.getChildren().add(bidListPanel.getRoot());
    }
}

