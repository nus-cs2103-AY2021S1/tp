package seedu.address.testutil.builders;

import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;

public class ActivityBuilder {

    public static final String DEFAULT_NAME = "Archery";
    public static final String DEFAULT_LOCATION = "33 Spring Valley";
    public static final String DEFAULT_COST = "100";
    public static final String DEFAULT_LEVELOFIMPORTANCE = "2";
    public static final String DEFAULT_ACTIVITYDATETIME = "2021-10-10 11:00";


    private Name name;
    private Location location;
    private Cost cost;
    private Importance levelOfImportance;
    private WanderlustDateTime activityDateTime;

    /**
     * Creates a {@code ActivityBuilder} with the default details.
     */
    public ActivityBuilder() {
        name = new Name(DEFAULT_NAME);
        location = new Location(DEFAULT_LOCATION);
        cost = new Cost(DEFAULT_COST);
        levelOfImportance = new Importance(DEFAULT_LEVELOFIMPORTANCE);
        activityDateTime = new WanderlustDateTime(DEFAULT_ACTIVITYDATETIME);
    }

    /**
     * Initializes the ActivityBuilder with the data of {@code ActivityToCopy}.
     */
    public ActivityBuilder(Activity activityToCopy) {
        name = activityToCopy.getName();
        location = activityToCopy.getLocation();
        cost = activityToCopy.getCost();
        levelOfImportance = activityToCopy.getLevelOfImportance();
        activityDateTime = activityToCopy.getActivityDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withLocation(String address) {
        this.location = new Location(address);
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withCost(String cost) {
        this.cost = new Cost(cost);
        return this;
    }

    /**
     * Sets the {@code LevelOfImportance} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withLevelOfImportance(String importance) {
        this.levelOfImportance = new Importance(importance);
        return this;
    }

    /**
     * Sets the {@code activityDateTime} of the {@code Activity} that we are building.
     */
    public ActivityBuilder withDateTime(String activityDateTime) {
        this.activityDateTime = new WanderlustDateTime(activityDateTime);
        return this;
    }

    public Activity build() {
        return new Activity(name, location, cost, levelOfImportance, activityDateTime);
    }
}
