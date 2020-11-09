package seedu.address.model.travelplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.ConstructorUtils.VALID_COST_B;
import static seedu.address.testutil.typicals.TypicalAccommodations.ALICEHOTEL;
import static seedu.address.testutil.typicals.TypicalAccommodations.getTypicalAccommodationList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.accommodation.exceptions.DuplicateAccommodationException;
import seedu.address.testutil.builders.AccommodationBuilder;

public class AccommodationListTest {

    private final AccommodationList accommodationList = new AccommodationList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), accommodationList.getObservableAccommodationList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accommodationList.resetData(null));
    }

    @Test
    public void resetData_withValidAccommodationList_replacesData() {
        AccommodationList newData = getTypicalAccommodationList(1);
        accommodationList.resetData(newData);
        assertEquals(newData, accommodationList);
    }

    @Test
    public void resetData_withDuplicateAccommodations_throwsDuplicateAccommodationException() {
        // Two accommodations with the same identity fields
        Accommodation editedAliceHotel = new AccommodationBuilder(ALICEHOTEL).withCost(VALID_COST_B).build();
        List<Accommodation> newAccommodations = Arrays.asList(ALICEHOTEL, editedAliceHotel);
        AccommodationListStub newData = new AccommodationListStub(newAccommodations);

        assertThrows(DuplicateAccommodationException.class, () -> accommodationList.resetData(newData));
    }

    @Test
    public void hasAccommodation_nullAccommodation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> accommodationList.hasAccommodation(null));
    }

    @Test
    public void hasAccommodation_accommodationNotInAccommodationList_returnsFalse() {
        assertFalse(accommodationList.hasAccommodation(ALICEHOTEL));
    }

    @Test
    public void hasAccommodation_accommodationInAccommodationList_returnsTrue() {
        accommodationList.addAccommodation(ALICEHOTEL);
        assertTrue(accommodationList.hasAccommodation(ALICEHOTEL));
    }

    @Test
    public void hasAccommodation_accommodationWithSameIdentityFieldsInAccommodationList_returnsTrue() {
        accommodationList.addAccommodation(ALICEHOTEL);
        Accommodation editedAliceHotel = new AccommodationBuilder(ALICEHOTEL).withCost(VALID_COST_B).build();
        assertTrue(accommodationList.hasAccommodation(editedAliceHotel));
    }

    @Test
    public void getAccommodationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                accommodationList.getObservableAccommodationList().remove(0));
    }

    /**
     * TODO:
     * A stub ReadOnlyAccommodationList whose accommodations list can violate interface constraints.
     */
    private static class AccommodationListStub extends AccommodationList {
        private final ObservableList<Accommodation> accommodations = FXCollections.observableArrayList();

        AccommodationListStub(Collection<Accommodation> accommodations) {
            this.accommodations.setAll(accommodations);
        }

        public ObservableList<Accommodation> getObservableAccommodationList() {
            return accommodations;
        }
    }

}
