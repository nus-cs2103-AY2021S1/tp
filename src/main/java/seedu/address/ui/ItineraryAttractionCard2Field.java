package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.itinerary.ItineraryAttraction;

public class ItineraryAttractionCard2Field extends ItineraryAttractionCard {

    private static final String FXML = "ItineraryAttractionListCard2Field.fxml";

    @FXML
    private Label field1;
    @FXML
    private Label field2;

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public ItineraryAttractionCard2Field(ItineraryAttraction itineraryAttraction) {
        super(itineraryAttraction, FXML);

        //optional fields

        int fieldsFilled = 0;

        if (!itineraryAttraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + itineraryAttraction.getAddress().value;
            field1.setText(address);

            fieldsFilled++;
        }

        if (!itineraryAttraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + itineraryAttraction.getPhone().value;
            if (fieldsFilled == 0) {
                field1.setText(phone);
            } else {
                field2.setText(phone);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + itineraryAttraction.getEmail().value;
            if (fieldsFilled == 0) {
                field1.setText(email);
            } else if (fieldsFilled == 1) {
                field2.setText(email);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83D\uDD56 " + itineraryAttraction.getOpeningHours().value;
            if (fieldsFilled == 0) {
                field1.setText(openingHours);
            } else if (fieldsFilled == 1) {
                field2.setText(openingHours);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getDescription().value.isEmpty()) {
            String description = itineraryAttraction.getDescription().value;
            if (fieldsFilled == 0) {
                field1.setText(description);
            } else {
                field2.setText(description);
            }
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
    }

}
