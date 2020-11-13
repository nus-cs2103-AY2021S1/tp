package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskComparators;

/**
 * Represents a Project in the main catalogue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {
    public static final Predicate<Task> SHOW_ALL_TASKS_PREDICATE = task -> true;
    // List of all Projects
    private static ArrayList<Project> allProjects = new ArrayList<>();

    // Identity fields
    private final ProjectName projectName;
    private final Deadline deadline;
    private final RepoUrl repoUrl;

    // Data fields
    private final ProjectDescription projectDescription;
    private final Set<ProjectTag> projectTags = new HashSet<>();
    private final HashMap<GitUserName, Participation> listOfParticipations = new HashMap<>();
    private Predicate<Task> taskFilter = SHOW_ALL_TASKS_PREDICATE;
    private Comparator<Task> taskComparator = TaskComparators.SORT_BY_DEADLINE;
    private final Set<Task> tasks = new HashSet<>();

    // Display helper
    private Optional<Task> taskOnView;
    private Optional<Participation> teammateOnView;

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, Deadline deadline, RepoUrl repoUrl, ProjectDescription projectDescription,
                   Set<ProjectTag> projectTags,
                   HashMap<GitUserName, Participation> listOfParticipations, Set<Task> tasks) {
        requireAllNonNull(projectName, deadline, repoUrl, projectDescription, projectTags,
                tasks);
        this.projectName = projectName;
        this.deadline = deadline;
        this.repoUrl = repoUrl;
        this.projectDescription = projectDescription;
        this.projectTags.addAll(projectTags);
        if (listOfParticipations != null) {
            this.listOfParticipations.putAll(listOfParticipations);
        }
        this.tasks.addAll(tasks);
        this.taskOnView = Optional.empty();
        this.teammateOnView = Optional.empty();
        allProjects.add(this);
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public RepoUrl getRepoUrl() {
        return repoUrl;
    }

    public ProjectDescription getProjectDescription() {
        return projectDescription;
    }

    public static ArrayList<Project> getAllProjects() {
        return allProjects;
    }

    /**
     * Adds a new unique task to the project
     *
     * @param task task to be added.
     * @return true if the task to be added is unique in the project, and false otherwise.
     */
    public boolean addTask(Task task) {
        Object[] taskArray = tasks.toArray();

        for (Object o : taskArray) {
            if (task.equals(o)) {
                return false;
            }
        }
        return tasks.add(task);

    }

    /**
     * Deletes a task from the project.
     *
     * @param task task to be deleted.
     * @return true if the task to be deleted is in the project, and false otherwise.
     */
    public boolean deleteTask(Task task) {
        for (Participation participation : this.getTeammates()) {
            participation.deleteTask(task);
        }
        return tasks.remove(task);
    }

    public void updateTaskFilter(Predicate<Task> predicate) {
        this.taskFilter = predicate;
    }

    public boolean isFullListOfTasks() {
        return this.taskFilter.equals(SHOW_ALL_TASKS_PREDICATE);
    }

    public void showAllTasks() {
        this.taskFilter = SHOW_ALL_TASKS_PREDICATE;
    }

    public void updateTaskComparator(Comparator<Task> comparator) {
        this.taskComparator = comparator;
    }

    public Optional<Task> getTaskOnView() {
        return taskOnView;
    }

    public Optional<Participation> getTeammateOnView() {
        return this.teammateOnView;
    }

    /**
     * Updates taskOnView with t.
     *
     * @param t taskOnView.
     */
    public void updateTaskOnView(Task t) {
        if (t == null) {
            taskOnView = Optional.empty();
        } else {
            taskOnView = Optional.of(t);
        }
    }

    /**
     * Updates teammateOnView with t.
     *
     * @param p teammateOnView.
     */
    public void updateTeammateOnView(Participation p) {
        if (p == null) {
            teammateOnView = Optional.empty();
        } else {
            teammateOnView = Optional.of(p);
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ProjectTag> getProjectTags() {
        return Collections.unmodifiableSet(projectTags);
    }

    /**
     * Returns an immutable task set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    /**
     * Adds a participation instance to a project
     */
    public void addParticipation(Participation p) {
        listOfParticipations.put(p.getPerson().getGitUserName(),
                new Participation(p.getPerson().getGitUserNameString(), projectName.toString()));
    }

    /**
     * Adds a participation instance to a project with a person
     */
    public Project addParticipation(Person p) {
        listOfParticipations.put(p.getGitUserName(),
                new Participation(p.getGitUserNameString(), projectName.fullProjectName));
        return this;
    }

    /**
     * Adds an existing participation instance to a project
     */
    public void addExistingParticipation(Participation p) {
        listOfParticipations.put(
                p.getPerson().getGitUserName(), p);
    }


    /**
     * Checks whether the project contains a member of the given name.
     */
    public boolean hasParticipation(String gitUserName) {
        return listOfParticipations.containsKey(new GitUserName(gitUserName));
    }

    /**
     * Gets the Participation with the member name.
     */
    public Participation getParticipation(String gitUserName) {
        return listOfParticipations.get(new GitUserName(gitUserName));
    }

    /**
     * Gets the list of Participations.
     *
     * @return
     */
    public Collection<Participation> getParticipationList() {
        return listOfParticipations.values();
    }

    /**
     * Gets the HashMap of Participations.
     *
     * @return
     */
    public HashMap<GitUserName, Participation> getParticipationHashMap() {
        return new HashMap<>(listOfParticipations);
    }

    /**
     * Deletes the Participation with the member Git UserName.
     */
    public void removeParticipationWithName(String gitUserName) {
        if (listOfParticipations.containsKey(new GitUserName(gitUserName))) {
            listOfParticipations.remove(new GitUserName(gitUserName));
        }
    }

    /**
     * Gets the complete list of Teammates associated with this project
     */
    /*public List<Person> getTeammates() {
        List<Person> listOfPersons = new ArrayList<>();
        for (Map.Entry<GitUserName, Participation> entry : listOfParticipations.entrySet()) {
            Person p = entry.getValue().getPerson();
            listOfPersons.add(p);
        }
        return listOfPersons;
    }*/
    public List<Participation> getTeammates() {
        List<Participation> res = new ArrayList<>(Collections.emptyList());
        res.addAll(listOfParticipations.values());
        return res;
    }

    /**
     * Checks if name is in teammate list
     * TODO: IMPROVE THE WAY A TEAMMATE IS FOUND IN THE LIST
     */
    public boolean getTeammatePresence(GitUserIndex gitUserIndex) {
        boolean teammatePresent = false;
        List<Participation> listOfTeammates = this.getTeammates();
        for (Participation teammate : listOfTeammates) {
            if (teammate.getPerson().getGitUserNameString().equals(gitUserIndex.getGitUserNameString())) {
                teammatePresent = true;
                break;
            }
        }
        return teammatePresent;
    }

    /**
     * returns the index of teammate found in the list
     */
    public int getTeammateIndex(GitUserIndex gitUserIndex) {
        List<Participation> listOfTeammates = this.getTeammates();
        for (int i = 0; i < listOfTeammates.size(); i++) {
            if (listOfTeammates.get(i).getPerson().getGitUserNameString().equals(gitUserIndex.getGitUserNameString())) {
                return i;
            }
        }
        //never reached
        return -1;
    }

    /**
     * Removes Teammate from Project
     * TODO: UPDATE STORAGE BY REMOVING TEAMMATE
     */
    public void removeParticipation(Participation teammate) {
        listOfParticipations.remove(teammate.getPerson().getGitUserName());
    }

    /**
     * Returns the filtered and sorted list of tasks that is last shown.
     */
    public List<Task> getFilteredSortedTaskList() {
        return tasks
                .stream()
                .filter(taskFilter)
                .sorted(taskComparator)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if both projects of the same projectName have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getProjectName().equals(getProjectName())
                && (otherProject.getDeadline().equals(getDeadline()) || otherProject.getRepoUrl().equals(getRepoUrl()));
    }

    /**
     * Removes all instances of a person's participation
     */
    public static void deleteAllParticipationOf(String gitUsername) {
        for (Project allProject : allProjects) {
            allProject.removeParticipationWithName(gitUsername);
        }
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;

        boolean taskBoolean = true;

        for (int i = 0; i < tasks.size(); i++) {
            Object[] currentTaskArray = tasks.toArray();
            Object[] otherTaskArray = otherProject.getTasks().toArray();

            for (int j = 0; j < otherTaskArray.length; j++) {
                if (currentTaskArray[i].equals(otherTaskArray[j])) {
                    taskBoolean = true;
                    break;
                } else {
                    taskBoolean = false;
                }
            }
            if (!taskBoolean) {
                break;
            }
        }

        return otherProject.getProjectName().equals(getProjectName())
                && otherProject.getDeadline().equals(getDeadline())
                && otherProject.getRepoUrl().equals(getRepoUrl())
                && otherProject.getProjectDescription().equals(getProjectDescription())
                && otherProject.getProjectTags().equals(getProjectTags())
                && taskBoolean;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName, deadline, repoUrl, projectDescription, projectTags, tasks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Project Name: ")
                .append(getProjectName())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" Email: ")
                .append(getRepoUrl())
                .append(" ProjectDescription: ")
                .append(getProjectDescription())
                .append(" Project Tags: ");
        getProjectTags().forEach(builder::append);
        builder.append(" Tasks: ");
        getTasks().forEach(builder::append);
        return builder.toString();
    }
}
