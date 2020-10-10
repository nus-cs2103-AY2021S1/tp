package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {


    // TODO These tags could be updated with more descriptive tag names.
    public static final Tag ALICE = new TagBuilder().withTagName("Alice Pauline")
            .withFileAddress("c:\\a\\b\\alice.txt")
            .build();
    public static final Tag BENSON = new TagBuilder().withTagName("Benson Meier")
            .withFileAddress("c:\\a\\b\\benson.txt")
            .build();

    public static final Tag CARL = new TagBuilder().withTagName("Carl Kurz")
            .withFileAddress("c:\\a\\b\\carl.txt").build();
    public static final Tag DANIEL = new TagBuilder().withTagName("Daniel Meier")
            .withFileAddress("c:\\a\\b\\daniel.txt").build();
    public static final Tag ELLE = new TagBuilder().withTagName("Elle Meyer")
            .withFileAddress("c:\\a\\b\\elle.txt").build();
    public static final Tag FIONA = new TagBuilder().withTagName("Fiona Kunz")
            .withFileAddress("c:\\a\\b\\fiona.txt").build();
    public static final Tag GEORGE = new TagBuilder().withTagName("George Best")
            .withFileAddress("c:\\a\\b\\george.txt").build();

    // Manually added
    public static final Tag HOON = new TagBuilder().withTagName("Hoon Meier")
            .withFileAddress("c:\\a\\b\\hoon.txt").build();
    public static final Tag IDA = new TagBuilder().withTagName("Ida Mueller")
            .withFileAddress("c:\\a\\b\\ida.txt").build();

    // Manually added - Tag's details found in {@code CommandTestUtil}
    public static final Tag AMY = new TagBuilder().withTagName(VALID_NAME_AMY)
            .withFileAddress(VALID_FILE_ADDRESS_AMY).build();
    public static final Tag BOB = new TagBuilder().withTagName(VALID_NAME_BOB)
            .withFileAddress(VALID_FILE_ADDRESS_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTags() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tag tag : getTypicalTags()) {
            ab.addTag(tag);
        }
        return ab;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
