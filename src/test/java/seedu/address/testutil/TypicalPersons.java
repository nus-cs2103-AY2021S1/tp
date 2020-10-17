package seedu.address.testutil;

import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Persons} objects to be used in tests.
 */
public class TypicalPersons {
    public static final Person ALICE = new PersonBuilder().withPersonName("Alice Pauline").withPhone("12345678")
            .withEmail("alicepauline@sample.com").withAddress("123, Jurong West Ave 6, #08-111").build();

    // TODO: May add more instances
}
