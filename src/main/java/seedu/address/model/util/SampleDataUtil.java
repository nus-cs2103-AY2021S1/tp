package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MainCatalogue;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.project.Address;
import seedu.address.model.project.Email;
import seedu.address.model.project.Name;
import seedu.address.model.project.Phone;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code MainCatalogue} with sample data.
 */
public class SampleDataUtil {
    public static Project[] getSampleProjects() {
        return new Project[] {
            new Project(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new HashMap<>(), getTaskSet("Refactor project class")),
            new Project(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new HashMap<>(),
                getTaskSet("Brainstorm user stories", "Refine data model")),
            new Project(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new HashMap<>(), getTaskSet("Write UG")),
            new Project(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new HashMap<>(), getTaskSet("Delete Address attribute")),
            new Project(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new HashMap<>(), getTaskSet()),
            new Project(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new HashMap<>(), getTaskSet("Write DG"))
        };
    }

    public static ReadOnlyMainCatalogue getSampleMainCatalogue() {
        MainCatalogue sampleAb = new MainCatalogue();
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
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

    /**
     * Returns a task set containing the list of strings given.
     */
    public static Set<Task> getTaskSet(String... strings) {
        return Arrays.stream(strings)
                .map(s -> new Task(s, null, null, 0, false))
                .collect(Collectors.toSet());
    }

}
