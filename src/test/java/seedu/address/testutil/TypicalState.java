package seedu.address.testutil;

import static seedu.address.model.budget.Threshold.NO_THRESHOLD_MESSAGE;

import seedu.address.model.budget.Threshold;
import seedu.address.state.Page;
import seedu.address.state.State;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;
import seedu.address.state.budgetindex.EmptyBudgetIndex;

public class TypicalState {

    public static final BudgetIndex MAIN_PAGE_BUDGET_INDEX = new EmptyBudgetIndex();
    public static final BudgetIndex MCDONALDS_BUDGET_INDEX = new BudgetIndexManager(0);
    public static final BudgetIndex KFC_BUDGET_INDEX = new BudgetIndexManager(1);
    public static final BudgetIndex SUBWAY_BUDGET_INDEX = new BudgetIndexManager(2);

    public static final Page ENUM_PAGE_MAIN = Page.MAIN;
    public static final Page ENUM_PAGE_BUDGET = Page.BUDGET;

    public static final String MAIN_PAGE_TITLE = "NUSave";
    public static final String MCDONALDS_PAGE_TITLE = "McDonalds";
    public static final String KFC_PAGE_TITLE = "KFC";
    public static final String SUBWAY_PAGE_TITLE = "Subway";

    public static final Threshold MAIN_THRESHOLD_VALUE = new Threshold(NO_THRESHOLD_MESSAGE);
    public static final Threshold MCDONALDS_THRESHOLD_VALUE = new Threshold("150");
    public static final String MCDONALDS_EXPENDITURE_VALUE = "100";

    public static final State TYPICAL_MAIN_PAGE_STATE = new StateBuilder().withBudgetIndex(MAIN_PAGE_BUDGET_INDEX)
            .withCurrentPage(ENUM_PAGE_MAIN).withPageTitle(MAIN_PAGE_TITLE).build();

    public static final State MCDONALDS_BUDGET_PAGE_STATE = new StateBuilder().withBudgetIndex(MCDONALDS_BUDGET_INDEX)
            .withCurrentPage(ENUM_PAGE_BUDGET).withPageTitle(MCDONALDS_PAGE_TITLE).build();

    public static final State KFC_BUDGET_PAGE_STATE = new StateBuilder().withBudgetIndex(KFC_BUDGET_INDEX)
            .withCurrentPage(ENUM_PAGE_BUDGET).withPageTitle(KFC_PAGE_TITLE).build();

    public static final State SUBWAY_BUDGET_PAGE_STATE = new StateBuilder().withBudgetIndex(SUBWAY_BUDGET_INDEX)
            .withCurrentPage(ENUM_PAGE_BUDGET).withPageTitle(SUBWAY_PAGE_TITLE).build();

    private TypicalState() {} // prevents instantiation

    public static State getTypicalMainPageState() {
        return new StateBuilder().withBudgetIndex(new EmptyBudgetIndex())
                .withCurrentPage(Page.MAIN).withPageTitle("NUSave").build();
    }

    public static State getMcdonaldsBudgetPageState() {
        return new StateBuilder().withBudgetIndex(new BudgetIndexManager(0))
                .withCurrentPage(Page.BUDGET).withPageTitle("McDonalds").build();
    }

    public static State getKfcBudgetPageState() {
        return new StateBuilder().withBudgetIndex(new BudgetIndexManager(1))
                .withCurrentPage(Page.BUDGET).withPageTitle("KFC").build();
    }

    public static State getSubwayBudgetPageState() {
        return new StateBuilder().withBudgetIndex(new BudgetIndexManager(2))
                .withCurrentPage(Page.BUDGET).withPageTitle("Subway").build();
    }
}
