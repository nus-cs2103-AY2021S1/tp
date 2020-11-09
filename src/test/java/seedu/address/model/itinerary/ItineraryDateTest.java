package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ItineraryDateTest {

    private final ItineraryDate firstDate = new ItineraryDate("25-02-2020");
    private final ItineraryDate secondDate = new ItineraryDate("03-03-2020");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItineraryDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ItineraryDate(invalidDate));
    }

    @Test
    public void isValidItineraryDate() {
        // null date
        assertThrows(NullPointerException.class, () -> ItineraryDate.isValidDate(null));

        // blank date
        assertFalse(ItineraryDate.isValidDate("")); // empty string
        assertFalse(ItineraryDate.isValidDate(" ")); //spaces only

        // Invalid dates
        assertFalse(ItineraryDate.isValidDate("31-11-2022")); // Month with invalid day
        assertFalse(ItineraryDate.isValidDate("00-12-2020")); // Invalid day
        assertFalse(ItineraryDate.isValidDate("32-12-2020")); // Invalid day
        assertFalse(ItineraryDate.isValidDate("5-10-2005")); // Invalid day
        assertFalse(ItineraryDate.isValidDate("04-00-2023")); // Invalid month
        assertFalse(ItineraryDate.isValidDate("09-13-2010")); // Invalid month
        assertFalse(ItineraryDate.isValidDate("15-1-2005")); // Invalid month
        assertFalse(ItineraryDate.isValidDate("15-1-5")); // Invalid year
        assertFalse(ItineraryDate.isValidDate("15-1-15")); // Invalid year
        assertFalse(ItineraryDate.isValidDate("15-1-2005")); // Invalid year
        assertFalse(ItineraryDate.isValidDate("29-02-2006")); // Not leap year
        assertFalse(ItineraryDate.isValidDate("12 02 2007")); // Invalid format
        assertFalse(ItineraryDate.isValidDate("12/02/2007")); // Invalid format
        assertFalse(ItineraryDate.isValidDate("2001 12 20")); // Invalid format
        assertFalse(ItineraryDate.isValidDate("30 04")); // Invalid format
        assertFalse(ItineraryDate.isValidDate("27-08")); // Invalid format
        assertFalse(ItineraryDate.isValidDate("12 Jan")); // Invalid format
        assertFalse(ItineraryDate.isValidDate("30 April 2013")); // Invalid format

        // Valid dates
        assertTrue(ItineraryDate.isValidDate("25-12-2017")); // Valid format
        assertTrue(ItineraryDate.isValidDate("29-02-2024")); // Leap year
    }

    @Test
    public void getLocalDate_validInput_correctResult() {
        ItineraryDate itineraryDate = new ItineraryDate("28-02-2024");
        assertEquals(itineraryDate.getLocalDate(), LocalDate.parse("2024-02-28"));
    }

    @Test
    public void isBeforeItineraryDate() {
        assertTrue(firstDate.isBefore(secondDate));
        assertFalse(firstDate.isBefore(firstDate));
    }

    @Test
    public void isAfterItineraryDate() {
        assertTrue(secondDate.isAfter(firstDate));
        assertFalse(firstDate.isAfter(firstDate));
    }

    @Test
    public void isEqualItineraryDate() {
        assertTrue(firstDate.isEqual(firstDate));
    }

    @Test
    public void daysBetweenItineraryDates() {
        assertEquals(ItineraryDate.daysBetween(firstDate, secondDate), 8);
    }

}
