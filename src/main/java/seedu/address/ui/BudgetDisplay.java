package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.budget.Budget;

/**
 * A ui for the budget balance to be displayed to the user.
 */
public class BudgetDisplay extends UiPart<Region> {

    private static final String FXML = "BudgetDisplay.fxml";
    private static final String BUDGET_BALANCE = "$%.02f / $%.02f";
    private static final String HEADER_MESSAGE = "Budget balance:";
    private static final String GREEN_BAR_STYLE_CLASS = "green-bar";
    private static final String ORANGE_BAR_STYLE_CLASS = "orange-bar";
    private static final String RED_BAR_STYLE_CLASS = "red-bar";
    private final ReadOnlyAddressBook addressBook;

    @FXML
    private HBox budgetPlaceHolder;

    @FXML
    private Label budgetHeader;

    @FXML
    private StackPane budgetBarHolder;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label budgetDisplay;

    /**
     * Constructor for {@code BudgetDisplay}.
     * @param addressBook addressBook to be stored.
     */
    public BudgetDisplay(ReadOnlyAddressBook addressBook) {
        super(FXML);

        requireNonNull(addressBook);
        this.addressBook = addressBook;

        budgetHeader.setText(HEADER_MESSAGE);

        progressBar.setProgress(getProgress());
        setBarStyle();

        budgetDisplay.setText(budgetBalance());
        budgetBarHolder.getChildren().setAll(progressBar, budgetDisplay);

        budgetPlaceHolder.getChildren().setAll(budgetHeader, budgetBarHolder);
    }

    /**
     * Returns the progress of budget balance.
     *
     * @return Progress as double.
     */
    private double getProgress() {
        Budget budget = addressBook.getBudget();
        double budgetAmount = budget.getAmount().asDouble();
        double expensesSum = addressBook.tallyExpenses();
        return 1 - expensesSum / budgetAmount;

    }

    /**
     * Returns budget balance after deducting expenses, as a fraction of total budget.
     *
     * @return Formatted budget balance as String.
     */
    private String budgetBalance() {
        Budget budget = addressBook.getBudget();
        double budgetAmount = budget.getAmount().asDouble();
        double expensesSum = addressBook.tallyExpenses();
        double balance = budgetAmount - expensesSum;
        return String.format(BUDGET_BALANCE, balance, budgetAmount);
    }

    /**
     * Sets bar colour as green if progress > 0.5, orange if 0.25 < progress < 0.5
     * and red if progress < 0.25
     */
    private void setBarStyle() {
        resetBarStyle();
        if (getProgress() > 0.5) {
            this.progressBar.getStyleClass().add(GREEN_BAR_STYLE_CLASS);
        } else if (getProgress() > 0.25) {
            this.progressBar.getStyleClass().add(ORANGE_BAR_STYLE_CLASS);
        } else {
            this.progressBar.getStyleClass().add(RED_BAR_STYLE_CLASS);
        }
    }

    /**
     * Resets the style class of progressBar in order to update colours accordingly.
     */
    private void resetBarStyle() {
        this.progressBar.getStyleClass().remove(GREEN_BAR_STYLE_CLASS);
        this.progressBar.getStyleClass().remove(ORANGE_BAR_STYLE_CLASS);
        this.progressBar.getStyleClass().remove(RED_BAR_STYLE_CLASS);
    }

    /**
     * Sets text display to the user and updates the progress bar.
     */
    public void setFeedbackToUser() {
        budgetDisplay.setText(budgetBalance());
        progressBar.setProgress(getProgress());
        setBarStyle();
    }

}
