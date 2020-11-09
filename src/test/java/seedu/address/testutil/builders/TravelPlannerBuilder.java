package seedu.address.testutil.builders;

import seedu.address.model.TravelPlanner;
import seedu.address.model.travelplan.TravelPlan;

/**
 * A utility class to help with building TravelPlanner objects.
 * Example usage: <br>
 *     {@code TravelPlanner tp = new TravelPlannerBuilder().withTravelPlan("Australia Trip").build();}
 */
public class TravelPlannerBuilder {

    private TravelPlanner travelPlanner;

    public TravelPlannerBuilder() {
        travelPlanner = new TravelPlanner();
    }

    public TravelPlannerBuilder(TravelPlanner travelPlanner) {
        this.travelPlanner = travelPlanner;
    }

    /**
     * Adds a new {@code TravelPlan} to the {@code TravelPlanner} that we are building.
     */
    public TravelPlannerBuilder withTravelPlan(TravelPlan travelPlan) {
        travelPlanner.addTravelPlan(travelPlan);
        return this;
    }

    public TravelPlanner build() {
        return travelPlanner;
    }
}
