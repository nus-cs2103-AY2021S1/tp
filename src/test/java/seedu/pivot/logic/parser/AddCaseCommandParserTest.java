package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.pivot.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.pivot.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.pivot.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.pivot.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.pivot.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.pivot.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.pivot.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.pivot.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.pivot.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.pivot.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.pivot.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.pivot.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.pivot.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalCases.AMY;
import static seedu.pivot.testutil.TypicalCases.BOB;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.casecommands.AddCaseCommand;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Status;
import seedu.pivot.model.investigationcase.Title;
import seedu.pivot.model.tag.Tag;
import seedu.pivot.testutil.CaseBuilder;

public class AddCaseCommandParserTest {
    private AddCaseCommandParser parser = new AddCaseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Case expectedCase = new CaseBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + STATUS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCaseCommand(expectedCase));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB
                + STATUS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCaseCommand(expectedCase));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, NAME_DESC_BOB
                + STATUS_DESC_AMY + STATUS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCaseCommand(expectedCase));

        // multiple tags - all accepted
        Case expectedCaseMultipleTags = new CaseBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB
                + STATUS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCaseCommand(expectedCaseMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Case expectedCase = new CaseBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY, new AddCaseCommand(expectedCase));

        // no status
        expectedCase = new CaseBuilder(expectedCase).withStatus("active").build();
        assertParseSuccess(parser, NAME_DESC_AMY, new AddCaseCommand(expectedCase));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCaseCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_NAME_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Title.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_STATUS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Status.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        // Is relevant for "add case t:TITLE s:STATUS" or any number of invalid fields
        // Using 2 invalid Fields Title and Status
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_STATUS_DESC, Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCaseCommand.MESSAGE_USAGE));
    }
}
