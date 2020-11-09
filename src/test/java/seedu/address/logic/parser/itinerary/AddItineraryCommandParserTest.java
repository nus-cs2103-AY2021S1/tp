package seedu.address.logic.parser.itinerary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BUDGET_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.BUDGET_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BUDGET_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_JAPAN_TRIP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itinerary.AddItineraryCommand;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryDate;
import seedu.address.testutil.ItineraryBuilder;

class AddItineraryCommandParserTest {

    private AddItineraryCommandParser parser = new AddItineraryCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Itinerary expectedItinerary = new ItineraryBuilder(JAPAN_TRIP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                        + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                new AddItineraryCommand(expectedItinerary));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_PARIS_TRIP + NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                        + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                new AddItineraryCommand(expectedItinerary));

        // multiple start dates - last start date accepted
        assertParseSuccess(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_PARIS_TRIP
                        + START_DATE_DESC_JAPAN_TRIP + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP
                        + BUDGET_DESC_JAPAN_TRIP, new AddItineraryCommand(expectedItinerary));

        // multiple end dates - last end date accepted
        assertParseSuccess(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP + END_DATE_DESC_PARIS_TRIP
                + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                new AddItineraryCommand(expectedItinerary));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP + END_DATE_DESC_JAPAN_TRIP
                        + DESCRIPTION_DESC_PARIS_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                new AddItineraryCommand(expectedItinerary));

        // multiple budgets - last budget accepted
        assertParseSuccess(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP + END_DATE_DESC_JAPAN_TRIP
                        + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_PARIS_TRIP + BUDGET_DESC_JAPAN_TRIP,
                new AddItineraryCommand(expectedItinerary));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        // No description given
        Itinerary expectedItineraryNoDescription = new ItineraryBuilder(JAPAN_TRIP).withDescription().build();
        assertParseSuccess(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                        + END_DATE_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                new AddItineraryCommand(expectedItineraryNoDescription));

        // No budget given
        Itinerary expectedItineraryNoBudget = new ItineraryBuilder(JAPAN_TRIP).withBudget().build();
        assertParseSuccess(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                        + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP,
                new AddItineraryCommand(expectedItineraryNoBudget));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItineraryCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP, expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, NAME_DESC_JAPAN_TRIP + VALID_START_DATE_JAPAN_TRIP
                + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP, expectedMessage);

        // missing end date prefix
        assertParseFailure(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                + VALID_END_DATE_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_JAPAN_TRIP + VALID_START_DATE_JAPAN_TRIP
                + VALID_END_DATE_JAPAN_TRIP + VALID_DESCRIPTION_JAPAN_TRIP + VALID_BUDGET_JAPAN_TRIP, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + START_DATE_DESC_JAPAN_TRIP
                + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                Name.MESSAGE_CONSTRAINTS);

        // invalid start date
        assertParseFailure(parser, NAME_DESC_JAPAN_TRIP + INVALID_START_DATE_DESC
                        + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                ItineraryDate.MESSAGE_CONSTRAINTS);

        // invalid end date
        assertParseFailure(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                        + INVALID_END_DATE_DESC + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                ItineraryDate.MESSAGE_CONSTRAINTS);

        // invalid budget
        assertParseFailure(parser, NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                        + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + INVALID_BUDGET_DESC,
                Budget.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_START_DATE_DESC
                + DESCRIPTION_DESC_JAPAN_TRIP + END_DATE_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                        + END_DATE_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItineraryCommand.MESSAGE_USAGE));
    }
}
