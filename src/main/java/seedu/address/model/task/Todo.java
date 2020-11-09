package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class Todo extends Task {
    /**
     * The format of inputted dates that the class can accept.
     */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * The format of outputted dates by the class.
     */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * The deadline of the task to be completed by.
     */
    private LocalDateTime deadline;

    /**The collaborative link url. */
    private CollaborativeLink collaborativeLink;

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     */
    public Todo(boolean isDone, String description, String deadline, Set<Tag> tags) {
        super(description, tags);
        this.isDone = isDone;
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     */
    public Todo(boolean isDone, String description, String deadline, Recurrence recurrence, Set<Tag> tags) {
        super(description, tags);
        this.isDone = isDone;
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.recurrence = recurrence;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     */
    public Todo(boolean isDone, String description, String deadline, CollaborativeLink link, Set<Tag> tags) {
        super(description, tags);
        this.isDone = isDone;
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.collaborativeLink = link;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     */
    public Todo(boolean isDone, String description, String deadline,
                CollaborativeLink link, Recurrence recurrence, Set<Tag> tags) {
        super(description, tags);
        this.isDone = isDone;
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.collaborativeLink = link;
        this.recurrence = recurrence;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(String description, String deadline, Set<Tag> tags) {
        super(description, tags);
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param collaborativeLink the collaborative link of todo.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(String description, String deadline, CollaborativeLink collaborativeLink, Set<Tag> tags) {
        super(description, tags);
        assert deadline != null;
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.collaborativeLink = collaborativeLink;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param collaborativeLink the collaborative link of todo.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(String description, LocalDateTime deadline, CollaborativeLink collaborativeLink, Set<Tag> tags) {
        super(description, tags);
        assert deadline != null;
        this.deadline = deadline;
        this.collaborativeLink = collaborativeLink;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(String description, String deadline, Recurrence recurrence, Set<Tag> tags) {
        super(description, tags);
        assert deadline != null;
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATE_TIME_FORMAT);
        this.recurrence = recurrence;
    }

    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a LocalDateTime in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(String description, LocalDateTime deadline, Recurrence recurrence, Set<Tag> tags) {
        super(description, tags);
        assert deadline != null;
        this.deadline = deadline;
        this.recurrence = recurrence;
    }


    /**
     * Constructs a task that has not been completed
     * with a brief description and deadline for the task to be completed by.
     *
     * @param isDone      indicates if the deadline has been completed.
     * @param description a brief description of the deadline.
     * @param deadline    a LocalDateTime in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     * @param link        the Collaborative Link of todo.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(boolean isDone, String description, LocalDateTime deadline,
                Recurrence recurrence, CollaborativeLink link, Set<Tag> tags) {
        super(isDone, description, tags);
        assert deadline != null;
        this.deadline = deadline;
        this.recurrence = recurrence;
        this.collaborativeLink = link;
    }

    /**
     * Constructs a task, which may or may not have been completed,
     * with a brief description and deadline for the task to be completed by.
     *
     * @param isDone      indicates if the deadline has been completed.
     * @param description a brief description of the deadline.
     * @param deadline    a String in a specific format (inputFormatter) which specifies a date.
     * @param recurrence the recurrence of todo.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(boolean isDone, String description, LocalDateTime deadline, Recurrence recurrence, Set<Tag> tags) {
        super(isDone, description, tags);
        assert deadline != null;
        this.deadline = deadline;
        this.recurrence = recurrence;
    }

    /**
     * Constructs a task, which may or may not have been completed,
     * with a brief description and deadline for the task to be completed by.
     *
     * @param isDone      indicates if the deadline has been completed.
     * @param description a brief description of the deadline.
     * @param deadline    a date and time specifies a deadline.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(boolean isDone, String description, LocalDateTime deadline, Set<Tag> tags) {
        super(isDone, description, tags);
        assert deadline != null;
        this.deadline = deadline;
    }

    /**
     * Constructs a task, which may or may not have been completed,
     * with a brief description and deadline for the task to be completed by.
     *
     * @param isDone      indicates if the deadline has been completed.
     * @param description a brief description of the deadline.
     * @param deadline    a date and time specifies a deadline.
     * @param link        a Collaborative Link for the todo.
     * @param tags        a set of tags attached to the todo
     */
    public Todo(boolean isDone, String description, LocalDateTime deadline, CollaborativeLink link, Set<Tag> tags) {
        super(isDone, description, tags);
        assert deadline != null;
        this.deadline = deadline;
        this.collaborativeLink = link;
    }

    /**
     * Constructs a task, which may or may not have been completed,
     * with a brief description and deadline for the task to be completed by.
     *
     * @param description a brief description of the deadline.
     * @param deadline    a date and time specifies a deadline.
     * @param recurrence the recurrence of todo.
     * @param link        a Collaborative Link for the todo.
     * @param tags        a set of tags attached to the todo.
     */
    public Todo(String description, LocalDateTime deadline,
                Recurrence recurrence, CollaborativeLink link, Set<Tag> tags) {
        super(description, tags);
        assert deadline != null;
        this.deadline = deadline;
        this.recurrence = recurrence;
        this.collaborativeLink = link;
    }
    /**
     * Returns a String representation of the deadline with the format of outputFormatter.
     *
     * @return a String representation of the deadline with the format of outputFormatter.
     */
    public String deadlineToString() {
        return this.deadline.format(OUTPUT_DATE_TIME_FORMAT).toString();
    }
    @Override
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Returns a String representation of the task.
     * This representation includes the status icon, description, and deadline in the format of outputFormatter.
     *
     * @return a String representation of the task.
     */
    @Override
    public String toString() {
        if (this.recurrence == null && this.collaborativeLink != null) {
            return "[" + getStatusIcon() + "] " + getDescription()
                    + " (by: " + deadlineToString() + ") " + "Link: " + this.collaborativeLink.toString() + " "
                    + getTagsToString();
        } else if (this.recurrence != null && this.collaborativeLink == null) {
            return "[" + getStatusIcon() + "] " + getDescription()
                    + " (by: " + deadlineToString() + ") " + "Recurrence: " + this.recurrence.toString() + " "
                    + getTagsToString();
        } else if (this.recurrence == null && this.collaborativeLink == null) {
            return "[" + getStatusIcon() + "] " + getDescription()
                    + " (by: " + deadlineToString() + ") " + getTagsToString();
        } else {
            return "[" + getStatusIcon() + "] " + getDescription()
                    + " (by: " + deadlineToString() + ") " + "Link: " + this.collaborativeLink.toString() + " "
                    + "Recurrence: " + this.recurrence.toString() + " " + getTagsToString();
        }
    }

    /**
     * Returns a boolean value indicating if the task is equal to
     * another object by determining if descriptions, deadline, and isDone parameters
     * are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the task is equal or not equal to the object respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Todo) {
            Todo task = (Todo) o;
            boolean isEqualDeadlines;
            if (this.recurrence == null && this.collaborativeLink != null) {
                isEqualDeadlines = this.description.equals(task.description)
                        && this.deadline.equals(task.deadline) && this.isDone == task.isDone
                        && this.collaborativeLink.equals(task.collaborativeLink) && task.recurrence == null
                        && this.tags.equals(task.tags);
            } else if (this.recurrence != null && this.collaborativeLink == null) {
                isEqualDeadlines = this.description.equals(task.description)
                        && this.deadline.equals(task.deadline) && this.isDone == task.isDone
                        && this.recurrence.equals(task.recurrence) && task.collaborativeLink == null
                        && this.tags.equals(task.tags);
            } else if (this.recurrence == null && this.collaborativeLink == null) {
                System.out.println("masuk else if");
                System.out.println(this);
                System.out.println(task);
                isEqualDeadlines = this.description.equals(task.description)
                        && this.deadline.equals(task.deadline) && this.isDone == task.isDone
                        && task.recurrence == null && task.collaborativeLink == null
                        && this.tags.equals(task.tags);
                System.out.println(isEqualDeadlines);
            } else {
                isEqualDeadlines = this.description.equals(task.description)
                        && this.deadline.equals(task.deadline) && this.isDone == task.isDone
                        && this.recurrence.equals(task.recurrence)
                        && this.collaborativeLink.equals(task.collaborativeLink)
                        && this.tags.equals(task.tags);
            }
            return isEqualDeadlines;
        } else {
            return false;
        }
    }

    /**
     * Returns true if both todos of the same description have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two todos.
     */
    public boolean isSameTodo(Todo otherTodo) {
        if (otherTodo == this) {
            return true;
        }

        return otherTodo != null
                && otherTodo.getDescription().equals(getDescription())
                && (otherTodo.getDescriptionDateTime().equals(getDescriptionDateTime()));
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    @Override
    public String saveFormat() {
        if (isDone) {
            return "D | 1 | " + this.getDescription() + " | "
                + getInputDate()
                + " | " + getTagsToString();
        } else {
            return "D | 0 | " + this.getDescription() + " | " + getInputDate()
                + " | " + getTagsToString();
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getDateTime() {
        return deadlineToString();
    }

    public String getInputDate() {
        return this.deadline.format(INPUT_DATE_TIME_FORMAT).toString();
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return this.deadline;
    }

    @Override
    public boolean isTodo() {
        return true;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    public String getDescriptionDateTime() {
        return this.description + " (by: " + getDateTime() + ")";
    }

    @Override
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public LocalDateTime getStart() {
        return this.deadline;
    }

    @Override
    public LocalDateTime getEnd() {
        return null;
    }

    @Override
    public String getType() {
        return "Todo";
    }

    @Override
    public Optional<Link> getLink() {
        return Optional.ofNullable(this.collaborativeLink);
    }

    public CollaborativeLink getCollaborativeLink() {
        return this.collaborativeLink;
    }

    @Override
    public Recurrence getRecurrence() {
        return this.recurrence;
    }

    public boolean hasRecurrence() {
        return getRecurrence() != null;
    }
}
