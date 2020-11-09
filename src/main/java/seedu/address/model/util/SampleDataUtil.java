package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AttractionList;
import seedu.address.model.ItineraryList;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryDate;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AttractionList} with sample data.
 */
public class SampleDataUtil {
    public static Attraction[] getSampleAttractions() {
        return new Attraction[]{
            new Attraction(new Name("Jurong Bird Park"), new Phone("94351253"), new Email("birdpark@example.com"),
                    new Address("2 Jurong Hill"),
                    new Description("The park offers a haven for close to 3500 birds across 400 species."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("MEDIUM"),
                    new Rating("4.5"), new Visited("FALSE"), getTagSet("animals")),
            new Attraction(new Name("Night Safari"), new Phone("98765432"), new Email("nightsafari@example.com"),
                    new Address("80 Mandai Lake Rd"),
                    new Description("The world's first nocturnal zoo."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1900-2300"), new PriceRange("MEDIUM"),
                    new Rating("4.2"), new Visited("FALSE"), getTagSet("animals", "night")),
            new Attraction(new Name("River Safari"), new Phone("93210283"), new Email("riversafari@example.com"),
                    new Address("80 Mandai Lake Rd"),
                    new Description("A river-themed zoo and aquarium in Singapore."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("MEDIUM"),
                    new Rating("4.4"), new Visited("FALSE"), getTagSet("animals", "panda")),
            new Attraction(new Name("Universal Studios Singapore"), new Phone("65482651"), new Email("uss@example.com"),
                    new Address("8 Sentosa Gateway, 098269"),
                    new Description("A world-renowned theme park with thrilling rides."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1400-2100"), new PriceRange("HIGH"),
                    new Rating("4.6"), new Visited("FALSE"), getTagSet("activities")),
            new Attraction(new Name("Snow City"), new Phone("65602306"), new Email("snowcity@example.com"),
                    new Address("21 Jurong Town Hall Rd, 609433"),
                    new Description("A winter wonderland amidst the tropical Singapore climate."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1700"), new PriceRange("LOW"),
                    new Rating("3.6"), new Visited("FALSE"), getTagSet("activities")),
            new Attraction(new Name("Trick Eye Museum"), new Phone("67952371"), new Email("trickeye@example.com"),
                    new Address("26 Sentosa Gateway, 098138"),
                    new Description("Unique museum with a large array of 3-dimensional "
                            + "artwork for interactive optical illusions."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("LOW"),
                    new Rating("4.2"), new Visited("FALSE"), getTagSet("activities")),
            new Attraction(new Name("Singapore Zoo"), new Phone("62693411"), new Email("riversafari@example.com"),
                    new Address("80 Mandai Lake Rd"),
                    new Description("Singapore Zoo's world-famous \"Open Concept” offers the opportunity to "
                            + "experience and be inspired by the wonders of nature."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("0830-1800"), new PriceRange("MEDIUM"),
                    new Rating("4.6"), new Visited("FALSE"), getTagSet("animals", "tiger")),
            new Attraction(new Name("Madame Tussauds"), new Phone("67154000"), new Email("madametussauds@example.com"),
                    new Address("40 Imbiah Rd, Sentosa, 099700"),
                    new Description("Skip the line and get a blast from the past as you meet athletes, movie stars "
                            + "and singers at Singapore's very own wax museum!"),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1100-1900"), new PriceRange("MEDIUM"),
                    new Rating("4.4"), new Visited("FALSE"), getTagSet("museum")),
            new Attraction(new Name("Marina Bay Sands SkyPark Observation Deck"), new Phone("66888826"),
                    new Email("skypark@example.com"),
                    new Address("10 Bayfront Ave, 018956"),
                    new Description("Located 57 storeys up from the heart of the Marina Bay area, the Sands SkyPark "
                            + "Observation Deck boasts scenic views of the panoramic vistas of Marina Bay."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1100-2100"), new PriceRange("LOW"),
                    new Rating("4.6"), new Visited("FALSE"), getTagSet("nightlife")),
            new Attraction(new Name("Esplanade"), new Phone("68288377"),
                    new Email("esplanade@example.com"),
                    new Address("1 Esplanade Drive, Singapore 038981"),
                    new Description("Named after the nearby Esplanade Park, it consists of a concert hall which seats "
                            + "about 1,600 and a theatre with a capacity of about 2,000 for the performing arts."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1100-1900"), new PriceRange("LOW"),
                    new Rating("4.6"), new Visited("FALSE"), getTagSet("culture"))
        };
    }

    public static Itinerary getSampleItinerary() {
        ArrayList<ItineraryAttraction> sampleIal = new ArrayList<>();
        sampleIal.add(new ItineraryAttraction(getSampleAttractions()[0], new ItineraryTime("1200"),
                    new ItineraryTime("1600")));
        sampleIal.add(new ItineraryAttraction(getSampleAttractions()[1], new ItineraryTime("1900"),
                    new ItineraryTime("2200")));
        Day sampleDay = new Day(1, sampleIal);
        ArrayList<Day> sampleDayl = new ArrayList<>();
        sampleDayl.add(sampleDay);
        return new Itinerary(new Name("Singapore Tour"),
                new Description("Spend your Rediscover Singapore vouchers here!"), new ItineraryDate("01-09-2020"),
                new ItineraryDate("05-09-2020"), new Budget("100"), sampleDayl);
    }

    public static ReadOnlyAttractionList getSampleAttractionsList() {
        AttractionList sampleAl = new AttractionList();
        for (Attraction sampleAttraction : getSampleAttractions()) {
            sampleAl.addAttraction(sampleAttraction);
        }
        return sampleAl;
    }

    public static ReadOnlyItineraryList getSampleItineraryList() {
        ItineraryList sampleIl = new ItineraryList();
        sampleIl.addItinerary(getSampleItinerary());
        return sampleIl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
