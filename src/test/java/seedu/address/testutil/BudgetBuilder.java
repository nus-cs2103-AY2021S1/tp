package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.budget.Budget;
import seedu.address.model.budget.Date;
import seedu.address.model.budget.Name;
import seedu.address.model.budget.Threshold;
import seedu.address.model.expenditure.ExpenditureList;

public class BudgetBuilder {
    public static final String DEFAULT_NAME = "McDonalds";
    public static final String DEFAULT_CREATED_ON = "2020-10-10T00:00:00.000000";
    public static final String DEFAULT_THRESHOLD = "100";
    public static final ExpenditureList DEFAULT_EXPENDITURES = TypicalExpenditures.getMcDonaldsExpenditures();

    private Name name;
    private Date createdOn;
    private Optional<Threshold> threshold;
    private ExpenditureList expenditures;

    /**
     * Creates a {@code BudgetBuilder} with a threshold using the default details.
     */
    public BudgetBuilder() {
        name = new Name(DEFAULT_NAME);
        createdOn = new Date(DEFAULT_CREATED_ON);
        threshold = new Threshold(DEFAULT_THRESHOLD).toOptional();
        expenditures = DEFAULT_EXPENDITURES;
    }

    /**
     * Creates a {@code BudgetBuilder} with the data of {@code budgetToCopy}.
     */
    public BudgetBuilder(Budget budgetToCopy) {
        name = budgetToCopy.getName();
        createdOn = budgetToCopy.getCreatedOn();
        threshold = budgetToCopy.getThreshold();
        expenditures = budgetToCopy.getExpenditures();
    }

    /**
     * Sets the {@code name} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code createdOn} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withCreatedOn(String createdOn) {
        this.createdOn = new Date(createdOn);
        return this;
    }

    /**
     * Sets the {@code threshold} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withThreshold(String threshold) {
        this.threshold = new Threshold(threshold).toOptional();
        return this;
    }

    /**
     * Sets the {@code expenditures} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withExpenditures(ExpenditureList expenditures) {
        this.expenditures = expenditures;
        return this;
    }

    public Budget build() {
        return new Budget(name, createdOn, threshold, expenditures);
    }
}
