package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.activity.Activity;
import seedu.address.model.travelplan.TravelPlan;

/**
 * Unmodifiable view of a travel planner
 */
public interface ReadOnlyTravelPlanner {

    /**
     * Returns an unmodifiable view of the travel plans list.
     * This list will not contain any duplicate travel plans.
     */
    ObservableList<TravelPlan> getObservableTravelPlanList();

    /**
     * Returns an unmodifiable view of the wishlist.
     * This list will not contain any duplicate activities.
     */
    ObservableList<Activity> getObservableWishlist();

}
