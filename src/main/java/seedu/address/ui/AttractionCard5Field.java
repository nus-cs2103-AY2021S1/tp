package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.attraction.Attraction;

public class AttractionCard5Field extends AttractionCard {

    private static final String FXML = "AttractionListCard5Field.fxml";

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
    public AttractionCard5Field(Attraction attraction, int displayedIndex) {
        super(attraction, displayedIndex, FXML);

        //optional fields

        if (!attraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + attraction.getAddress().value;
            field1.setText(address);
        }

        if (!attraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + attraction.getPhone().value;
            field2.setText(phone);
        }

        if (!attraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + attraction.getEmail().value;
            field3.setText(email);
        }

        if (!attraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83D\uDD56 " + attraction.getOpeningHours().value;
            field4.setText(openingHours);
        }

        if (!attraction.getDescription().value.isEmpty()) {
            String description = attraction.getDescription().value;
            field5.setText(description);
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
        field3.setWrapText(true);
        field4.setWrapText(true);
        field5.setWrapText(true);

    }

}
