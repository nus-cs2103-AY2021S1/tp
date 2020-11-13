package seedu.internhunter.logic.parser.delete;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.delete.DeleteApplicationCommand;
import seedu.internhunter.logic.commands.delete.DeleteCompanyCommand;
import seedu.internhunter.logic.commands.delete.DeleteInternshipCommand;
import seedu.internhunter.logic.commands.delete.DeleteProfileCommand;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private static final String VALID_COMPANY_INPUT = " com 1";
    private static final String VALID_INTERNSHIP_INPUT = " int 1 i/1";
    private static final String VALID_APPLICATION_INPUT = " app 1";
    private static final String VALID_PROFILE_INPUT = " me 1";
    private DeleteCommandParser deleteCommandParser = new DeleteCommandParser();

    @BeforeEach
    public void setUp() {
        deleteCommandParser = new DeleteCommandParser();
    }

    @Test
    public void parse_invalidTypes_throwsParseException() {
        // invalid item type
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(" hello"));
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(" 1"));
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(" "));
    }

    @Test
    public void parse_missingInput_throwsParseException() {
        // missing description as input
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(COMPANY_ALIAS));
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(COMPANY_ALIAS + " "));

        assertThrows(ParseException.class, () -> deleteCommandParser.parse(INTERNSHIP_ALIAS));
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(INTERNSHIP_ALIAS + " "));

        assertThrows(ParseException.class, () -> deleteCommandParser.parse(APPLICATION_ALIAS));
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(APPLICATION_ALIAS + " "));

        assertThrows(ParseException.class, () -> deleteCommandParser.parse(PROFILE_ALIAS));
        assertThrows(ParseException.class, () -> deleteCommandParser.parse(PROFILE_ALIAS + " "));
    }

    @Test
    public void parse_deleteCommandParserPassingToCorrectParser_true() throws ParseException {
        // item type app, description present -> FindApplicationCommand
        assertTrue(deleteCommandParser.parse(VALID_APPLICATION_INPUT) instanceof DeleteApplicationCommand);

        assertTrue(deleteCommandParser.parse(VALID_PROFILE_INPUT) instanceof DeleteProfileCommand);

        assertTrue(deleteCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof DeleteInternshipCommand);

        assertTrue(deleteCommandParser.parse(VALID_COMPANY_INPUT) instanceof DeleteCompanyCommand);
    }

    @Test
    public void parse_deleteCommandParserPassingToCorrectParser_false() throws ParseException {
        assertFalse(deleteCommandParser.parse(VALID_APPLICATION_INPUT) instanceof DeleteInternshipCommand);
        assertFalse(deleteCommandParser.parse(VALID_APPLICATION_INPUT) instanceof DeleteCompanyCommand);
        assertFalse(deleteCommandParser.parse(VALID_APPLICATION_INPUT) instanceof DeleteProfileCommand);

        assertFalse(deleteCommandParser.parse(VALID_PROFILE_INPUT) instanceof DeleteCompanyCommand);
        assertFalse(deleteCommandParser.parse(VALID_PROFILE_INPUT) instanceof DeleteInternshipCommand);
        assertFalse(deleteCommandParser.parse(VALID_PROFILE_INPUT) instanceof DeleteApplicationCommand);

        assertFalse(deleteCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof DeleteCompanyCommand);
        assertFalse(deleteCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof DeleteProfileCommand);
        assertFalse(deleteCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof DeleteApplicationCommand);

        assertFalse(deleteCommandParser.parse(VALID_COMPANY_INPUT) instanceof DeleteInternshipCommand);
        assertFalse(deleteCommandParser.parse(VALID_COMPANY_INPUT) instanceof DeleteApplicationCommand);
        assertFalse(deleteCommandParser.parse(VALID_COMPANY_INPUT) instanceof DeleteProfileCommand);
    }

}
