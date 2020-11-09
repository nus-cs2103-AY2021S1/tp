package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.vendor.Vendor;

/**
 * An UI component that displays information of a {@code Vendor}.
 */
public class VendorCard extends UiPart<Region> {

    private static final String FXML = "VendorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on VendorManager level 4</a>
     */

    public final Vendor vendor;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Vendor} and index to display.
     */
    public VendorCard(Vendor vendor, int displayedIndex) {
        super(FXML);
        this.vendor = vendor;
        id.setText(displayedIndex + ". ");
        name.setText(vendor.getName().fullName);
        phone.setText(vendor.getPhone().value);
        address.setText(vendor.getAddress().value);
        email.setText(vendor.getEmail().value);
        vendor.getTags().stream()
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
        if (!(other instanceof VendorCard)) {
            return false;
        }

        // state check
        VendorCard card = (VendorCard) other;
        return id.getText().equals(card.id.getText())
                && vendor.equals(card.vendor);
    }
}
