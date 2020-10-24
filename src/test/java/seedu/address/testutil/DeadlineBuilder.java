package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.deadline.DeadlineDateTime;
import seedu.address.model.task.deadline.DoneDateTime;
import seedu.address.model.task.deadline.Duration;
import seedu.address.model.task.deadline.Status;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class DeadlineBuilder {

    public static final String DEFAULT_TITLE = "School work";
    public static final String DEFAULT_DEADLINE_DATE_TIME = "01-01-2020 12:00";
    public static final String DEFAULT_DESCRIPTION = "6 midterms next week.";
    public static final String DEFAULT_DONE_DATE_TIME = "02-01-2020 12:00";
    public static final int DEFAULT_DURATION = 60;
    public static final boolean DEFAULT_STATUS = true;

    private Title title;
    private DeadlineDateTime deadlineDateTime;
    private Description description;
    private DoneDateTime doneDateTime;
    private Duration duration;
    private Set<Tag> tags;
    private Status status;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public DeadlineBuilder() {
        title = new Title(DEFAULT_TITLE);
        deadlineDateTime = new DeadlineDateTime(DEFAULT_DEADLINE_DATE_TIME);
        description = new Description(DEFAULT_DESCRIPTION);
        doneDateTime = new DoneDateTime(DEFAULT_DONE_DATE_TIME);
        tags = new HashSet<>();
        status = new Status(DEFAULT_STATUS);
        duration = new Duration(DEFAULT_DURATION);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public DeadlineBuilder(Deadline deadlineToCopy) {
        title = deadlineToCopy.getTitle();
        deadlineDateTime = deadlineToCopy.getDeadlineDateTime();
        description = deadlineToCopy.getDescription();
        doneDateTime = deadlineToCopy.getDoneDateTime();
        tags = deadlineToCopy.getTags();
        status = deadlineToCopy.getStatus();
        duration = deadlineToCopy.getDuration();
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public DeadlineBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withDeadlineDateTime(String deadlineDateTime) {
        this.deadlineDateTime = new DeadlineDateTime(deadlineDateTime);
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withDoneDateTime(String doneDateTime) {
        this.doneDateTime = new DoneDateTime(doneDateTime);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Task} that we are building to be default DateTime.
     * Simulates the situation that the task is created without a DateTime field.
     */
    public DeadlineBuilder withNullDeadlineDateTime() {
        this.deadlineDateTime = DeadlineDateTime.createNullDeadlineDateTime();
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Task} that we are building to be default DateTime.
     * Simulates the situation that the task is created without a DateTime field.
     */
    public DeadlineBuilder withNullDoneDateTime() {
        this.doneDateTime = DoneDateTime.createNullDoneDateTime();
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public DeadlineBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building to be default description.
     * Simulates the situation that the task is created without a description field.
     */
    public DeadlineBuilder withDefaultDescription() {
        this.description = Description.defaultDescription();
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withStatus(boolean status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Deadline} that we are building to be incomplete status.
     * Simulates the situation that the deadline is first been created.
     */
    public DeadlineBuilder withIncompleteStatus() {
        this.status = Status.createIncompleteStatus();
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task}  that we are building.
     */
    public DeadlineBuilder withDuration(int duration) {
        this.duration = new Duration(duration);
        return this;
    }

    public Deadline build() {
        return new Deadline(title, deadlineDateTime, description, tags, status, duration, doneDateTime);
    }
}
