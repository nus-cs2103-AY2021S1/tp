package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Planus;
import seedu.address.model.task.deadline.status;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new DeadlineBuilder().withTitle("Alice Pauline")
            .withType("event").withDescription("alice,example.com")
            .withDateTime("01-01-2020 12:00")
            .withTags("friends").build();
    public static final Task BENSON = new DeadlineBuilder().withTitle("Benson Meier")
            .withType("event")
            .withDescription("johnd,example.com").withDateTime("02-02-2020 12:00")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new DeadlineBuilder().withTitle("Carl Kurz").withDateTime("03-03-2020 12:00")
            .withDescription("heinz,example.com").withType("event").withStatus(State.COMPLETE).build();
    public static final Task DANIEL = new DeadlineBuilder().withTitle("Daniel Meier").withDateTime("04-04-2020 12:00")
            .withDescription("cornelia,example.com").withType("event").withTags("friends").build();
    public static final Task ELLE = new DeadlineBuilder().withTitle("Elle Meyer").withDateTime("05-05-2020 12:00")
            .withDescription("werner,example.com").withType("event").build();
    public static final Task FIONA = new DeadlineBuilder().withTitle("Fiona Kunz").withDateTime("06-06-2020 12:00")
            .withDescription("lydia,example.com").withType("event").build();
    public static final Task GEORGE = new DeadlineBuilder().withTitle("George Best").withDateTime("07-07-2020 12:00")
            .withDescription("anna,example.com").withType("event").build();

    // Manually added
    public static final Task HOON = new DeadlineBuilder().withTitle("Hoon Meier").withDateTime("08-08-2020 12:00")
            .withDescription("stefan,example.com").withType("event").build();
    public static final Task IDA = new DeadlineBuilder().withTitle("Ida Mueller").withDateTime("09-09-2020 12:00")
            .withDescription("hans,example.com").withType("event").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new DeadlineBuilder().withTitle(VALID_TITLE_AMY).withDateTime(VALID_DATE_TIME_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withType(VALID_TYPE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new DeadlineBuilder().withTitle(VALID_TITLE_BOB).withDateTime(VALID_DATE_TIME_BOB)
            .withDescription(VALID_DESCRIPTION_BOB).withType(VALID_TYPE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code Planus} with all the typical tasks.
     */
    public static Planus getTypicalPlanus() {
        Planus ab = new Planus();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
