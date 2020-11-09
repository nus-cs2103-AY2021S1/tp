package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryDate;

/**
 * A utility class to help with building Itinerary objects.
 */
public class ItineraryBuilder {
    public static final String DEFAULT_NAME = "Singapore Trip";
    public static final String DEFAULT_DESCRIPTION = "Explore the wonders of Singapore!";
    public static final String DEFAULT_START_DATE = "12-12-2020";
    public static final String DEFAULT_END_DATE = "15-12-2020";
    public static final String DEFAULT_BUDGET = "100";

    private Name name;
    private Description description;
    private ItineraryDate startDate;
    private ItineraryDate endDate;
    private Budget budget;
    private List<Day> days;

    /**
     * Creates a {@code ItineraryBuilder} with the default details.
     */
    public ItineraryBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        startDate = new ItineraryDate(DEFAULT_START_DATE);
        endDate = new ItineraryDate(DEFAULT_END_DATE);
        budget = new Budget(DEFAULT_BUDGET);
        days = new ArrayList<>();
        for (int i = 1; i <= ItineraryDate.daysBetween(startDate, endDate); i++) {
            days.add(new Day(i));
        }
    }

    /**
     * Initializes the ItineraryBuilder with the data of {@code itineraryToCopy}.
     */
    public ItineraryBuilder(Itinerary itineraryToCopy) {
        name = itineraryToCopy.getName();
        description = itineraryToCopy.getDescription();
        startDate = itineraryToCopy.getStartDate();
        endDate = itineraryToCopy.getEndDate();
        budget = itineraryToCopy.getBudget();
        days = new ArrayList<>(itineraryToCopy.getDays());
    }

    /**
     * Sets the {@code Name} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Itinerary} that we are building to empty string.
     */
    public ItineraryBuilder withDescription() {
        this.description = new Description();
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withBudget(String budget) {
        this.budget = new Budget(budget);
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Itinerary} that we are building to empty string.
     */
    public ItineraryBuilder withBudget() {
        this.budget = new Budget();
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withStartDate(String startDate) {
        this.startDate = new ItineraryDate(startDate);
        int numberOfDays = ItineraryDate.daysBetween(this.startDate, endDate);
        List<Day> newDays = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            if (i < days.size()) {
                newDays.add(days.get(i));
            } else {
                newDays.add(new Day(i + 1));
            }
        }
        days = newDays;
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withEndDate(String endDate) {
        this.endDate = new ItineraryDate(endDate);
        int numberOfDays = ItineraryDate.daysBetween(startDate, this.endDate);
        List<Day> newDays = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            if (i < days.size()) {
                newDays.add(days.get(i));
            } else {
                newDays.add(new Day(i + 1));
            }
        }
        days = newDays;
        return this;
    }

    /**
     * Adds an {@code itineraryAttraction} to the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withItineraryAttraction(ItineraryAttraction itineraryAttraction, Index day) {
        this.days.get(day.getZeroBased()).addItineraryAttraction(itineraryAttraction);
        return this;
    }

    /**
     * Sets the {@code days} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withDays(List<Day> days) {
        this.days = new ArrayList<>(days);
        return this;
    }

    /**
     * Initializes a new itinerary.
     *
     * @return a new Itinerary.
     */
    public Itinerary build() {
        return new Itinerary(name, description, startDate, endDate, budget, days);
    }
}
