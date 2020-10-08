package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withTitle("Alice Pauline")
            .withDocument("name", "test1.txt")
            .withStatus("COLD")
            .withVictims("Tom")
            .withWitnesses("Janice")
            .withTags("friends")
            .build();
    public static final Person BENSON = new PersonBuilder().withTitle("Benson Meier")
            .withStatus("CLOSED")
            .withDocument("name", "test1.txt")
            .withVictims("Tom")
            .withWitnesses("Mary")
            .withTags("owesMoney", "friends")
            .build();

    public static final Person CARL = new PersonBuilder().withTitle("Carl Kurz")
            .build();
    public static final Person DANIEL = new PersonBuilder().withTitle("Daniel Meier")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withTitle("Elle Meyer")
            .build();
    public static final Person FIONA = new PersonBuilder().withTitle("Fiona Kunz")
            .build();
    public static final Person GEORGE = new PersonBuilder().withTitle("George Best")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withTitle("Hoon Meier")
            .build();
    public static final Person IDA = new PersonBuilder().withTitle("Ida Mueller")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withTitle(VALID_NAME_AMY)
            .withStatus(VALID_STATUS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withTitle(VALID_NAME_BOB)
            .withStatus(VALID_STATUS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
