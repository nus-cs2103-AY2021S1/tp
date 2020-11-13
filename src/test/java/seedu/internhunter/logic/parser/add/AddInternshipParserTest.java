package seedu.internhunter.logic.parser.add;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY_RANDOM;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_PERIOD_DESC;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_REQUIREMENT_DESC;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_WAGE_DESC;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.JOB_TITLE_DESC_FE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.JOB_TITLE_DESC_SWE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.PERIOD_DESC_THREE_MONTHS;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.PERIOD_DESC_TWO_MONTHS;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.REQUIREMENT_DESC_R;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.REQUIREMENT_DESC_VUE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.WAGE_DESC_FE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.WAGE_DESC_SWE;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_TWO_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_R;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_VUE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.add.AddInternshipCommand;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;

/**
 * Contains the tests for AddInternshipCommandParser.
 */
public class AddInternshipParserTest {

    private AddInternshipCommandParser parser = new AddInternshipCommandParser();
    private String expectedGeneralMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        AddInternshipCommand.MESSAGE_USAGE);
    private AddInternshipCommand expectedCommand = new AddInternshipCommand(INDEX_FIRST,
            new JobTitle(VALID_JOB_TITLE_SWE),
            new Wage(VALID_WAGE_2000),
            new Period(VALID_PERIOD_TWO_MONTHS),
            new HashSet<Requirement>(Arrays.asList(new Requirement(VALID_REQUIREMENT_VUE))));

    private HashSet<Requirement> multipleRequirements =
            new HashSet<>(Arrays.asList(new Requirement(VALID_REQUIREMENT_VUE),
                new Requirement(VALID_REQUIREMENT_R)));

    @Test
    public void parse_allFieldsPresent_success() {
        AddInternshipCommand expectedInternshipCommand = expectedCommand;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE, expectedInternshipCommand);

        // multiple jobTitles - last jobTitle accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_FE + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE, expectedInternshipCommand);

        // multiple wages - last wage accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_FE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE, expectedInternshipCommand);

        // multiple periods - last period accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_THREE_MONTHS + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE, expectedInternshipCommand);

        AddInternshipCommand expectedCommandMultipleRequirements = new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_2000),
                new Period(VALID_PERIOD_TWO_MONTHS),
                multipleRequirements);

        // multiple requirements - all accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE + REQUIREMENT_DESC_R, expectedCommandMultipleRequirements);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // missing wage
        AddInternshipCommand expectedCommandNoWage = new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage("-"),
                new Period(VALID_PERIOD_TWO_MONTHS),
                new HashSet<>());

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                        + JOB_TITLE_DESC_SWE + PERIOD_DESC_TWO_MONTHS, expectedCommandNoWage);

        // missing period
        AddInternshipCommand expectedCommandNoPeriod = new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_2000),
                new Period("-"),
                new HashSet<>());

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE, expectedCommandNoPeriod);

        // zero requirements
        AddInternshipCommand expectedCommandNoRequirements = new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_2000),
                new Period(VALID_PERIOD_TWO_MONTHS),
                new HashSet<>());

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS,
                expectedCommandNoRequirements);

        // missing all optional
        AddInternshipCommand expectedCommandNoOptional = new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage("-"),
                new Period("-"),
                new HashSet<>());

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                        + JOB_TITLE_DESC_SWE, expectedCommandNoOptional);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + JOB_TITLE_DESC_SWE
                + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS, expectedGeneralMessage);

        // missing jobTitle prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS, expectedGeneralMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY_RANDOM + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE, MESSAGE_INVALID_INDEX);

        // invalid index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "hello"
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS, MESSAGE_INVALID_INDEX);

        // out of bounds index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + "0"
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS, MESSAGE_INVALID_INDEX);

        // invalid jobTitle
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + INVALID_JOB_TITLE_DESC + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS, JobTitle.MESSAGE_CONSTRAINTS);

        // invalid wage
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + INVALID_WAGE_DESC + PERIOD_DESC_TWO_MONTHS, Wage.MESSAGE_CONSTRAINTS);

        // invalid period
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + INVALID_PERIOD_DESC, Period.MESSAGE_CONSTRAINTS);

        // invalid requirements
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                        + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS + INVALID_REQUIREMENT_DESC,
                Requirement.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + INVALID_JOB_TITLE_DESC + INVALID_WAGE_DESC + PERIOD_DESC_TWO_MONTHS, JobTitle.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + JOB_TITLE_DESC_SWE + INVALID_WAGE_DESC + INVALID_PERIOD_DESC, Wage.MESSAGE_CONSTRAINTS);
    }
}
