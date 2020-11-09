package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalActivities.ARCHERY;
import static seedu.address.testutil.typicals.TypicalActivities.getTypicalActivityList;
import static seedu.address.testutil.typicals.TypicalTravelPlans.AUSTRALIA_TRIP;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;
import seedu.address.model.travelplan.TravelPlan;
import seedu.address.model.travelplan.exceptions.DuplicateTravelPlanException;
import seedu.address.testutil.builders.ActivityBuilder;
import seedu.address.testutil.builders.TravelPlanBuilder;

public class TravelPlannerTest {

    private final TravelPlanner travelPlanner = new TravelPlanner();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), travelPlanner.getObservableTravelPlanList());
        assertEquals(Collections.emptyList(), travelPlanner.getObservableWishlist());
    }

    @Test
    public void constructor_withValidReadOnlyTravelPlanner() {
        TravelPlanner newData = getTypicalTravelPlanner();
        TravelPlanner newTravelPlanner = new TravelPlanner(newData);
        assertEquals(newData, newTravelPlanner);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> travelPlanner.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTravelPlanner_replacesData() {
        TravelPlanner newData = getTypicalTravelPlanner();
        travelPlanner.resetData(newData);
        assertEquals(newData, travelPlanner);
    }

    @Test
    public void resetData_withDuplicateTravelPlans_throwsDuplicateTravelPlanException() {
        // Two travel plans with the same identity fields
        TravelPlan editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP)
                .withActivityList(getTypicalActivityList(2))
                .build();
        List<TravelPlan> newTravelPlans = Arrays.asList(AUSTRALIA_TRIP, editedAustraliaTrip);
        TravelPlannerStub newData = new TravelPlannerStub(newTravelPlans);

        assertThrows(DuplicateTravelPlanException.class, () -> travelPlanner.resetData(newData));
    }

    @Test
    public void hasTravelPlan_nullTravelPlan_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> travelPlanner.hasTravelPlan(null));
    }

    @Test
    public void hasActivity_nullActivity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> travelPlanner.hasActivity(null));
    }

    @Test
    public void hasTravelPlan_travelPlanNotInTravelPlanner_returnsFalse() {
        assertFalse(travelPlanner.hasTravelPlan(AUSTRALIA_TRIP));
    }

    @Test
    public void hasActivity_activityNotInTravelPlanner_returnsFalse() {
        assertFalse(travelPlanner.hasActivity(ARCHERY));
    }

    @Test
    public void hasTravelPlan_travelPlanInTravelPlanner_returnsTrue() {
        travelPlanner.addTravelPlan(AUSTRALIA_TRIP);
        assertTrue(travelPlanner.hasTravelPlan(AUSTRALIA_TRIP));
    }

    @Test
    public void hasActivity_activityInTravelPlanner_returnsTrue() {
        travelPlanner.addActivity(ARCHERY);
        assertTrue(travelPlanner.hasActivity(ARCHERY));
    }

    @Test
    public void hasTravelPlan_travelPlanWithSameIdentityFieldsInTravelPlanner_returnsTrue() {
        travelPlanner.addTravelPlan(AUSTRALIA_TRIP);
        TravelPlan editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP)
                .withActivityList(getTypicalActivityList(2))
                .build();
        assertTrue(travelPlanner.hasTravelPlan(editedAustraliaTrip));
    }

    @Test
    public void hasActivity_activityWithSameIdentityFieldsInTravelPlanner_returnsTrue() {
        travelPlanner.addActivity(ARCHERY);
        Activity editedArchery = new ActivityBuilder(ARCHERY).withCost(ActivityBuilder.DEFAULT_COST).build();
        assertTrue(travelPlanner.hasActivity(editedArchery));
    }

    @Test
    public void getTravelPlanList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> travelPlanner.getObservableTravelPlanList().remove(0));
    }

    @Test
    public void getWishlist_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> travelPlanner.getObservableWishlist().remove(0));
    }

    /**
     * A stub ReadOnlyTravelPlanner whose travel plan list and wishlist can violate interface constraints.
     */
    private static class TravelPlannerStub implements ReadOnlyTravelPlanner {
        private final ObservableList<TravelPlan> travelPlans = FXCollections.observableArrayList();
        private final ObservableList<Activity> wishlist = FXCollections.observableArrayList();

        TravelPlannerStub(Collection<TravelPlan> travelPlans) {
            this.travelPlans.setAll(travelPlans);
        }

        @Override
        public ObservableList<TravelPlan> getObservableTravelPlanList() {
            return travelPlans;
        }

        @Override
        public ObservableList<Activity> getObservableWishlist() {
            return wishlist;
        }
    }
}
