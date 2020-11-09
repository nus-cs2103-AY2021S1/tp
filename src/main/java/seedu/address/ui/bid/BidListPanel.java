package seedu.address.ui.bid;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.bid.Bid;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of bids.
 */
public class BidListPanel extends UiPart<Region> {
    private static final String FXML = "bid/BidListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BidListPanel.class);

    @FXML
    private ListView<Bid> bidListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */

    public BidListPanel(ObservableList<Bid> bidList) {
        super(FXML);
        bidListView.setItems(bidList);
        bidListView.setCellFactory(listView -> new BidListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class BidListViewCell extends ListCell<Bid> { // person to bid
        @Override
        protected void updateItem(Bid bid, boolean empty) { //changed Person to Bid
            super.updateItem(bid, empty);

            if (empty || bid == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BidCard(bid, getIndex() + 1).getRoot());
            }
        }
    }

}

