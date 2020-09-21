package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Project in the main catalogue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final Name name;
    private final Leader leader;
    private final ProjectDescription projectDescription;

    // Data fields
    private final DueDate dueDate;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Project(Name name, Leader leader, ProjectDescription projectDescription, DueDate dueDate, Set<Tag> tags) {
        requireAllNonNull(name, leader, projectDescription, dueDate, tags);
        this.name = name;
        this.leader = leader;
        this.projectDescription = projectDescription;
        this.dueDate = dueDate;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Leader getLeader() {
        return leader;
    }

    public ProjectDescription getEmail() {
        return projectDescription;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both projects of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getName().equals(getName())
                && (otherProject.getLeader().equals(getLeader()) || otherProject.getEmail().equals(getEmail()));
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
        return otherProject.getName().equals(getName())
                && otherProject.getLeader().equals(getLeader())
                && otherProject.getEmail().equals(getEmail())
                && otherProject.getDueDate().equals(getDueDate())
                && otherProject.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, leader, projectDescription, dueDate, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Leader: ")
                .append(getLeader())
                .append(" Email: ")
                .append(getEmail())
                .append(" Due Date: ")
                .append(getDueDate())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
