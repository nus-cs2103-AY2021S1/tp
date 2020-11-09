package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_JAPAN_TRIP;
import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;
import static seedu.address.testutil.TypicalAttractions.LOUVRE;
import static seedu.address.testutil.TypicalAttractions.NIGHT_SAFARI;
import static seedu.address.testutil.TypicalAttractions.NOTRE_DAME;
import static seedu.address.testutil.TypicalAttractions.RIVER_SAFARI;
import static seedu.address.testutil.TypicalAttractions.SINGAPORE_ZOO;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ItineraryList;
import seedu.address.model.itinerary.Itinerary;

/**
 * A utility class containing a list of {@code Itinerary} objects to be used in tests.
 */
public class TypicalItineraries {

    public static final Itinerary SG_ZOOS_TOUR = new ItineraryBuilder().withName("Singapore Zoos")
            .withDescription("Rediscover our wildlife parks!")
            .withStartDate("06-07-2019")
            .withEndDate("07-07-2019")
            .withBudget("500")
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(SINGAPORE_ZOO)
                    .withStartTime("1000")
                    .withEndTime("1700")
                    .build(), INDEX_FIRST)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(NIGHT_SAFARI)
                    .withStartTime("1900")
                    .withEndTime("2300")
                    .build(), INDEX_FIRST)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(RIVER_SAFARI)
                    .withStartTime("1200")
                    .withEndTime("1800")
                    .build(), INDEX_SECOND)
            .build();

    public static final Itinerary PARIS_TRIP = new ItineraryBuilder().withName("Paris Trip")
            .withDescription("Visit the City of Light")
            .withStartDate("21-12-2020")
            .withEndDate("26-12-2020")
            .withBudget()
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(EIFFEL_TOWER)
                    .withStartTime("0900")
                    .withEndTime("1500")
                    .build(), INDEX_FIRST)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(LOUVRE)
                    .withStartTime("1100")
                    .withEndTime("1800")
                    .build(), INDEX_SECOND)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(NOTRE_DAME)
                    .withStartTime("1200")
                    .withEndTime("1500")
                    .build(), INDEX_THIRD)
            .build();

    // Matches {@code CommandTestUtil}
    public static final Itinerary JAPAN_TRIP = new ItineraryBuilder().withName(VALID_NAME_JAPAN_TRIP)
            .withDescription(VALID_DESCRIPTION_JAPAN_TRIP).withStartDate(VALID_START_DATE_JAPAN_TRIP)
            .withEndDate(VALID_END_DATE_JAPAN_TRIP).withBudget(VALID_BUDGET_JAPAN_TRIP).build();

    private TypicalItineraries() {
    } // prevents instantiation

    /**
     * Returns an {@code ItineraryList} with all the typical itineraries.
     */
    public static ItineraryList getTypicalItineraryList() {
        ItineraryList il = new ItineraryList();
        for (Itinerary itinerary : getTypicalItineraries()) {
            il.addItinerary(itinerary);
        }
        return il;
    }

    public static List<Itinerary> getTypicalItineraries() {
        return new ArrayList<>(Arrays.asList(SG_ZOOS_TOUR, PARIS_TRIP));
    }
}
