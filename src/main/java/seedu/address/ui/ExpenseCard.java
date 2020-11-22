package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.expense.Expense;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ExpenseCard extends UiPart<Region> {

    private static final String FXML = "ExpenseListCard.fxml";


    public final Expense expense;

    @FXML
    private HBox cardPane;
    @FXML
    private Label amount;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label category;
    @FXML
    private Label description;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ExpenseCard(Expense expense, int displayedIndex) {
        super(FXML);
        this.expense = expense;
        id.setText(displayedIndex + ". ");
        amount.setText(String.format("%s %s", expense.getAmount(), expense.getCurrency()));
        date.setText(expense.getDate().date);
        category.setText("Category: " + expense.getCategory().categoryName);
        description.setText("Description: " + expense.getDescription().value);
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
