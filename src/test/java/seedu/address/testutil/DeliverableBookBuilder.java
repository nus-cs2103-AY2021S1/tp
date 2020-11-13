package seedu.address.testutil;

import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * A utility class to help with building DeliverableBook objects.
 * Example usage: <br>
 *     {@code DeliverableBook ab = new DeliverableBookBuilder().withTitle("Profile screens").build();}
 */
public class DeliverableBookBuilder {

    private DeliverableBook deliverableBook;

    public DeliverableBookBuilder() {
        deliverableBook = new DeliverableBook();
    }

    public DeliverableBookBuilder(DeliverableBook deliverableBook) {
        this.deliverableBook = deliverableBook;
    }

    /**
     * Adds a new {@code Deliverable} to the {@code DeliverableBook} that we are building.
     */
    public DeliverableBookBuilder withDeliverable(Deliverable deliverable) {
        deliverableBook.addDeliverable(deliverable);
        return this;
    }

    public DeliverableBook build() {
        return deliverableBook;
    }
}
