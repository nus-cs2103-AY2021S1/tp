package seedu.expense.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.expense.commons.core.LogsCenter;
import seedu.expense.model.Statistics;

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
    private final Statistics statistics;
    private final Logger logger = LogsCenter.getLogger(getClass());

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
     * @param statistics expenseBook to be stored.
     */
    public BudgetDisplay(Statistics statistics) {
        super(FXML);

        requireNonNull(statistics);
        this.statistics = statistics;

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
        double budgetAmount = statistics.tallyBudgets();
        assert budgetAmount >= 0;
        double expensesSum = statistics.tallyExpenses();
        assert expensesSum >= 0;
        return 1 - expensesSum / budgetAmount;

    }

    /**
     * Returns budget balance after deducting expenses, as a fraction of total budget.
     *
     * @return Formatted budget balance as String.
     */
    private String budgetBalance() {
        double budgetAmount = statistics.tallyBudgets();
        double balance = statistics.tallyBalance();
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
        assert getProgress() <= 1;
        double initialProgress = progressBar.getProgress();
        double finalProgress = getProgress();

        if (initialProgress != finalProgress) {
            budgetDisplay.setText(budgetBalance());
            progressBar.setProgress(getProgress());
            setBarStyle();
            logger.info("Progress bar updated. Current progress: "
                    + String.format("%.02f", getProgress()));
        }

    }

}
