package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final HashMap<PersonName, Participation> listOfParticipations = new HashMap<>();
    private final Set<Task> tasks = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Project(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                   HashMap<PersonName, Participation> listOfParticipations, Set<Task> tasks) {
        requireAllNonNull(name, phone, email, address, tags, listOfParticipations, tasks);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.listOfParticipations.putAll(listOfParticipations);
        this.tasks.addAll(tasks);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
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

    public void addParticipation(Person p) {
        listOfParticipations.put(p.getName(), new Participation(p, this));
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
                && (otherProject.getPhone().equals(getPhone()) || otherProject.getEmail().equals(getEmail()));
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
                && otherProject.getPhone().equals(getPhone())
                && otherProject.getEmail().equals(getEmail())
                && otherProject.getAddress().equals(getAddress())
                && otherProject.getTags().equals(getTags())
                && otherProject.getTasks().equals(getTasks());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, tasks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Tasks: ");
        getTasks().forEach(builder::append);
        return builder.toString();
    }

}
