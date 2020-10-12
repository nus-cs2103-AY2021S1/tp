package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.budget.Budget;

public class BudgetDisplay extends UiPart<Region> {

    private static final String FXML = "BudgetDisplay.fxml";
    private static final String BUDGET_BALANCE = "Budget balance: $%.02f / $%.02f";
    private final ReadOnlyAddressBook addressBook;

    @FXML
    private TextArea budgetDisplay;

    public BudgetDisplay(ReadOnlyAddressBook addressBook) {
        super(FXML);

        requireNonNull(addressBook);
        this.addressBook = addressBook;
    }

    private String budgetBalance() {
        Budget budget = addressBook.getBudget();
        double budgetAmount = budget.getAmount().asDouble();
        double expensesSum = addressBook.tallyExpenses();
        double balance = budgetAmount - expensesSum;
        return String.format(BUDGET_BALANCE, balance, budgetAmount);
    }

    public void setFeedbackToUser() {
        budgetDisplay.setText(budgetBalance());
    }

}
