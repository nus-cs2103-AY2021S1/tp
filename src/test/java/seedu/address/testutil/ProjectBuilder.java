package seedu.address.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.project.Address;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Email;
import seedu.address.model.project.Name;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_DEADLINE = "29-02-2020 00:00:00";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Deadline deadline;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Set<Task> tasks;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        tasks = new HashSet<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        name = projectToCopy.getName();
        deadline = projectToCopy.getDeadline();
        email = projectToCopy.getEmail();
        address = projectToCopy.getAddress();
        tags = new HashSet<>(projectToCopy.getTags());
        tasks = new HashSet<>(projectToCopy.getTasks());
    }

    /**
     * Sets the {@code Name} of the {@code Project} that we are building.
     */
    public ProjectBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code Set<Task>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTasks(String ... tasks) {
        this.tasks = SampleDataUtil.getTaskSet(tasks);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Project} that we are building.
     */
    public ProjectBuilder withAddress(String address) {
        this.address = new Address(address);
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
     * Sets the {@code Email} of the {@code Project} that we are building.
     */
    public ProjectBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Project build() {
        return new Project(name, deadline, email, address, tags, new HashMap<>(), tasks);
    }

}
