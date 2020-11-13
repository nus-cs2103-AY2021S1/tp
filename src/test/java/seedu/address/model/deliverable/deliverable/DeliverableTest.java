package seedu.address.model.deliverable.deliverable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeliverables.HOME_PAGE;
import static seedu.address.testutil.TypicalDeliverables.NAVIGATION;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeliverableBuilder;

public class DeliverableTest {

    //Not implemented yet
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        assertFalse(false);
    }

    @Test
    public void isSameDeliverable() {
        // same object -> returns true
        assertTrue(HOME_PAGE.isSameDeliverable(HOME_PAGE));

        // null -> returns false
        assertFalse(HOME_PAGE.isSameDeliverable(null));

        // different title, everything else same -> returns false
        Deliverable editedHomepage = new DeliverableBuilder(HOME_PAGE).withTitle("Implement home page").build();
        assertFalse(HOME_PAGE.isSameDeliverable(editedHomepage));

        // same title and deadline, different other attributes -> returns true
        Deliverable editedNavigation = new DeliverableBuilder(NAVIGATION).withTitle("Build home page")
                .withDeadline("12-05-2020 12:00").build();
        assertTrue(HOME_PAGE.isSameDeliverable(editedNavigation));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deliverable homepageCopy = new DeliverableBuilder(HOME_PAGE).build();
        assertTrue(HOME_PAGE.equals(homepageCopy));

        // same object -> returns true
        assertTrue(HOME_PAGE.isSameDeliverable(HOME_PAGE));

        // null -> returns false
        assertFalse(HOME_PAGE.equals(null));

        // different type -> returns false
        assertFalse(HOME_PAGE.equals(5));

        // different deliverable -> returns false
        assertFalse(HOME_PAGE.equals(NAVIGATION));

        // different title -> returns false
        Deliverable editedHomepage = new DeliverableBuilder(HOME_PAGE).withTitle("Implement home page").build();
        assertFalse(HOME_PAGE.isSameDeliverable(editedHomepage));

        // different milestone -> returns false

        // different description -> returns false

        // different deadline -> returns false

        // different contacts -> returns false

    }
}
