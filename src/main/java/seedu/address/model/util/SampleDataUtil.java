package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.Type;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Title("Alex Yeoh"), new DateTime("01-01-2020 12:00"), new Description("alexyeoh,example.com"),
                new Type("todo"),
                getTagSet("friends")),
            new Task(new Title("Bernice Yu"), new DateTime("02-02-2020 12:00"),
                    new Description("berniceyu,example.com"), new Type("deadline"),
                getTagSet("colleagues", "friends")),
            new Task(new Title("Charlotte Oliveiro"), new DateTime("03-03-2020 12:00"),
                    new Description("charlotte,example.com"), new Type("event"),
                getTagSet("neighbours")),
            new Task(new Title("David Li"), new DateTime("04-04-2020 12:00"), new Description("lidavid,example.com"),
                new Type("lesson"),
                getTagSet("family")),
            new Task(new Title("Irfan Ibrahim"), new DateTime("05-05-2020 12:00"), new Description("irfan,example.com"),
                new Type("tutorial"),
                getTagSet("classmates")),
            new Task(new Title("Roy Balakrishnan"), new DateTime("06-06-2020 12:00"),
                    new Description("royb,example.com"), new Type("session"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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
