package seedu.address.ui.bid;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.bid.Bid;
import seedu.address.ui.UiPart;


/**
 * An UI component that displays information of a {@code Bid}.
 */
public class BidCard extends UiPart<Region> {

    private static final String FXML = "bid/BidListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Bid bid;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label propertyId;
    @FXML
    private Label bidderId;
    @FXML
    private Label bidAmount;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BidCard(Bid bid, int displayedIndex) {
        super(FXML);
        this.bid = bid;
        id.setText(displayedIndex + ". ");
        propertyId.setText("Property Id: " + bid.getPropertyId());
        bidderId.setText("Bidder Id: " + bid.getBidderId());
        bidAmount.setText("Bid Amount: " + bid.getBidAmount());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BidCard)) {
            return false;
        }

        // state check
        BidCard card = (BidCard) other;
        return id.getText().equals(card.id.getText())
                && bid.equals(card.bid);
    }
}

