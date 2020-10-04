package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Address;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TITLE = "School work";
    public static final String DEFAULT_DATE = "01-01-2020-1200";
    public static final String DEFAULT_DESCRIPTION = "6 midterms next week.";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Title title;
    private DateTime dateTime;
    private Description description;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        dateTime = new DateTime(DEFAULT_DATE);
        description = new Description(DEFAULT_DESCRIPTION);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        dateTime = taskToCopy.getDateTime();
        description = taskToCopy.getDescription();
        address = taskToCopy.getAddress();
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Task} that we are building.
     */
    public TaskBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Task build() {
        return new Task(title, dateTime, description, address, tags);
    }

}
