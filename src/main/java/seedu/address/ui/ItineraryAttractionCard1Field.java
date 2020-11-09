package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.itinerary.ItineraryAttraction;

public class ItineraryAttractionCard1Field extends ItineraryAttractionCard {

    private static final String FXML = "ItineraryAttractionListCard1Field.fxml";

    @FXML
    private Label field1;

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public ItineraryAttractionCard1Field(ItineraryAttraction itineraryAttraction) {
        super(itineraryAttraction, FXML);

        //optional fields
        if (!itineraryAttraction.getDescription().value.isEmpty()) {
            String description = itineraryAttraction.getDescription().value;
            field1.setText(description);
            field1.setWrapText(true);
        } else if (!itineraryAttraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + itineraryAttraction.getAddress().value;
            field1.setText(address);
            field1.setWrapText(true);
        } else if (!itineraryAttraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + itineraryAttraction.getPhone().value;
            field1.setText(phone);
            field1.setWrapText(true);
        } else if (!itineraryAttraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + itineraryAttraction.getEmail().value;
            field1.setText(email);
            field1.setWrapText(true);
        } else if (!itineraryAttraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83D\uDD56 " + itineraryAttraction.getOpeningHours().value;
            field1.setText(openingHours);
            field1.setWrapText(true);
        }
    }

}
