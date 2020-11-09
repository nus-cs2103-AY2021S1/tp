package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.attraction.Attraction;

public class AttractionCard2Field extends AttractionCard {

    private static final String FXML = "AttractionListCard2Field.fxml";

    @FXML
    private Label field1;
    @FXML
    private Label field2;


    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public AttractionCard2Field(Attraction attraction, int displayedIndex) {
        super(attraction, displayedIndex, FXML);

        //optional fields

        int fieldsFilled = 0;

        if (!attraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + attraction.getAddress().value;
            field1.setText(address);

            fieldsFilled++;
        }

        if (!attraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + attraction.getPhone().value;
            if (fieldsFilled == 0) {
                field1.setText(phone);
            } else {
                field2.setText(phone);
            }
            fieldsFilled++;
        }

        if (!attraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + attraction.getEmail().value;
            if (fieldsFilled == 0) {
                field1.setText(email);
            } else if (fieldsFilled == 1) {
                field2.setText(email);
            }
            fieldsFilled++;
        }

        if (!attraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83D\uDD56 " + attraction.getOpeningHours().value;
            if (fieldsFilled == 0) {
                field1.setText(openingHours);
            } else if (fieldsFilled == 1) {
                field2.setText(openingHours);
            }
            fieldsFilled++;
        }

        if (!attraction.getDescription().value.isEmpty()) {
            String description = attraction.getDescription().value;
            if (fieldsFilled == 0) {
                field1.setText(description);
            } else if (fieldsFilled == 1) {
                field2.setText(description);
            }
            fieldsFilled++;
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
    }

}
