package seedu.address.logic.parser.gradetrackerparsers;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulercommands.EditEventCommand;
import seedu.address.logic.parser.schedulerparsers.EditEventParser;
import seedu.address.model.event.EventName;
import seedu.address.testutil.event.EditEventDescriptorBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

public class EditAssignmentParserTest {
    private static final int INDEX = 1;
    private static final String VALID_INPUT_EDIT_NAME = " " + INDEX
            + " " + PREFIX_NAME + VALID_EVENT_NAME_1;

    private static final String VALID_MULTIPLE_INPUT_EDIT_NAME = " " + INDEX
            + " " + PREFIX_NAME + VALID_EVENT_NAME_1
            + " " + PREFIX_NAME + VALID_EVENT_NAME_2;

    private static final String VALID_INPUT_EDIT_DATE = " " + INDEX
            + " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private static final String VALID_INPUT_ALL_FIELDS = " " + INDEX
            + " " + PREFIX_NAME + VALID_EVENT_NAME_1
            + " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private static final String INVALID_INPUT_EDIT_NAME = " " + INDEX
            + " " + PREFIX_NAME + INVALID_EVENT_NAME;

    private static final String INVALID_INPUT_EDIT_DATE = " " + INDEX
            + " " + PREFIX_DATE + INVALID_EVENT_DATE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventParser parser = new EditEventParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_1)
                .withDate(VALID_EVENT_DATE_1).build();
        EditEventCommand expectedCommand = new EditEventCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_ALL_FIELDS, expectedCommand);
    }

    @Test
    public void parse_onlyNameSpecified_success() {
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_1).build();
        EditEventCommand expectedCommand = new EditEventCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_EDIT_NAME, expectedCommand);
    }

    @Test
    public void parse_onlyDateSpecified_success() {
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withDate(VALID_EVENT_DATE_1).build();
        EditEventCommand expectedCommand = new EditEventCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_EDIT_DATE, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_2)
                .build();
        EditEventCommand expectedCommand = new EditEventCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_MULTIPLE_INPUT_EDIT_NAME, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String invalidThenValid = " " + 1
                + " " + PREFIX_NAME + "@123"
                + " " + PREFIX_NAME + VALID_EVENT_NAME_1;
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_1).build();
        EditEventCommand expectedCommand = new EditEventCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, invalidThenValid, expectedCommand);
    }

    @Test
    public void parse_missingIndex_failure() {
        String missingIndex = " " + PREFIX_NAME + VALID_EVENT_NAME_1;
        assertParseFailure(parser, missingIndex, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingAllFields_failure() {
        String missingFields = " ";
        assertParseFailure(parser, missingFields, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidName_failure() {
        String invalidName = " " + 1
                + " " + PREFIX_NAME + "@123";
        assertParseFailure(parser, invalidName, EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTag_failure() {
        // TODO: Implement tagging for events
        /*
        String invalidTag = " " + 1
                + " " + PREFIX_TAG + "@@@@";
        assertParseFailure(parser, invalidTag, Tag.MESSAGE_CONSTRAINTS);
         */
    }
}
