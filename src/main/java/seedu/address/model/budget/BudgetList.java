package seedu.address.model.budget;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.ExpenditureList;
import seedu.address.model.sort.SortBudgetsByCreateDate;
import seedu.address.model.sort.SortBudgetsByName;
import seedu.address.state.budgetindex.BudgetIndex;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class BudgetList implements Iterable<Budget> {
    private final List<Budget> budgets;

    public BudgetList() {
        this.budgets = new ArrayList<>();
    }

    public BudgetList(List<Budget> budgets) {
        this.budgets = budgets;
    }

    /**
     * Creates a deep copy of the given {@code BudgetList}.
     * @param toClone the {@code BudgetList} to be copied.
     * @return the deep copy of the given {@code BudgetList}.
     */
    public static BudgetList clone(BudgetList toClone) {
        requireNonNull(toClone);
        BudgetList clone = new BudgetList();
        for (Budget budget : toClone.budgets) {
            clone.add(Budget.clone(budget));
        }
        return clone;
    }

    /**
     * Adds a budget to the front of the list.
     */
    public void addToFront(Budget toAdd) {
        requireNonNull(toAdd);
        budgets.add(0, toAdd);
    }

    /**
     * Adds a budget to the back of the list.
     * @param toAdd the {@code Budget} to be added.
     */
    public void add(Budget toAdd) {
        requireNonNull(toAdd);
        budgets.add(toAdd);
    }

    /**
     * Adds an expenditure to a the budget at index index in budgets.
     * @param toAdd the Expenditure object to be added.
     * @param index the index of the budget which the expenditure will be added into.
     */
    public void addExpenditure(Expenditure toAdd, int index) {
        Budget budget = budgets.get(index);
        budget.addExpenditureToFront(toAdd);
    }

    /**
     * Edits an expenditure to a budget at {code: index} in budgets.
     * @param oldExpenditure
     * @param newExpenditure
     * @param index
     */
    public void editExpenditure(Expenditure oldExpenditure, Expenditure newExpenditure, int index) {
        Budget budget = budgets.get(index);
        budget.editExpenditure(oldExpenditure, newExpenditure);
    }

    /**
     * Removes the equivalent budget from the list.
     * The budget must exist in the list.
     */
    public void remove(Budget toRemove) {
        requireNonNull(toRemove);
        budgets.remove(toRemove);
    }

    public ExpenditureList getExpenditures(int index) {
        Budget budget = budgets.get(index);
        return budget.getExpenditures();
    }

    public List<Expenditure> getExpendituresAsList(int index) {
        Budget budget = budgets.get(index);
        return budget.getExpendituresList();
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public String getBudgetName(BudgetIndex budgetIndex) {
        requireNonNull(budgetIndex);
        assert budgetIndex.getBudgetIndex().isPresent();
        int index = budgetIndex.getBudgetIndex().get();
        return this.budgets.get(index).getName().value;
    }

    public Optional<Threshold> getBudgetThreshold(int index) {
        requireNonNull(index);
        Optional<Threshold> threshold = this.budgets.get(index).getThreshold();
        return threshold;
    }

    public int getSize() {
        return budgets.size();
    }

    public String getTotalExpenditureValue(int index) {
        Budget budget = budgets.get(index);
        return budget.getTotalExpenditure();
    }

    /**
     * Replaces the contents of this list with {@code budgets}.
     */
    public void setBudgets(List<Budget> budgets) {
        requireAllNonNull(budgets);
        this.budgets.clear();
        this.budgets.addAll(budgets);
    }

    public int getIndexOfBudget(Budget budget) {
        return this.budgets.indexOf(budget);
    }

    /**
     * Edit a budget from NUSave.
     * @param oldBudget the budget to be edited
     * @param editedBudget edited budget to replace the old budget
     */
    public void editBudget(Budget oldBudget, Budget editedBudget) {
        int index = this.budgets.indexOf(oldBudget);
        this.budgets.set(index, editedBudget);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Budget> asUnmodifiableObservableList() {
        ObservableList<Budget> internalBudgetList = FXCollections.observableArrayList();
        internalBudgetList.setAll(budgets);
        ObservableList<Budget> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalBudgetList);
        return internalUnmodifiableList;
    }

    public void sortBudgetsListByName() {
        budgets.sort(new SortBudgetsByName());
    }

    public void sortBudgetListByCreatedDate() {
        this.budgets.sort(new SortBudgetsByCreateDate());
    }

    @Override
    public Iterator<Budget> iterator() {
        return budgets.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BudgetList // instanceof handles nulls
                && (budgets.containsAll(((BudgetList) other).budgets))
                && ((BudgetList) other).getBudgets().containsAll(budgets));
    }
}
