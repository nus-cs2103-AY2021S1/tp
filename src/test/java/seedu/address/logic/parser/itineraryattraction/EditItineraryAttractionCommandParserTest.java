package seedu.address.logic.parser.itineraryattraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.DAY_VISITING_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.DAY_VISITING_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_VISITING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPENING_HOURS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_RANGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VISITED_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_HOURS_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_HOURS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_RANGE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_RANGE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_VISITING_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_VISITING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EIFFEL;
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
import static seedu.address.logic.commands.CommandTestUtil.VISITED_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VISITED_DESC_MBS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditItineraryAttractionDescriptorBuilder;


public class EditItineraryAttractionCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItineraryAttractionCommand.MESSAGE_USAGE);

    private EditItineraryAttractionCommandParser parser = new EditItineraryAttractionCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        Index dayVisiting = Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_EIFFEL));
        String userInput = targetIndex.getOneBased()
                + NAME_DESC_EIFFEL
                + PHONE_DESC_EIFFEL
                + EMAIL_DESC_EIFFEL
                + ADDRESS_DESC_EIFFEL
                + DESCRIPTION_DESC_EIFFEL
                + LOCATION_DESC_EIFFEL
                + OPENING_HOURS_DESC_EIFFEL
                + PRICE_RANGE_DESC_EIFFEL
                + RATING_DESC_EIFFEL
                + VISITED_DESC_EIFFEL
                + TAG_DESC_SIGHTSEEING
                + TAG_DESC_ACTIVITY
                + DAY_VISITING_DESC_EIFFEL
                + START_TIME_DESC_EIFFEL
                + END_TIME_DESC_EIFFEL;

        EditItineraryAttractionDescriptor descriptor =
                new EditItineraryAttractionDescriptorBuilder()
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
                        .withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_ACTIVITY)
                        .withStartTime(VALID_START_TIME_EIFFEL)
                        .withEndTime(VALID_END_TIME_EIFFEL).build();

        EditItineraryAttractionCommand expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        Index dayVisiting = Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_EIFFEL));
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MBS + EMAIL_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;

        EditItineraryAttractionDescriptor descriptor = new EditItineraryAttractionDescriptorBuilder()
                .withPhone(VALID_PHONE_MBS).withEmail(VALID_EMAIL_EIFFEL).build();
        EditItineraryAttractionCommand expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        final Index dayVisiting = Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_EIFFEL));

        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        EditItineraryAttractionDescriptor descriptor =
                new EditItineraryAttractionDescriptorBuilder().withName(VALID_NAME_EIFFEL).build();
        EditItineraryAttractionCommand expectedCommand = new EditItineraryAttractionCommand(
                targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withPhone(VALID_PHONE_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withEmail(VALID_EMAIL_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withAddress(VALID_ADDRESS_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withDescription(VALID_DESCRIPTION_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withLocation(VALID_LOCATION_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // opening hours
        userInput = targetIndex.getOneBased() + OPENING_HOURS_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder()
                .withOpeningHours(VALID_OPENING_HOURS_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price range
        userInput = targetIndex.getOneBased() + PRICE_RANGE_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withPriceRange(VALID_PRICE_RANGE_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = targetIndex.getOneBased() + RATING_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withRating(VALID_RATING_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = targetIndex.getOneBased() + VISITED_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withVisited(VALID_VISITED_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_ACTIVITY + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withTags(VALID_TAG_ACTIVITY).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + START_TIME_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withStartTime(VALID_START_TIME_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + END_TIME_DESC_EIFFEL + DAY_VISITING_DESC_EIFFEL;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withEndTime(VALID_END_TIME_EIFFEL).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index dayVisiting = Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_MBS));
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased()
                + START_TIME_DESC_EIFFEL
                + END_TIME_DESC_EIFFEL
                + PHONE_DESC_EIFFEL
                + ADDRESS_DESC_EIFFEL
                + EMAIL_DESC_EIFFEL
                + LOCATION_DESC_EIFFEL
                + DESCRIPTION_DESC_EIFFEL
                + OPENING_HOURS_DESC_EIFFEL
                + PRICE_RANGE_DESC_EIFFEL
                + RATING_DESC_EIFFEL
                + VISITED_DESC_EIFFEL
                + TAG_DESC_ACTIVITY
                + PHONE_DESC_EIFFEL
                + ADDRESS_DESC_EIFFEL
                + EMAIL_DESC_EIFFEL
                + TAG_DESC_ACTIVITY
                + PHONE_DESC_MBS
                + ADDRESS_DESC_MBS
                + EMAIL_DESC_MBS
                + LOCATION_DESC_MBS
                + DESCRIPTION_DESC_MBS
                + OPENING_HOURS_DESC_MBS
                + PRICE_RANGE_DESC_MBS
                + RATING_DESC_MBS
                + START_TIME_DESC_MBS
                + END_TIME_DESC_MBS
                + VISITED_DESC_MBS
                + DAY_VISITING_DESC_MBS
                + TAG_DESC_SIGHTSEEING;

        EditItineraryAttractionDescriptor descriptor =
                new EditItineraryAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS)
                        .withEmail(VALID_EMAIL_MBS).withAddress(VALID_ADDRESS_MBS)
                        .withLocation(VALID_LOCATION_MBS).withDescription(VALID_DESCRIPTION_MBS)
                        .withOpeningHours(VALID_OPENING_HOURS_MBS).withPriceRange(VALID_PRICE_RANGE_MBS)
                        .withRating(VALID_RATING_MBS).withVisited(VALID_VISITED_MBS)
                        .withTags(VALID_TAG_ACTIVITY, VALID_TAG_SIGHTSEEING)
                        .withStartTime(VALID_START_TIME_MBS)
                        .withEndTime(VALID_END_TIME_MBS).build();
        EditItineraryAttractionCommand expectedCommand = new EditItineraryAttractionCommand(
                targetIndex, dayVisiting, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        Index dayVisiting = Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_MBS));

        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_MBS + DAY_VISITING_DESC_MBS;
        EditItineraryAttractionDescriptor descriptor = new EditItineraryAttractionDescriptorBuilder()
                .withPhone(VALID_PHONE_MBS).build();
        EditItineraryAttractionCommand expectedCommand = new EditItineraryAttractionCommand(
                targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_MBS + INVALID_PHONE_DESC + ADDRESS_DESC_MBS + PHONE_DESC_MBS
                + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS + DAY_VISITING_DESC_MBS;
        descriptor = new EditItineraryAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS)
                .withEmail(VALID_EMAIL_MBS).withAddress(VALID_ADDRESS_MBS).withDescription(VALID_DESCRIPTION_MBS)
                .withLocation(VALID_LOCATION_MBS).withOpeningHours(VALID_OPENING_HOURS_MBS).build();
        expectedCommand = new EditItineraryAttractionCommand(targetIndex, dayVisiting, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        Index dayVisiting = Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_MBS));
        String userInput = targetIndex.getOneBased() + TAG_EMPTY + DAY_VISITING_DESC_MBS;

        EditItineraryAttractionDescriptor descriptor = new EditItineraryAttractionDescriptorBuilder()
                .withTags().build();
        EditItineraryAttractionCommand expectedCommand = new EditItineraryAttractionCommand(
                targetIndex, dayVisiting, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_EIFFEL, MESSAGE_INVALID_FORMAT);

        // no day specified
        assertParseFailure(parser, INDEX_DESC_EIFFEL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, INDEX_DESC_EIFFEL + DAY_VISITING_DESC_MBS,
                EditItineraryAttractionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DAY_VISITING_DESC_EIFFEL + NAME_DESC_EIFFEL, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DAY_VISITING_DESC_EIFFEL + NAME_DESC_EIFFEL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_LOCATION_DESC,
                Location.MESSAGE_CONSTRAINTS);

        // invalid opening hours
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_OPENING_HOURS_DESC,
                OpeningHours.MESSAGE_CONSTRAINTS);

        // invalid price range
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_PRICE_RANGE_DESC,
                PriceRange.MESSAGE_CONSTRAINTS);

        // invalid rating
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS);

        // invalid visited
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_VISITED_DESC,
                Visited.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_START_TIME_DESC,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_END_TIME_DESC,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // negative day
        assertParseFailure(parser, "2" + INVALID_DAY_VISITING_DESC + NAME_DESC_EIFFEL, Day.MESSAGE_CONSTRAINTS);

        // zero day
        assertParseFailure(parser, "2" + " day/0" + NAME_DESC_EIFFEL, Day.MESSAGE_CONSTRAINTS);

        // multiple day
        assertParseFailure(parser, "2 day/213 321" + NAME_DESC_EIFFEL, Day.MESSAGE_CONSTRAINTS);

        // not a day
        assertParseFailure(parser, "2 day/123 yum" + NAME_DESC_EIFFEL, Day.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "3 day/fda" + NAME_DESC_EIFFEL, Day.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_PHONE_DESC + EMAIL_DESC_EIFFEL,
                Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + PHONE_DESC_MBS + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code ItineraryAttraction} being
        // edited, parsing it together with a valid tag results in error
        assertParseFailure(parser,
                "1" + DAY_VISITING_DESC_EIFFEL + TAG_DESC_ACTIVITY + TAG_DESC_SIGHTSEEING + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + DAY_VISITING_DESC_EIFFEL + TAG_DESC_ACTIVITY + TAG_EMPTY + TAG_DESC_SIGHTSEEING,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + DAY_VISITING_DESC_EIFFEL + TAG_EMPTY + TAG_DESC_ACTIVITY + TAG_DESC_SIGHTSEEING,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + DAY_VISITING_DESC_EIFFEL + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_ADDRESS_EIFFEL + VALID_PHONE_EIFFEL + VALID_DESCRIPTION_EIFFEL + VALID_LOCATION_EIFFEL
                + VALID_OPENING_HOURS_EIFFEL + VALID_PRICE_RANGE_EIFFEL + VALID_RATING_EIFFEL
                + VALID_VISITED_EIFFEL, Name.MESSAGE_CONSTRAINTS);
    }
}
