package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Address;
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
import seedu.address.model.itinerary.ItineraryDate;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "Bot@nic Gardens";
    private static final String INVALID_PHONE = "+6471 7138";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_OPENING_HOURS = "1-2";
    private static final String INVALID_PRICE_RANGE = "MEDUM";
    private static final String INVALID_RATING = "9";
    private static final String INVALID_VISITED = "Fase";
    private static final String INVALID_TAG = "#nature";
    private static final String INVALID_ITINERARY_DATE = "3 february";
    private static final String INVALID_BUDGET = "345.123";
    private static final String INVALID_ITINERARY_TIME = "10";

    private static final String VALID_NAME = "Botanic Gardens";
    private static final String VALID_PHONE = "64717138";
    private static final String VALID_ADDRESS = "1 Cluny Rd, 259569";
    private static final String VALID_EMAIL = "botanicgardens@example.com";
    private static final String VALID_DESCRIPTION = "First UNESCO Heritage Site in Singapore";
    private static final String VALID_LOCATION = "Singapore, Singapore";
    private static final String VALID_OPENING_HOURS = "1000-1800";
    private static final String VALID_PRICE_RANGE = "LOW";
    private static final String VALID_RATING = "4.9";
    private static final String VALID_VISITED = "FALSE";
    private static final String VALID_TAG_1 = "nature";
    private static final String VALID_TAG_2 = "sightseeing";
    private static final String VALID_ITINERARY_DATE = "03-02-2016";
    private static final String VALID_BUDGET = "100.50";
    private static final String VALID_ITINERARY_TIME = "1000";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseLocation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocation((String) null));
    }

    @Test
    public void parseLocation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocation(INVALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithoutWhitespace_returnsLocation() throws Exception {
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(VALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithWhitespace_returnsTrimmedLocation() throws Exception {
        String locationWithWhitespace = WHITESPACE + VALID_LOCATION + WHITESPACE;
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(locationWithWhitespace));
    }

    @Test
    public void parseOpeningHours_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseOpeningHours((String) null));
    }

    @Test
    public void parseOpeningHours_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseOpeningHours(INVALID_OPENING_HOURS));
    }

    @Test
    public void parseOpeningHours_validValueWithoutWhitespace_returnsOpeningHours() throws Exception {
        OpeningHours expectedOpeningHours = new OpeningHours(VALID_OPENING_HOURS);
        assertEquals(expectedOpeningHours, ParserUtil.parseOpeningHours(VALID_OPENING_HOURS));
    }

    @Test
    public void parseOpeningHours_validValueWithWhitespace_returnsTrimmedOpeningHours() throws Exception {
        String openingHoursWithWhitespace = WHITESPACE + VALID_OPENING_HOURS + WHITESPACE;
        OpeningHours expectedOpeningHours = new OpeningHours(VALID_OPENING_HOURS);
        assertEquals(expectedOpeningHours, ParserUtil.parseOpeningHours(openingHoursWithWhitespace));
    }

    @Test
    public void parsePriceRange_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriceRange((String) null));
    }

    @Test
    public void parsePriceRange_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriceRange(INVALID_PRICE_RANGE));
    }

    @Test
    public void parsePriceRange_validValueWithoutWhitespace_returnsPriceRange() throws Exception {
        PriceRange expectedPriceRange = new PriceRange(VALID_PRICE_RANGE);
        assertEquals(expectedPriceRange, ParserUtil.parsePriceRange(VALID_PRICE_RANGE));
    }

    @Test
    public void parsePriceRange_validValueWithWhitespace_returnsTrimmedPriceRange() throws Exception {
        String priceRangeWithWhitespace = WHITESPACE + VALID_PRICE_RANGE + WHITESPACE;
        PriceRange expectedPriceRange = new PriceRange(VALID_PRICE_RANGE);
        assertEquals(expectedPriceRange, ParserUtil.parsePriceRange(priceRangeWithWhitespace));
    }

    @Test
    public void parseRating_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRating((String) null));
    }

    @Test
    public void parseRating_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRating(INVALID_RATING));
    }

    @Test
    public void parseRating_validValueWithoutWhitespace_returnsRating() throws Exception {
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(VALID_RATING));
    }

    @Test
    public void parseRating_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String ratingWithWhitespace = WHITESPACE + VALID_RATING + WHITESPACE;
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(ratingWithWhitespace));
    }

    @Test
    public void parseVisited_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVisited((String) null));
    }

    @Test
    public void parseVisited_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVisited(INVALID_VISITED));
    }

    @Test
    public void parseVisited_validValueWithoutWhitespace_returnsVisited() throws Exception {
        Visited expectedVisited = new Visited(VALID_VISITED);
        assertEquals(expectedVisited, ParserUtil.parseVisited(VALID_VISITED));
    }

    @Test
    public void parseVisited_validValueWithWhitespace_returnsTrimmedVisited() throws Exception {
        String visitedWithWhitespace = WHITESPACE + VALID_VISITED + WHITESPACE;
        Visited expectedVisited = new Visited(VALID_VISITED);
        assertEquals(expectedVisited, ParserUtil.parseVisited(visitedWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseItineraryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseItineraryDate((String) null));
    }

    @Test
    public void parseItineraryDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseItineraryDate(INVALID_ITINERARY_DATE));
    }

    @Test
    public void parseItineraryDate_validValueWithoutWhitespace_returnsItineraryDate() throws Exception {
        ItineraryDate expectedItineraryDate = new ItineraryDate(VALID_ITINERARY_DATE);
        assertEquals(expectedItineraryDate, ParserUtil.parseItineraryDate(VALID_ITINERARY_DATE));
    }

    @Test
    public void parseItineraryDate_validValueWithWhitespace_returnsTrimmedItineraryDate() throws Exception {
        String itineraryDateWithWhitespace = WHITESPACE + VALID_ITINERARY_DATE + WHITESPACE;
        ItineraryDate expectedItineraryDate = new ItineraryDate(VALID_ITINERARY_DATE);
        assertEquals(expectedItineraryDate, ParserUtil.parseItineraryDate(itineraryDateWithWhitespace));
    }

    @Test
    public void parseBudget_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBudget((String) null));
    }

    @Test
    public void parseBudget_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBudget(INVALID_BUDGET));
    }

    @Test
    public void parseBudget_validValueWithoutWhitespace_returnsBudget() throws Exception {
        Budget expectedBudget = new Budget(VALID_BUDGET);
        assertEquals(expectedBudget, ParserUtil.parseBudget(VALID_BUDGET));
    }

    @Test
    public void parseBudget_validValueWithWhitespace_returnsTrimmedBudget() throws Exception {
        String budgetWithWhitespace = WHITESPACE + VALID_BUDGET + WHITESPACE;
        Budget expectedBudget = new Budget(VALID_BUDGET);
        assertEquals(expectedBudget, ParserUtil.parseBudget(budgetWithWhitespace));
    }

    @Test
    public void parseDayIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDayIndex("10 a"));
    }

    @Test
    public void parseDayIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, Day.MESSAGE_CONSTRAINTS, ()
            -> ParserUtil.parseDayIndex(Long.toString(Integer.MAX_VALUE + 1)));

        assertThrows(ParseException.class, Day.MESSAGE_CONSTRAINTS, ()
            -> ParserUtil.parseDayIndex(Long.toString(-2)));

        assertThrows(ParseException.class, Day.MESSAGE_CONSTRAINTS, ()
            -> ParserUtil.parseDayIndex(Long.toString(0)));
    }

    @Test
    public void parseDayIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseDayIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseDayIndex("  1  "));
    }

    @Test
    public void parseItineraryTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseItineraryTime((String) null));
    }

    @Test
    public void parseItineraryTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseItineraryTime(INVALID_ITINERARY_TIME));
    }

    @Test
    public void parseItineraryTime_validValueWithoutWhitespace_returnsItineraryTime() throws Exception {
        ItineraryTime expectedItineraryTime = new ItineraryTime(VALID_ITINERARY_TIME);
        assertEquals(expectedItineraryTime, ParserUtil.parseItineraryTime(VALID_ITINERARY_TIME));
    }

    @Test
    public void parseItineraryTime_validValueWithWhitespace_returnsTrimmedItineraryTime() throws Exception {
        String itineraryTimeWithWhitespace = WHITESPACE + VALID_ITINERARY_TIME + WHITESPACE;
        ItineraryTime expectedItineraryTime = new ItineraryTime(VALID_ITINERARY_TIME);
        assertEquals(expectedItineraryTime, ParserUtil.parseItineraryTime(itineraryTimeWithWhitespace));
    }
}
