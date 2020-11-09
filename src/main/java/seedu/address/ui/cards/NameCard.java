package seedu.address.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays a name with an index.
 */
public class NameCard extends UiPart<Region> {
    private static final String FXML = "cards/NameCard.fxml";

    @FXML
    private Label id;
    @FXML
    private Label name;

    /**
     * Creates a {@code NameCard} with the given {@code value} and index to display.
     */
    public NameCard(String value, int displayedIndex) {
        super(FXML);
        id.setText(displayedIndex + ". ");
        name.setText(value);
    }
}
