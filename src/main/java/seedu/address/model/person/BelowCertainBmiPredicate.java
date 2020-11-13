package seedu.address.model.person;

import java.util.function.Predicate;

public class BelowCertainBmiPredicate implements Predicate<Person> {
    private double bmi;

    public BelowCertainBmiPredicate(double bmi) {
        this.bmi = bmi;
    }

    @Override
    public boolean test(Person person) {
        return person.getCurrentBmi() > bmi;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BelowCertainBmiPredicate // instanceof handles nulls
                && bmi == ((BelowCertainBmiPredicate) other).bmi); // state check
    }
}
