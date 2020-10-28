package seedu.address.model.util;

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
                    Description.defaultDescription(), new Tag("CS1101S")),
            new Deadline(new Title("Assignment 1"), new DeadlineDateTime("25-11-2020 09:00"),
                    new Description("Programming Assignment 2 of CS3230, Very hard."),
                    new Tag("CS3230"), Status.createCompleteStatus(),
                    new Duration(20), DoneDateTime.createDoneNow()),
            Event.createUserEvent(new Title("Source Academy Orientation"), new StartDateTime("25-11-2020 13:00"),
                    new EndDateTime("25-11-2020 18:00"), Description.defaultDescription(),
                    new Tag("CS1101S")),
            Event.createUserEvent(new Title("CS2105 tutorial"), new StartDateTime("25-11-2020 13:00"),
                    new EndDateTime("25-11-2020 15:00"), Description.defaultDescription(),
                    new Tag(("neighbours")))
        };
    }

    public static ReadOnlyPlanus getSamplePlanus() {
        Planus samplePlanus = new Planus();
        for (Task sampleTask : getSampleTasks()) {
            samplePlanus.addTask(sampleTask);
        }
        return samplePlanus;
    }



}
