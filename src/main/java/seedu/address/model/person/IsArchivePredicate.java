package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code isArchive} is true.
 */
public class IsArchivePredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getIsArchive();
    }
}
