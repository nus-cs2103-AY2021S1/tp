package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.participation.Participation;
import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_PROJECT_NAME = "Alice Pauline";
    public static final String DEFAULT_DEADLINE = "29-02-2020 00:00:00";
    public static final String DEFAULT_REPOURL = "https://github.com/a/b.git";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private ProjectName projectName;
    private Deadline deadline;
    private RepoUrl repoUrl;
    private ProjectDescription projectDescription;
    private Set<ProjectTag> projectTags;
    private Set<Task> tasks;
    private HashMap<GitUserName, Participation> participations;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        repoUrl = new RepoUrl(DEFAULT_REPOURL);
        projectDescription = new ProjectDescription(DEFAULT_ADDRESS);
        projectTags = new HashSet<>();
        tasks = new HashSet<>();
        participations = new HashMap<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectName = projectToCopy.getProjectName();
        deadline = projectToCopy.getDeadline();
        repoUrl = projectToCopy.getRepoUrl();
        projectDescription = projectToCopy.getProjectDescription();
        projectTags = new HashSet<>(projectToCopy.getProjectTags());
        tasks = new HashSet<>(projectToCopy.getTasks());
        participations = new HashMap<>();
        participations.putAll(projectToCopy.getParticipationHashMap());
    }

    /**
     * Sets the {@code ProjectName} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectName(String projectName) {
        this.projectName = new ProjectName(projectName);
        return this;
    }

    /**
     * Parses the {@code projectTags} into a {@code Set<ProjectTag>} and set it to the {@code Project}
     * that we are building.
     */
    public ProjectBuilder withTags(String... projectTags) {
        this.projectTags = SampleDataUtil.getTagSet(projectTags);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code Set<Task>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTasks(ArrayList<String>... tasks) {
        this.tasks = SampleDataUtil.getTaskSet(tasks);
        return this;
    }

    /**
     * Sets the {@code ProjectDescription} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectDescription(String projectDescription) {
        this.projectDescription = new ProjectDescription(projectDescription);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code RepoUrl} of the {@code Project} that we are building.
     */
    public ProjectBuilder withRepoUrl(String repoUrl) {
        this.repoUrl = new RepoUrl(repoUrl);
        return this;
    }

    /**
     * Sets the {@code Participation} of the {@code Project} that we are building.
     */
    public ProjectBuilder withPeople(String... people) {
        Arrays.stream(people)
                .map(name -> new Participation(name, projectName.toString()))
                .map(participation -> participations.put(participation.getPerson().getGitUserName(), participation));
        return this;
    }

    /**
     * Creates a project
     *
     * @return project sample
     */
    public Project build() {
        return new Project(projectName, deadline, repoUrl, projectDescription,
                projectTags, participations, tasks);
    }

    /**
     * Makes a copy of the given project.
     */
    public static Project makeCopy(Project project) {
        return new Project(project.getProjectName(), project.getDeadline(), project.getRepoUrl(),
            project.getProjectDescription(), project.getProjectTags(),
            project.getParticipationHashMap(), project.getTasks());
    }

    /**
     * Creates copies for all the projects in the given list.
     */
    public static List<Project> makeCopies(List<Project> projectList) {
        return projectList
            .stream()
            .map(project -> makeCopy(project))
            .collect(Collectors.toList());
    }
}

