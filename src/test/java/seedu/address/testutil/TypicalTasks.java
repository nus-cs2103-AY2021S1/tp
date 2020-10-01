package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task HOMEWORK = new TaskBuilder().withTitle("Do science homework")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Task MOVIE = new TaskBuilder().withTitle("Watch movie")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Task CHORES = new TaskBuilder().withTitle("Do chores").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Task PROJECT = new TaskBuilder().withTitle("Do coding project").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Task RUN = new TaskBuilder().withTitle("Run 3km").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Task MEET = new TaskBuilder().withTitle("Meet Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Task ESSAY = new TaskBuilder().withTitle("Write essay").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Task CALL = new TaskBuilder().withTitle("Call mum").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Task NAP = new TaskBuilder().withTitle("Power nap").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task COOK = new TaskBuilder().withTitle(VALID_TITLE_COOK).withPhone(VALID_PHONE_COOK)
            .withEmail(VALID_EMAIL_COOK).withAddress(VALID_ADDRESS_COOK).withTags(VALID_TAG_FRIEND).build();
    public static final Task WASH = new TaskBuilder().withTitle(VALID_TITLE_WASH).withPhone(VALID_PHONE_WASH)
            .withEmail(VALID_EMAIL_WASH).withAddress(VALID_ADDRESS_WASH).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
        return new ArrayList<>(Arrays.asList(HOMEWORK, MOVIE, CHORES, PROJECT, RUN, MEET, ESSAY));
    }
}
