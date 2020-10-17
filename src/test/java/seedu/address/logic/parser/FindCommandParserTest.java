package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.OfficeContainsKeywordsPredicate;
import seedu.address.model.person.predicates.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Liddel")));
        assertParseSuccess(parser, "Alice Liddel", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Liddel  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithPrefix_returnsFindCommand() {
        // name
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_NAME + "Alice Bob",
                expectedFindCommand);

        // department
        expectedFindCommand =
                new FindCommand(new DepartmentContainsKeywordsPredicate(Collections.singletonList("Computing")));
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_DEPARTMENT + "Computing",
                expectedFindCommand);

        // office
        expectedFindCommand =
                new FindCommand(new OfficeContainsKeywordsPredicate(Arrays.asList("com2", "02")));
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_OFFICE + "com2 02",
                expectedFindCommand);

        // remark
        expectedFindCommand =
                new FindCommand(new RemarkContainsKeywordsPredicate(Arrays.asList("glasses", "beard")));
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_REMARK + "glasses beard",
                expectedFindCommand);

        // tag
        expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friend", "colleague")));
        assertParseSuccess(parser, COMMAND_WORD + " " + PREFIX_TAG + "friend colleague",
                expectedFindCommand);
    }

}
