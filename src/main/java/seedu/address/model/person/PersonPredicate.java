package seedu.address.model.person;

import java.util.function.Predicate;

public interface PersonPredicate extends Predicate<Person> {
    @Override
    public boolean equals(Object obj);
}
