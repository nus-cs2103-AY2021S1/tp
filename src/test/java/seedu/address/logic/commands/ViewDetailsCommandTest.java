package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.NameIsExactlyPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewDetailsCommand}.
 */
public class ViewDetailsCommandTest {

    @Test
    public void equals() {
        NameIsExactlyPredicate firstPredicate =
                new NameIsExactlyPredicate(Collections.singletonList("first"));
        NameIsExactlyPredicate secondPredicate =
                new NameIsExactlyPredicate(Collections.singletonList("second"));

        ViewDetailsCommand findFirstCommand = new ViewDetailsCommand(firstPredicate);
        ViewDetailsCommand findSecondCommand = new ViewDetailsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ViewDetailsCommand findFirstCommandCopy = new ViewDetailsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}
