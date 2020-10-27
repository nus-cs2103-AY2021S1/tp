package seedu.address.ui.property;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.property.Property;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Property}.
 */
public class PropertyCard extends UiPart<Region> {

    private static final String FXML = "property/PropertyListCard.fxml";

    public final Property property;

    @FXML
    private HBox cardPane;
    @FXML
    private Text propertyName;
    @FXML
    private Label id;
    @FXML
    private Label propertyId;
    @FXML
    private Label address;
    @FXML
    private Label sellerId;
    @FXML
    private Label askingPrice;
    @FXML
    private Label propertyType;
    @FXML
    private Label isRental;

    /**
     * Creates a {@code PropertyCard} with the given {@code Property} and index to display.
     */
    public PropertyCard(Property property, int displayedIndex) {
        super(FXML);
        this.property = property;
        id.setText(displayedIndex + ". ");
        propertyId.setText("Property Id: " + property.getPropertyId().toString());
        propertyName.setText(property.getPropertyName().toString());
        propertyName.setWrappingWidth(150);
        address.setText("Address: " + property.getAddress().toString());
        sellerId.setText("Seller Id: " + property.getSellerId().toString());
        askingPrice.setText("Asking price: " + property.getAskingPrice().toString());
        propertyType.setText("Property type: " + property.getPropertyType().toString());
        isRental.setText("Is rental: " + property.getIsRental().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PropertyCard)) {
            return false;
        }

        // state check
        PropertyCard card = (PropertyCard) other;
        return id.getText().equals(card.id.getText())
                && property.equals(card.property);
    }

}
