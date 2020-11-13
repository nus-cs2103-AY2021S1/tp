package seedu.address.testutil;

import static seedu.address.testutil.TypicalBudget.KFC;
import static seedu.address.testutil.TypicalBudget.MC_DONALDS;
import static seedu.address.testutil.TypicalBudget.SUBWAY;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.Nusave;
import seedu.address.model.budget.BudgetList;

public class TypicalBudgets {
    public static final BudgetList FAST_FOOD_BUDGETS = new BudgetList(
            new ArrayList<>(Arrays.asList(
                    MC_DONALDS,
                    KFC,
                    SUBWAY
            )));

    private TypicalBudgets() {} // prevents instantiation

    public static BudgetList getFastFoodBudgets() {
        BudgetList budgetList = new BudgetList();
        budgetList.addToFront(TypicalBudget.getMcDonaldsBudget());
        budgetList.addToFront(TypicalBudget.getKfcBudget());
        budgetList.addToFront(TypicalBudget.getSubwayBudget());
        return budgetList;
    }

    public static Nusave getTypicalNusave() {
        Nusave nusave = new Nusave();
        nusave.setBudgets(getFastFoodBudgets());
        return nusave;
    }
}
