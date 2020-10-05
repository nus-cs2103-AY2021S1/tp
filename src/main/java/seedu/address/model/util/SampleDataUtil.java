package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MainCatalogue;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Email;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code MainCatalogue} with sample data.
 */
public class SampleDataUtil {
    public static Project[] getSampleProjects() {
        return new Project[] {
            new Project(new ProjectName("Alex Yeoh"), new Deadline("21-04-2021 00:00:00"),
                new Email("alexyeoh@example.com"), new ProjectDescription("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new HashMap<>(), getTaskSet("Refactor project class")),
            new Project(new ProjectName("Bernice Yu"), new Deadline("21-04-2021 00:00:00"),
                new Email("berniceyu@example.com"), new ProjectDescription("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new HashMap<>(),
                getTaskSet("Brainstorm user stories", "Refine data model")),
            new Project(new ProjectName("Charlotte Oliveiro"), new Deadline("21-04-2021 00:00:00"),
                new Email("charlotte@example.com"), new ProjectDescription("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new HashMap<>(), getTaskSet("Write UG")),
            new Project(new ProjectName("David Li"), new Deadline("21-04-2021 00:00:00"),
                new Email("lidavid@example.com"), new ProjectDescription("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new HashMap<>(), getTaskSet("Delete ProjectDescription attribute")),
            new Project(new ProjectName("Irfan Ibrahim"), new Deadline("21-04-2021 00:00:00"),
                new Email("irfan@example.com"), new ProjectDescription("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new HashMap<>(), getTaskSet()),
            new Project(new ProjectName("Roy Balakrishnan"), new Deadline("21-04-2021 00:00:00"),
                new Email("royb@example.com"), new ProjectDescription("Blk 45 Aljunied Street 85, #11-31"),
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
