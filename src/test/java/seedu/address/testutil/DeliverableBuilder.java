package seedu.address.testutil;

import seedu.address.model.deliverable.deliverable.Deadline;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.deliverable.deliverable.Milestone;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

/**
 * A utility class to help with building Deliverable objects.
 */
public class DeliverableBuilder {

    public static final String DEFAULT_TITLE = "Deliverable1";
    public static final String DEFAULT_MILESTONE = "1.0";
    public static final String DEFAULT_DESCRIPTION = "Finalise features";
    public static final String DEFAULT_DEADLINE = "12-12-2020 23:59";
    public static final boolean DEFAULT_IS_COMPLETE = false;
    public static final String DEFAULT_CONTACTS = "Michael, Samuel";

    private Title title;
    private Milestone milestone;
    private Description description;
    private Deadline deadline;
    private Contacts contacts;
    private boolean isComplete;

    /**
     * Creates a {@code DeliverableBuilder} with the default details.
     */
    public DeliverableBuilder() {
        title = new Title(DEFAULT_TITLE);
        milestone = new Milestone(DEFAULT_MILESTONE);
        description = new Description(DEFAULT_DESCRIPTION);
        deadline = new Deadline(DEFAULT_DEADLINE);
        contacts = new Contacts(DEFAULT_CONTACTS);
        isComplete = DEFAULT_IS_COMPLETE;
    }

    /**
     * Initializes the DeliverableBuilder with the data of {@code deliverableToCopy}.
     */
    public DeliverableBuilder(Deliverable deliverableToCopy) {
        title = deliverableToCopy.getTitle();
        milestone = deliverableToCopy.getMilestone();
        description = deliverableToCopy.getDescription();
        deadline = deliverableToCopy.getDeadline();
        contacts = deliverableToCopy.getContacts();
        isComplete = deliverableToCopy.getIsComplete();
    }

    /**
     * Sets the {@code Title} of the {@code Deliverable} that we are building.
     */
    public DeliverableBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Milestone} of the {@code Deliverable} that we are building.
     */
    public DeliverableBuilder withMilestone(String milestone) {
        this.milestone = new Milestone(milestone);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Deliverable} that we are building.
     */
    public DeliverableBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Deliverable} that we are building.
     */
    public DeliverableBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Contacts} of the {@code Deliverable} that we are building.
     */
    public DeliverableBuilder withContacts(String contacts) {
        this.contacts = new Contacts(contacts);
        return this;
    }

    /**
     * Sets the {@code isComplete} of the {@code Deliverable} that we are building.
     */
    public DeliverableBuilder withIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    public Deliverable build() {
        return new Deliverable(title, milestone, description, deadline, isComplete, contacts);
    }

}
