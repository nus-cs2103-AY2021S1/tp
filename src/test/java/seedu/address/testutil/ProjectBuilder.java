package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.project.DueDate;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.Leader;
import seedu.address.model.project.Name;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Leader leader;
    private ProjectDescription projectDescription;
    private DueDate dueDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        leader = new Leader(DEFAULT_PHONE);
        projectDescription = new ProjectDescription(DEFAULT_EMAIL);
        dueDate = new DueDate(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        name = projectToCopy.getName();
        leader = projectToCopy.getPhone();
        projectDescription = projectToCopy.getEmail();
        dueDate = projectToCopy.getAddress();
        tags = new HashSet<>(projectToCopy.getTags());
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
     * Sets the {@code Address} of the {@code Project} that we are building.
     */
    public ProjectBuilder withAddress(String address) {
        this.dueDate = new DueDate(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Project} that we are building.
     */
    public ProjectBuilder withPhone(String phone) {
        this.leader = new Leader(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Project} that we are building.
     */
    public ProjectBuilder withEmail(String email) {
        this.projectDescription = new ProjectDescription(email);
        return this;
    }

    public Project build() {
        return new Project(name, leader, projectDescription, dueDate, tags);
    }

}
