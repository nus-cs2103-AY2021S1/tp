package seedu.address.ui.seller;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.seller.Seller;
import seedu.address.ui.UiPart;

public class SellerCard extends UiPart<Region> {

    private static final String FXML = "seller/SellerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */


    public final Seller seller;

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
    private Label sellerId;

    /**
     * Creates a {@code SellerCode} with the given {@code Seller} and index to display.
     */
    public SellerCard(Seller seller, int displayedIndex) {
        super(FXML);
        this.seller = seller;
        id.setText(displayedIndex + ". ");
        name.setText("Name: " + seller.getName().fullName);
        phone.setText("Phone: " + seller.getPhone().value);
        sellerId.setText("Seller Id: " + seller.getId());
        seller.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SellerCard)) {
            return false;
        }

        // state check
        SellerCard card = (SellerCard) other;
        return id.getText().equals(card.id.getText())
                && seller.equals(card.seller);
    }

}
