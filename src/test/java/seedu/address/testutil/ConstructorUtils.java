package seedu.address.testutil;

import static seedu.address.testutil.typicals.TypicalAccommodations.getTypicalAccommodationList;
import static seedu.address.testutil.typicals.TypicalActivities.getTypicalActivityList;
import static seedu.address.testutil.typicals.TypicalFriends.getTypicalFriendList;

import seedu.address.model.travelplan.AccommodationList;
import seedu.address.model.travelplan.ActivityList;
import seedu.address.model.travelplan.FriendList;

public class ConstructorUtils {
    public static final String VALID_NAME_A = "Alice";
    public static final String VALID_NAME_B = "Bob";
    public static final String VALID_START_DATE_A = "2020-01-01";
    public static final String VALID_START_DATE_B = "2020-03-03";
    public static final String VALID_END_DATE_A = "2020-12-12";
    public static final String VALID_END_DATE_B = "2020-11-11";
    public static final String VALID_PHONE_A = "94351253";
    public static final String VALID_PHONE_B = "98765432";
    public static final String VALID_PASSPORT_A = "A1234567";
    public static final String VALID_PASSPORT_B = "B1234567";
    public static final String VALID_COST_A = "100";
    public static final String VALID_COST_B = "255";
    public static final AccommodationList VALID_ACCOMMODATION_LIST_A = getTypicalAccommodationList(1);
    public static final AccommodationList VALID_ACCOMMODATION_LIST_B = getTypicalAccommodationList(2);
    public static final ActivityList VALID_ACTIVITY_LIST_A = getTypicalActivityList(1);
    public static final ActivityList VALID_ACTIVITY_LIST_B = getTypicalActivityList(2);
    public static final FriendList VALID_FRIEND_LIST_A = getTypicalFriendList(1);
    public static final FriendList VALID_FRIEND_LIST_B = getTypicalFriendList(2);
}
