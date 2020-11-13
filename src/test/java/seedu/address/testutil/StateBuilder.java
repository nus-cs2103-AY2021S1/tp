package seedu.address.testutil;

import seedu.address.state.Page;
import seedu.address.state.State;
import seedu.address.state.StateManager;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;

public class StateBuilder {
    public static final BudgetIndex DEFAULT_BUDGET_INDEX = new BudgetIndexManager(0);
    public static final Page MAIN_PAGE = Page.MAIN;
    public static final Page BUDGET_PAGE = Page.BUDGET;
    public static final String DEFAULT_PAGE_TITLE = "NUSave";

    private BudgetIndex budgetIndex;
    private Page currentPage;
    private String pageTitle;

    /**
     * Creates a {@code StateBuilder} with a threshold using the default details.
     */
    public StateBuilder() {
        budgetIndex = DEFAULT_BUDGET_INDEX;
        currentPage = MAIN_PAGE;
        this.pageTitle = DEFAULT_PAGE_TITLE;
    }

    /**
     * Sets the {@code budgetIndex} of the {@code State} that we are building.
     */
    public StateBuilder withBudgetIndex(BudgetIndex budgetIndex) {
        this.budgetIndex = budgetIndex;
        return this;
    }

    /**
     * Sets the {@code currentPage} of the {@code State} that we are building.
     */
    public StateBuilder withCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    /**
     * Sets the {@code pageTitle} of the {@code State} that we are building.
     */
    public StateBuilder withPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        return this;
    }

    public State build() {
        return new StateManager(budgetIndex, currentPage, pageTitle);
    }
}
