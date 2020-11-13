package seedu.internhunter.logic.parser.find;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.find.FindCompanyCommand;
import seedu.internhunter.model.company.CompanyNameContainsKeyWordsPredicate;

public class FindCompanyCommandParserTest {

    private static final String MESSAGE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        FindCompanyCommand.MESSAGE_USAGE);
    private FindCompanyCommandParser findCompanyCommandParser;

    @BeforeEach
    public void setUp() {
        findCompanyCommandParser = new FindCompanyCommandParser();
    }

    @Test
    public void parse_missingDescription_throwsParseException() {
        assertParseFailure(findCompanyCommandParser, "    ", MESSAGE);
        assertParseFailure(findCompanyCommandParser, " ", MESSAGE);
    }

    @Test
    public void parse_validArguments_returnsFindCompanyCommand() {
        FindCompanyCommand expectedCommand = new FindCompanyCommand(
            new CompanyNameContainsKeyWordsPredicate(Arrays.asList("Google", "Facebook", "Garena")));

        assertParseSuccess(findCompanyCommandParser, "Google Facebook Garena", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(findCompanyCommandParser, "\n Google \n \t Facebook Garena\t",
            expectedCommand);
    }

}
