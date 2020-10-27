package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_INVALID_STATUS;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_INVALID_TITLE_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_STATUS_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_STATUS_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_TAG_FRIEND;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_TAG_HUSBAND;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_TITLE_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREFIX_WITH_TITLE_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREIFX_WITH_INVALID_TAG;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TITLE_BOB;
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
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PREFIX_WITH_TITLE_BOB
                + PREFIX_WITH_STATUS_BOB + PREFIX_WITH_TAG_FRIEND,
                new AddCaseCommand(expectedCase));

        // multiple names - last name accepted
        assertParseSuccess(parser, PREFIX_WITH_TITLE_AMY + PREFIX_WITH_TITLE_BOB
                + PREFIX_WITH_STATUS_BOB + PREFIX_WITH_TAG_FRIEND,
                new AddCaseCommand(expectedCase));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, PREFIX_WITH_TITLE_BOB
                + PREFIX_WITH_STATUS_AMY + PREFIX_WITH_STATUS_BOB + PREFIX_WITH_TAG_FRIEND,
                new AddCaseCommand(expectedCase));

        // multiple tags - all accepted
        Case expectedCaseMultipleTags = new CaseBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser, PREFIX_WITH_TITLE_BOB
                + PREFIX_WITH_STATUS_BOB + PREFIX_WITH_TAG_HUSBAND + PREFIX_WITH_TAG_FRIEND,
                new AddCaseCommand(expectedCaseMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Case expectedCase = new CaseBuilder(AMY).withTags().build();
        assertParseSuccess(parser, PREFIX_WITH_TITLE_AMY, new AddCaseCommand(expectedCase));

        // no status
        expectedCase = new CaseBuilder(expectedCase).withStatus("active").build();
        assertParseSuccess(parser, PREFIX_WITH_TITLE_AMY, new AddCaseCommand(expectedCase));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCaseCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TITLE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, PREFIX_WITH_INVALID_TITLE_AMY + PREFIX_WITH_TAG_HUSBAND + PREFIX_WITH_TAG_FRIEND,
                Title.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, PREFIX_WITH_TITLE_BOB + PREFIX_WITH_INVALID_STATUS
                        + PREFIX_WITH_TAG_HUSBAND + PREFIX_WITH_TAG_FRIEND,
                Status.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, PREFIX_WITH_TITLE_BOB + PREIFX_WITH_INVALID_TAG + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        // TODO: for "add case t:TITLE", this test case may not be so relevant bc only one value
        // but might be relevant for "add case t:TITLE d:DESCRIPTION" <-- can KIV for future use?
        assertParseFailure(parser, PREFIX_WITH_INVALID_TITLE_AMY, Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PREFIX_WITH_TITLE_BOB
                        + PREFIX_WITH_TAG_HUSBAND + PREFIX_WITH_TAG_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCaseCommand.MESSAGE_USAGE));
    }
}
