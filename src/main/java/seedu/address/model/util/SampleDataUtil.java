package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.TaskManager;
import seedu.address.model.ReadOnlyTaskManager;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Event;
import seedu.address.model.task.Description;
import seedu.address.model.task.DoneStatus;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Title;
import seedu.address.model.task.ToDo;

/**
 * Contains utility methods for populating {@code TaskManager} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new ToDo(new Title("cs2103 tp"), new Description("Team Project"), new Priority("High"),
                getTagSet("cs2103", "projects")),
            new Deadline(new Title("cs2103 peer review"), new Description("Peer reviews for tp"),
                new Priority("Medium"), new TaskDate("2020-10-10"),
                getTagSet("cs2103")),
            new Event(new Title("cs2103 lecture"), new Description("Weekly lecture"), new Priority("Low"),
                new TaskDate("2020-10-09"), new TaskTime("12:00"),
                getTagSet("neighbours")),
        };
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        TaskManager sampleTm = new TaskManager();
        for (Task sampleTask : getSampleTasks()) {
            sampleTm.addTask(sampleTask);
        }
        return sampleTm;
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
