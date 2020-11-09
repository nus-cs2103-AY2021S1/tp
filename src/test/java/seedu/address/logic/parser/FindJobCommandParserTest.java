package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VACANCY_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_IRAS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindJobCommand;
import seedu.address.model.information.Job;
import seedu.address.model.information.predicate.JobAddressContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobCompanyNameContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobEmailContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobJobTitleContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobPhoneContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobPriorityContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobVacancyContainsKeywordsPredicate;

public class FindJobCommandParserTest {

    private FindJobCommandParser parser = new FindJobCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJobCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPreamble_throwsParseException() {
        assertParseFailure(parser, "n/Software Engineer",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJobCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyInputField_throwsParseException() {
        assertParseFailure(parser, EMPTY_COMPANY_NAME_DESC,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJobCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindJobCommand() {

        // one field
        List<Predicate<Job>> predicates = new ArrayList<>();
        predicates.add(new JobJobTitleContainsKeywordsPredicate(parser.splitInput(VALID_JOB_TITLE_IRAS)));
        Command expectedFindJobCommand = new FindJobCommand(predicates);
        assertParseSuccess(parser, JOB_TITLE_DESC_IRAS, expectedFindJobCommand);

        // multiple fields
        predicates = new ArrayList<>();
        predicates.add(new JobJobTitleContainsKeywordsPredicate(parser.splitInput(VALID_JOB_TITLE_IRAS)));
        predicates.add(new JobCompanyNameContainsKeywordsPredicate(parser.splitInput(VALID_COMPANY_NAME_IRAS)));
        predicates.add(new JobPhoneContainsKeywordsPredicate(parser.splitInput(VALID_PHONE_IRAS)));
        predicates.add(new JobAddressContainsKeywordsPredicate(parser.splitInput(VALID_ADDRESS_IRAS)));
        expectedFindJobCommand = new FindJobCommand(predicates);
        assertParseSuccess(parser, JOB_TITLE_DESC_IRAS + COMPANY_NAME_DESC_IRAS + PHONE_DESC_IRAS + ADDRESS_DESC_IRAS,
                expectedFindJobCommand);

        predicates = new ArrayList<>();
        predicates.add(new JobEmailContainsKeywordsPredicate(parser.splitInput(VALID_EMAIL_IRAS)));
        predicates.add(new JobPriorityContainsKeywordsPredicate(parser.splitInput(VALID_PRIORITY_IRAS)));
        predicates.add(new JobVacancyContainsKeywordsPredicate(parser.splitInput(VALID_VACANCY_IRAS)));
        expectedFindJobCommand = new FindJobCommand(predicates);
        assertParseSuccess(parser, EMAIL_DESC_IRAS + PRIORITY_DESC_IRAS + VACANCY_DESC_IRAS,
            expectedFindJobCommand);
    }
}
