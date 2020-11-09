package seedu.address.testutil.typicals;

import static seedu.address.logic.command.CommandTestUtil.VALID_ACTIVITYDATETIME_SKI;
import static seedu.address.logic.command.CommandTestUtil.VALID_ACTIVITYDATETIME_ZOO;
import static seedu.address.logic.command.CommandTestUtil.VALID_COST_SKI;
import static seedu.address.logic.command.CommandTestUtil.VALID_COST_ZOO;
import static seedu.address.logic.command.CommandTestUtil.VALID_LEVELOFIMPORTANCE_SKI;
import static seedu.address.logic.command.CommandTestUtil.VALID_LEVELOFIMPORTANCE_ZOO;
import static seedu.address.logic.command.CommandTestUtil.VALID_LOCATION_SKI;
import static seedu.address.logic.command.CommandTestUtil.VALID_LOCATION_ZOO;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_SKI;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_ZOO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.activity.Activity;
import seedu.address.model.travelplan.ActivityList;
import seedu.address.model.wishlist.Wishlist;
import seedu.address.testutil.builders.ActivityBuilder;

public class TypicalActivities {

    public static final Activity ARCHERY = new ActivityBuilder().withName("Archery")
            .withCost("100").withLevelOfImportance("1")
            .withLocation("92 Archery Avenue")
            .withDateTime("2021-05-05 09:00").build();
    public static final Activity BUNGEEJUMPING = new ActivityBuilder().withName("Bungee Jumping")
            .withCost("225").withLevelOfImportance("5")
            .withLocation("1 BungeeJump Gateway")
            .withDateTime("2021-06-06 14:00").build();
    public static final Activity CAMPING = new ActivityBuilder().withName("Camping")
            .withCost("10").withLevelOfImportance("2")
            .withLocation("100 Secret Woods")
            .withDateTime("2021-05-31 20:00").build();
    public static final Activity DONUTDATE = new ActivityBuilder().withName("Donut Date")
            .withCost("2").withLevelOfImportance("1")
            .withLocation("Dunkin Donut Palace")
            .withDateTime("2021-06-02 11:00").build();

    public static final Activity ZOO = new ActivityBuilder().withName(VALID_NAME_ZOO)
            .withCost(VALID_COST_ZOO).withLevelOfImportance(VALID_LEVELOFIMPORTANCE_ZOO)
            .withLocation(VALID_LOCATION_ZOO)
            .withDateTime(VALID_ACTIVITYDATETIME_ZOO).build();
    public static final Activity THEMEPARK = new ActivityBuilder().withName("Universal Studios Singapore")
            .withCost("80").withLevelOfImportance("5")
            .withLocation("8 Sentosa Gateway")
            .withDateTime("2021-10-11 12:00").build();
    public static final Activity SKI = new ActivityBuilder().withName(VALID_NAME_SKI)
            .withCost(VALID_COST_SKI).withLevelOfImportance(VALID_LEVELOFIMPORTANCE_SKI)
            .withLocation(VALID_LOCATION_SKI)
            .withDateTime(VALID_ACTIVITYDATETIME_SKI).build();
    public static final Activity HIKING = new ActivityBuilder().withName("Treetop Walk")
            .withCost("0").withLevelOfImportance("2")
            .withLocation("601 Island Club Rd")
            .withDateTime("2021-06-10 12:00").build();

    private TypicalActivities() {} // prevents instantiation

    /**
     * Returns a {@code Wishlist} with all the typical activities.
     */
    public static Wishlist getTypicalWishlist() {
        Wishlist wl = new Wishlist();
        for (Activity activity : getTypicalActivities1()) {
            wl.addActivity(activity);
        }
        for (Activity activity : getTypicalActivities2()) {
            wl.addActivity(activity);
        }
        return wl;
    }

    /**
     * Returns an {@code ActivityList} with a set of typical activities (either set 1 or set 2).
     */
    public static ActivityList getTypicalActivityList(int set) {
        if (set != 1 && set != 2) {
            throw new IllegalArgumentException("getTypicalActivityList only takes in set 1 or 2 as argument.");
        }
        List<Activity> activities = set == 1 ? getTypicalActivities1() : getTypicalActivities2();
        ActivityList al = new ActivityList();
        for (Activity activity : activities) {
            al.addActivity(new ActivityBuilder(activity).build());
        }
        return al;
    }

    public static List<Activity> getTypicalActivities1() {
        return new ArrayList<>(Arrays.asList(ARCHERY, BUNGEEJUMPING, CAMPING, DONUTDATE));
    }

    public static List<Activity> getTypicalActivities2() {
        return new ArrayList<>(Arrays.asList(ZOO, THEMEPARK, SKI, HIKING));
    }
}
