package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Represents a Project in the main catalogue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {
    private static final Predicate<Task> SHOW_ALL_TASKS_PREDICATE = task -> true;
    private static final Predicate<Meeting> SHOW_ALL_MEETINGS_PREDICATE = meeting -> true;
    // Identity fields
    private final ProjectName projectName;
    private final Deadline deadline;
    private final RepoUrl repoUrl;

    // Data fields
    private final ProjectDescription projectDescription;
    private final Set<ProjectTag> projectTags = new HashSet<>();
    private final HashMap<GitUserName, Participation> listOfParticipations = new HashMap<>();
    private Predicate<Task> taskFilter = SHOW_ALL_TASKS_PREDICATE;
    private Predicate<Meeting> meetingFilter = SHOW_ALL_MEETINGS_PREDICATE;
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Meeting> meetings = new HashSet<>();

    // Display helper
    private Optional<Task> taskOnView;

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, Deadline deadline, RepoUrl repoUrl, ProjectDescription projectDescription,
                   Set<ProjectTag> projectTags,
                   HashMap<GitUserName, Participation> listOfParticipations, Set<Task> tasks, Set<Meeting> meetings) {
        requireAllNonNull(projectName, deadline, repoUrl, projectDescription, projectTags,
            listOfParticipations, tasks, meetings);
        this.projectName = projectName;
        this.deadline = deadline;
        this.repoUrl = repoUrl;
        this.projectDescription = projectDescription;
        this.projectTags.addAll(projectTags);
        this.listOfParticipations.putAll(listOfParticipations);
        this.tasks.addAll(tasks);
        this.meetings.addAll(meetings);
        this.taskOnView = Optional.empty();
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

    public Set<Meeting> getMeetings() {
        return meetings;
    }
    public boolean addMeeting(Meeting meeting) {
        return meetings.add(meeting);
    }
    public boolean addTask(Task task) {
        return tasks.add(task);
    }
    public boolean deleteTask(Task task) {
        return tasks.remove(task);
    }
    public void updateTaskFilter(Predicate<Task> predicate) {
        this.taskFilter = predicate;
    }
    public void showAllTasks() {
        this.taskFilter = SHOW_ALL_TASKS_PREDICATE;
    }
    public void updateMeetingFilter(Predicate<Meeting> predicate) {
        this.meetingFilter = predicate;
    }
    public void showAllMeetings() {
        this.meetingFilter = SHOW_ALL_MEETINGS_PREDICATE;
    }
    public Optional<Task> getTaskOnView() {
        return taskOnView;
    }
    public void updateTaskOnView(Task t) {
        taskOnView = Optional.of(t);
    }

    /**
     * Gets all attendees of a specific meeting
     */
    public Set<Person> getAttendeesOfMeeting(Meeting meeting) {
        HashSet<Person> attendees = new HashSet<>();
        for (Map.Entry<GitUserName, Participation> entry: listOfParticipations.entrySet()) {
            attendees.add(entry.getValue().getPerson());
        }
        return attendees;
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
     * Adds a participation instance of a Person to a project
     */
    public void addParticipation(Person p) {
        listOfParticipations.put(
            p.getGitUserName(), new Participation(p, this));
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
     * Deletes the Participation with the member Git UserName.
     */
    public void deleteParticipation(String gitUserName) {
        if (listOfParticipations.containsKey(new GitUserName(gitUserName))) {
            listOfParticipations.remove(new GitUserName(gitUserName));
        }
    }

    /**
     * Gets the complete list of Teammates associated with this project
     */
    public List<Person> getTeammates() {
        List<Person> listOfPersons = new ArrayList<>();
        for (Map.Entry<GitUserName, Participation> entry: listOfParticipations.entrySet()) {
            Person p = entry.getValue().getPerson();
            listOfPersons.add(p);
        }
        return listOfPersons;
    }

    /**
     * Checks if name is in teammate list
     * TODO: IMPROVE THE WAY A TEAMMATE IS FOUND IN THE LIST
     */
    public boolean getTeammatePresence(GitUserIndex gitUserIndex) {
        boolean teammatePresent = false;
        List<Person> listOfTeammates = this.getTeammates();
        for (Person teammate : listOfTeammates) {
            if (teammate.getGitUserNameString().equals(gitUserIndex.getGitUserName())) {
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
        List<Person> listOfTeammates = this.getTeammates();
        for (int i = 0; i < listOfTeammates.size(); i++) {
            if (listOfTeammates.get(i).getGitUserNameString().equals(gitUserIndex.getGitUserName())) {
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
    public void removeParticipation(Person teammate) {
        listOfParticipations.remove(teammate.getGitUserName());
    }

    /**
     * Returns the filtered list of tasks that is last shown.
     */
    public List<Task> getFilteredTaskList() {
        return tasks.stream().filter(taskFilter).collect(Collectors.toList());
    }

    /**
     * Returns the filtered list of meetings that is last shown.
     */
    public List<Meeting> getFilteredMeetingList() {
        return meetings.stream().filter(meetingFilter).collect(Collectors.toList());
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
        return otherProject.getProjectName().equals(getProjectName())
            && otherProject.getDeadline().equals(getDeadline())
            && otherProject.getRepoUrl().equals(getRepoUrl())
            && otherProject.getProjectDescription().equals(getProjectDescription())
            && otherProject.getProjectTags().equals(getProjectTags())
            && otherProject.getTasks().equals(getTasks());
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
