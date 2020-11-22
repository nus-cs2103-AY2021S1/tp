package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CountryNoteDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CountryNoteDeleteCommandParserTest {

    private final CountryNoteDeleteCommandParser parser = new CountryNoteDeleteCommandParser();

    @Test
    public void parse_noIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" random string"));
        assertThrows(ParseException.class, () -> parser.parse(" 2123 21string"));
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("  "));
        assertThrows(ParseException.class, () -> parser.parse("   owej o23r "));
    }

    @Test
    public void parse_validIndex_returnsExpected() {
        new CountryNoteDeleteCommand(Index.fromOneBased(1));
        assertDoesNotThrow(() -> parser.parse("1"));
    }

}
