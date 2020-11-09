package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_JOB_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_JOB_VACANCY;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_PERSON_BLACKLIST;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_PERSON_DOA;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_PERSON_EXP;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_PERSON_NAME;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TYPE_PERSON_SAL;
import static seedu.address.logic.commands.SortPersonCommand.MESSAGE_SORT_TYPE_INVALID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.comparator.PersonBlackListComparator;
import seedu.address.model.information.comparator.PersonDateOfApplicationComparator;
import seedu.address.model.information.comparator.PersonExpectedSalaryComparator;
import seedu.address.model.information.comparator.PersonExperienceComparator;
import seedu.address.model.information.comparator.PersonNameComparator;


public class SortPersonCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPersonCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_ORDER = "Order can only be 'asc' or 'desc'";

    private SortPersonCommandParser parser = new SortPersonCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sort order specified
        assertParseFailure(parser, SORT_TYPE_PERSON_DOA, MESSAGE_INVALID_FORMAT);
        // no sort type specified
        assertParseFailure(parser, SORT_ORDER_ASCENDING, MESSAGE_INVALID_FORMAT);

        // no sort type and order specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid type followed by valid order
        assertParseFailure(parser, SORT_TYPE_JOB_VACANCY + SORT_ORDER_ASCENDING,
                MESSAGE_SORT_TYPE_INVALID);

        // valid type followed by invalid order
        assertParseFailure(parser, SORT_TYPE_PERSON_DOA + INVALID_SORT_ORDER_DESC, MESSAGE_INVALID_ORDER);

        // valid type followed by invalid type. The test case for invalid type followed by valid type
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, SORT_TYPE_PERSON_EXP + SORT_TYPE_JOB_PRIORITY + SORT_ORDER_ASCENDING,
                MESSAGE_SORT_TYPE_INVALID);

        // both invalid values, but only the invalid order description is captured
        assertParseFailure(parser, SORT_TYPE_JOB_VACANCY + INVALID_SORT_ORDER_DESC, MESSAGE_INVALID_ORDER);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        String userInput = SORT_TYPE_PERSON_BLACKLIST + SORT_ORDER_DESCENDING;
        PersonBlackListComparator comparator = new PersonBlackListComparator();
        SortPersonCommand expectedCommand = new SortPersonCommand(comparator, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = SORT_TYPE_PERSON_DOA + SORT_ORDER_ASCENDING;
        PersonDateOfApplicationComparator doaComparator = new PersonDateOfApplicationComparator();
        expectedCommand = new SortPersonCommand(doaComparator, true);
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = SORT_TYPE_PERSON_NAME + SORT_ORDER_ASCENDING;
        PersonNameComparator nameComparator = new PersonNameComparator();
        expectedCommand = new SortPersonCommand(nameComparator, true);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = SORT_TYPE_PERSON_EXP + SORT_ORDER_ASCENDING
                + SORT_TYPE_PERSON_SAL + SORT_ORDER_DESCENDING;
        PersonExpectedSalaryComparator comparator = new PersonExpectedSalaryComparator();
        SortPersonCommand expectedCommand = new SortPersonCommand(comparator, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = SORT_TYPE_JOB_VACANCY + SORT_ORDER_ASCENDING + SORT_TYPE_PERSON_EXP;
        PersonExperienceComparator comparator = new PersonExperienceComparator();
        SortPersonCommand expectedCommand = new SortPersonCommand(comparator, true);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
