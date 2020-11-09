package seedu.address.logic.parser.attraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPENING_HOURS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_RANGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VISITED_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_HOURS_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_HOURS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_RANGE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_RANGE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VISITED_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VISITED_DESC_MBS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;
import static seedu.address.testutil.TypicalAttractions.MBS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.attraction.AddAttractionCommand;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AttractionBuilder;

public class AddAttractionCommandParserTest {
    private AddAttractionCommandParser parser = new AddAttractionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Attraction expectedAttraction = new AttractionBuilder(MBS).withTags(VALID_TAG_ACTIVITY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MBS
                + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS
                + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS
                + RATING_DESC_MBS + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_EIFFEL + NAME_DESC_MBS + PHONE_DESC_MBS
                + EMAIL_DESC_MBS + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_EIFFEL + PHONE_DESC_MBS
                + EMAIL_DESC_MBS + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_EIFFEL + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_EIFFEL
                + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + DESCRIPTION_DESC_EIFFEL + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple locations - last location accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + DESCRIPTION_DESC_MBS + LOCATION_DESC_EIFFEL + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple opening hours - last opening hours accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_EIFFEL
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple price ranges - last price range accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_EIFFEL + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple ratings - last rating accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_EIFFEL + RATING_DESC_MBS
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple visited - last visited accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS + VISITED_DESC_EIFFEL
                + VISITED_DESC_MBS + TAG_DESC_ACTIVITY, new AddAttractionCommand(expectedAttraction));

        // multiple tags - all accepted
        Attraction expectedAttractionMultipleTags = new AttractionBuilder(MBS)
                .withTags(VALID_TAG_ACTIVITY, VALID_TAG_SIGHTSEEING).build();
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS
                        + VISITED_DESC_MBS + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY,
                new AddAttractionCommand(expectedAttractionMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Attraction expectedAttraction = new AttractionBuilder(EIFFEL_TOWER).withTags().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL
                        + EMAIL_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL,
                new AddAttractionCommand(expectedAttraction));

        // No phone number given
        Attraction expectedAttractionNoPhone = new AttractionBuilder(EIFFEL_TOWER).withPhone().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + EMAIL_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL + TAG_DESC_ACTIVITY,
                new AddAttractionCommand(expectedAttractionNoPhone));

        // No email given
        Attraction expectedAttractionNoEmail = new AttractionBuilder(EIFFEL_TOWER).withEmail().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL
                        + OPENING_HOURS_DESC_EIFFEL + PRICE_RANGE_DESC_EIFFEL
                        + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL + TAG_DESC_ACTIVITY,
                new AddAttractionCommand(expectedAttractionNoEmail));

        // No address given
        Attraction expectedAttractionNoAddress = new AttractionBuilder(EIFFEL_TOWER).withAddress().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + VISITED_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY,
                new AddAttractionCommand(expectedAttractionNoAddress));

        // No description given
        Attraction expectedAttractionNoDescription = new AttractionBuilder(EIFFEL_TOWER).withDescription().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
                        + VISITED_DESC_EIFFEL
                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY,
                new AddAttractionCommand(expectedAttractionNoDescription));

        // No opening hours given
        Attraction expectedAttractionNoOpeningHours = new AttractionBuilder(EIFFEL_TOWER).withOpeningHours().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
                        + VISITED_DESC_EIFFEL
                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY,
                new AddAttractionCommand(expectedAttractionNoOpeningHours));

        // No price range given
        Attraction expectedAttractionNoPriceRange = new AttractionBuilder(EIFFEL_TOWER).withPriceRange().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
                        + VISITED_DESC_EIFFEL
                        + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY + ADDRESS_DESC_EIFFEL,
                new AddAttractionCommand(expectedAttractionNoPriceRange));

        // No rating given
        Attraction expectedAttractionNoRating = new AttractionBuilder(EIFFEL_TOWER).withRating().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
                        + VISITED_DESC_EIFFEL
                        + PRICE_RANGE_DESC_EIFFEL + TAG_DESC_ACTIVITY + ADDRESS_DESC_EIFFEL,

                new AddAttractionCommand(expectedAttractionNoRating));

        // No visited given
        Attraction expectedAttractionNoVisited = new AttractionBuilder(EIFFEL_TOWER).withVisited().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
                        + RATING_DESC_EIFFEL
                        + PRICE_RANGE_DESC_EIFFEL + TAG_DESC_ACTIVITY + ADDRESS_DESC_EIFFEL,
                new AddAttractionCommand(expectedAttractionNoVisited));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttractionCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS
                + RATING_DESC_MBS + VISITED_DESC_MBS, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + VALID_LOCATION_MBS + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS
                + RATING_DESC_MBS + VISITED_DESC_MBS, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MBS + VALID_PHONE_MBS + VALID_EMAIL_MBS + VALID_ADDRESS_MBS
                + VALID_LOCATION_MBS + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS
                + RATING_DESC_MBS + VISITED_DESC_MBS, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS
                        + PHONE_DESC_MBS
                        + PRICE_RANGE_DESC_MBS
                        + RATING_DESC_MBS
                        + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS
                        + INVALID_PHONE_DESC
                        + PRICE_RANGE_DESC_MBS
                        + RATING_DESC_MBS
                        + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + INVALID_EMAIL_DESC
                        + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS
                        + PHONE_DESC_MBS
                        + PRICE_RANGE_DESC_MBS
                        + RATING_DESC_MBS
                        + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                Email.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + INVALID_LOCATION_DESC
                        + OPENING_HOURS_DESC_MBS
                        + PHONE_DESC_MBS
                        + PRICE_RANGE_DESC_MBS
                        + RATING_DESC_MBS
                        + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                Location.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS
                        + PHONE_DESC_MBS
                        + PRICE_RANGE_DESC_MBS
                        + RATING_DESC_MBS
                        + VISITED_DESC_MBS
                        + INVALID_TAG_DESC
                        + VALID_TAG_ACTIVITY,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid Opening hours
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + LOCATION_DESC_MBS
                        + INVALID_OPENING_HOURS_DESC
                        + PHONE_DESC_MBS
                        + PRICE_RANGE_DESC_MBS
                        + RATING_DESC_MBS
                        + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                OpeningHours.MESSAGE_CONSTRAINTS);

        // invalid Price Range
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS
                        + PHONE_DESC_MBS
                        + INVALID_PRICE_RANGE_DESC
                        + RATING_DESC_MBS
                        + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                PriceRange.MESSAGE_CONSTRAINTS);

        // invalid Rating
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS
                        + PHONE_DESC_MBS
                        + PRICE_RANGE_DESC_MBS
                        + INVALID_RATING_DESC
                        + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                Rating.MESSAGE_CONSTRAINTS);

        // invalid Visited
        assertParseFailure(parser, NAME_DESC_MBS
                        + ADDRESS_DESC_MBS
                        + DESCRIPTION_DESC_MBS
                        + EMAIL_DESC_MBS
                        + LOCATION_DESC_MBS
                        + OPENING_HOURS_DESC_MBS
                        + PHONE_DESC_MBS
                        + PRICE_RANGE_DESC_MBS
                        + RATING_DESC_MBS
                        + INVALID_VISITED_DESC
                        + TAG_DESC_SIGHTSEEING
                        + TAG_DESC_ACTIVITY,
                Visited.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_MBS + INVALID_LOCATION_DESC + ADDRESS_DESC_MBS
                + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS
                + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS + VISITED_DESC_MBS, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                        + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS
                        + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS + VISITED_DESC_MBS
                        + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttractionCommand.MESSAGE_USAGE));
    }
}
