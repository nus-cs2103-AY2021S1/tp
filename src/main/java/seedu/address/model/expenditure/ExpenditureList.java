package seedu.address.model.expenditure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.sort.SortExpendituresByCreateDate;
import seedu.address.model.sort.SortExpendituresByName;

public class ExpenditureList {
    private final List<Expenditure> expenditures;
    public ExpenditureList() {
        this.expenditures = new ArrayList<>();
    }

    public ExpenditureList(List<Expenditure> expenditures) {
        this.expenditures = expenditures;
    }

    /**
     * Creates a deep copy of the given {@code ExpenditureList}.
     * @param toClone the {@code ExpenditureList} to be copied.
     * @return the deep copy of the given {@code ExpenditureList}.
     */
    public static ExpenditureList clone(ExpenditureList toClone) {
        requireAllNonNull(toClone);
        ExpenditureList clone = new ExpenditureList();
        for (Expenditure expenditure : toClone.expenditures) {
            clone.add(Expenditure.clone(expenditure));
        }
        return clone;
    }
    /**
     * Adds a expenditure to the front of the list.
     */
    public void addToFront(Expenditure toAdd) {
        requireNonNull(toAdd);
        expenditures.add(0, toAdd);
    }

    /**
     * Adds an {@code Expenditure} to the back of the list.
     * @param toAdd the {@code Expenditure} to be added.
     */
    public void add(Expenditure toAdd) {
        requireNonNull(toAdd);
        expenditures.add(toAdd);
    }

    public List<Expenditure> getExpenditures() {
        return expenditures;
    }

    public int getExpenditureSize() {
        return expenditures.size();
    }

    /**
     * Get total value in the expenditure list.
     */
    public String getTotalExpenditure() {
        Double resultDouble = 0.0;
        for (Expenditure e : expenditures) {
            Price price = e.getPrice();
            String value = price.value;
            resultDouble += Double.parseDouble(value);
        }
        return String.format("%.2f", Double.valueOf(resultDouble));
    }

    /**
     * Edits an expenditure within the expenditures by replacing the {code: oldExpenditure}
     * with a {code: newExpenditure}.
     * @param oldExpenditure
     * @param editedExpenditure
     */
    public void editExpenditure(Expenditure oldExpenditure, Expenditure editedExpenditure) {
        int index = expenditures.indexOf(oldExpenditure);
        expenditures.set(index, editedExpenditure);
    }

    /**
     * Sort the expenditure list by name in alphabetical order.
     */
    public void sortExpendituresByName() {
        expenditures.sort(new SortExpendituresByName());
    }

    /**
     * Sort the expenditure list by Created date with latest on top.
     */
    public void sortExpendituresByCreateDate() {
        this.expenditures.sort(new SortExpendituresByCreateDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpenditureList that = (ExpenditureList) o;
        return Objects.equals(expenditures, that.expenditures);
    }
}
