package seedu.internhunter.logic.parser.list;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.list.ListApplicationCommand;
import seedu.internhunter.logic.commands.list.ListCompanyCommand;
import seedu.internhunter.logic.commands.list.ListProfileCommand;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Test for list command parser.
 */
public class ListCommandParserTest {

    private ListCommandParser listCommandParser;

    @BeforeEach
    public void setUp() {
        listCommandParser = new ListCommandParser();
    }

    @Test
    public void parse_invalidTypes_throwsParseException() {
        assertThrows(ParseException.class, () -> listCommandParser.parse(" hello"));
        assertThrows(ParseException.class, () -> listCommandParser.parse(" 1"));
        assertThrows(ParseException.class, () -> listCommandParser.parse(" "));
    }

    @Test
    public void parse_correctCommandProduced_true() throws ParseException {
        assertTrue(listCommandParser.parse(APPLICATION_ALIAS) instanceof ListApplicationCommand);
        assertTrue(listCommandParser.parse(COMPANY_ALIAS) instanceof ListCompanyCommand);
        assertTrue(listCommandParser.parse(PROFILE_ALIAS) instanceof ListProfileCommand);
    }

    @Test
    public void parse_correctCommandProduced_success() throws ParseException {
        assertFalse(listCommandParser.parse(COMPANY_ALIAS) instanceof ListApplicationCommand);
        assertFalse(listCommandParser.parse(PROFILE_ALIAS) instanceof ListApplicationCommand);

        assertFalse(listCommandParser.parse(APPLICATION_ALIAS) instanceof ListCompanyCommand);
        assertFalse(listCommandParser.parse(PROFILE_ALIAS) instanceof ListCompanyCommand);

        assertFalse(listCommandParser.parse(COMPANY_ALIAS) instanceof ListProfileCommand);
        assertFalse(listCommandParser.parse(APPLICATION_ALIAS) instanceof ListProfileCommand);
    }

    @Test
    public void parse_listExcessInput_throwsParseException() {
        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + APPLICATION_ALIAS + "great"));
        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + APPLICATION_ALIAS + "1"));

        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + PROFILE_ALIAS + "great"));
        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + PROFILE_ALIAS + "1"));

        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + COMPANY_ALIAS + "great"));
        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + COMPANY_ALIAS + "1"));
    }

}
