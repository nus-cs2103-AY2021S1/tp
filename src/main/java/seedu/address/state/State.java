package seedu.address.state;

import java.util.Optional;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import seedu.address.model.budget.Threshold;
import seedu.address.state.budgetindex.BudgetIndex;

public interface State {

    Optional<Integer> getBudgetIndex();

    Page getPage();

    BooleanProperty getIsBudgetPageProp();

    StringProperty getInfoBoxSecondRowProp();

    StringProperty getThresholdStringProp();

    boolean isBudgetPage();

    String getPageTitle();

    boolean isMain();

    boolean isBudget();

    void setIsBudgetPage(boolean isExpenditure);

    void setPage(Page page);

    void setTotalExpenditure(String expenditure);

    void setThresholdStringProp(String threshold);

    void setBudgetIndex(BudgetIndex index);

    void setPageTitle(String pageTitle);

    void setOpenCommandState(String pageName, String newExpenditureValue, Optional<Threshold> newThreshold);

    void setCloseCommandState();
}
