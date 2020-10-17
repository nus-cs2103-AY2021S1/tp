package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MainCatalogue;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code MainCatalogue} with sample data.
 */
public class SampleDataUtil {
    public static Project[] getSampleProjects() {
        return new Project[]{
            new Project(new ProjectName("Aeroknotty"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("Made for pilots wanting to tie the " + "knot"),
                    getTagSet("creative"), new HashMap<>(), getTaskSet("Refactor project class"),
                    getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Basket Web"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("Puts all your ususal searches into one place"),
                    getTagSet("internet", "iot"), new HashMap<>(),
                    getTaskSet("Brainstorm user stories", "Refine data model"), getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Charletan"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("To nurture a more informed people"),
                    getTagSet("neighbours"), new HashMap<>(),
                    getTaskSet("Write UG"), getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Dacharie"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("kitchen knife subscription service"),
                    getTagSet("family"), new HashMap<>(), getTaskSet("Delete ProjectDescription attribute"),
                    getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Iterab"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"), new ProjectDescription("Habit tracker"),
                    getTagSet("classmates"), new HashMap<>(), getTaskSet(), getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Reuletan"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("Brings low cost investment to the " + "masses"),
                    getTagSet("colleagues"), new HashMap<>(), getTaskSet("Write DG"), getMeetingSet("2020-10-10"))
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
    public static Set<ProjectTag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(ProjectTag::new)
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

    /**
     * Returns a meeting set containing the list of strings given.
     */
    public static Set<Meeting> getMeetingSet(String... strings) {
        return Arrays.stream(strings)
                .map(Meeting::new)
                .collect(Collectors.toSet());
    }

}
