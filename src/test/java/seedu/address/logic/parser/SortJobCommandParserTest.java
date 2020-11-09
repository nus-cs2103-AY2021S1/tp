package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_JOB_COMPANY;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_JOB_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_JOB_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_JOB_VACANCY;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_PERSON_BLACKLIST;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_PERSON_EXP;
import static seedu.address.logic.commands.SortJobCommand.MESSAGE_SORT_JOB_TYPE_INVALID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.comparator.JobCompanyComparator;
import seedu.address.model.information.comparator.JobPriorityComparator;
import seedu.address.model.information.comparator.JobTitleComparator;
import seedu.address.model.information.comparator.JobVacancyComparator;

public class SortJobCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortJobCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_ORDER = "Order can only be 'asc' or 'desc'";

    private SortJobCommandParser parser = new SortJobCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sort order specified
        assertParseFailure(parser, SORT_TYPE_JOB_VACANCY, MESSAGE_INVALID_FORMAT);

        // no sort type specified
        assertParseFailure(parser, SORT_ORDER_ASCENDING, MESSAGE_INVALID_FORMAT);

        // no sort type and order specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid type followed by valid order
        assertParseFailure(parser, SORT_TYPE_PERSON_BLACKLIST + SORT_ORDER_ASCENDING,
                MESSAGE_SORT_JOB_TYPE_INVALID);

        // valid type followed by invalid order
        assertParseFailure(parser, SORT_TYPE_JOB_VACANCY + INVALID_SORT_ORDER_DESC, MESSAGE_INVALID_ORDER);

        // valid type followed by invalid type. The test case for invalid type followed by valid type
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, SORT_TYPE_JOB_VACANCY + SORT_TYPE_PERSON_BLACKLIST + SORT_ORDER_ASCENDING,
                MESSAGE_SORT_JOB_TYPE_INVALID);

        // both invalid values, but only the invalid order description is captured
        assertParseFailure(parser, SORT_TYPE_PERSON_EXP + INVALID_SORT_ORDER_DESC, MESSAGE_INVALID_ORDER);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        String userInput = SORT_TYPE_JOB_VACANCY + SORT_ORDER_ASCENDING;

        JobVacancyComparator comparator = new JobVacancyComparator();
        SortJobCommand expectedCommand = new SortJobCommand(comparator, true);

        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = SORT_TYPE_JOB_COMPANY + SORT_ORDER_DESCENDING;

        JobCompanyComparator companyComparator = new JobCompanyComparator();
        expectedCommand = new SortJobCommand(companyComparator, false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = SORT_TYPE_JOB_PRIORITY + SORT_ORDER_ASCENDING
            + SORT_TYPE_JOB_VACANCY + SORT_ORDER_DESCENDING + SORT_TYPE_JOB_PRIORITY;

        JobPriorityComparator comparator = new JobPriorityComparator();
        SortJobCommand expectedCommand = new SortJobCommand(comparator, false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = SORT_TYPE_PERSON_EXP + SORT_ORDER_DESCENDING + SORT_TYPE_JOB_TITLE;

        JobTitleComparator comparator = new JobTitleComparator();
        SortJobCommand expectedCommand = new SortJobCommand(comparator, false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
