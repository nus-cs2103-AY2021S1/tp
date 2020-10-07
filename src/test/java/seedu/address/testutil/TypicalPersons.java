package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalPersons {


    public static final Tag ALICE = new PersonBuilder().withTagName("Alice Pauline")
            .withFileAddress("c:\\a\\b\\alice.txt")
            .build();
    public static final Tag BENSON = new PersonBuilder().withTagName("Benson Meier")
            .withFileAddress("c:\\a\\b\\benson.txt")
            .build();

    public static final Tag CARL = new PersonBuilder().withTagName("Carl Kurz")
            .withFileAddress("c:\\a\\b\\carl.txt").build();
    public static final Tag DANIEL = new PersonBuilder().withTagName("Daniel Meier")
            .withFileAddress("c:\\a\\b\\daniel.txt").build();
    public static final Tag ELLE = new PersonBuilder().withTagName("Elle Meyer")
            .withFileAddress("c:\\a\\b\\elle.txt").build();
    public static final Tag FIONA = new PersonBuilder().withTagName("Fiona Kunz")
            .withFileAddress("c:\\a\\b\\fiona.txt").build();
    public static final Tag GEORGE = new PersonBuilder().withTagName("George Best")
            .withFileAddress("c:\\a\\b\\george.txt").build();

    // Manually added
    public static final Tag HOON = new PersonBuilder().withTagName("Hoon Meier")
            .withFileAddress("c:\\a\\b\\hoon.txt").build();
    public static final Tag IDA = new PersonBuilder().withTagName("Ida Mueller")
            .withFileAddress("c:\\a\\b\\ida.txt").build();

    // Manually added - Tag's details found in {@code CommandTestUtil}
    public static final Tag AMY = new PersonBuilder().withTagName(VALID_NAME_AMY)
            .withFileAddress(VALID_FILE_ADDRESS_AMY).build();
    public static final Tag BOB = new PersonBuilder().withTagName(VALID_NAME_BOB)
            .withFileAddress(VALID_FILE_ADDRESS_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tag tag : getTypicalTags()) {
            ab.addPerson(tag);
        }
        return ab;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
