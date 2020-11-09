package seedu.address.model.accommodation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.typicals.TypicalAccommodations.ALICEHOTEL;
import static seedu.address.testutil.typicals.TypicalAccommodations.BOBHOTEL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.builders.AccommodationBuilder;

public class AccommodationTest {

    @Test
    public void isSameAccommodation() {
        // same object -> returns true
        assertTrue(ALICEHOTEL.isSameAccommodation(ALICEHOTEL));

        // null -> returns false
        assertFalse(ALICEHOTEL.isSameAccommodation(null));

        // different Cost and Location -> returns true
        Accommodation editedAlice = new AccommodationBuilder(ALICEHOTEL)
                .withCost("10")
                .withLocation("Not Alice")
                .build();
        assertTrue(ALICEHOTEL.isSameAccommodation(editedAlice));

        // different name -> returns false
        editedAlice = new AccommodationBuilder(ALICEHOTEL).withName("Bob Hotel").build();
        assertFalse(ALICEHOTEL.isSameAccommodation(editedAlice));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Accommodation aliceCopy = new AccommodationBuilder(ALICEHOTEL).build();
        assertTrue(ALICEHOTEL.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICEHOTEL.equals(ALICEHOTEL));

        // null -> returns false
        assertFalse(ALICEHOTEL.equals(null));

        // different type -> returns false
        assertFalse(ALICEHOTEL.equals(5));

        // different Activity -> returns false
        assertFalse(ALICEHOTEL.equals(BOBHOTEL));

        // different name -> returns false
        Accommodation editedAlice = new AccommodationBuilder(ALICEHOTEL).withName("Adam").build();
        assertFalse(ALICEHOTEL.equals(editedAlice));

        // different Cost -> returns false
        editedAlice = new AccommodationBuilder(ALICEHOTEL).withCost("10").build();
        assertFalse(ALICEHOTEL.equals(editedAlice));

        // different Location -> returns false
        editedAlice = new AccommodationBuilder(ALICEHOTEL).withLocation("123 Pasir Ris Park").build();
        assertFalse(ALICEHOTEL.equals(editedAlice));

        // different start date -> returns false
        editedAlice = new AccommodationBuilder(ALICEHOTEL).withStartDate("2019-12-31").build();
        assertFalse(ALICEHOTEL.equals(editedAlice));

        // different end date -> returns false
        editedAlice = new AccommodationBuilder(ALICEHOTEL).withEndDate("2021-05-31").build();
        assertFalse(ALICEHOTEL.equals(editedAlice));
    }
}
