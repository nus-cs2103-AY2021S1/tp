package seedu.address.model.budget;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.model.Renderable;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.ExpenditureList;

public class Budget implements Renderable {
    private final Name name;
    private final Date createdOn;
    private final Optional<Threshold> threshold;
    private final ExpenditureList expenditures;

    /**
     *
     * @param name
     * @param expenditures
     */
    public Budget(Name name, Date date, ExpenditureList expenditures) {
        requireAllNonNull(name, expenditures);
        this.name = name;
        this.createdOn = date;
        this.expenditures = expenditures;
        this.threshold = Optional.empty();
    }

    /**
     *
     * @param name
     * @param threshold
     * @param expenditures
     */
    public Budget(Name name, Date date, Optional<Threshold> threshold, ExpenditureList expenditures) {
        requireAllNonNull(name, expenditures, threshold);
        this.name = name;
        this.createdOn = date;
        this.threshold = threshold;
        this.expenditures = expenditures;
    }

    /**
     * Creates a deep copy of the given {@code Budget}.
     * @param toClone the {@code Budget} to be copied.
     * @return the deep copy of the given {@code Budget}.
     */
    public static Budget clone(Budget toClone) {
        requireAllNonNull(toClone);
        Name nameClone = Name.clone(toClone.name);
        Date dateClone = Date.clone(toClone.createdOn);
        Optional<Threshold> thresholdToClone = toClone.threshold;
        Optional<Threshold> thresholdClone = thresholdToClone.isEmpty()
                ? Optional.empty()
                : Threshold.clone(thresholdToClone.get()).toOptional();
        ExpenditureList expendituresClone = ExpenditureList.clone(toClone.expenditures);
        return new Budget(nameClone, dateClone, thresholdClone, expendituresClone);
    }

    public Name getName() {
        return name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Optional<Threshold> getThreshold() {
        return threshold;
    }

    public ExpenditureList getExpenditures() {
        return expenditures;
    }

    public List<Expenditure> getExpendituresList() {
        return expenditures.getExpenditures();
    }
    public int getExpenditureSize() {
        return expenditures.getExpenditureSize();
    }

    public String getTotalExpenditure() {
        return expenditures.getTotalExpenditure();
    }

    public void addExpenditureToFront(Expenditure expenditure) {
        expenditures.addToFront(expenditure);
    }

    public void addExpenditure(Expenditure expenditure) {
        expenditures.add(expenditure);
    }

    /**
     * Edits an expenditure within the expenditures by replacing the {code: oldExpenditure}
     * with a {code: newExpenditure}.
     * @param oldExpenditure
     * @param editedExpenditure
     */
    public void editExpenditure(Expenditure oldExpenditure, Expenditure editedExpenditure) {
        expenditures.editExpenditure(oldExpenditure, editedExpenditure);
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Budget)) {
            return false;
        }

        Budget otherBudget = (Budget) other;
        return otherBudget.getName().equals(getName())
                && otherBudget.getCreatedOn().equals(getCreatedOn())
                && otherBudget.getThreshold().equals(getThreshold())
                && otherBudget.getExpenditures().equals(getExpenditures());
    }


    /**
     * Checks if the contents within Budget is the same.
     * @param other
     * @return returns whether the contents are identical or not.
     */

    public boolean contentEquals(Object other) {
        Budget otherBudget = (Budget) other;
        return otherBudget.getName().equals(getName())
                && otherBudget.getThreshold().equals(getThreshold())
                && otherBudget.getExpenditures().equals(getExpenditures());
    }

    /**
     * Returns true if {@code Name} contains the given {@code string}.
     */
    public boolean contains(String string) { //Todo: Change method name to be more descriptive.
        String nameLowerCase = name.value.toLowerCase(); //Todo: Tell, don't ask.
        return nameLowerCase.contains(string.toLowerCase());
    }
}
