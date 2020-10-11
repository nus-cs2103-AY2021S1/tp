package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Represents a Project in the main catalogue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final ProjectName projectName;
    private final Deadline deadline;
    private final RepoUrl repoUrl;

    // Data fields
    private final ProjectDescription projectDescription;
    private final Set<ProjectTag> projectTags = new HashSet<>();
    private final HashMap<PersonName, Participation> listOfParticipations = new HashMap<>();
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Meeting> meetings = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, Deadline deadline, RepoUrl repoUrl, ProjectDescription projectDescription,
                   Set<ProjectTag> projectTags,
                   HashMap<PersonName, Participation> listOfParticipations, Set<Task> tasks) {
        requireAllNonNull(projectName, deadline, repoUrl, projectDescription, projectTags, listOfParticipations, tasks);
        this.projectName = projectName;
        this.deadline = deadline;
        this.repoUrl = repoUrl;
        this.projectDescription = projectDescription;
        this.projectTags.addAll(projectTags);
        this.listOfParticipations.putAll(listOfParticipations);
        this.tasks.addAll(tasks);
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

    /**
     * Gets all attendees of a specific meeting
     */
    public Set<Person> getAttendeesOfMeeting(Meeting meeting) {
        HashSet<Person> attendees = new HashSet<Person>();
        for (Map.Entry<PersonName, Participation> entry: listOfParticipations.entrySet()) {
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
            p.getPersonName(), new Participation(p, this));
    }

    public List<Task> getFilteredTaskList() {
        return new ArrayList<>(tasks);
    } // TODO: May update when adding filters

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
