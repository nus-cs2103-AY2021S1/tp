package seedu.address.model.person.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonComparatorTest {

    private final PersonComparator personComparator = new PersonComparator();

    @Test
    public void isCorrectOrder() {
        assertTrue(personComparator.compare(ALICE, ALICE) == 0);

        assertTrue(personComparator.compare(ALICE, BENSON) < 0);
        assertTrue(personComparator.compare(BENSON, ALICE) > 0);

        // Alice Pauline < alice Pauline
        Person editedAlice = new PersonBuilder(ALICE).withName("alice Pauline").build();
        assertTrue(personComparator.compare(ALICE, editedAlice) < 0);

        // Alice Pauline > Alice
        editedAlice = new PersonBuilder(ALICE).withName("Alice").build();
        assertTrue(personComparator.compare(ALICE, editedAlice) > 0);

        // null Persons
        assertThrows(NullPointerException.class, () -> personComparator.compare(null, ALICE));
        assertThrows(NullPointerException.class, () -> personComparator.compare(ALICE, null));
    }
}
