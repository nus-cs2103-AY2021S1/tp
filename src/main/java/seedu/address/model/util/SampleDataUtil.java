package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MainCatalogue;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.project.DueDate;
import seedu.address.model.project.Leader;
import seedu.address.model.project.Name;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code MainCatalogue} with sample data.
 */
public class SampleDataUtil {
    public static Project[] getSampleProjects() {
        return new Project[] {
            new Project(new Name("Alex Yeoh"), new Leader("87438807"), new ProjectDescription("alexyeoh@example.com"),
                new DueDate("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Project(new Name("Bernice Yu"), new Leader("99272758"), new ProjectDescription("berniceyu@example.com"),
                new DueDate("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Project(new Name("Charlotte Oliveiro"), new Leader("93210283"),
                new ProjectDescription("charlotte@example.com"),
                new DueDate("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Project(new Name("David Li"), new Leader("91031282"), new ProjectDescription("lidavid@example.com"),
                new DueDate("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Project(new Name("Irfan Ibrahim"), new Leader("92492021"), new ProjectDescription("irfan@example.com"),
                new DueDate("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Project(new Name("Roy Balakrishnan"), new Leader("92624417"),
                new ProjectDescription("royb@example.com"),
                new DueDate("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
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

}
