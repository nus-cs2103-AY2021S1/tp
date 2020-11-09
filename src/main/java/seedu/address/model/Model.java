package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Nameable;
import seedu.address.model.commons.TravelPlanObject;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.friend.Friend;
import seedu.address.model.travelplan.AccommodationList;
import seedu.address.model.travelplan.ActivityList;
import seedu.address.model.travelplan.FriendList;
import seedu.address.model.travelplan.TravelPlan;
import seedu.address.model.travelplan.UniqueTravelPlanList;
import seedu.address.model.wishlist.Wishlist;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Nameable> PREDICATE_SHOW_ALL_TRAVEL_PLAN = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Nameable> PREDICATE_SHOW_ALL_ACTIVITY = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Nameable> PREDICATE_SHOW_ALL = unused -> true;
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' travel planner file path.
     */
    Path getTravelPlannerFilePath();

    /**
     * Sets the user prefs' travel planner file path.
     */
    void setTravelPlannerFilePath(Path travelPlannerFilePath);

    /**
     * Replaces travel planner data with the data in {@code travelPlanner}.
     */
    void setTravelPlanner(ReadOnlyTravelPlanner travelPlanner);

    /** Returns the TravelPlanner */
    ReadOnlyTravelPlanner getTravelPlanner();

    /**
     * Returns true if a travel plan with the same identity as {@code travelPlan} exists in the travel planner.
     */
    boolean hasTravelPlan(TravelPlan travelPlan);

    /**
     * Returns true if an activity with the same identity as {@code activity} exists in the wishlist.
     */
    boolean hasActivity(Activity activity);

    /**
     * Returns true if a travel plan object with the same identity as {@code tPObj} exists in the directory.
     */
    boolean hasTravelPlanObject(TravelPlanObject tPObj);

    /**
     * Returns true if a travel plan object with the same identity as {@code tPObj} exists in the travel plan.
     */
    boolean hasTravelPlanObject(TravelPlanObject tPObj, int travelPlanIndex);

    /**
     * Deletes the given travel plan.
     * The travel plan must exist in the travel planner.
     */
    void deleteTravelPlan(TravelPlan target);

    /**
     * Deletes the given activity.
     * The activity must exist in the wishlist.
     */
    void deleteActivity(Activity target);

    /**
     * Deletes the given travel plan object.
     * The travel plan object must exist in the travel plan.
     */
    void deleteTravelPlanObject(TravelPlanObject tPObj);

    /**
     * Adds the given travel plan.
     * {@code travelPlan} must not already exist in the travel planner.
     */
    void addTravelPlan(TravelPlan travelPlan);

    /**
     * Adds the given activity.
     * {@code activity} must not already exist in the wishlist.
     */
    void addActivity(Activity activity);

    /**
     * Adds the given activity to the travel plan at the given index in the travel plan list.
     */
    void addActivity(Activity activity, Index travelPlanIndex);

    /**
     * Adds the given travel plan object.
     * {@code tpObj} must not already exist in the travel plan.
     */
    void addTravelPlanObject(TravelPlanObject tPObj);

    /**
     * Replaces the given travel plan {@code target} with {@code editedTravelPlan}.
     * {@code target} must exist in the travel planner.
     * The travel plan identity of {@code editedTravelPlan} must not be the same as another existing travel plan in the
     * travel planner.
     */
    void setTravelPlan(TravelPlan target, TravelPlan editedTravelPlan);

    /**
     * Replaces the given activity {@code target} with {@code editedActivity}.
     * {@code target} must exist in the wishlist.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the
     * wishlist.
     */
    void setActivity(Activity target, Activity editedActivity);

    /**
     * Replaces the given travel plan object {@code target} with {@code editedTravelPlanObject}.
     * {@code target} must exist in the travel plan.
     * The travel plan object identity of {@code editedTravelPlanObject} must not be the same as another existing travel
     * plan object in the travel plan.
     */
    void setTravelPlanObject(TravelPlanObject target, TravelPlanObject editedTravelPlanObject);

    /** Returns an unmodifiable view of the filtered travel plan list */
    ObservableList<TravelPlan> getFilteredTravelPlanList();

    /** Returns an unmodifiable view of the filtered wishlist */
    ObservableList<Activity> getFilteredWishlist();

    /** Returns an unmodifiable view of the filtered activity list */
    ObservableList<Activity> getFilteredActivityList();

    /** Returns an unmodifiable view of the filtered accommodation list */
    ObservableList<Accommodation> getFilteredAccommodationList();

    /** Returns an unmodifiable view of the filtered friend list */
    ObservableList<Friend> getFilteredFriendList();

    /**
     * Updates the filter of the filtered travel plan list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTravelPlanList(Predicate<Nameable> predicate);

    /**
     * Updates the filter of the filtered wishlist to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredWishlist(Predicate<Nameable> predicate);

    /**
     * Updates the filter of the filtered activity list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredActivityList(Predicate<Nameable> predicate);

    /**
     * Updates the filter of the filtered accommodation list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAccommodationList(Predicate<Nameable> predicate);

    /**
     * Updates the filter of the filtered friend list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFriendList(Predicate<Nameable> predicate);

    /**
     * Replaces the directory with the directory at {@code index}.
     * {@code index} must exist in the travel planner.
     */
    void setDirectory(int index);

    /**
     * Returns the current directory that the wanderlust is currently in.
     */
    Directory getDirectory();

    /**
     * Checks if Directory is travelplan or wishlist.
     * @return true if travelplan, false if wishlist.
     */
    boolean isDirectoryTypeTravelPlan();

    /**
     * Returns the {@code ObservableDirectory}.
     */
    ObservableDirectory getObservableDirectory();

    /**
     * Creates a deep copy of the activity and adds the deep copy to the travel plan at the
     * given index in the travel plan list.
     */
    void copyActivity(Activity activity, Index travelPlanIndex);

    /**
     * Returns the travel plan list as {@code UniqueTravelPlanList}.
     */
    UniqueTravelPlanList getTravelPlanList();

    /**
     * Returns the wishlist as {@code Wishlist}.
     */
    Wishlist getWishlist();

    /**
     * Returns the activity list as {@code ActivityList}.
     */
    ActivityList getActivityList();

    /**
     * Returns the accommodation list as {@code AccommodationList}.
     */
    AccommodationList getAccommodationList();

    /**
     * Returns the friend list as {@code FriendList}.
     */
    FriendList getFriendList();

    /**
     * Sorts the wishlist with the given comparator.
     * Can be sorted according to cost, importance, date or name.
     */
    void sortWishlist(Comparator<Activity> comparator);

    /**
     * Sorts the activity list with the given comparator.
     * Can be sorted according to cost, importance, date or name.
     */
    void sortActivityList(Comparator<Activity> comparator);

    /**
     * Sorts the accommodation list with the given comparator.
     * Can be sorted according to cost, date or name.
     */
    void sortAccommodationList(Comparator<Accommodation> comparator);

    /**
     * Sorts the friend list with the given comparator.
     * Can be sorted according to name.
     */
    void sortFriendList(Comparator<Friend> comparator);

    /**
     * Checks if accommodation object date is within the model's travel plan start and end date.
     * @return false if accommodation start date and end date is within travel plan start date and end date.
     */
    boolean isValidAccommodationDate(WanderlustDate startDate, WanderlustDate endDate);

    /**
     * Checks if activity object date time is within the model's travel plan start and end date.
     * @return true if activity date is within travel plan start date and end date range.
     */
    boolean isValidActivityDate(WanderlustDateTime activityDateTime);

    /**
     * Checks if activity object date time is within the model's travel plan start and end date.
     * @return true if activity date is within travel plan start date and end date range.
     */
    boolean isValidActivityDate(WanderlustDateTime activityDateTime, TravelPlan travelPlan);
}
