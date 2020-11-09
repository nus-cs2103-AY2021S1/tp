package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BLACKLIST_STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EXPERIENCE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.URL_LINK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLACKLIST_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LINK_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.information.Person;
import seedu.address.model.information.predicate.PersonAddressContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonApplicationContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonBlacklistContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonEmailContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonExperienceContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonNameContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonPhoneContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonSalaryContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonTagsContainKeywordsPredicate;
import seedu.address.model.information.predicate.PersonUrlLinkContainsKeywordsPredicate;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPreamble_throwsParseException() {
        assertParseFailure(parser, "n/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_emptyInputField_throwsParseException() {
        assertParseFailure(parser, EMPTY_NAME_DESC,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPersonCommand() {

        // one field
        List<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(new PersonNameContainsKeywordsPredicate(parser.splitInput(VALID_NAME_AMY)));
        Command expectedFindPersonCommand = new FindPersonCommand(predicates);
        assertParseSuccess(parser, NAME_DESC_AMY, expectedFindPersonCommand);

        // multiple fields
        predicates = new ArrayList<>();
        predicates.add(new PersonNameContainsKeywordsPredicate(parser.splitInput(VALID_NAME_AMY)));
        predicates.add(new PersonPhoneContainsKeywordsPredicate(parser.splitInput(VALID_PHONE_AMY)));
        predicates.add(new PersonEmailContainsKeywordsPredicate(parser.splitInput(VALID_EMAIL_AMY)));
        predicates.add(new PersonAddressContainsKeywordsPredicate(parser.splitInput(VALID_ADDRESS_AMY)));
        predicates.add(new PersonExperienceContainsKeywordsPredicate(parser.splitInput(VALID_EXPERIENCE_AMY)));
        predicates.add(new PersonApplicationContainsKeywordsPredicate(parser.splitInput(VALID_DATE_AMY)));
        predicates.add(new PersonSalaryContainsKeywordsPredicate(parser.splitInput(VALID_SALARY_AMY)));
        predicates.add(new PersonBlacklistContainsKeywordsPredicate(parser.splitInput(VALID_BLACKLIST_STATUS_AMY)));
        predicates.add(new PersonUrlLinkContainsKeywordsPredicate(parser.splitInput(VALID_URL_LINK_AMY)));
        predicates.add(new PersonTagsContainKeywordsPredicate(parser.splitInput(VALID_TAG_FRIEND)));
        expectedFindPersonCommand = new FindPersonCommand(predicates);
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + EXPERIENCE_DESC_AMY + DATE_DESC_AMY + SALARY_DESC_AMY
                        + BLACKLIST_STATUS_DESC_AMY + URL_LINK_DESC_AMY + TAG_DESC_FRIEND,
                expectedFindPersonCommand);
    }
}
