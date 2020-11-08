package seedu.address.testutil.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Tasks objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Finish LAB 02";
    public static final String[] ARRAY_OF_TAGS = new String[] {"CS2100", "LAB"};
    public static final List<String> DEFAULT_TAGS = new ArrayList<>(Arrays.asList(ARRAY_OF_TAGS));
    public static final String DEFAULT_PRIORITY = "HIGH";
    public static final String DEFAULT_DATE = "2020-10-10";
    public static final String DEFAULT_STATUS = "NOT_COMPLETED";
    public static final String DEFAULT_DATE_CREATED = "2020-10-01";

    private TaskName name;
    private Set<Tag> tags;
    private Priority priority;
    private Date date;
    private Status status;
    private LocalDate dateCreated;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new TaskName(DEFAULT_NAME);
        tags = new HashSet<>();
        tags.addAll(DEFAULT_TAGS.stream().map(Tag::new).collect(Collectors.toList()));
        priority = Priority.valueOf(DEFAULT_PRIORITY);
        date = new Date(DEFAULT_DATE);
        status = Status.valueOf(DEFAULT_STATUS);
        dateCreated = LocalDate.parse(DEFAULT_DATE_CREATED);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName().orElse(null);
        tags = taskToCopy.getTags().orElse(new HashSet<>());
        priority = taskToCopy.getPriority().orElse(null);
        date = taskToCopy.getDate().orElse(null);
        status = taskToCopy.getStatus().orElse(null);
        dateCreated = taskToCopy.getDateCreated().orElse(null);
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new TaskName(name);
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
     * Sets the {@code Priority} of the {@code Task} that we are building.
     */
    public TaskBuilder withPriority(String priority) {
        if (priority != null) {
            this.priority = Priority.valueOf(priority);
        } else {
            this.priority = null;
        }
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        if (date != null) {
            this.date = new Date(date);
        } else {
            this.date = null;
        }
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(String status) {
        this.status = Status.valueOf(status);
        return this;
    }

    /**
     * Sets the {@code LocalDate dateCreated} of the {@code Task} that we are building.
     */
    public TaskBuilder withDateCreated(String dateCreated) {
        this.dateCreated = LocalDate.parse(dateCreated);
        return this;
    }

    /**
     * Builds a task.
     *
     * @return a task
     */
    public Task build() {
        // the date created is set to now
        Task temp = new Task(name);
        temp = temp.setTags(tags);
        temp = temp.setPriority(priority);
        temp = temp.setDate(date);
        temp = temp.setStatus(status);
        temp = temp.setDateCreated(dateCreated);
        return temp;
    }
}
