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
import static seedu.address.logic.commands.CommandTestUtil.OPENING_HOURS_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_HOURS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_RANGE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_RANGE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
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
import seedu.address.logic.commands.attraction.EditAttractionCommand;
import seedu.address.logic.commands.attraction.EditAttractionCommand.EditAttractionDescriptor;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditAttractionDescriptorBuilder;

public class EditAttractionCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAttractionCommand.MESSAGE_USAGE);

    private EditAttractionCommandParser parser = new EditAttractionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_EIFFEL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAttractionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_EIFFEL, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_EIFFEL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

        // valid prefix for itinerary but invalid for attraction being parsed as preamble
        assertParseFailure(parser, "1 b/100", MESSAGE_INVALID_FORMAT); // budget
        assertParseFailure(parser, "1 sd/20-12-2020", MESSAGE_INVALID_FORMAT); // start date
        assertParseFailure(parser, "1 ed/30-12-2020", MESSAGE_INVALID_FORMAT); // end date
        assertParseFailure(parser, "1 st/1000", MESSAGE_INVALID_FORMAT); // start time
        assertParseFailure(parser, "1 et/1400", MESSAGE_INVALID_FORMAT); // end time
        assertParseFailure(parser, "1 day/3", MESSAGE_INVALID_FORMAT); // day
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_OPENING_HOURS_DESC,
                OpeningHours.MESSAGE_CONSTRAINTS); // invalid opening hours
        assertParseFailure(parser, "1" + INVALID_PRICE_RANGE_DESC,
                PriceRange.MESSAGE_CONSTRAINTS); // invalid price range
        assertParseFailure(parser, "1" + INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, "1" + INVALID_VISITED_DESC, Visited.MESSAGE_CONSTRAINTS); // invalid visited
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_EIFFEL, Phone.MESSAGE_CONSTRAINTS);

        // invalid rating followed by valid address
        assertParseFailure(parser, "1" + INVALID_RATING_DESC + ADDRESS_DESC_EIFFEL, Rating.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_MBS + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Attraction} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_ACTIVITY + TAG_DESC_SIGHTSEEING + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_ACTIVITY + TAG_EMPTY + TAG_DESC_SIGHTSEEING, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_ACTIVITY + TAG_DESC_SIGHTSEEING, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + ADDRESS_DESC_EIFFEL
                + PHONE_DESC_EIFFEL + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
                + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_EIFFEL + TAG_DESC_SIGHTSEEING
                + EMAIL_DESC_EIFFEL + ADDRESS_DESC_EIFFEL + NAME_DESC_EIFFEL + DESCRIPTION_DESC_EIFFEL
                + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL + PRICE_RANGE_DESC_EIFFEL
                + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL + TAG_DESC_ACTIVITY;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_EIFFEL)
                .withPhone(VALID_PHONE_EIFFEL).withEmail(VALID_EMAIL_EIFFEL).withAddress(VALID_ADDRESS_EIFFEL)
                .withDescription(VALID_DESCRIPTION_EIFFEL).withLocation(VALID_LOCATION_EIFFEL)
                .withOpeningHours(VALID_OPENING_HOURS_EIFFEL).withPriceRange(VALID_PRICE_RANGE_EIFFEL)
                .withRating(VALID_RATING_EIFFEL).withVisited(VALID_VISITED_EIFFEL)
                .withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_ACTIVITY).build();
        EditAttractionCommand expectedCommand = new EditAttractionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MBS + EMAIL_DESC_EIFFEL;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS)
                .withEmail(VALID_EMAIL_EIFFEL).build();
        EditAttractionCommand expectedCommand = new EditAttractionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EIFFEL;
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_EIFFEL).build();
        EditAttractionCommand expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withEmail(VALID_EMAIL_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withAddress(VALID_ADDRESS_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withDescription(VALID_DESCRIPTION_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withLocation(VALID_LOCATION_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // opening hours
        userInput = targetIndex.getOneBased() + OPENING_HOURS_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withOpeningHours(VALID_OPENING_HOURS_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price range
        userInput = targetIndex.getOneBased() + PRICE_RANGE_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withPriceRange(VALID_PRICE_RANGE_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = targetIndex.getOneBased() + RATING_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withRating(VALID_RATING_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = targetIndex.getOneBased() + VISITED_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withVisited(VALID_VISITED_EIFFEL).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_ACTIVITY;
        descriptor = new EditAttractionDescriptorBuilder().withTags(VALID_TAG_ACTIVITY).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_EIFFEL + ADDRESS_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                + LOCATION_DESC_EIFFEL + DESCRIPTION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL + PRICE_RANGE_DESC_EIFFEL
                + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL + TAG_DESC_ACTIVITY + PHONE_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
                + EMAIL_DESC_EIFFEL + TAG_DESC_ACTIVITY + PHONE_DESC_MBS + ADDRESS_DESC_MBS + EMAIL_DESC_MBS
                + LOCATION_DESC_MBS + DESCRIPTION_DESC_MBS + OPENING_HOURS_DESC_MBS + PRICE_RANGE_DESC_MBS
                + RATING_DESC_MBS + VISITED_DESC_MBS + TAG_DESC_SIGHTSEEING;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS)
                .withEmail(VALID_EMAIL_MBS).withAddress(VALID_ADDRESS_MBS)
                .withLocation(VALID_LOCATION_MBS).withDescription(VALID_DESCRIPTION_MBS)
                .withOpeningHours(VALID_OPENING_HOURS_MBS).withPriceRange(VALID_PRICE_RANGE_MBS)
                .withRating(VALID_RATING_MBS).withVisited(VALID_VISITED_MBS)
                .withTags(VALID_TAG_ACTIVITY, VALID_TAG_SIGHTSEEING).build();
        EditAttractionCommand expectedCommand = new EditAttractionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_MBS;
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS).build();
        EditAttractionCommand expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_MBS + INVALID_PHONE_DESC + ADDRESS_DESC_MBS
                + PHONE_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS;
        descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS).withEmail(VALID_EMAIL_MBS)
                .withAddress(VALID_ADDRESS_MBS).withDescription(VALID_DESCRIPTION_MBS)
                .withLocation(VALID_LOCATION_MBS).withOpeningHours(VALID_OPENING_HOURS_MBS).build();
        expectedCommand = new EditAttractionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withTags().build();
        EditAttractionCommand expectedCommand = new EditAttractionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
