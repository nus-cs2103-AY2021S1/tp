package seedu.address.testutil.builders;

import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.travelplan.AccommodationList;
import seedu.address.model.travelplan.ActivityList;
import seedu.address.model.travelplan.FriendList;
import seedu.address.model.travelplan.TravelPlan;

public class TravelPlanBuilder {
    public static final String DEFAULT_NAME = "Trip to Hawaii";
    public static final String DEFAULT_START_DATE = "2020-12-12";
    public static final String DEFAULT_END_DATE = "2020-12-25";

    private Name name;
    private WanderlustDate startDate;
    private WanderlustDate endDate;
    private ActivityList activityList;
    private AccommodationList accommodationList;
    private FriendList friendList;

    /**
     * Creates a {TravelPlanBuilder} with the default details.
     */
    public TravelPlanBuilder() {
        name = new Name(DEFAULT_NAME);
        startDate = new WanderlustDate(DEFAULT_START_DATE);
        endDate = new WanderlustDate(DEFAULT_END_DATE);
        activityList = new ActivityList();
        accommodationList = new AccommodationList();
        friendList = new FriendList();
    }

    /**
     * Initializes the TravelPlanBuilder with the data of {@code TravelPlanToCopy}.
     */
    public TravelPlanBuilder(TravelPlan travelPlanToCopy) {
        name = travelPlanToCopy.getName();
        startDate = travelPlanToCopy.getStartDate();
        endDate = travelPlanToCopy.getEndDate();
        activityList = travelPlanToCopy.getActivityList();
        friendList = travelPlanToCopy.getFriendList();
        accommodationList = travelPlanToCopy.getAccommodationList();
    }

    /**
     * Sets the {@code Name} of the {@code TravelPlan} that we are building.
     */
    public TravelPlanBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code TravelPlan} that we are building.
     */
    public TravelPlanBuilder withStartDate(String startDate) {
        this.startDate = new WanderlustDate(startDate);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code TravelPlan} that we are building.
     */
    public TravelPlanBuilder withEndDate(String endDate) {
        this.endDate = new WanderlustDate(endDate);
        return this;
    }

    /**
     * Sets the {@code ActivityList} of the {@code TravelPlan} that we are building.
     */
    public TravelPlanBuilder withActivityList(ActivityList activityList) {
        this.activityList = activityList;
        return this;
    }

    /**
     * Sets the {@code AccommodationList} of the {@code TravelPlan} that we are building.
     */
    public TravelPlanBuilder withAccommodationList(AccommodationList accommodationList) {
        this.accommodationList = accommodationList;
        return this;
    }

    /**
     * Sets the {@code FriendList} of the {@code TravelPlan} that we are building.
     */
    public TravelPlanBuilder withFriendList(FriendList friendList) {
        this.friendList = friendList;
        return this;
    }

    public TravelPlan build() {
        return new TravelPlan(name, startDate, endDate, activityList, accommodationList, friendList);
    }

}
