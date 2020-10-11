package seedu.address.ui.bidder;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.ui.UiPart;

public class BidderListPanel extends UiPart<Region> {
    private static final String FXML = "BidderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BidderListPanel.class);

    @FXML
    private ListView<Bidder> sellerListView;

    /**
     * Creates a {@code BidderListPanel} with the given {@code ObservableList}.
     */

    public BidderListPanel(ObservableList<Bidder> sellerList) {
        super(FXML);
        sellerListView.setItems(sellerList);
        sellerListView.setCellFactory(listView -> new BidderListPanel.BidderListViewCell());
    }



    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Bidder} using a {@code BidderCard}.
     */
    class BidderListViewCell extends ListCell<Bidder> {
        @Override
        protected void updateItem(Bidder seller, boolean empty) {
            super.updateItem(seller, empty);

            if (empty || seller == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BidderCard(seller, getIndex() + 1).getRoot());
            }
        }
    }

}

