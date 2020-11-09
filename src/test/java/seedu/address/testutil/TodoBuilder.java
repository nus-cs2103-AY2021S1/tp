package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Todo;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building To-do objects.
 */
public class TodoBuilder {
    /**
     * The format of inputted dates that the class can accept.
     */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    /**
     * The format of outputted dates by the class.
     */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    private static final String DEFAULT_DESC = "homework";
    private static final String DEFAULT_DATETIME = "14-12-2020 2359";

    private String description;
    private LocalDateTime dateTime;
    private Recurrence recurrence;
    private CollaborativeLink link;
    private Set<Tag> tags;

    /**
     * Creates a {@code TodoBuilder} with the default details.
     */
    public TodoBuilder() {
        description = DEFAULT_DESC;
        dateTime = LocalDateTime.parse(DEFAULT_DATETIME, INPUT_DATE_TIME_FORMAT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TodoBuilder with the data of {@code TodoToCopy}.
     */
    public TodoBuilder(Todo todoToCopy) {
        description = todoToCopy.getDescription();
        dateTime = todoToCopy.getDeadline();
        recurrence = todoToCopy.getRecurrence();
        link = todoToCopy.getCollaborativeLink();
        tags = new HashSet<>(todoToCopy.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code To-do} that we are building.
     */
    public TodoBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code To-do} that we are building.
     */
    public TodoBuilder withDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime, INPUT_DATE_TIME_FORMAT);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code To-do} that we are building.
     */
    public TodoBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Sets the {@code Recurrence} of the {@code To-do} that we are building.
     */
    public TodoBuilder withRecurrence(String recurrenceInput) {
        String[] recurrenceSplit = recurrenceInput.split(" ");
        Integer recurrenceValue = Integer.parseInt(recurrenceSplit[0]);
        String recurrenceTimePeriod = recurrenceSplit[1];
        this.recurrence = new Recurrence(recurrenceValue, recurrenceTimePeriod);
        return this;
    }

    /**
     * Sets the {@code CollaborativeLink} of the {@code To-do} that we are building.
     */
    public TodoBuilder withLink(CollaborativeLink link) {
        this.link = link;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Todo} that we are building.
     */
    public TodoBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Todo build() {
        return new Todo(description, dateTime, recurrence, link, tags);
    }

}
