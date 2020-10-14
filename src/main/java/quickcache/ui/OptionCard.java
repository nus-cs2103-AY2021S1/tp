package quickcache.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code option}.
 */
public class OptionCard extends UiPart<Region> {

    private static final String FXML = "OptionCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on QuickCache level 4</a>
     */

    public final String option;

    @FXML
    private HBox cardPane;
    @FXML
    private Label optionPlaceholder;
    @FXML
    private Label id;

    /**
     * Creates a {@code OptionCard} with the given {@code Option} and index to display.
     */
    public OptionCard(String option, int displayedIndex) {
        super(FXML);
        this.option = option;
        id.setText(displayedIndex + ". ");
        optionPlaceholder.setText(option);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OptionCard)) {
            return false;
        }

        // state check
        OptionCard card = (OptionCard) other;
        return id.getText().equals(card.id.getText())
                && option.equals(card.option);
    }
}
