package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.AMY;
import static seedu.address.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.Type;
import seedu.address.testutil.DeadlineBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new DeadlineBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TYPE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_AMY + TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TYPE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple dateTimes - last dateTime accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_TIME_DESC_AMY + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TYPE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple emails - last email accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB
                + TYPE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple types - last type accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + TYPE_DESC_AMY
                + TYPE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new DeadlineBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + TYPE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new DeadlineBuilder(AMY).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_AMY + DATE_TIME_DESC_AMY + DESCRIPTION_DESC_AMY + TYPE_DESC_AMY,
                new AddCommand(expectedTask));

        // missing dateTime field
        expectedTask = new DeadlineBuilder(AMY).withTags().withDefaultDateTime().build();
        assertParseSuccess(parser, TITLE_DESC_AMY + DESCRIPTION_DESC_AMY + TYPE_DESC_AMY,
                new AddCommand(expectedTask));

        // missing description field
        expectedTask = new DeadlineBuilder(AMY).withTags().withDefaultDescription().build();
        assertParseSuccess(parser, TITLE_DESC_AMY + DATE_TIME_DESC_AMY + TYPE_DESC_AMY,
                new AddCommand(expectedTask));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + TYPE_DESC_BOB,
                expectedMessage);

        // missing type prefix
        assertParseFailure(parser, TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + VALID_TYPE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_BOB + VALID_DATE_TIME_BOB + VALID_DESCRIPTION_BOB + VALID_TYPE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + TYPE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Title.MESSAGE_CONSTRAINTS);

        // invalid dateTime
        assertParseFailure(parser, TITLE_DESC_BOB + INVALID_DATE_TIME_DESC + DESCRIPTION_DESC_BOB + TYPE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, DateTime.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, TITLE_DESC_BOB + DATE_TIME_DESC_BOB + INVALID_DESCRIPTION_DESC + TYPE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Description.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + INVALID_TYPE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Type.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + TYPE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                        + INVALID_TYPE_DESC, Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_BOB + DATE_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
