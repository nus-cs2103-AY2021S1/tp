package seedu.address.logic.command.add;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Directory;
import seedu.address.model.Model;
import seedu.address.model.ObservableDirectory;
import seedu.address.model.ReadOnlyTravelPlanner;
import seedu.address.model.ReadOnlyUserPrefs;
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

public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getTravelPlannerFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTravelPlannerFilePath(Path travelPlannerFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTravelPlanner(ReadOnlyTravelPlanner travelPlanner) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTravelPlanner getTravelPlanner() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTravelPlan(TravelPlan travelPlan) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasActivity(Activity activity) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTravelPlanObject(TravelPlanObject tPObj) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTravelPlanObject(TravelPlanObject tPObj, int travelPlanIndex) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTravelPlan(TravelPlan target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteActivity(Activity target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTravelPlanObject(TravelPlanObject tPObj) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTravelPlan(TravelPlan travelPlan) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addActivity(Activity activity) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public void addActivity(Activity activity, Index travelPlanIndex) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTravelPlanObject(TravelPlanObject tPObj) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTravelPlan(TravelPlan target, TravelPlan editedTravelPlan) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setActivity(Activity target, Activity editedActivity) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTravelPlanObject(TravelPlanObject target, TravelPlanObject editedTravelPlanObject) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<TravelPlan> getFilteredTravelPlanList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Activity> getFilteredWishlist() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Activity> getFilteredActivityList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Accommodation> getFilteredAccommodationList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Friend> getFilteredFriendList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTravelPlanList(Predicate<Nameable> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredWishlist(Predicate<Nameable> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredActivityList(Predicate<Nameable> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredAccommodationList(Predicate<Nameable> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredFriendList(Predicate<Nameable> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDirectory(int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Directory getDirectory() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableDirectory getObservableDirectory() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public UniqueTravelPlanList getTravelPlanList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Wishlist getWishlist() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ActivityList getActivityList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public AccommodationList getAccommodationList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public FriendList getFriendList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortWishlist(Comparator<Activity> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortActivityList(Comparator<Activity> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortAccommodationList(Comparator<Accommodation> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortFriendList(Comparator<Friend> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void copyActivity(Activity activity, Index travelPlanIndex) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isValidAccommodationDate(WanderlustDate startDate, WanderlustDate endDate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isValidActivityDate(WanderlustDateTime activityDateTime) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isValidActivityDate(WanderlustDateTime activityDateTime, TravelPlan travelPlan) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public boolean isDirectoryTypeTravelPlan() {
        return true;
    }
}
