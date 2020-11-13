package seedu.address.model.expenditure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenditureBuilder;
import seedu.address.testutil.TypicalExpenditure;


class ExpenditureTest {

    private Expenditure mcMuffin;

    @BeforeEach
    public void setUp() {
        mcMuffin = TypicalExpenditure.getMcMuffinExpenditure();
    }

    @Test
    public void equalsTest() {
        // same object -> returns true
        assertTrue(mcMuffin.equals(mcMuffin));

        // different type -> returns false
        assertFalse(mcMuffin.equals(5));

        // null -> returns false
        assertFalse(mcMuffin.equals(null));

        // different expenditure -> returns false
        assertFalse(mcMuffin.equals(new ExpenditureBuilder(TypicalExpenditure.getKfcBanditoExpenditure()).build()));

        // different createdOn -> returns false
        Expenditure editedMcmuffin = new ExpenditureBuilder(mcMuffin).withCreatedOn("2020-10-13T00:00:00.000000")
                .build();
        assertFalse(mcMuffin.equals(editedMcmuffin));

        // different name -> returns false
        editedMcmuffin = new ExpenditureBuilder(mcMuffin).withName("McSpicy").build();
        assertFalse(mcMuffin.equals(editedMcmuffin));

        // different price -> returns false
        editedMcmuffin = new ExpenditureBuilder(mcMuffin).withPrice("12.00").build();
        assertFalse(mcMuffin.equals(editedMcmuffin));

        // different tags -> returns false
        editedMcmuffin = new ExpenditureBuilder(mcMuffin).withTags("Burger").build();
        assertFalse(mcMuffin.equals(editedMcmuffin));

        // same value -> return true
        editedMcmuffin = new ExpenditureBuilder(mcMuffin).build();
        assertTrue(mcMuffin.equals(editedMcmuffin));
    }

    @Test
    void getName() {
        assertEquals(new Name("McMuffin"), mcMuffin.getName());
    }

    @Test
    void getPrice() {
        assertEquals(new Price("4.50"), mcMuffin.getPrice());
    }

    @Test
    void getCreatedOn() {
        assertEquals(new Date("2020-10-02T00:00:00.000000"), mcMuffin.getCreatedOn());
    }

    @Test
    void getFormattedCreatedOn() {
        assertEquals(Timestamp.valueOf(LocalDateTime.parse("2020-10-02T00:00:00.000000")),
                mcMuffin.getFormattedCreatedOn());
    }

    @Test
    void getTags() {
        assertEquals(new HashSet<>(), mcMuffin.getTags());
    }


    @Test
    void testToString() {
        assertEquals("McMuffin", mcMuffin.toString());
    }

    @Test
    void contains() {
        assertTrue(mcMuffin.contains("MC"));
    }
}
