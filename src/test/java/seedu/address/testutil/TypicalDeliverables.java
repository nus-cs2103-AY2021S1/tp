package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.util.TimeEventComparator;

/**
 * A utility class containing a list of {@code Deliverable} objects to be used in tests.
 */
public class TypicalDeliverables {

    public static final Deliverable HOME_PAGE = new DeliverableBuilder().withTitle("Build home page")
            .withMilestone("1.1")
            .withDescription("Include company description")
            .withDeadline("12-05-2020 12:00")
            .withContacts("Alice, Bob")
            .withIsComplete(false)
            .build();
    public static final Deliverable NAVIGATION = new DeliverableBuilder().withTitle("Implement navigation")
            .withMilestone("1.2")
            .withDescription("Include about us tab")
            .withDeadline("12-09-2020 10:00")
            .withContacts("Alyssa, Bobby Bobby")
            .withIsComplete(false)
            .build();
    public static final Deliverable SORT_AND_FILTER = new DeliverableBuilder().withTitle("Implement sort and filter")
            .withMilestone("1.2")
            .withDescription("For search box")
            .withDeadline("12-09-2020 14:00")
            .withContacts("Cassandra, McClander")
            .withIsComplete(false)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDeliverables() {} // prevents instantiation

    /**
     * Returns an {@code DeliverableBook} with all the typical deliverables.
     */
    public static DeliverableBook getTypicalDeliverableBook() {
        DeliverableBook ab = new DeliverableBook();
        for (Deliverable deliverable : getTypicalDeliverables()) {
            ab.addDeliverable(deliverable);
        }
        return ab;
    }

    public static List<Deliverable> getTypicalDeliverables() {
        ArrayList<Deliverable> deliverableArrayList =
                new ArrayList<>(Arrays.asList(HOME_PAGE, NAVIGATION, SORT_AND_FILTER));
        deliverableArrayList.sort(new TimeEventComparator());
        return deliverableArrayList;
    }
}
