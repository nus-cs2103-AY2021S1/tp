package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * An UI component that displays information of a {@code ItineraryAttraction}.
 */
public class ItineraryAttractionCard extends UiPart<Region> {

    public final ItineraryAttraction itineraryAttraction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label startEndTime;

    @FXML
    // For JavaFX, rename location to locale as location is a reserved keyword in JavaFX
    private Label locale;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItineraryAttractionCode} with the given {@code ItineraryAttraction} and index to display.
     */
    public ItineraryAttractionCard(ItineraryAttraction itineraryAttraction, String fxml) {
        super(fxml);

        this.itineraryAttraction = itineraryAttraction;

        id.setText(itineraryAttraction.getIndex() + ". ");

        name.setText(itineraryAttraction.getName().fullName);
        name.setWrapText(true);

        locale.setText("\uD83C\uDF0E " + itineraryAttraction.getLocation().value);
        locale.setWrapText(true);

        startEndTime.setText(itineraryAttraction.getStartTime().toString()
                + " - "
                + itineraryAttraction.getEndTime().toString());
        startEndTime.setWrapText(true);

        if (itineraryAttraction.getPriceRange().toString() != "") {
            Label priceRange = new Label(itineraryAttraction.getPriceRange().toString());
            priceRange.setStyle("-fx-background-color: #800;");
            tags.getChildren().add(priceRange);
        }

        if (itineraryAttraction.getRating().toString() != "") {
            Label rating = new Label(itineraryAttraction.getRating().toString() + "\u2605");
            rating.setStyle("-fx-background-color: #080;");
            tags.getChildren().add(rating);
        }

        if (itineraryAttraction.getVisited().toString() != "") {
            Label visited = new Label(itineraryAttraction.getVisited().toString());
            visited.setText(itineraryAttraction.getVisited().toString());
            visited.setStyle("-fx-background-color: #9933ff;");
            tags.getChildren().add(visited);
        }

        itineraryAttraction.getTags().stream()
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
        if (!(other instanceof ItineraryAttractionCard)) {
            return false;
        }

        // state check
        ItineraryAttractionCard card = (ItineraryAttractionCard) other;
        return id.getText().equals(card.id.getText())
                && itineraryAttraction.equals(card.itineraryAttraction)
                && startEndTime.getText().equals(card.startEndTime.getText());
    }
}
