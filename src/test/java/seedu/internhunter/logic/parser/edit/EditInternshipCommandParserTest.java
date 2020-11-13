package seedu.internhunter.logic.parser.edit;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INDEX_DESC_FIRST;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INDEX_DESC_INVALID_PREFIX;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INDEX_DESC_NAN;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INDEX_DESC_NEGATIVE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INDEX_DESC_ZERO;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_PERIOD_DESC;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_WAGE_DESC;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.JOB_TITLE_DESC_FE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.JOB_TITLE_DESC_SWE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.PERIOD_DESC_TWO_MONTHS;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.REQUIREMENT_DESC_R;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.REQUIREMENT_DESC_VUE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.WAGE_DESC_SWE;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_REQUIREMENT;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_FE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_TWO_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_R;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_VUE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.commands.edit.EditInternshipCommand;
import seedu.internhunter.logic.commands.edit.EditInternshipCommand.EditInternshipDescriptor;
import seedu.internhunter.logic.commands.util.internship.EditInternshipDescriptorBuilder;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;

public class EditInternshipCommandParserTest {

    private static final String REQUIREMENT_EMPTY = " " + PREFIX_REQUIREMENT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInternshipCommand.MESSAGE_USAGE);

    private EditInternshipCommandParser parser = new EditInternshipCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no company index specified
        assertParseFailure(parser, INDEX_DESC_FIRST + JOB_TITLE_DESC_SWE, MESSAGE_INVALID_FORMAT);

        // no internship index specified
        assertParseFailure(parser, "1" + JOB_TITLE_DESC_SWE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST, EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndexes_failure() {
        // invalid company index
        // negative index
        assertParseFailure(parser, "-5" + INDEX_DESC_FIRST + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + INDEX_DESC_FIRST + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + INDEX_DESC_FIRST + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 d/string" + INDEX_DESC_FIRST + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);

        //invalid internship index
        // negative index
        assertParseFailure(parser, "1" + INDEX_DESC_NEGATIVE + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "1" + INDEX_DESC_ZERO + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);

        // invalid arguments present
        assertParseFailure(parser, "1" + INDEX_DESC_NAN + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);

        // invalid prefix present
        assertParseFailure(parser, "1" + INDEX_DESC_INVALID_PREFIX + WAGE_DESC_SWE, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid job title
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + INVALID_JOB_TITLE_DESC, JobTitle.MESSAGE_CONSTRAINTS);

        // invalid wage
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + INVALID_WAGE_DESC, Wage.MESSAGE_CONSTRAINTS);

        // invalid period
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + INVALID_PERIOD_DESC, Period.MESSAGE_CONSTRAINTS);

        // invalid value followed by valid value of a different type
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + INVALID_PERIOD_DESC + WAGE_DESC_SWE,
                Period.MESSAGE_CONSTRAINTS);

        // valid value followed by invalid value of the same type. The test case for invalid value followed
        // by valid value of the same type is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + WAGE_DESC_SWE + INVALID_WAGE_DESC,
                Wage.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_REQUIREMENT} alone will reset the requirements of the {@code InternshipItem}
        // being edited, parsing it together with a valid requirement results in error
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + REQUIREMENT_DESC_VUE + REQUIREMENT_DESC_R
                        + REQUIREMENT_EMPTY, Requirement.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + REQUIREMENT_DESC_VUE + REQUIREMENT_EMPTY
                        + REQUIREMENT_DESC_R, Requirement.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + REQUIREMENT_EMPTY + REQUIREMENT_DESC_VUE
                        + REQUIREMENT_DESC_R, Requirement.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INDEX_DESC_FIRST + INVALID_JOB_TITLE_DESC + INVALID_PERIOD_DESC,
                JobTitle.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = "1" + INDEX_DESC_FIRST + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withJobTitle(VALID_JOB_TITLE_SWE)
                .withWage(VALID_WAGE_2000)
                .withPeriod(VALID_PERIOD_TWO_MONTHS)
                .withRequirements(VALID_REQUIREMENT_VUE)
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = "1" + INDEX_DESC_FIRST + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withWage(VALID_WAGE_2000)
                .withPeriod(VALID_PERIOD_TWO_MONTHS)
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // job title
        String userInput = "1" + INDEX_DESC_FIRST + JOB_TITLE_DESC_SWE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withJobTitle(VALID_JOB_TITLE_SWE)
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // wage
        userInput = "1" + INDEX_DESC_FIRST + WAGE_DESC_SWE;

        descriptor = new EditInternshipDescriptorBuilder()
                .withWage(VALID_WAGE_2000)
                .build();
        expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // period
        userInput = "1" + INDEX_DESC_FIRST + PERIOD_DESC_TWO_MONTHS;

        descriptor = new EditInternshipDescriptorBuilder()
                .withPeriod(VALID_PERIOD_TWO_MONTHS)
                .build();
        expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // requirement
        userInput = "1" + INDEX_DESC_FIRST + REQUIREMENT_DESC_VUE;

        descriptor = new EditInternshipDescriptorBuilder()
                .withRequirements(VALID_REQUIREMENT_VUE)
                .build();
        expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFieldsNotRequirement_acceptsLast() {
        String userInput = "1" + INDEX_DESC_FIRST + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + JOB_TITLE_DESC_FE
                + PERIOD_DESC_TWO_MONTHS + REQUIREMENT_DESC_VUE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withJobTitle(VALID_JOB_TITLE_FE)
                .withWage(VALID_WAGE_2000)
                .withPeriod(VALID_PERIOD_TWO_MONTHS)
                .withRequirements(VALID_REQUIREMENT_VUE)
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedRequirements_acceptsAll() {
        String userInput = "1" + INDEX_DESC_FIRST + JOB_TITLE_DESC_SWE + WAGE_DESC_SWE + PERIOD_DESC_TWO_MONTHS
                + REQUIREMENT_DESC_VUE + REQUIREMENT_DESC_R;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withJobTitle(VALID_JOB_TITLE_SWE)
                .withWage(VALID_WAGE_2000)
                .withPeriod(VALID_PERIOD_TWO_MONTHS)
                .withRequirements(VALID_REQUIREMENT_VUE, VALID_REQUIREMENT_R)
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = "1" + INDEX_DESC_FIRST + INVALID_WAGE_DESC + WAGE_DESC_SWE;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withWage(VALID_WAGE_2000)
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = "1" + INDEX_DESC_FIRST + INVALID_WAGE_DESC + PERIOD_DESC_TWO_MONTHS + WAGE_DESC_SWE
                + REQUIREMENT_DESC_R;

        descriptor = new EditInternshipDescriptorBuilder()
                .withWage(VALID_WAGE_2000)
                .withPeriod(VALID_PERIOD_TWO_MONTHS)
                .withRequirements(VALID_REQUIREMENT_R)
                .build();
        expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetRequirements_success() {
        String userInput = "1" + INDEX_DESC_FIRST + REQUIREMENT_EMPTY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withRequirements()
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
