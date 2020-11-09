package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.attraction.Attraction;

public class AttractionCard1Field extends AttractionCard {

    private static final String FXML = "AttractionListCard1Field.fxml";

    @FXML
    private Label field1;

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public AttractionCard1Field(Attraction attraction, int displayedIndex) {
        super(attraction, displayedIndex, FXML);

        //optional fields
        if (!attraction.getDescription().value.isEmpty()) {
            String description = attraction.getDescription().value;
            field1.setText(description);
        } else if (!attraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + attraction.getAddress().value;
            field1.setText(address);
        } else if (!attraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + attraction.getPhone().value;
            field1.setText(phone);
        } else if (!attraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + attraction.getEmail().value;
            field1.setText(email);
        } else if (!attraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83D\uDD56 " + attraction.getOpeningHours().value;
            field1.setText(openingHours);
        }

        field1.setWrapText(true);
    }

}
