package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_MBS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * A utility class containing a list of {@code ItineraryAttraction} objects to be used in tests.
 */
public class TypicalItineraryAttractions {

    public static final ItineraryAttraction JURONG_BIRD_PARK = new ItineraryAttractionBuilder()
            .withName("JurongBirdPark")
            .withAddress("2 Jurong Hill")
            .withEmail("birdpark@example.com")
            .withPhone("94351253")
            .withDescription("The park offers a haven for close to 3500 birds across 400 species.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-1800")
            .withPriceRange()
            .withRating()
            .withVisited()
            .withTags("animals")
            .withStartTime("1000")
            .withEndTime("1200")
            .build();
    public static final ItineraryAttraction NIGHT_SAFARI = new ItineraryAttractionBuilder()
            .withName("Night Safari")
            .withAddress("80 Mandai Lake Rd")
            .withEmail("nightsafari@example.com")
            .withPhone("98765432")
            .withDescription("The world's first nocturnal zoo.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1800-2300")
            .withPriceRange()
            .withRating()
            .withVisited()
            .withTags("animals", "night")
            .withStartTime("1050")
            .withEndTime("1242")
            .build();
    public static final ItineraryAttraction SINGAPORE_ZOO = new ItineraryAttractionBuilder()
            .withName("Singapore Zoo")
            .withPhone("95352563")
            .withEmail("singaporezoo@example.com")
            .withAddress("80 Mandai Lake Rd")
            .withDescription()
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-1800")
            .withPriceRange()
            .withRating()
            .withVisited()
            .withStartTime("1240")
            .withEndTime("1743")
            .build();
    public static final ItineraryAttraction RIVER_SAFARI = new ItineraryAttractionBuilder()
            .withName("River Safari")
            .withPhone("87652533")
            .withEmail("riversafari@example.com")
            .withAddress("80 Mandai Lake Rd")
            .withDescription("A river-themed zoo and aquarium in Singapore.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-1800")
            .withPriceRange()
            .withRating()
            .withVisited()
            .withTags("panda")
            .withStartTime("0422")
            .withEndTime("1522")
            .build();
    public static final ItineraryAttraction ORCHARD_ROAD = new ItineraryAttractionBuilder()
            .withName("Orchard Road")
            .withPhone("9482224")
            .withEmail("orchardroad@example.com")
            .withAddress("Orchard Road")
            .withDescription("Orchard Road is Singapore’s retail heart, with discount outlets, "
                    + "department stores and upscale boutiques, alongside luxury hotels.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours()
            .withPriceRange()
            .withRating()
            .withVisited()
            .withStartTime("1000")
            .withEndTime("1200")
            .build();
    public static final ItineraryAttraction BOTANIC_GARDENS = new ItineraryAttractionBuilder()
            .withName("Botanic Gardens")
            .withPhone("9482427")
            .withEmail("botanicgardens@example.com")
            .withAddress("1 Cluny Rd")
            .withDescription("The only tropical garden to be honoured as a UNESCO World Heritage Site.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours()
            .withPriceRange()
            .withRating()
            .withVisited()
            .withStartTime("1211")
            .withEndTime("2314")
            .build();
    public static final ItineraryAttraction JURONG_LAKE = new ItineraryAttractionBuilder()
            .withName("Jurong Lake")
            .withPhone("9482442")
            .withEmail("juronglake@example.com")
            .withAddress("Jurong")
            .withDescription("Sprawling park around a lake & swamp forest with a boardwalk, "
                    + "playgrounds, a dog run & water sports.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours()
            .withPriceRange()
            .withRating()
            .withVisited()
            .withStartTime("1232")
            .withEndTime("2132")
            .build();
    public static final ItineraryAttraction NOTRE_DAME = new ItineraryAttractionBuilder()
            .withName("Cathedrale Notre Dame de Paris")
            .withPhone()
            .withEmail()
            .withAddress("6 Parvis Notre-Dame - Pl. Jean-Paul II, 75004")
            .withDescription()
            .withLocation("Paris, France")
            .withOpeningHours()
            .withPriceRange()
            .withRating()
            .withVisited()
            .withStartTime("1234")
            .withEndTime("2345")
            .build();

    // Manually added
    public static final ItineraryAttraction ESPLANADE = new ItineraryAttractionBuilder()
            .withName("Esplanade")
            .withPhone("8482424").withEmail("esplanade@example.com")
            .withAddress("1 Esplanade Dr")
            .withDescription("Esplanade – Theatres on the Bay is a performing arts centre "
                    + "located in Downtown Core near the mouth of the Singapore River.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-2200")
            .withPriceRange("HIGH")
            .withRating("4.0")
            .withVisited("TRUE")
            .withStartTime("1000")
            .withEndTime("2300")
            .build();
    public static final ItineraryAttraction SUNTEC = new ItineraryAttractionBuilder()
            .withName("Suntec City")
            .withPhone("8482131").withEmail("suntec@example.com")
            .withAddress("3 Temasek Blvd")
            .withDescription("Suntec City is a major mixed-use development located in  "
                    + "Centre, a subzone of the Downtown Core in Singapore.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-2000")
            .withPriceRange("LOW")
            .withRating("4.5")
            .withVisited("TRUE")
            .withStartTime("1000")
            .withEndTime("2000")
            .build();
    public static final ItineraryAttraction LOUVRE = new ItineraryAttractionBuilder()
            .withName("Louvre Museum")
            .withPhone("330140205317")
            .withEmail("info@louvre.fr")
            .withAddress("Rue de Rivoli, 75001")
            .withDescription("The Louvre, or the Louvre Museum, is the world's largest art museum and a"
                    + " historic monument in Paris, France.")
            .withLocation("Paris, France")
            .withOpeningHours("0900-1800")
            .withPriceRange("MEDIUM")
            .withRating("4.7")
            .withVisited("FALSE")
            .withStartTime("0900")
            .withEndTime("1500")
            .build();

    // Manually added - ItineraryAttraction's details found in {@code CommandTestUtil}
    public static final ItineraryAttraction EIFFEL_TOWER = new ItineraryAttractionBuilder()
            .withName(VALID_NAME_EIFFEL)
            .withPhone(VALID_PHONE_EIFFEL)
            .withEmail(VALID_EMAIL_EIFFEL)
            .withAddress(VALID_ADDRESS_EIFFEL)
            .withDescription(VALID_DESCRIPTION_EIFFEL)
            .withLocation(VALID_LOCATION_EIFFEL)
            .withOpeningHours(VALID_OPENING_HOURS_EIFFEL)
            .withPriceRange(VALID_PRICE_RANGE_EIFFEL)
            .withRating(VALID_RATING_EIFFEL)
            .withVisited(VALID_VISITED_EIFFEL)
            .withTags(VALID_TAG_ACTIVITY)
            .withStartTime(VALID_START_TIME_EIFFEL)
            .withEndTime(VALID_END_TIME_EIFFEL)
            .build();
    public static final ItineraryAttraction MBS = new ItineraryAttractionBuilder()
            .withName(VALID_NAME_MBS)
            .withPhone(VALID_PHONE_MBS)
            .withEmail(VALID_EMAIL_MBS)
            .withAddress(VALID_ADDRESS_MBS)
            .withDescription(VALID_DESCRIPTION_MBS)
            .withLocation(VALID_LOCATION_MBS)
            .withOpeningHours(VALID_OPENING_HOURS_MBS)
            .withPriceRange(VALID_PRICE_RANGE_MBS)
            .withRating(VALID_RATING_MBS)
            .withVisited(VALID_VISITED_MBS)
            .withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_ACTIVITY)
            .withStartTime(VALID_START_TIME_MBS)
            .withEndTime(VALID_END_TIME_MBS)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItineraryAttractions() {
    } // prevents instantiation


    public static List<ItineraryAttraction> getTypicalItineraryAttractions() {
        return new ArrayList<>(Arrays.asList(JURONG_BIRD_PARK, NIGHT_SAFARI, SINGAPORE_ZOO, RIVER_SAFARI, ORCHARD_ROAD,
                BOTANIC_GARDENS, JURONG_LAKE));
    }
}
