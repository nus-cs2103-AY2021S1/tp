package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTravelPlanner;
import seedu.address.model.TravelPlanner;
import seedu.address.model.activity.Activity;
import seedu.address.model.travelplan.TravelPlan;

/**
 * An Immutable TravelPlanner that is serializable to JSON format.
 */
@JsonRootName(value = "travelplanner")
public class JsonSerializableTravelPlanner {

    public static final String MESSAGE_DUPLICATE_TRAVEL_PLAN = "Travel plans list contains duplicate travel plan(s).";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "Wishlist contains duplicate activity.";

    private final List<JsonAdaptedTravelPlan> travelPlans = new ArrayList<>();
    private final List<JsonAdaptedActivity> wishlist = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTravelPlanner} with the given travel plans and wishlist.
     */
    @JsonCreator
    public JsonSerializableTravelPlanner(@JsonProperty("travelPlans") List<JsonAdaptedTravelPlan> travelPlans,
                                         @JsonProperty("wishlist") List<JsonAdaptedActivity> wishlist) {
        this.travelPlans.addAll(travelPlans);
        this.wishlist.addAll(wishlist);
    }

    /**
     * Converts a given {@code ReadOnlyTravelPlanner} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTravelPlanner}.
     */
    public JsonSerializableTravelPlanner(ReadOnlyTravelPlanner source) {
        travelPlans.addAll(source.getObservableTravelPlanList().stream().map(JsonAdaptedTravelPlan::new)
                .collect(Collectors.toList()));
        wishlist.addAll(source.getObservableWishlist().stream().map(JsonAdaptedActivity::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this travel planner into the model's {@code TravelPlanner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TravelPlanner toModelType() throws IllegalValueException {
        TravelPlanner travelPlanner = new TravelPlanner();

        for (JsonAdaptedTravelPlan jsonAdaptedTravelPlan : travelPlans) {
            TravelPlan travelPlan = jsonAdaptedTravelPlan.toModelType();
            if (travelPlanner.hasTravelPlan(travelPlan)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRAVEL_PLAN);
            }
            travelPlanner.addTravelPlan(travelPlan);
        }

        for (JsonAdaptedActivity jsonAdaptedActivity : wishlist) {
            Activity activity = jsonAdaptedActivity.toModelType();
            if (travelPlanner.hasActivity(activity)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ACTIVITY);
            }
            travelPlanner.addActivity(activity);
        }

        return travelPlanner;
    }
}
