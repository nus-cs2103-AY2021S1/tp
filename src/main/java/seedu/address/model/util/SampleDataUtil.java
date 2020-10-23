package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Planus;
import seedu.address.model.ReadOnlyPlanus;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.deadline.DeadlineDateTime;
import seedu.address.model.task.deadline.DoneDateTime;
import seedu.address.model.task.deadline.Duration;
import seedu.address.model.task.deadline.Status;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;


/**
 * Contains utility methods for populating {@code Planus} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            Deadline.createDeadline(new Title("Borrow books"), new DeadlineDateTime("25-11-2020 09:00"),
                    Description.defaultDescription(), getTagSet("friends")),
            new Deadline(new Title("Assignment 1"), new DeadlineDateTime("25-11-2020 09:00"),
                    new Description("Programming Assignment 2 of CS3230, Very hard."),
                    getTagSet("colleagues", "friends"), Status.createCompleteStatus(),
                    new Duration(20), DoneDateTime.createDoneNow()),
            Event.createUserEvent(new Title("CCA Orientation"), new StartDateTime("16-01-2021 13:00"),
                    new EndDateTime("16-01-2021 18:00"), Description.defaultDescription(),
                    getTagSet("neighbours")),
            Event.createUserEvent(new Title("CS2105 tutorial"), new StartDateTime("19-01-2021 13:00"),
                    new EndDateTime("19-01-2021 15:00"), Description.defaultDescription(),
                    getTagSet("neighbours")),
        };
    }

    public static ReadOnlyPlanus getSamplePlanus() {
        Planus samplePlanus = new Planus();
        for (Task sampleTask : getSampleTasks()) {
            samplePlanus.addTask(sampleTask);
        }
        return samplePlanus;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
