package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withTitle("Alice Pauline")
            .withType("123, Jurong West Ave 6, #08-111").withDescription("alice,example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withTitle("Benson Meier")
            .withType("311, Clementi Ave 2, #02-25")
            .withDescription("johnd,example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withTitle("Carl Kurz").withPhone("95352563")
            .withDescription("heinz,example.com").withType("wall street").build();
    public static final Task DANIEL = new TaskBuilder().withTitle("Daniel Meier").withPhone("87652533")
            .withDescription("cornelia,example.com").withType("10th street").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withTitle("Elle Meyer").withPhone("9482224")
            .withDescription("werner,example.com").withType("michegan ave").build();
    public static final Task FIONA = new TaskBuilder().withTitle("Fiona Kunz").withPhone("9482427")
            .withDescription("lydia,example.com").withType("little tokyo").build();
    public static final Task GEORGE = new TaskBuilder().withTitle("George Best").withPhone("9482442")
            .withDescription("anna,example.com").withType("4th street").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withTitle("Hoon Meier").withPhone("8482424")
            .withDescription("stefan,example.com").withType("little india").build();
    public static final Task IDA = new TaskBuilder().withTitle("Ida Mueller").withPhone("8482131")
            .withDescription("hans,example.com").withType("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withTitle(VALID_TITLE_AMY).withPhone(VALID_PHONE_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withType(VALID_TYPE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withTitle(VALID_TITLE_BOB).withPhone(VALID_PHONE_BOB)
            .withDescription(VALID_DESCRIPTION_BOB).withType(VALID_TYPE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
