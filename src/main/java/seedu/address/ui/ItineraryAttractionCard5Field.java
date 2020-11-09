package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.itinerary.ItineraryAttraction;

public class ItineraryAttractionCard5Field extends ItineraryAttractionCard {

    private static final String FXML = "ItineraryAttractionListCard5Field.fxml";

    @FXML
    private Label field1;
    @FXML
    private Label field2;
    @FXML
    private Label field3;
    @FXML
    private Label field4;
    @FXML
    private Label field5;

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public ItineraryAttractionCard5Field(ItineraryAttraction itineraryAttraction) {
        super(itineraryAttraction, FXML);

        //optional fields

        if (!itineraryAttraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + itineraryAttraction.getAddress().value;
            field1.setText(address);
        }

        if (!itineraryAttraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + itineraryAttraction.getPhone().value;
            field2.setText(phone);
        }

        if (!itineraryAttraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + itineraryAttraction.getEmail().value;
            field3.setText(email);
        }

        if (!itineraryAttraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83D\uDD56 " + itineraryAttraction.getOpeningHours().value;
            field4.setText(openingHours);
        }

        if (!itineraryAttraction.getDescription().value.isEmpty()) {
            String description = itineraryAttraction.getDescription().value;
            field5.setText(description);
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
        field3.setWrapText(true);
        field4.setWrapText(true);
        field5.setWrapText(true);
    }
}
