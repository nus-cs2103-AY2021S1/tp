package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Planus;
import seedu.address.model.task.Task;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.event.Event;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Deadline DEADLINE1 = new DeadlineBuilder().withTitle("Submit Developer Guide")
            .withDescription("Submit through luminus")
            .withDeadlineDateTime("01-01-2020 12:00").withIncompleteStatus()
            .withTag("CS2103T").build();
    public static final Deadline DEADLINE2 = new DeadlineBuilder().withTitle("Assignment 3")
            .withDeadlineDateTime("02-02-2020 12:59").withTag("CS2102").withCompleteStatus()
            .withDuration(20).withDescription("important").withDoneDateTime("24-10-2020 04:07").build();
    public static final Deadline DEADLINE3 = new DeadlineBuilder().withTitle("GEQ Readings")
            .withNullDeadlineDateTime().withDescription("15 pages reading")
            .withIncompleteStatus().withTag("GEQ1000").build();
    public static final Deadline DEADLINE4 = new DeadlineBuilder().withTitle("Grade Mission")
            .withDeadlineDateTime("04-04-2020 12:00").withIncompleteStatus()
            .withDescription("grading all student mission").withTag("CS1101S").build();
    public static final Event EVENT1 = new EventBuilder().withTitle("Project meeting")
            .withStartDateTime("05-05-2020 12:00").withEndDateTime("05-05-2020 14:00")
            .withDescription("discuss v1.3 content").withTag("CS2103T").build();
    public static final Event EVENT2 = new EventBuilder().withTitle("Project meeting2")
            .withStartDateTime("05-05-2020 14:00").withEndDateTime("05-05-2020 16:00")
            .withDescription("discuss project database diagram").withTag("CS2102").build();
    public static final Event EVENT3 = new EventBuilder().withTitle("Project meeting3")
            .withStartDateTime("05-05-2020 18:00").withEndDateTime("05-05-2020 20:00")
            .withDescription("discuss v1.3 content").withTag("CS2103T").build();


    // Manually added
    public static final Deadline DEADLINE5 = new DeadlineBuilder().withTitle("Assignment 10")
            .withDeadlineDateTime("02-02-2020 12:59").withTag("CS2102").withCompleteStatus().build();
    public static final Event EVENT4 = new EventBuilder().withTitle("Project meeting 2020")
            .withStartDateTime("05-05-2020 12:00").withEndDateTime("05-05-2020 14:00")
            .withDescription("dicuss v1.3 content").withTag("CS2103T").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Deadline DEADLINE_TEST = new DeadlineBuilder()
            .withTitle(VALID_TITLE_AMY).withDeadlineDateTime(VALID_DATE_TIME_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withTag(VALID_TAG_FRIEND).build();
    public static final Event EVENT_TEST = new EventBuilder().withTitle(VALID_TITLE_BOB)
            .withStartDateTime(VALID_DATE_TIME_BOB).withEndDateTime(VALID_DATE_TIME_BOB)
            .withDescription(VALID_DESCRIPTION_BOB).withTag(VALID_TAG_FRIEND).build();

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
        return new ArrayList<>(Arrays.asList(DEADLINE1, DEADLINE2, DEADLINE3, DEADLINE4, EVENT1, EVENT2, EVENT3));
    }
}
