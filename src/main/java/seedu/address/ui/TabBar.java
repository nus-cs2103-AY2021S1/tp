package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.EntityType;
import seedu.address.ui.bid.BidListPanel;
import seedu.address.ui.bidder.BidderListPanel;
import seedu.address.ui.property.PropertyListPanel;
import seedu.address.ui.seller.SellerListPanel;


public class TabBar extends UiPart<Region> {

    private static final String FXML = "TabBar.fxml";
    private static final String FXML2 = "PersonListPanel.fxml";
    private static final String FXML3 = "BidListPanel.fxml";
    private static final String FXML4 = "BidderListPanel.fxml";
    private static final String FXML5 = "SellerListPanel.fxml";
    private static final String FXML6 = "property/PropertyListPanel.fxml";

    private Logic logic;

    @FXML
    private TabPane tabBar;

    @FXML
    private Tab sellerTab;

    @FXML
    private Tab bidderTab;

    @FXML
    private Tab bidTab;

    @FXML
    private Tab propertyTab;

    @FXML
    private StackPane sellerListPanelPlaceholder;

    @FXML
    private StackPane bidderListPanelPlaceholder;

    @FXML
    private StackPane bidListPanelPlaceholder;

    @FXML
    private StackPane propertyListPanelPlaceholder;

    /**
     * Creates a {@code TabBar} with the given {@code Logic}.
     */
    public TabBar(Logic logic) {
        super(FXML);
        this.logic = logic;
        setTabBar();
        populateTab();
    }

    /**
     * Initialises all tabs on the tabpane.
     */

    private void setTabBar() {
        bidTab.setText("Bids");
        bidderTab.setText("Bidders");
        sellerTab.setText("Sellers");
        propertyTab.setText("Properties");
        tabBar.setTabMinWidth(335);
        tabBar.setTabMaxWidth(335);
    }

    /**
     * Fills the tabs with a list of applicants or jobs.
     */
    private void populateTab() {
        BidListPanel bidListPanel = new BidListPanel(logic.getFilteredBidList());
        PropertyListPanel propertyListPanel = new PropertyListPanel(logic.getFilteredPropertyList());
        BidderListPanel bidderListPanel = new BidderListPanel(logic.getFilteredBidderList());
        SellerListPanel sellerListPanel = new SellerListPanel(logic.getFilteredSellerList());
        bidListPanelPlaceholder.getChildren().add(bidListPanel.getRoot());
        bidderListPanelPlaceholder.getChildren().add(bidderListPanel.getRoot());
        sellerListPanelPlaceholder.getChildren().add(sellerListPanel.getRoot());
        propertyListPanelPlaceholder.getChildren().add(propertyListPanel.getRoot());
    }

    public void setTab(EntityType entityType) {
        if (entityType == EntityType.BID) {
            tabBar.getSelectionModel().select(bidTab);
        }
        if (entityType == EntityType.BIDDER) {
            tabBar.getSelectionModel().select(bidderTab);
        }
        if (entityType == EntityType.SELLER) {
            tabBar.getSelectionModel().select(sellerTab);
        }
        if (entityType == EntityType.PROPERTY) {
            tabBar.getSelectionModel().select(propertyTab);
        }
    }
}

