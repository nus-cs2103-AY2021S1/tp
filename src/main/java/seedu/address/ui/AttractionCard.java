package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attraction.Attraction;

/**
 * An UI component that displays information of a {@code Attraction}.
 */
public class AttractionCard extends UiPart<Region> {

    public final Attraction attraction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;

    @FXML
    // For JavaFX, rename location to locale as location is a reserved keyword in JavaFX
    private Label locale;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code AttractionCode} with the given {@code FXML}.
     */
    public AttractionCard(Attraction attraction, int displayedIndex, String fxml) {
        super(fxml);
        this.attraction = attraction;
        id.setText(displayedIndex + ". ");

        name.setText(attraction.getName().fullName);
        name.setWrapText(true);

        locale.setText("\uD83C\uDF0E " + attraction.getLocation().value);
        locale.setWrapText(true);

        if (attraction.getPriceRange().toString() != "") {
            Label priceRange = new Label(attraction.getPriceRange().toString());
            priceRange.setStyle("-fx-background-color: #800;");
            tags.getChildren().add(priceRange);
        }

        if (attraction.getRating().toString() != "") {
            Label rating = new Label(attraction.getRating().toString() + "\u2605");
            rating.setStyle("-fx-background-color: #080;");
            tags.getChildren().add(rating);
        }

        if (attraction.getVisited().toString() != "") {
            Label visited = new Label(attraction.getVisited().toString());
            visited.setText(attraction.getVisited().toString());
            visited.setStyle("-fx-background-color: #9933ff;");
            tags.getChildren().add(visited);
        }

        attraction.getTags().stream()
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
        if (!(other instanceof AttractionCard)) {
            return false;
        }

        // state check
        AttractionCard card = (AttractionCard) other;
        return id.getText().equals(card.id.getText())
                && attraction.equals(card.attraction);
    }
}
