package seedu.address.model.deliverable.deliverable;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.event.TimeEvent;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

/**
 * Represents a Deliverable in the deliverable book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deliverable implements TimeEvent {

    // Identity fields
    private final Title title;

    // Data fields
    private final Milestone milestone;
    private final Description description;
    private final Deadline deadline;
    private final boolean isComplete;
    private final Contacts contacts;

    /**
     * Only title and milestone field must be present. Used when adding new deliverable.
     */
    public Deliverable(Title title, Milestone milestone, Description description,
                       Deadline deadline, Contacts contacts) {
        requireAllNonNull(title, milestone);
        this.title = title;
        this.milestone = milestone;
        this.description = description;
        this.deadline = deadline;
        this.isComplete = false;
        this.contacts = contacts;
    }

    /**
     * Used when editing or completing existing deliverable.
     */
    public Deliverable(Title title, Milestone milestone, Description description, Deadline deadline,
                       boolean isComplete, Contacts contacts) {
        requireAllNonNull(title);
        this.title = title;
        this.milestone = milestone;
        this.description = description;
        this.deadline = deadline;
        this.isComplete = isComplete;
        this.contacts = contacts;
    }

    public Title getTitle() {
        return title;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public Description getDescription() {
        return description;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public Contacts getContacts() {
        return contacts;
    }

    @Override
    public LocalDateTime getIndicatorTime() {
        return deadline.getLocalDateTime();
    }

    /**
     * Returns true if both deliverables share the same title.
     * This defines a weaker notion of equality between two deliverables.
     */
    public boolean isSameDeliverable(Deliverable otherDeliverable) {
        if (otherDeliverable == this) {
            return true;
        }

        return otherDeliverable != null
                && otherDeliverable.getTitle().equals(getTitle())
                && otherDeliverable.getDeadline().equals(getDeadline());
    }

    /**
     * Returns true if both deliverables have the same identity and data fields.
     * This defines a stronger notion of equality between two deliverables.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deliverable)) {
            return false;
        }

        Deliverable otherDeliverable = (Deliverable) other;
        return otherDeliverable.getTitle().equals(getTitle())
                && otherDeliverable.getMilestone().equals(getMilestone())
                && otherDeliverable.getDescription().equals(getDescription())
                && otherDeliverable.getDeadline().equals(getDeadline())
                && otherDeliverable.getContacts().equals(getContacts())
                && otherDeliverable.getIsComplete() == getIsComplete();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, milestone, description, deadline, contacts, isComplete);

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" Milestone: ")
                .append(getMilestone())
                .append(" Contact(s): ")
                .append(getContacts())
                .append(" Description: ")
                .append(getDescription());
        return builder.toString();
    }

}
