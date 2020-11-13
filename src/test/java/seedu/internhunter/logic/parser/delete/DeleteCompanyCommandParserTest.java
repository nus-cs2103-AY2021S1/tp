package seedu.internhunter.logic.parser.delete;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.delete.DeleteCompanyCommand;

public class DeleteCompanyCommandParserTest {

    private DeleteCompanyCommandParser deleteCompanyCommandParser;

    @BeforeEach
    public void setUp() {
        deleteCompanyCommandParser = new DeleteCompanyCommandParser();
    }

    @Test
    public void parse_validArgs_returnsDeleteApplicationCommand() {
        assertParseSuccess(deleteCompanyCommandParser, "1", new DeleteCompanyCommand(INDEX_FIRST));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        final String messageExpected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteCompanyCommand.MESSAGE_USAGE);

        // empty argument
        assertParseFailure(deleteCompanyCommandParser, "", messageExpected);
        // blank argument
        assertParseFailure(deleteCompanyCommandParser, "  ", messageExpected);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(deleteCompanyCommandParser, "a", MESSAGE_INVALID_INDEX);
    }
}
