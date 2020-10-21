package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    private static ArrayList<String> task1 = new ArrayList<>(Arrays.asList(
            "Refactor project class",
            "tedious :(",
            null,
            "25.0",
            "false"));
    private static ArrayList<String> task2 = new ArrayList<>(Arrays.asList(
            "Brainstorm user stories",
            null,
            null,
            "100.0",
            "true"));
    private static ArrayList<String> task3 = new ArrayList<>(Arrays.asList(
            "Refine data model",
            "so hard T.T",
            null,
            "0.0",
            "false"
    ));
    private static ArrayList<String> task4 = new ArrayList<>(Arrays.asList(
            "Write UG",
            null,
            null,
            "50.0",
            "false"
    ));
    private static ArrayList<String> task5 = new ArrayList<>(Arrays.asList(
            "Delete ProjectDescription attribute",
            "easy fast game",
            null,
            "0.0",
            "false"
    ));
    private static ArrayList<String> task6 = new ArrayList<>(Arrays.asList(
            "Write DG",
            "barely done, how now brown cow",
            null,
            "15.0",
            "false"
    ));

    public static Project[] getSampleProjects() {

        return new Project[]{
            new Project(new ProjectName("Aeroknotty"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("Made for pilots wanting to tie the " + "knot"),
                    getTagSet("creative"), new HashMap<>(),
                    getTaskSet(task1),
                    getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Basket Web"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("Puts all your ususal searches into one place"),
                    getTagSet("internet", "iot"), new HashMap<>(),
                    getTaskSet(task2, task3), getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Charletan"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("To nurture a more informed people"),
                    getTagSet("neighbours"), new HashMap<>(),
                    getTaskSet(task4), getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Dacharie"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("kitchen knife subscription service"),
                    getTagSet("family"), new HashMap<>(), getTaskSet(task5),
                    getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Iterab"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"), new ProjectDescription("Habit tracker"),
                    getTagSet("classmates"), new HashMap<>(), getTaskSet(), getMeetingSet("2020-10-10")),
            new Project(new ProjectName("Reuletan"), new Deadline("21-04-2021 00:00:00"),
                    new RepoUrl("http://github.com/a/b.git"),
                    new ProjectDescription("Brings low cost investment to the " + "masses"),
                    getTagSet("colleagues"), new HashMap<>(), getTaskSet(task6), getMeetingSet("2020-10-10"))
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
    public static Set<Task> getTaskSet(List<String>... strings) {
        return Arrays.stream(strings)
                .map(s -> new Task(s.get(0),
                        s.get(1),
                        s.get(2),
                        Double.parseDouble(s.get(3)),
                        Boolean.parseBoolean(s.get(4))))
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

    public static ArrayList<String> getTask1() {
        return task1;
    }

    public static ArrayList<String> getTask2() {
        return task2;
    }

    public static ArrayList<String> getTask3() {
        return task3;
    }

    public static ArrayList<String> getTask4() {
        return task4;
    }

    public static ArrayList<String> getTask5() {
        return task5;
    }

    public static ArrayList<String> getTask6() {
        return task6;
    }
}
