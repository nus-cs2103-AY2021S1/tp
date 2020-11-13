package seedu.address.model.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;
import seedu.address.testutil.TypicalBudget;
import seedu.address.testutil.TypicalBudgets;
import seedu.address.testutil.TypicalExpenditure;


class BudgetListTest {

    private final BudgetList budgetList = TypicalBudgets.getFastFoodBudgets();

    @Test
    void testClone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.clone(null));
    }

    @Test
    void testClone_validBudgetList_success() {
        BudgetList cloneList = TypicalBudgets.getFastFoodBudgets();
        assertTrue(budgetList.clone(cloneList) != cloneList);
    }

    @Test
    void addToFront_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.addToFront(null));
    }

    @Test
    void addToFront_budgetInList_success() {
        budgetList.addToFront(TypicalBudget.getKfcBudget());
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        assertEquals(budgetList.getBudgetName(budgetIndex), TypicalBudget.KFC_NAME);
    }

    @Test
    void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.add(null));
    }

    @Test
    void add_budgetInList_success() {
        budgetList.add(TypicalBudget.getKfcBudget());
        BudgetIndex budgetIndex = new BudgetIndexManager(3);
        assertEquals(budgetList.getBudgetName(budgetIndex), TypicalBudget.KFC_NAME);
    }

    @Test
    void addExpenditure_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.addExpenditure(null, 0));
    }

    @Test
    void addExpenditure_validExpenditure_success() {
        Expenditure toAdd = TypicalExpenditure.getMcMuffinExpenditure();
        int index = 1;
        budgetList.addExpenditure(toAdd, index);
        assertTrue(budgetList.getBudgets().get(1).getExpenditureSize() == 4);
    }

    @Test
    void editExpenditure_null_indexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                budgetList.editExpenditure(null, null, 1));
    }

    @Test
    void editExpenditure_validExpenditure_success() {
        Expenditure oldExpenditure = budgetList.getBudgets().get(0).getExpendituresList().get(0);
        Expenditure newExpenditure = TypicalExpenditure.getKfcBanditoExpenditure();
        budgetList.editExpenditure(oldExpenditure, newExpenditure, 0);
        assertEquals(budgetList.getBudgets().get(0)
                .getExpendituresList().get(0).getName(), TypicalExpenditure.getKfcBanditoExpenditure().getName());
    }

    @Test
    void remove_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.remove(null));
    }

    @Test
    void remove_validBudget_success() {
        Budget toRemove = budgetList.getBudgets().get(0);
        budgetList.remove(toRemove);
        assertTrue(!budgetList.getBudgets().contains(toRemove));
    }

    @Test
    void getExpenditures_indexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> budgetList.getExpenditures(4));
    }

    @Test
    void getExpenditures_validIndex_success() {
        assertTrue(budgetList.getExpenditures(1) != null);
    }

    @Test
    void getExpendituresAsList_indexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> budgetList.getExpendituresAsList(4));
    }

    @Test
    void getExpendituresAsList_validIndex_success() {
        assertTrue(budgetList.getExpendituresAsList(1) != null);
    }

    @Test
    void getBudgets() {
        assertTrue(budgetList.getBudgets() != null);
    }

    @Test
    void getBudgetName() {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        String budgetName = budgetList.getBudgetName(budgetIndex);
        assertEquals(budgetName, TypicalBudget.SUBWAY_NAME);
    }

    @Test
    void getBudgetName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.getBudgetName(null));
    }

    @Test
    void getBudgetThreshold() {
        Optional<Threshold> threshold = budgetList.getBudgetThreshold(0);
        assertEquals(threshold.get().value, TypicalBudget.SUBWAY_THRESHOLD);
    }

    @Test
    void getSize() {
        int size = budgetList.getSize();
        assertEquals(size, 3);
    }

    @Test
    void getTotalExpenditureValue() {
        String totalValue = TypicalBudget.getSubwayBudget().getTotalExpenditure();
        assertEquals(budgetList.getTotalExpenditureValue(0), totalValue);
    }

    @Test
    void setBudgets_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setBudgets(null));
    }

    @Test
    void setBudgets_validBudgets_success() {
        List<Budget> budgets = new ArrayList<>();
        budgetList.setBudgets(budgets);
        assertEquals(budgetList.getBudgets(), budgets);
    }

    @Test
    void getIndexOfBudget() {
        Budget budget = budgetList.getBudgets().get(0);
        assertEquals(budgetList.getIndexOfBudget(budget), 0);
    }

    @Test
    void editBudget() {
        Budget oldBudget = budgetList.getBudgets().get(0);
        Budget newBudget = TypicalBudget.getMcDonaldsBudget();
        budgetList.editBudget(oldBudget, newBudget);
        assertEquals(budgetList.getBudgets().get(0), newBudget);
    }

    @Test
    void asUnmodifiableObservableList() {
        ObservableList<Budget> internalBudgetList = FXCollections.observableArrayList();
        internalBudgetList.setAll(budgetList.getBudgets());
        ObservableList<Budget> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalBudgetList);
        assertEquals(budgetList.asUnmodifiableObservableList(), internalUnmodifiableList);
    }

    @Test
    void sortBudgetsListByName() {
        String name = TypicalBudget.KFC_NAME;
        budgetList.sortBudgetsListByName();
        assertEquals(budgetList.getBudgets().get(0).getName().value, name);
    }

    @Test
    void iterator() {
        Iterator<Budget> first = TypicalBudgets.getFastFoodBudgets().iterator();
        Iterator<Budget> second = budgetList.iterator();
        assertTrue(first != second);
    }

    @Test
    void equals() {
        assertTrue(budgetList.equals(budgetList));
        assertFalse(budgetList.equals(5));
        assertFalse(budgetList.equals(null));
        BudgetList newList = TypicalBudgets.getFastFoodBudgets();
        assertTrue(budgetList.equals(newList));
    }


    @Test
    void sortBudgetListByCreatedDate() {
        String name = TypicalBudget.MC_DONALDS_NAME;
        budgetList.sortBudgetListByCreatedDate();
        assertEquals(budgetList.getBudgets().get(0).getName().value, name);
    }

}
