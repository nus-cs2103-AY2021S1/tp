package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTravelPlan.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalAccommodations.ALICEHOTEL;
import static seedu.address.testutil.typicals.TypicalActivities.ARCHERY;
import static seedu.address.testutil.typicals.TypicalFriends.ALICE;
import static seedu.address.testutil.typicals.TypicalTravelPlans.AUSTRALIA_TRIP;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;

public class JsonAdaptedTravelPlanTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_START_DATE = "20200202";
    private static final String INVALID_END_DATE = "20200203";
    private static final String INVALID_ACTIVITY_NAME = "ASDF#$%";
    private static final String INVALID_ACCOMMODATION_NAME = "ASDF^%&";
    private static final String INVALID_FRIEND_NAME = "!@#ADSF";

    private static final String VALID_NAME = AUSTRALIA_TRIP.getName().getValue();
    private static final String VALID_START_DATE = AUSTRALIA_TRIP.getStartDate().getDate();
    private static final String VALID_END_DATE = AUSTRALIA_TRIP.getEndDate().getDate();
    private static final List<JsonAdaptedActivity> VALID_ACTIVITIES = AUSTRALIA_TRIP.getObservableActivityList()
            .stream()
            .map(JsonAdaptedActivity::new)
            .collect(Collectors.toList());
    private static final String VALID_ACTIVITY_LOCATION = ARCHERY.getLocation().getValue();
    private static final String VALID_ACTIVITY_COST = ARCHERY.getCost().getValue();
    private static final String VALID_ACTIVITY_IMPORTANCE = ARCHERY.getLevelOfImportance().getValue();
    private static final String VALID_ACTIVITY_DATE_TIME = ARCHERY.getActivityDateTime().getDateTime();
    private static final List<JsonAdaptedAccommodation> VALID_ACCOMMODATIONS = AUSTRALIA_TRIP
            .getObservableAccommodationList()
            .stream()
            .map(JsonAdaptedAccommodation::new)
            .collect(Collectors.toList());
    private static final String VALID_ACCOMMODATION_START_DATE = ALICEHOTEL.getStartDate().getDate();
    private static final String VALID_ACCOMMODATION_END_DATE = ALICEHOTEL.getEndDate().getDate();
    private static final String VALID_ACCOMMODATION_COST = ALICEHOTEL.getCost().getValue();
    private static final String VALID_ACCOMMODATION_LOCATION = ALICEHOTEL.getLocation().getValue();
    private static final List<JsonAdaptedFriend> VALID_FRIENDS = AUSTRALIA_TRIP.getObservableFriendList().stream()
            .map(JsonAdaptedFriend::new)
            .collect(Collectors.toList());
    private static final String VALID_FRIEND_PASSPORT = ALICE.getPassport().getValue();
    private static final String VALID_FRIEND_PHONE = ALICE.getMobile().getValue();

    @Test
    public void toModelType_validTravelPlanDetails_returnsTravelPlan() throws Exception {
        JsonAdaptedTravelPlan travelPlan = new JsonAdaptedTravelPlan(AUSTRALIA_TRIP);
        assertEquals(AUSTRALIA_TRIP, travelPlan.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTravelPlan travelPlan =
                new JsonAdaptedTravelPlan(INVALID_NAME, VALID_START_DATE, VALID_END_DATE, VALID_ACTIVITIES,
                        VALID_ACCOMMODATIONS, VALID_FRIENDS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, travelPlan::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTravelPlan travelPlan = new JsonAdaptedTravelPlan(null, VALID_START_DATE, VALID_END_DATE,
                VALID_ACTIVITIES, VALID_ACCOMMODATIONS, VALID_FRIENDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, travelPlan::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedTravelPlan travelPlan =
                new JsonAdaptedTravelPlan(VALID_NAME, INVALID_START_DATE, VALID_END_DATE, VALID_ACTIVITIES,
                        VALID_ACCOMMODATIONS, VALID_FRIENDS);
        String expectedMessage = WanderlustDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, travelPlan::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedTravelPlan travelPlan = new JsonAdaptedTravelPlan(VALID_NAME, null, VALID_END_DATE,
                VALID_ACTIVITIES, VALID_ACCOMMODATIONS, VALID_FRIENDS);
        String expectedMessage = String.format(JsonAdaptedTravelPlan.MISSING_FIELD_MESSAGE_FORMAT,
                WanderlustDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, travelPlan::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedTravelPlan travelPlan =
                new JsonAdaptedTravelPlan(VALID_NAME, VALID_START_DATE, INVALID_END_DATE, VALID_ACTIVITIES,
                        VALID_ACCOMMODATIONS, VALID_FRIENDS);
        String expectedMessage = WanderlustDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, travelPlan::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedTravelPlan travelPlan = new JsonAdaptedTravelPlan(VALID_NAME, VALID_START_DATE, null,
                VALID_ACTIVITIES, VALID_ACCOMMODATIONS, VALID_FRIENDS);
        String expectedMessage = String.format(JsonAdaptedTravelPlan.MISSING_FIELD_MESSAGE_FORMAT,
                WanderlustDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, travelPlan::toModelType);
    }

    @Test
    public void toModelType_invalidActivities_throwsIllegalValueException() {
        List<JsonAdaptedActivity> invalidActivities = new ArrayList<>(VALID_ACTIVITIES);
        invalidActivities.add(new JsonAdaptedActivity(INVALID_ACTIVITY_NAME, VALID_ACTIVITY_LOCATION,
                VALID_ACTIVITY_COST, VALID_ACTIVITY_IMPORTANCE, VALID_ACTIVITY_DATE_TIME));
        JsonAdaptedTravelPlan travelPlan =
                new JsonAdaptedTravelPlan(VALID_NAME, VALID_START_DATE, VALID_END_DATE, invalidActivities,
                        VALID_ACCOMMODATIONS, VALID_FRIENDS);
        assertThrows(IllegalValueException.class, travelPlan::toModelType);
    }

    @Test
    public void toModelType_invalidAccommodations_throwsIllegalValueException() {
        List<JsonAdaptedAccommodation> invalidAccommodations = new ArrayList<>(VALID_ACCOMMODATIONS);
        invalidAccommodations.add(new JsonAdaptedAccommodation(INVALID_ACCOMMODATION_NAME,
                VALID_ACCOMMODATION_START_DATE, VALID_ACCOMMODATION_END_DATE, VALID_ACCOMMODATION_COST,
                VALID_ACCOMMODATION_LOCATION));
        JsonAdaptedTravelPlan travelPlan =
                new JsonAdaptedTravelPlan(VALID_NAME, VALID_START_DATE, VALID_END_DATE, VALID_ACTIVITIES,
                        invalidAccommodations, VALID_FRIENDS);
        assertThrows(IllegalValueException.class, travelPlan::toModelType);
    }

    @Test
    public void toModelType_invalidFriends_throwsIllegalValueException() {
        List<JsonAdaptedFriend> invalidFriends = new ArrayList<>(VALID_FRIENDS);
        invalidFriends.add(new JsonAdaptedFriend(INVALID_FRIEND_NAME, VALID_FRIEND_PASSPORT, VALID_FRIEND_PHONE));
        JsonAdaptedTravelPlan travelPlan =
                new JsonAdaptedTravelPlan(VALID_NAME, VALID_START_DATE, VALID_END_DATE, VALID_ACTIVITIES,
                        VALID_ACCOMMODATIONS, invalidFriends);
        assertThrows(IllegalValueException.class, travelPlan::toModelType);
    }
}
