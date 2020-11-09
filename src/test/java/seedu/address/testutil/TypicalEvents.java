package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.task.Event;

/**
 * A utility class containing a list of {@code Events} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event MEETING = new EventBuilder().withDescription("meeting")
            .withStartDateTime("12-12-2020 1000").withEndDateTime("12-12-2020 1130")
            .withTags("CS2103T").build();
    public static final Event PARTY = new EventBuilder().withDescription("party")
            .withStartDateTime("01-01-2020 1800").withEndDateTime("02-01-2020 0600")
            .withRecurrence("1 year").build();
    public static final Event LECTURE = new EventBuilder().withDescription("lecture")
            .withStartDateTime("23-10-2020 1600").withEndDateTime("23-10-2020 1600").withTags("CS2103T").build();
    public static final Event WORKSHOP = new EventBuilder().withDescription("attend workshop")
            .withStartDateTime("12-11-2020 1200").withEndDateTime("12-11-2020 1600").withTags("workshop").build();
    public static final Event MEETING2 = new EventBuilder().withDescription("meeting2")
            .withStartDateTime("12-12-2020 1000").withEndDateTime("12-12-2020 1130").build();
    public static final Event MEETING3 = new EventBuilder().withDescription("meeting3")
            .withStartDateTime("16-12-2020 1000").withEndDateTime("18-12-2020 1130").build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical Events.
     */
    public static TaskList getTypicalEventsTaskList() {
        TaskList ab = new TaskList();
        for (Event event: getTypicalEvents()) {
            ab.addTask(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(MEETING, PARTY, LECTURE, WORKSHOP, MEETING2, MEETING3));
    }
}
