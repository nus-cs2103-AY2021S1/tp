package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.friend.Friend;
import seedu.address.model.travelplan.AccommodationList;
import seedu.address.model.travelplan.ActivityList;
import seedu.address.model.travelplan.FriendList;
import seedu.address.model.travelplan.TravelPlan;

/**
 * Jackson-friendly version of {@link TravelPlan}.
 */
public class JsonAdaptedTravelPlan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TravelPlan's %s field is missing!";

    private final String name;
    private final String startDate;
    private final String endDate;
    private final List<JsonAdaptedActivity> activities = new ArrayList<>();
    private final List<JsonAdaptedAccommodation> accommodations = new ArrayList<>();
    private final List<JsonAdaptedFriend> friends = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTravelPlan} with the given TravelPlan details.
     */
    @JsonCreator
    public JsonAdaptedTravelPlan(@JsonProperty("name") String name,
                                 @JsonProperty("startDate") String startDate,
                                 @JsonProperty("endDate") String endDate,
                                 @JsonProperty("activities") List<JsonAdaptedActivity> activities,
                                 @JsonProperty("accommodations") List<JsonAdaptedAccommodation> accommodations,
                                 @JsonProperty("friends") List<JsonAdaptedFriend> friends) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        if (activities != null) {
            this.activities.addAll(activities);
        }
        if (accommodations != null) {
            this.accommodations.addAll(accommodations);
        }
        if (friends != null) {
            this.friends.addAll(friends);
        }
    }

    /**
     * Converts a given {@code TravelPlan} into this class for Jackson use.
     */
    public JsonAdaptedTravelPlan(TravelPlan source) {
        name = source.getName().getValue();
        startDate = source.getStartDate().getDate();
        endDate = source.getEndDate().getDate();
        activities.addAll(source.getObservableActivityList().stream()
                .map(JsonAdaptedActivity::new)
                .collect(Collectors.toList()));
        accommodations.addAll(source.getObservableAccommodationList().stream()
                .map(JsonAdaptedAccommodation::new)
                .collect(Collectors.toList()));
        friends.addAll(source.getObservableFriendList().stream()
                .map(JsonAdaptedFriend::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted travel plan object into the model's {@code TravelPlan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted travel plan.
     */
    public TravelPlan toModelType() throws IllegalValueException {
        final List<Activity> travelPlanActivities = new ArrayList<>();
        for (JsonAdaptedActivity activity : activities) {
            travelPlanActivities.add(activity.toModelType());
        }

        final List<Accommodation> travelPlanAccommodations = new ArrayList<>();
        for (JsonAdaptedAccommodation accommodation : accommodations) {
            travelPlanAccommodations.add(accommodation.toModelType());
        }

        final List<Friend> travelPlanFriends = new ArrayList<>();
        for (JsonAdaptedFriend friend : friends) {
            travelPlanFriends.add(friend.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    WanderlustDate.class.getSimpleName()));
        }
        if (!WanderlustDate.isValidWanderlustDate(startDate)) {
            throw new IllegalValueException(WanderlustDate.MESSAGE_CONSTRAINTS);
        }
        final WanderlustDate modelStartDate = new WanderlustDate(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    WanderlustDate.class.getSimpleName()));
        }
        if (!WanderlustDate.isValidWanderlustDate(endDate)) {
            throw new IllegalValueException(WanderlustDate.MESSAGE_CONSTRAINTS);
        }
        final WanderlustDate modelEndDate = new WanderlustDate(endDate);

        final ActivityList modelActivities = new ActivityList();
        modelActivities.setActivities(travelPlanActivities);

        final AccommodationList modelAccommodations = new AccommodationList();
        modelAccommodations.setAccommodations(travelPlanAccommodations);

        final FriendList modelFriends = new FriendList();
        modelFriends.setFriends(travelPlanFriends);

        return new TravelPlan(modelName, modelStartDate, modelEndDate, modelActivities, modelAccommodations,
                modelFriends);
    }
}
