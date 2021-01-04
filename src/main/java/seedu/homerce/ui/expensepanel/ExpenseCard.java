package seedu.homerce.ui.expensepanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.ui.UiPart;

public class ExpenseCard extends UiPart<Region> {

    private static final String FXML = "expensepanel/ExpenseListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable titles cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Expense expense;

    @FXML
    private HBox cardPane;

    @FXML
    private Label id;

    @FXML
    private Label description;

    @FXML
    private Label isFixed;

    @FXML
    private Label value;

    @FXML
    private Label date;

    @FXML
    private Label tag;

    /**
     * Creates a {@code ServiceCode} with the given {@code Service} and index to display.
     */
    public ExpenseCard(Expense expense, int displayedIndex) {
        super(FXML);
        this.expense = expense;
        id.setText("E" + displayedIndex);
        description.setText("Description: " + expense.getDescription().toString());
        //description.setStyle("-fx-font-weight: bold;");
        isFixed.setText("Type: " + (expense.getIsFixed().value ? "Fixed" : "Not Fixed"));
        value.setText("Value: " + expense.getValue().value);
        date.setText(expense.getDate().toUiString());
        tag.setText(expense.getTag().toUiString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpenseCard)) {
            return false;
        }

        // state check
        ExpenseCard card = (ExpenseCard) other;
        return id.getText().equals(card.id.getText())
                && expense.equals(card.expense);
    }

}
