package seedu.homerce.ui.revenuepanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.ui.UiPart;

public class RevenueCard extends UiPart<Region> {

    private static final String FXML = "revenuepanel/RevenueListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable titles cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Revenue revenue;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label price;
    @FXML
    private Label date;

    /**
     * Creates a {@code ServiceCode} with the given {@code Service} and index to display.
     */
    public RevenueCard(Revenue revenue, int displayedIndex) {
        super(FXML);
        this.revenue = revenue;
        id.setText("R" + displayedIndex);
        title.setText("Description: " + revenue.getService().getTitle().value);
        price.setText("Value: $" + revenue.getValue().value);
        date.setText(revenue.getDate().toUiString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RevenueCard)) {
            return false;
        }

        // state check
        RevenueCard card = (RevenueCard) other;
        return id.getText().equals(card.id.getText())
            && revenue.equals(card.revenue);
    }

}
