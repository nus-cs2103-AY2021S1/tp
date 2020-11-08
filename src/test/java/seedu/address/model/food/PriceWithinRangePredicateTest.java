package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.exceptions.ParseException;

public class PriceWithinRangePredicateTest {
    @Test
    public void constructor_invalidPrice_throwsParseException() {
        Inequality inequality = Inequality.get("<=");
        assertThrows(ParseException.class, () -> new PriceWithinRangePredicate(inequality, -0.1));
        assertThrows(ParseException.class, () -> new PriceWithinRangePredicate(inequality, -1.3));
        assertThrows(ParseException.class, () -> new PriceWithinRangePredicate(inequality, 1000.01));
        assertThrows(ParseException.class, () -> new PriceWithinRangePredicate(inequality, 10000));
    }

    @Test
    public void equals() throws ParseException {
        Inequality inequality0 = Inequality.get("<");
        Inequality inequality1 = Inequality.get("<=");
        PriceWithinRangePredicate predicate0 = new PriceWithinRangePredicate(inequality0, 1.00);
        PriceWithinRangePredicate predicate1 = new PriceWithinRangePredicate(inequality1, 1.00);
        PriceWithinRangePredicate predicate2 = new PriceWithinRangePredicate(inequality1, 2.00);

        // same object -> returns true
        assertTrue(predicate0.equals(predicate0));

        //same values -> returns true
        PriceWithinRangePredicate predicateCopy = new PriceWithinRangePredicate(inequality0, 1.00);
        assertTrue(predicate0.equals(predicateCopy));

        //same price but different inequality -> returns false
        assertFalse(predicate0.equals(predicate1));

        //same inequality but different price -> returns false
        assertFalse(predicate1.equals(predicate2));

        // different inequality and price returns false
        assertFalse(predicate0.equals(predicate2));

        // different type -> returns false
        assertFalse(predicate0.equals(0));

        // null -> returns false
        assertFalse(predicate0.equals(null));
    }

    @Test
    public void predicateTestMenuItemPrice_success() throws ParseException {
        Inequality inequalityLesserThan = Inequality.get("<");
        Inequality inequalityLesserThanOrEqual = Inequality.get("<=");
        Inequality inequalityGreaterThanOrEqual = Inequality.get(">=");
        Inequality inequalityGreaterThan = Inequality.get(">");

        PriceWithinRangePredicate predicate1 = new PriceWithinRangePredicate(inequalityLesserThan, 2);
        PriceWithinRangePredicate predicate2 = new PriceWithinRangePredicate(inequalityLesserThanOrEqual, 2);
        PriceWithinRangePredicate predicate3 = new PriceWithinRangePredicate(inequalityGreaterThan, 3);
        PriceWithinRangePredicate predicate4 = new PriceWithinRangePredicate(inequalityGreaterThanOrEqual, 3);

        PriceWithinRangePredicate predicateWithDifferentPrice = new PriceWithinRangePredicate(inequalityLesserThan,
                4);
        MenuItem item0 = new MenuItem("Prata", 2.00, new HashSet<>(), "");
        MenuItem item1 = new MenuItem("Cheese Fries", 3.00, new HashSet<>(), "");

        assertFalse(predicate1.test(item0));
        assertTrue(predicate2.test(item0));
        assertFalse(predicate1.test(item1));
        assertFalse(predicate2.test(item1));

        assertFalse(predicate3.test(item1));
        assertTrue(predicate4.test(item1));
        assertFalse(predicate3.test(item0));
        assertFalse(predicate4.test(item0));

        assertTrue(predicateWithDifferentPrice.test(item1));
        assertTrue(predicateWithDifferentPrice.test(item1));
    }
}
