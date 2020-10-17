package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Planus;
import seedu.address.model.ReadOnlyPlanus;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.State;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.Type;


/**
 * Contains utility methods for populating {@code Planus} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("Borrow books"), DateTime.defaultDateTime(), Description.defaultDescription(),
                new Type("todo"),
                getTagSet("friends"), new Status(State.COMPLETE)),
            new Task(new Title("Assignment 1"), new DateTime("25-11-2020 09:00"),
                    new Description("Programming Assignment 2 of CS3230, Very hard."), new Type("deadline"),
                getTagSet("colleagues", "friends")),
            new Task(new Title("CCA Orientation"), new DateTime("16-01-2021 13:00"),
                    Description.defaultDescription(), new Type("event"),
                getTagSet("neighbours")),
            new Task(new Title("Do laundry"), new DateTime("16-10-2020 17:00"), Description.defaultDescription(),
                    new Type("todo"), getTagSet("what")),
            new Task(new Title("CS2105 tutorial"), new DateTime("15-10-2020 16:00"), new Description("Remember to do "
                    + "tutorial questions before the tutorial"),
                    new Type("event"),
                    getTagSet("friends"), new Status(State.COMPLETE)),
            new Task(new Title("Return books"), DateTime.defaultDateTime(), Description.defaultDescription(),
                    new Type("todo"),
                    getTagSet("friends")),
        };
    }

    public static ReadOnlyPlanus getSamplePlanus() {
        Planus sampleAb = new Planus();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
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
