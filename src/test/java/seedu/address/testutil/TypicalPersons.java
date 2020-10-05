package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
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

    public static final Person ALICE = new PersonBuilder().withDescription("Alice Pauline")
            .withDate("04-10-2020")
            .withAmount("24.00").withRemark("She likes aardvarks.")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withDescription("Benson Meier")
            .withRemark("He can't take beer!")
            .withDate("02-10-2020").withAmount("98.00")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withDescription("Carl Kurz").withAmount("4.00")
            .withDate("28-09-2020").build();
    public static final Person DANIEL = new PersonBuilder().withDescription("Daniel Meier").withAmount("4.50")
            .withDate("11-09-2020").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withDescription("Elle Meyer").withAmount("2.40")
            .withDate("15-09-2020").build();
    public static final Person FIONA = new PersonBuilder().withDescription("Fiona Kunz").withAmount("65.00")
            .withDate("21-09-2020").build();
    public static final Person GEORGE = new PersonBuilder().withDescription("George Best").withAmount("49.00")
            .withDate("28-09-2020").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withDescription("Hoon Meier").withAmount("38.00")
            .withDate("29-09-2020").build();
    public static final Person IDA = new PersonBuilder().withDescription("Ida Mueller").withAmount("63.00")
            .withDate("03-10-2020").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withDescription(VALID_DESCRIPTION_AMY)
            .withAmount(VALID_AMOUNT_AMY).withDate(VALID_DATE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withDescription(VALID_DESCRIPTION_BOB)
            .withAmount(VALID_AMOUNT_BOB).withDate(VALID_DATE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

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
