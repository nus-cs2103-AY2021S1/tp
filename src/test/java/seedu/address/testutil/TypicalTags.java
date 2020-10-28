package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;

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
    public static final Tag CS2103 = new TagBuilder().withTagName(VALID_TAG_NAME_CS2103)
            .withFileAddress(VALID_FILE_ADDRESS_CS2103)
            .build();
    public static final Tag CS2101 = new TagBuilder().withTagName(VALID_TAG_NAME_CS2101)
            .withFileAddress(VALID_FILE_ADDRESS_CS2101)
            .build();

    public static final Tag MYFILE = new TagBuilder().withTagName("myfile")
            .withFileAddress("c:\\a\\b\\myfile.txt")
            .withLabels("project", "myfile")
            .build();
    public static final Tag MYFILE2 = new TagBuilder().withTagName("myfile 2")
            .withFileAddress("c:\\a\\b\\myfile2.txt")
            .withLabels("project")
            .build();
    public static final Tag MYFILE3 = new TagBuilder().withTagName("myfile 3")
            .withFileAddress("c:\\a\\b\\myfile3.txt").build();

    public static final Tag MYFILE4 = new TagBuilder().withTagName("myfile 4")
            .withFileAddress("c:\\a\\b\\myfile4.txt").build();
    public static final Tag MYFILE5 = new TagBuilder().withTagName("myfile 5")
            .withFileAddress("c:\\a\\b\\myfile5.txt").build();

    // Manually added
    public static final Tag MYFILE6 = new TagBuilder().withTagName("myfile 6")
            .withFileAddress("c:\\a\\b\\myfile6.txt").build();
    public static final Tag MYFILE7 = new TagBuilder().withTagName("myfile 7")
            .withFileAddress("c:\\a\\b\\myfile7.txt").build();

    private TypicalTags() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tags.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab3 = new AddressBook();
        for (Tag tag : getTypicalTags()) {
            ab3.addTag(tag);
        }
        return ab3;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(CS2103, CS2101, MYFILE, MYFILE2, MYFILE3, MYFILE4, MYFILE5));
    }
}
