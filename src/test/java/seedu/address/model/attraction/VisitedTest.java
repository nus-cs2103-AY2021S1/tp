package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VisitedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Visited(null));
    }

    @Test
    public void constructor_invalidVisited_throwsIllegalArgumentException() {
        String invalidVisited = "not yet";
        assertThrows(IllegalArgumentException.class, () -> new Visited(invalidVisited));
    }

    @Test
    public void isValidVisited() {
        // null rating
        assertThrows(NullPointerException.class, () -> Visited.isValidVisited(null));

        // invalid ratings
        assertFalse(Visited.isValidVisited(" ")); // spaces only
        assertFalse(Visited.isValidVisited("Tru")); //Spelling Errors
        assertFalse(Visited.isValidVisited("Fase"));
        assertFalse(Visited.isValidVisited("Tue"));
        assertFalse(Visited.isValidVisited("TRUE1"));
        assertFalse(Visited.isValidVisited(" TRUE")); // leading space
        assertFalse(Visited.isValidVisited("TRUE ")); // trailing space

        // valid price ranges
        assertTrue(Visited.isValidVisited("")); // empty string
        assertTrue(Visited.isValidVisited("TRUE"));
        assertTrue(Visited.isValidVisited("FALSE"));
        assertTrue(Visited.isValidVisited("False"));
        assertTrue(Visited.isValidVisited("TruE"));
    }
}
