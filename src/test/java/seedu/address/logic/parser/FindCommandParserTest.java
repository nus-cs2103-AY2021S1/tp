package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.OfficeContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsWithOneTypePrefix_returnsFindCommand() {

        FindCommand expectedFindCommand;

        // no leading and trailing whitespaces
        expectedFindCommand = new FindCommand(prepareNamePredicate("Alice Liddel"));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Liddel", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Liddel  \t", expectedFindCommand);

        // phone
        expectedFindCommand = new FindCommand(preparePhonePredicate(VALID_PHONE_BOB));
        assertParseSuccess(parser, PHONE_DESC_BOB, expectedFindCommand);

        // email
        expectedFindCommand = new FindCommand(prepareEmailPredicate(VALID_EMAIL_BOB));
        assertParseSuccess(parser, EMAIL_DESC_BOB, expectedFindCommand);

        // department
        expectedFindCommand = new FindCommand(prepareDeptPredicate(VALID_DEPARTMENT_BOB));
        assertParseSuccess(parser, DEPARTMENT_DESC_BOB, expectedFindCommand);

        // office
        expectedFindCommand = new FindCommand(prepareOfficePredicate(VALID_OFFICE_BOB));
        assertParseSuccess(parser, OFFICE_DESC_BOB, expectedFindCommand);

        // remark
        expectedFindCommand = new FindCommand(prepareRemarkPredicate(VALID_REMARK_BOB));
        assertParseSuccess(parser, REMARK_DESC_BOB, expectedFindCommand);

        // tag
        expectedFindCommand = new FindCommand(prepareTagPredicate(VALID_TAG_FRIEND + " " + VALID_TAG_HUSBAND));
        assertParseSuccess(parser, TAG_DESC_FRIEND + " " + VALID_TAG_HUSBAND, expectedFindCommand);

        // whitespace preamble
        expectedFindCommand = new FindCommand(prepareNamePredicate(VALID_NAME_BOB));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB, expectedFindCommand);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsWithDuplicatePrefix_returnsParseException() {

        // name
        assertParseFailure(parser, NAME_DESC_BOB + NAME_DESC_AMY,
            String.format(MESSAGE_DUPLICATE_PREFIX, PREFIX_NAME));

        // department
        assertParseFailure(parser, DEPARTMENT_DESC_BOB + DEPARTMENT_DESC_AMY,
            String.format(MESSAGE_DUPLICATE_PREFIX, PREFIX_DEPARTMENT));

        // office
        assertParseFailure(parser, OFFICE_DESC_BOB + OFFICE_DESC_AMY,
            String.format(MESSAGE_DUPLICATE_PREFIX, PREFIX_OFFICE));

        // remark
        assertParseFailure(parser, REMARK_DESC_BOB + REMARK_DESC_AMY,
            String.format(MESSAGE_DUPLICATE_PREFIX, PREFIX_REMARK));

        // tag
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
            String.format(MESSAGE_DUPLICATE_PREFIX, PREFIX_TAG));

    }

    @Test
    public void parse_validArgsWithMultiplePrefixes_returnsFindCommand() {

        List<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(prepareNamePredicate(VALID_NAME_BOB));
        predicateList.add(prepareDeptPredicate(VALID_DEPARTMENT_BOB));
        predicateList.add(prepareOfficePredicate(VALID_OFFICE_BOB));
        predicateList.add(prepareRemarkPredicate(VALID_REMARK_BOB));
        predicateList.add(prepareTagPredicate(VALID_TAG_FRIEND));

        FindCommand expectedFindCommand = new FindCommand(predicateList);

        assertParseSuccess(parser, NAME_DESC_BOB + DEPARTMENT_DESC_BOB
                + OFFICE_DESC_BOB + REMARK_DESC_BOB + TAG_DESC_FRIEND,
            expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithAllPrefixes_returnsFindCommand() {

        List<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(prepareNamePredicate(VALID_NAME_BOB));
        predicateList.add(preparePhonePredicate(VALID_PHONE_BOB));
        predicateList.add(prepareEmailPredicate(VALID_EMAIL_BOB));
        predicateList.add(prepareDeptPredicate(VALID_DEPARTMENT_BOB));
        predicateList.add(prepareOfficePredicate(VALID_OFFICE_BOB));
        predicateList.add(prepareRemarkPredicate(VALID_REMARK_BOB));
        predicateList.add(prepareTagPredicate(VALID_TAG_FRIEND));

        FindCommand expectedFindCommand = new FindCommand(predicateList);

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DEPARTMENT_DESC_BOB
                        + OFFICE_DESC_BOB + REMARK_DESC_BOB + TAG_DESC_FRIEND,
                expectedFindCommand);
        // Same arguments different order
        assertParseSuccess(parser, OFFICE_DESC_BOB + NAME_DESC_BOB + DEPARTMENT_DESC_BOB + TAG_DESC_FRIEND
                        + REMARK_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB, expectedFindCommand);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private PhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code DepartmentContainsKeywordsPredicate}.
     */
    private DepartmentContainsKeywordsPredicate prepareDeptPredicate(String userInput) {
        return new DepartmentContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code OfficeContainsKeywordsPredicate}.
     */
    private OfficeContainsKeywordsPredicate prepareOfficePredicate(String userInput) {
        return new OfficeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RemarkContainsKeywordsPredicate}.
     */
    private RemarkContainsKeywordsPredicate prepareRemarkPredicate(String userInput) {
        return new RemarkContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
