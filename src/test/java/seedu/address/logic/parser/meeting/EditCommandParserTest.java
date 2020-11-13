package seedu.address.logic.parser.meeting;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.meeting.CommandTestUtil.CONTACTS_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.CONTACTS_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.FROM_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.FROM_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.INVALID_FROM_DESC;
import static seedu.address.logic.commands.meeting.CommandTestUtil.LOCATION_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.TITLE_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.TO_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.TO_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_CONTACTS_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_CONTACTS_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_FROM_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_FROM_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_LOCATION_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TITLE_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TO_B;
import static seedu.address.logic.parser.meeting.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.meeting.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditCommand;
import seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

class EditCommandParserTest {

    private static final String EMPTY = " ";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TITLE_DESC_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // TODO:Create Meeting Templates
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_A, MESSAGE_INVALID_FORMAT);

        // TODO:Create Meeting Templates
        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;

        String userInput = targetIndex.getOneBased() + FROM_DESC_A
                + CONTACTS_DESC_A + TITLE_DESC_A;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_A)
                .withContacts(VALID_CONTACTS_A).withFrom(VALID_FROM_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + CONTACTS_DESC_B + LOCATION_DESC_A;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withContacts(VALID_CONTACTS_B)
                .withLocation(VALID_LOCATION_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_A;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // From
        userInput = targetIndex.getOneBased() + FROM_DESC_A;
        descriptor = new EditMeetingDescriptorBuilder().withFrom(VALID_FROM_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_A;
        descriptor = new EditMeetingDescriptorBuilder().withLocation(VALID_LOCATION_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + FROM_DESC_A + TO_DESC_A
                + FROM_DESC_A + TO_DESC_A
                + FROM_DESC_B + TO_DESC_B;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withFrom(VALID_FROM_B)
                .withTo(VALID_TO_B)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_FROM_DESC + FROM_DESC_B;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withFrom(VALID_FROM_B).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TO_DESC_B + INVALID_FROM_DESC
                + FROM_DESC_B;
        descriptor = new EditMeetingDescriptorBuilder().withFrom(VALID_FROM_B).withTo(VALID_TO_B)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
