package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Represents a Project in the main catalogue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final ProjectName projectName;
    private final Deadline deadline;
    private final Email email;

    // Data fields
    private final ProjectDescription projectDescription;
    private final Set<Tag> tags = new HashSet<>();
    private final HashMap<PersonName, Participation> listOfParticipations = new HashMap<>();
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Meeting> meetings = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, Deadline deadline, Email email, ProjectDescription projectDescription,
                   Set<Tag> tags,
                   HashMap<PersonName, Participation> listOfParticipations, Set<Task> tasks) {
        requireAllNonNull(projectName, deadline, email, projectDescription, tags, listOfParticipations, tasks);
        this.projectName = projectName;
        this.deadline = deadline;
        this.email = email;
        this.projectDescription = projectDescription;
        this.tags.addAll(tags);
        this.listOfParticipations.putAll(listOfParticipations);
        this.tasks.addAll(tasks);
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Email getEmail() {
        return email;
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
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && (otherProject.getDeadline().equals(getDeadline()) || otherProject.getEmail().equals(getEmail()));
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
                && otherProject.getEmail().equals(getEmail())
                && otherProject.getProjectDescription().equals(getProjectDescription())
                && otherProject.getTags().equals(getTags())
                && otherProject.getTasks().equals(getTasks());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName, deadline, email, projectDescription, tags, tasks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Project Name: ")
                .append(getProjectName())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" Email: ")
                .append(getEmail())
                .append(" ProjectDescription: ")
                .append(getProjectDescription())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Tasks: ");
        getTasks().forEach(builder::append);
        return builder.toString();
    }

}
