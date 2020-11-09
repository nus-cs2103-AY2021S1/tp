package seedu.address.model.travelplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.ConstructorUtils.VALID_ACCOMMODATION_LIST_B;
import static seedu.address.testutil.ConstructorUtils.VALID_ACTIVITY_LIST_B;
import static seedu.address.testutil.ConstructorUtils.VALID_FRIEND_LIST_B;
import static seedu.address.testutil.typicals.TypicalTravelPlans.AUSTRALIA_TRIP;
import static seedu.address.testutil.typicals.TypicalTravelPlans.BOSTON_TRIP;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.travelplan.exceptions.DuplicateTravelPlanException;
import seedu.address.model.travelplan.exceptions.TravelPlanNotFoundException;
import seedu.address.testutil.builders.TravelPlanBuilder;

public class UniqueTravelPlanListTest {

    private final UniqueTravelPlanList uniqueTravelPlanList = new UniqueTravelPlanList();

    @Test
    public void contains_nullTravelPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTravelPlanList.contains(null));
    }

    @Test
    public void contains_travelPlanNotInList_returnsFalse() {
        assertFalse(uniqueTravelPlanList.contains(AUSTRALIA_TRIP));
    }

    @Test
    public void contains_travelPlanInList_returnsTrue() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        assertTrue(uniqueTravelPlanList.contains(AUSTRALIA_TRIP));
    }

    @Test
    public void contains_travelPlanWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        TravelPlan editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP)
                .withAccommodationList(VALID_ACCOMMODATION_LIST_B)
                .withActivityList(VALID_ACTIVITY_LIST_B)
                .withFriendList(VALID_FRIEND_LIST_B)
                .build();
        assertTrue(uniqueTravelPlanList.contains(editedAustraliaTrip));
    }

    @Test
    public void add_nullTravelPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTravelPlanList.add(null));
    }

    @Test
    public void add_duplicateTravelPlan_throwsDuplicateTravelPlanException() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        assertThrows(DuplicateTravelPlanException.class, () -> uniqueTravelPlanList.add(AUSTRALIA_TRIP));
    }

    @Test
    public void setTravelPlan_nullTargetTravelPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTravelPlanList.setTravelPlan(null, AUSTRALIA_TRIP));
    }

    @Test
    public void setTravelPlan_nullEditedTravelPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTravelPlanList.setTravelPlan(AUSTRALIA_TRIP, null));
    }

    @Test
    public void setTravelPlan_targetTravelPlanNotInList_throwsTravelPlanNotFoundException() {
        assertThrows(TravelPlanNotFoundException.class, () ->
                uniqueTravelPlanList.setTravelPlan(AUSTRALIA_TRIP, AUSTRALIA_TRIP));
    }

    @Test
    public void setTravelPlan_editedTravelPlanIsSameTravelPlan_success() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        uniqueTravelPlanList.setTravelPlan(AUSTRALIA_TRIP, AUSTRALIA_TRIP);
        UniqueTravelPlanList expectedUniqueTravelPlanList = new UniqueTravelPlanList();
        expectedUniqueTravelPlanList.add(AUSTRALIA_TRIP);
        assertEquals(expectedUniqueTravelPlanList, uniqueTravelPlanList);
    }

    @Test
    public void setTravelPlan_editedTravelPlanHasSameIdentity_success() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        TravelPlan editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP)
                .withAccommodationList(VALID_ACCOMMODATION_LIST_B)
                .withActivityList(VALID_ACTIVITY_LIST_B)
                .withFriendList(VALID_FRIEND_LIST_B)
                .build();
        uniqueTravelPlanList.setTravelPlan(AUSTRALIA_TRIP, editedAustraliaTrip);
        UniqueTravelPlanList expectedUniqueTravelPlanList = new UniqueTravelPlanList();
        expectedUniqueTravelPlanList.add(editedAustraliaTrip);
        assertEquals(expectedUniqueTravelPlanList, uniqueTravelPlanList);
    }

    @Test
    public void setTravelPlan_editedTravelPlanHasDifferentIdentity_success() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        uniqueTravelPlanList.setTravelPlan(AUSTRALIA_TRIP, BOSTON_TRIP);
        UniqueTravelPlanList expectedUniqueTravelPlanList = new UniqueTravelPlanList();
        expectedUniqueTravelPlanList.add(BOSTON_TRIP);
        assertEquals(expectedUniqueTravelPlanList, uniqueTravelPlanList);
    }

    @Test
    public void setTravelPlan_editedTravelPlanHasNonUniqueIdentity_throwsDuplicateTravelPlanException() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        uniqueTravelPlanList.add(BOSTON_TRIP);
        assertThrows(DuplicateTravelPlanException.class, () ->
                uniqueTravelPlanList.setTravelPlan(AUSTRALIA_TRIP, BOSTON_TRIP));
    }

    @Test
    public void remove_nullTravelPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTravelPlanList.remove(null));
    }

    @Test
    public void remove_travelPlanDoesNotExist_throwsTravelPlanNotFoundException() {
        assertThrows(TravelPlanNotFoundException.class, () -> uniqueTravelPlanList.remove(AUSTRALIA_TRIP));
    }

    @Test
    public void remove_existingTravelPlan_removesTravelPlan() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        uniqueTravelPlanList.remove(AUSTRALIA_TRIP);
        UniqueTravelPlanList expectedUniqueTravelPlanList = new UniqueTravelPlanList();
        assertEquals(expectedUniqueTravelPlanList, uniqueTravelPlanList);
    }

    @Test
    public void setTravelPlans_nullUniqueTravelPlanList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTravelPlanList.setTravelPlans((UniqueTravelPlanList) null));
    }

    @Test
    public void setTravelPlans_uniqueTravelPlanList_replacesOwnListWithProvidedUniqueTravelPlanList() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        UniqueTravelPlanList expectedUniqueTravelPlanList = new UniqueTravelPlanList();
        expectedUniqueTravelPlanList.add(BOSTON_TRIP);
        uniqueTravelPlanList.setTravelPlans(expectedUniqueTravelPlanList);
        assertEquals(expectedUniqueTravelPlanList, uniqueTravelPlanList);
    }

    @Test
    public void setTravelPlans_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTravelPlanList.setTravelPlans((List<TravelPlan>) null));
    }

    @Test
    public void setTravelPlans_list_replacesOwnListWithProvidedList() {
        uniqueTravelPlanList.add(AUSTRALIA_TRIP);
        List<TravelPlan> travelPlanList = Collections.singletonList(BOSTON_TRIP);
        uniqueTravelPlanList.setTravelPlans(travelPlanList);
        UniqueTravelPlanList expectedUniqueTravelPlanList = new UniqueTravelPlanList();
        expectedUniqueTravelPlanList.add(BOSTON_TRIP);
        assertEquals(expectedUniqueTravelPlanList, uniqueTravelPlanList);
    }

    @Test
    public void setTravelPlans_listWithDuplicateTravelPlans_throwsDuplicateTravelPlanException() {
        List<TravelPlan> listWithDuplicateTravelPlans = Arrays.asList(AUSTRALIA_TRIP, AUSTRALIA_TRIP);
        assertThrows(DuplicateTravelPlanException.class, () ->
                uniqueTravelPlanList.setTravelPlans(listWithDuplicateTravelPlans));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueTravelPlanList.asUnmodifiableObservableList().remove(0));
    }
}
