package seedu.address.logic.parser.itinerary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BUDGET_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.BUDGET_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BUDGET_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_PARIS_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JAPAN_TRIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_JAPAN_TRIP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itinerary.EditItineraryCommand;
import seedu.address.logic.commands.itinerary.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.ItineraryDate;
import seedu.address.testutil.EditItineraryDescriptorBuilder;

class EditItineraryCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItineraryCommand.MESSAGE_USAGE);

    private EditItineraryCommandParser parser = new EditItineraryCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_JAPAN_TRIP, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditItineraryCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_JAPAN_TRIP, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_JAPAN_TRIP, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_START_DATE_DESC,
                ItineraryDate.MESSAGE_CONSTRAINTS); // invalid start date
        assertParseFailure(parser, "1" + INVALID_END_DATE_DESC,
                ItineraryDate.MESSAGE_CONSTRAINTS); // invalid end date
        assertParseFailure(parser, "1" + INVALID_BUDGET_DESC,
                Budget.MESSAGE_CONSTRAINTS); // invalid budget

        // invalid budget followed by valid start date
        assertParseFailure(parser, "1" + INVALID_BUDGET_DESC + START_DATE_DESC_JAPAN_TRIP,
                Budget.MESSAGE_CONSTRAINTS);

        // valid end date followed by invalid end date. The test case for invalid end date followed by valid end date
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + END_DATE_DESC_JAPAN_TRIP + INVALID_END_DATE_DESC,
                ItineraryDate.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + NAME_DESC_JAPAN_TRIP + INVALID_START_DATE_DESC
                + END_DATE_DESC_JAPAN_TRIP + VALID_DESCRIPTION_JAPAN_TRIP + INVALID_BUDGET_DESC,
                ItineraryDate.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + NAME_DESC_JAPAN_TRIP + DESCRIPTION_DESC_JAPAN_TRIP
                + START_DATE_DESC_JAPAN_TRIP + END_DATE_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP;

        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withName(VALID_NAME_JAPAN_TRIP)
                .withDescription(VALID_DESCRIPTION_JAPAN_TRIP).withStartDate(VALID_START_DATE_JAPAN_TRIP)
                .withEndDate(VALID_END_DATE_JAPAN_TRIP).withBudget(VALID_BUDGET_JAPAN_TRIP).build();
        EditItineraryCommand expectedCommand = new EditItineraryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + START_DATE_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP;

        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder()
                .withStartDate(VALID_START_DATE_JAPAN_TRIP)
                .withBudget(VALID_BUDGET_JAPAN_TRIP).build();
        EditItineraryCommand expectedCommand = new EditItineraryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_JAPAN_TRIP;
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withName(VALID_NAME_JAPAN_TRIP)
                .build();
        EditItineraryCommand expectedCommand = new EditItineraryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_JAPAN_TRIP;
        descriptor = new EditItineraryDescriptorBuilder().withDescription(VALID_DESCRIPTION_JAPAN_TRIP).build();
        expectedCommand = new EditItineraryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + START_DATE_DESC_JAPAN_TRIP;
        descriptor = new EditItineraryDescriptorBuilder().withStartDate(VALID_START_DATE_JAPAN_TRIP).build();
        expectedCommand = new EditItineraryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end date
        userInput = targetIndex.getOneBased() + END_DATE_DESC_JAPAN_TRIP;
        descriptor = new EditItineraryDescriptorBuilder().withEndDate(VALID_END_DATE_JAPAN_TRIP).build();
        expectedCommand = new EditItineraryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // budget
        userInput = targetIndex.getOneBased() + BUDGET_DESC_JAPAN_TRIP;
        descriptor = new EditItineraryDescriptorBuilder().withBudget(VALID_BUDGET_JAPAN_TRIP).build();
        expectedCommand = new EditItineraryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_PARIS_TRIP + END_DATE_DESC_JAPAN_TRIP
                + NAME_DESC_PARIS_TRIP + END_DATE_DESC_JAPAN_TRIP + START_DATE_DESC_PARIS_TRIP + NAME_DESC_JAPAN_TRIP
                + BUDGET_DESC_PARIS_TRIP + BUDGET_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP
                + DESCRIPTION_DESC_JAPAN_TRIP;

        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withName(VALID_NAME_JAPAN_TRIP)
                .withDescription(VALID_DESCRIPTION_JAPAN_TRIP)
                .withStartDate(VALID_START_DATE_JAPAN_TRIP)
                .withEndDate(VALID_END_DATE_JAPAN_TRIP)
                .withBudget(VALID_BUDGET_JAPAN_TRIP).build();
        EditItineraryCommand expectedCommand = new EditItineraryCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_START_DATE_DESC + START_DATE_DESC_JAPAN_TRIP;
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder()
                .withStartDate(VALID_START_DATE_JAPAN_TRIP).build();
        EditItineraryCommand expectedCommand = new EditItineraryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_JAPAN_TRIP + INVALID_START_DATE_DESC
                + DESCRIPTION_DESC_JAPAN_TRIP + START_DATE_DESC_JAPAN_TRIP + BUDGET_DESC_JAPAN_TRIP
                + END_DATE_DESC_JAPAN_TRIP;
        descriptor = new EditItineraryDescriptorBuilder().withName(VALID_NAME_JAPAN_TRIP)
                .withStartDate(VALID_START_DATE_JAPAN_TRIP).withEndDate(VALID_END_DATE_JAPAN_TRIP)
                .withDescription(VALID_DESCRIPTION_JAPAN_TRIP).withBudget(VALID_BUDGET_JAPAN_TRIP)
                .build();
        expectedCommand = new EditItineraryCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
