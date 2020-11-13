package seedu.internhunter.logic.parser.add;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.add.AddApplicationCommand;
import seedu.internhunter.logic.commands.add.AddCompanyCommand;
import seedu.internhunter.logic.commands.add.AddInternshipCommand;
import seedu.internhunter.logic.commands.add.AddProfileCommand;
import seedu.internhunter.logic.parser.exceptions.ParseException;

public class AddCommandParserTest {

    private static final String VALID_COMPANY_INPUT = " com n/Tencent p/62343434 e/1234214@gmail.com a/201 Simei Rd";
    private static final String VALID_INTERNSHIP_INPUT = " int 1 j/Software Engineer";
    private static final String VALID_APPLICATION_INPUT = " app 1 i/1";
    private static final String VALID_PROFILE_INPUT = " me t/Learn HTML c/skill";
    private AddCommandParser addCommandParser;

    @BeforeEach
    public void setUp() {
        addCommandParser = new AddCommandParser();
    }

    @Test
    public void parse_invalidTypes_throwsParseException() {
        // invalid item type
        assertThrows(ParseException.class, () -> addCommandParser.parse(" hello"));
        assertThrows(ParseException.class, () -> addCommandParser.parse(" 1"));
        assertThrows(ParseException.class, () -> addCommandParser.parse(" "));
    }

    @Test
    public void parse_missingInput_throwsParseException() {
        // missing description as input
        assertThrows(ParseException.class, () -> addCommandParser.parse(COMPANY_ALIAS));
        assertThrows(ParseException.class, () -> addCommandParser.parse(COMPANY_ALIAS + " "));

        assertThrows(ParseException.class, () -> addCommandParser.parse(INTERNSHIP_ALIAS));
        assertThrows(ParseException.class, () -> addCommandParser.parse(INTERNSHIP_ALIAS + " "));

        assertThrows(ParseException.class, () -> addCommandParser.parse(APPLICATION_ALIAS));
        assertThrows(ParseException.class, () -> addCommandParser.parse(APPLICATION_ALIAS + " "));

        assertThrows(ParseException.class, () -> addCommandParser.parse(PROFILE_ALIAS));
        assertThrows(ParseException.class, () -> addCommandParser.parse(PROFILE_ALIAS + " "));
    }

    @Test
    public void parse_addCommandParserPassingToCorrectParser_true() throws ParseException {
        // item type app, description present -> FindApplicationCommand
        assertTrue(addCommandParser.parse(VALID_APPLICATION_INPUT) instanceof AddApplicationCommand);

        assertTrue(addCommandParser.parse(VALID_PROFILE_INPUT) instanceof AddProfileCommand);

        assertTrue(addCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof AddInternshipCommand);

        assertTrue(addCommandParser.parse(VALID_COMPANY_INPUT) instanceof AddCompanyCommand);
    }

    @Test
    public void parse_addCommandParserPassingToCorrectParser_false() throws ParseException {
        assertFalse(addCommandParser.parse(VALID_APPLICATION_INPUT) instanceof AddInternshipCommand);
        assertFalse(addCommandParser.parse(VALID_APPLICATION_INPUT) instanceof AddCompanyCommand);
        assertFalse(addCommandParser.parse(VALID_APPLICATION_INPUT) instanceof AddProfileCommand);

        assertFalse(addCommandParser.parse(VALID_PROFILE_INPUT) instanceof AddCompanyCommand);
        assertFalse(addCommandParser.parse(VALID_PROFILE_INPUT) instanceof AddInternshipCommand);
        assertFalse(addCommandParser.parse(VALID_PROFILE_INPUT) instanceof AddApplicationCommand);

        assertFalse(addCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof AddCompanyCommand);
        assertFalse(addCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof AddProfileCommand);
        assertFalse(addCommandParser.parse(VALID_INTERNSHIP_INPUT) instanceof AddApplicationCommand);

        assertFalse(addCommandParser.parse(VALID_COMPANY_INPUT) instanceof AddInternshipCommand);
        assertFalse(addCommandParser.parse(VALID_COMPANY_INPUT) instanceof AddApplicationCommand);
        assertFalse(addCommandParser.parse(VALID_COMPANY_INPUT) instanceof AddProfileCommand);
    }
}
