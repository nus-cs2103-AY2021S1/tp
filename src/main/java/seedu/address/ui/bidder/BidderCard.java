package seedu.address.ui.bidder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.ui.UiPart;

public class BidderCard extends UiPart<Region> {

    private static final String FXML = "bidder/BidderListCard.fxml";

    public final Bidder bidder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private FlowPane tags;
    @FXML
    private Label bidderId;

    /**
     * Creates a {@code BidderCode} with the given {@code Bidder} and index to display.
     */
    public BidderCard(Bidder bidder, int displayedIndex) {
        super(FXML);
        this.bidder = bidder;
        id.setText(displayedIndex + ". ");
        name.setText(bidder.getName().fullName);
        phone.setText(bidder.getPhone().value);
        name.setText("Name: " + bidder.getName().fullName);
        phone.setText("Phone: " + bidder.getPhone().value);
        bidderId.setText("Bidder Id: " + bidder.getId());
        tags.getChildren().add(new Label(bidder.getTag().tagName));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BidderCard)) {
            return false;
        }

        // state check
        BidderCard card = (BidderCard) other;
        return id.getText().equals(card.id.getText())
                && bidder.equals(card.bidder);
    }

}
