package seedu.address.ui.seller;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.seller.Seller;
import seedu.address.ui.UiPart;

public class SellerListPanel extends UiPart<Region> {
    private static final String FXML = "seller/SellerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SellerListPanel.class);

    @FXML
    private ListView<Seller> sellerListView;

    /**
     * Creates a {@code SellerListPanel} with the given {@code ObservableList}.
     */

    public SellerListPanel(ObservableList<Seller> sellerList) {
        super(FXML);
        sellerListView.setItems(sellerList);
        sellerListView.setCellFactory(listView -> new SellerListPanel.SellerListViewCell());
    }



    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Seller} using a {@code SellerCard}.
     */
    class SellerListViewCell extends ListCell<Seller> {
        @Override
        protected void updateItem(Seller seller, boolean empty) {
            super.updateItem(seller, empty);

            if (empty || seller == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SellerCard(seller, getIndex() + 1).getRoot());
            }
        }
    }

}

